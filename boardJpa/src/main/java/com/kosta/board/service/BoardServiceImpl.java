package com.kosta.board.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entitiy.BFile;
import com.kosta.board.entitiy.Board;
import com.kosta.board.entitiy.BoardLike;
import com.kosta.board.entitiy.Member;
import com.kosta.board.repository.BoardLikeRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.util.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private BoardLikeRepository boardLikeRepository;

	@Value("${upload.path}")
	private String uploadPath;

	@Override
	public Integer boardWrite(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception {

		// 이미지파일 업로드 (선택해서 업로드니까 if)
		if (file != null && !file.isEmpty()) {
			BFile bFile = new BFile();
			bFile.setDirectory(uploadPath);
			bFile.setName(file.getOriginalFilename());
			bFile.setSize(file.getSize());
			bFile.setContentType(file.getContentType());
			fileRepository.save(bFile);

			File upFile = new File(uploadPath, bFile.getNum() + "");
			file.transferTo(upFile);

			boardDto.setImgFileNum(bFile.getNum());
		}

		// 그 외 파일 업로드 (선택해서 업로드니까 if)
		if (dfile != null && !dfile.isEmpty()) {
			BFile bFile = new BFile();
			bFile.setDirectory(uploadPath);
			bFile.setName(dfile.getOriginalFilename());
			bFile.setSize(dfile.getSize());
			bFile.setContentType(dfile.getContentType());
			fileRepository.save(bFile);

			File upFile = new File(uploadPath, dfile.getOriginalFilename());
			dfile.transferTo(upFile);

			boardDto.setUploadFileNum(bFile.getNum());
		}
		Board board = boardDto.toEntity();
		boardRepository.save(board);
		return board.getNum();
	}

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Board board = boardRepository.findById(num).orElseThrow(() -> new Exception("글번호 오류"));
		board.setViewCount(board.getViewCount()+1);
		boardRepository.save(board);
		return board.toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {		
		Optional<BoardLike> oBoardLike = boardLikeRepository.findByMember_IdAndBoard_Num(memberId, boardNum);
		if(oBoardLike.isPresent()) {
			return oBoardLike.get().getNum();
		}
		return null;
	}

	@Override
	public Integer boardModify(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception {
		//1. 기존 파일이 있으면서 파일을 변경하는 경우 
		//2. 기존 파일이 없으면서 파일을 추가하는 경우
		//3. 기존 파일이 있는데 변경하지 않는 경우
		
		Board board = boardRepository.findById(boardDto.getNum()).get();
		board.setSubject(boardDto.getSubject());
		board.setContent(boardDto.getContent());
		
		if(file != null && !file.isEmpty()) {								//이미지 파일 변경 O
			BFile imageFile = BFile.builder()
							   .name(file.getOriginalFilename())
							   .directory(uploadPath)
							   .size(file.getSize())
							   .contentType(file.getContentType())
							   .build();
			fileRepository.save(imageFile);
			File upFile = new File(uploadPath, imageFile.getNum()+"");
			file.transferTo(upFile);
			board.setImageFile(imageFile);
		}
		
		
		if(dfile != null && !dfile.isEmpty()) {								//업로드 파일 변경 O
			BFile uploadFile = BFile.builder()
							   .name(dfile.getOriginalFilename())
							   .directory(uploadPath)
							   .size(dfile.getSize())
							   .contentType(dfile.getContentType())
							   .build();
			fileRepository.save(uploadFile);
			File upFile = new File(uploadPath, dfile.getOriginalFilename());
			dfile.transferTo(upFile);
			board.setUploadFile(uploadFile);
		} 
		boardRepository.save(board);
		return board.getNum();
	}

	@Override
	public List<BoardDto> boardList(PageInfo pageInfo, String type, String word) throws Exception {
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, 10, Sort.by(Sort.Direction.DESC, "num"));
		// 스프링부트에서 제공해주는 Pageable의 PageRequest 사용
		Page<Board> pages = null;
		
		//검색조건에 따른 검색 + 페이징 처리 
		if(word == null || word.trim().equals("")) {
			pages = boardRepository.findAll(pageRequest);
		} else {
			if(type.equals("subject")) {
				pages = boardRepository.findBySubjectContains(word, pageRequest);
			} else if (type.equals("content")) {
				pages = boardRepository.findByContentContains(word, pageRequest);
			} else if (type.equals("writer")) {
				pages = boardRepository.findByMember_Nickname(word, pageRequest);
			}
		}

		pageInfo.setAllPage(pages.getTotalPages());
		Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		Integer endPage = Math.min(startPage + 10 - 1, pageInfo.getAllPage());
		
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		return pages.getContent().stream().map(i -> i.toDto()).collect(Collectors.toList());
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		Integer boardLikeNum = checkHeart(id, boardNum);
		if(boardLikeNum == null) {
			boardLikeRepository.save(BoardLike.builder()
							   				  .member(Member.builder().id(id).build())
							   				  .board(Board.builder().num(boardNum).build())
							   				  .build());
			return true;
		} else {
			boardLikeRepository.deleteById(boardLikeNum);
		}
		return false;
	}

}
