package com.kosta.board.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entitiy.BFile;
import com.kosta.board.entitiy.Board;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.util.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private FileRepository fileRepository;
	
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
		return boardRepository.findById(num).orElseThrow(() -> new Exception("글번호 오류")).toDto();
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer boardModify(BoardDto boardDto, MultipartFile file, MultipartFile dfile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDto> boardList(PageInfo page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void boardWrite(BoardDto board, MultipartFile file, MultipartFile dfile) {
//		// TODO Auto-generated method stub
//		
//	}

}
