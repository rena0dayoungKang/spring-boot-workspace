package com.kosta.board.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.BoardLike;
import com.kosta.board.repository.BoardDslRepository;
import com.kosta.board.repository.BoardLikeRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final BoardDslRepository boardDslRepository;
	private final FileRepository fileRepository;
	private final BoardLikeRepository boardLikeRepository;
	
	@Value("${upload.path}")
	private String uploadPath;

	@Override
	public Integer boardWrite(BoardDto boardDto, List<MultipartFile> fileList) throws Exception {
		Board board = boardDto.toEntity();
		boardRepository.save(board);
		
		if (fileList != null && fileList.size() > 0) {
			for (MultipartFile file : fileList) {
				BFile bFile = new BFile();
				bFile.setDirectory(uploadPath);
				bFile.setName(file.getOriginalFilename());
				bFile.setContentType(file.getContentType());
				bFile.setSize(file.getSize());
				bFile.setBoard(board);
				fileRepository.save(bFile);
				
				File upFile = new File(uploadPath, bFile.getNum()+"");
				file.transferTo(upFile);
			}
		}
		
		return board.getNum();
	}

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Board board = boardRepository.findById(num).orElseThrow(() -> new Exception("글 번호 오류"));
		board.setViewCount(board.getViewCount()+1);
		boardRepository.save(board);
		return board.toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		return boardDslRepository.findBoardLike(memberId, boardNum);
	}

	@Override
	public Integer boardModify(BoardDto boardDto, MultipartFile imageFile, MultipartFile uploadFile) throws Exception {
//		//null인경우 수정하지 않고 기존게 있어야 한다. 
//		
//		Board board = boardRepository.findById(boardDto.getNum()).orElseThrow(() -> new Exception("글 번호 오류"));
//		board.setSubject(boardDto.getSubject());
//		board.setContent(boardDto.getContent());
//		
//		Integer bImageFileNum = null;
//		Integer bUploadFileNum = null;	
//		
//		//기존에 파일이 있으면 삭제하기 기능 추가하기  
//		if (imageFile != null && !imageFile.isEmpty()) {	
//			
//			//이미지 파일 변경시 기존 파일번호 임시저장
//			if(board.getImageFile() != null) {
//				bImageFileNum = board.getImageFile().getNum();
//			}
//			
//			//기존파일 변경
//			BFile bImageFile = new BFile();
//			bImageFile.setDirectory(uploadPath);
//			bImageFile.setName(imageFile.getOriginalFilename());
//			bImageFile.setContentType(imageFile.getContentType());
//			bImageFile.setSize(imageFile.getSize());
//			fileRepository.save(bImageFile);
//			
//			File newFile = new File(uploadPath, bImageFile.getNum()+"");
//			imageFile.transferTo(newFile);
//			
//			board.setImageFile(bImageFile);
//		}
//		
//		if (uploadFile != null &&!uploadFile.isEmpty()) {
//			//업로드 파일 변경시 기존 파일과 파일정보 삭제
//			if(board.getUploadFile() != null) {
//				bUploadFileNum = board.getUploadFile().getNum();
//			}
//			
//			//기존파일 변경
//			BFile bUploadFile = new BFile();
//			bUploadFile.setDirectory(uploadPath);
//			bUploadFile.setName(uploadFile.getOriginalFilename());
//			bUploadFile.setContentType(uploadFile.getContentType());
//			bUploadFile.setSize(uploadFile.getSize());
//			fileRepository.save(bUploadFile);
//			
//			File nfile = new File(uploadPath, bUploadFile.getNum()+"");
//			uploadFile.transferTo(nfile);
//			
//			board.setUploadFile(bUploadFile);
//		}
//		
//		
//		boardRepository.save(board);
//		
//		if(bImageFileNum != null) {	//기존 이미지 파일과 DB정보 삭제 
//			new File(uploadPath, bImageFileNum+"").delete();
//			fileRepository.deleteById(bImageFileNum);
//		}
//		if(bUploadFileNum != null) {	//기존 업로드 파일과 DB정보 삭제 
//			new File(uploadPath, bUploadFileNum+"").delete();
//			fileRepository.deleteById(bUploadFileNum);
//		}
//		
//		return board.getNum();
		return 0;
	}

	@Override
	public List<BoardDto> boardList(PageInfo pageInfo, String type, String word) throws Exception {
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, 10);
		List<BoardDto> boardDtoList = null;
		Long allCnt = 0L;
		if (word == null || word.trim().equals("")) {	//전체목록
			boardDtoList = boardDslRepository.findBoardListByPaging(pageRequest).stream()
																			 .map(b -> b.toDto())
																			 .collect(Collectors.toList());
			allCnt = boardDslRepository.findBoardCount();
		} else {	//검색
			boardDtoList = boardDslRepository.searchBoardListByPaging(pageRequest, type, word)
										  .stream()
										  .map(b -> b.toDto())
										  .collect(Collectors.toList());
			allCnt = boardDslRepository.searchBoardCount(type, word);
		}
		
		Integer allPage = (int) (Math.ceil(allCnt.doubleValue() / pageRequest.getPageSize()));
		Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		Integer endPage = Math.min(startPage + 10 - 1, allPage);
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		return boardDtoList;
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		Integer boardLikeNum = boardDslRepository.findBoardLike(id, boardNum);
		if (boardLikeNum == null) {
			boardLikeRepository.save(BoardLike.builder().memId(id).boardNum(boardNum).build());
			return true;
		} else {
			boardLikeRepository.deleteById(boardLikeNum);
			return false;
		}
	}

	@Override
	@Transactional //update처럼 내용을 바꾸는 코드를 쓰려면 Transactional 어노테이션 사용
	public void boardDelete(Integer num) throws Exception {
		boardLikeRepository.deleteByBoardNum(num);
		boardRepository.deleteById(num);		
	}

}
