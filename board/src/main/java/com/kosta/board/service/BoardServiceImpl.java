package com.kosta.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dao.FileDao;
import com.kosta.board.dao.HeartDao;
import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardDao boardDao;
	private final HeartDao heartDao;
	private final FileDao fileDao;

	@Override
	public void boardWrite(Board board, MultipartFile file, MultipartFile dfile) throws Exception {		
		// 이미지파일 업로드 (선택해서 업로드니까 if)
		if (file != null && !file.isEmpty()) {
			String path = "C:/kdy/uploadImage/";
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(file.getOriginalFilename());
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			fileDao.insertFile(bFile);

			File upFile = new File(path, bFile.getNum() + "");
			file.transferTo(upFile);

			board.setFilename(bFile.getNum() + "");
		}

		// 그 외 파일 업로드 (선택해서 업로드니까 if)
		if (dfile != null && !dfile.isEmpty()) {
			String path = "C:/kdy/upload/";
			// board.setDfilename(dfile.getOriginalFilename());
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(dfile.getOriginalFilename());
			bFile.setSize(dfile.getSize());
			bFile.setContenttype(dfile.getContentType());
			fileDao.insertFile(bFile);

			File upFile = new File(path, dfile.getOriginalFilename());
			dfile.transferTo(upFile);

			board.setDfilename(bFile.getName());
		}

		boardDao.insertBoard(board);
	}

	@Override
	public Board boardDetail(Integer num) throws Exception {
		Board board = boardDao.selectBoard(num);
		if (board == null)
			throw new Exception("글 번호 오류");
		boardDao.updateViewCnt(num);
		return board;
	}

	@Override
	public Integer boardModify(Board board, MultipartFile file, MultipartFile dfile) throws Exception {
		Board beforeBoard = boardDao.selectBoard(board.getNum());
		
		if (file != null && !file.isEmpty()) {
			String uploadImagePath = "C:/kdy/uploadImage/";
			BFile bFile = new BFile();
			bFile.setDirectory(uploadImagePath);
			bFile.setName(file.getOriginalFilename());
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			fileDao.insertFile(bFile);

			File upFile = new File(uploadImagePath, bFile.getNum() + "");
			file.transferTo(upFile);

			board.setFilename(bFile.getNum() + "");
			
			//만약에 기존에 이미지가 있으면 지우고 새로 넣기 위해서 delete를 추가함 (안해도 되긴 함) 
			if(beforeBoard.getFilename() != null) {
				File beforeFile = new File(uploadImagePath, beforeBoard.getFilename());
				beforeFile.delete();			
			}
		}

		if (dfile!= null && !dfile.isEmpty()) {
			String uploadPath = "C:/kdy/upload/";
			BFile bFile = new BFile();
			bFile.setDirectory(uploadPath);
			bFile.setName(dfile.getOriginalFilename());
			bFile.setSize(dfile.getSize());
			bFile.setContenttype(dfile.getContentType());
			fileDao.insertFile(bFile);

			File upFile = new File(uploadPath, dfile.getOriginalFilename());
			dfile.transferTo(upFile);

			board.setDfilename(bFile.getName());
			
			//만약에 기존에 이미지가 있으면 지우고 새로 넣기 위해서 delete를 추가함 (안해도 되긴 함) 
			if (beforeBoard.getDfilename() != null) {
				File beforeFile = new File(uploadPath, beforeBoard.getDfilename());
				beforeFile.delete();
			}
		}
		
		boardDao.updateBoard(board);
		
		return board.getNum();
	}

	@Override
	public List<Board> boardList(PageInfo pageInfo) throws Exception {
		Integer boardCnt = boardDao.selectBoardCount();

		Integer allPage = (int) Math.ceil((double) boardCnt / 10);
		// startPage : 1~10=>1, 11~20=>11
		Integer startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1; // 1,11,21,31...
		Integer endPage = startPage + 10 - 1; // 10,20,30...
		if (endPage > allPage)
			endPage = allPage;

		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);

		Integer row = (pageInfo.getCurPage() - 1) * 10 + 1;
		return boardDao.selectBoardList(row-1);
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		return heartDao.selectHeart(memberId, boardNum);
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		Integer heartNum = heartDao.selectHeart(id, boardNum);
		if (heartNum == null) {
			heartDao.insertHeart(id, boardNum);
			return true;
		} else {
			heartDao.deleteHeart(id, boardNum);
			return false;
		}
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		String file = request.getParameter("file");
		String path = request.getServletContext().getRealPath("upload");

		FileInputStream fis = new FileInputStream(new File(path, file));

		String mimeType = request.getServletContext().getMimeType(path + "\\" + file);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		String encoding = new String(file.getBytes("utf-8"), "8859_1");
		response.setHeader("content-Disposition", "attachmemt; filename= " + encoding);

		OutputStream out = response.getOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while ((len = fis.read(buff)) > 0) {
			out.write(buff, 0, len);
		}

		fis.close();
	}
}
