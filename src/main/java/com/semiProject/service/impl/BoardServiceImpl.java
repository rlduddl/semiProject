package com.semiProject.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.semiProject.service.impl.BoardServiceImpl;
import com.semiProject.exception.BadRequestException;
import com.semiProject.exception.NotFoundException;
import com.semiProject.mapper.BoardMapper;
import com.semiProject.payload.response.ApiResponse;
import com.semiProject.vo.BoardVO;
import com.semiProject.vo.MemberVO;
import com.semiProject.service.CrudService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BoardServiceImpl implements CrudService<BoardVO> {
	
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(BoardVO e) {
		return boardMapper.selectList(e);
	}

	@Override
	public BoardVO selectOne(BoardVO e) {
		return null;
	}
	
	public BoardVO selectBoard(Long idx) {
		BoardVO boardVO = boardMapper.selectOne(idx);
		
		if (boardVO == null) throw new NotFoundException("게시물을 찾을 수 없습니다.");
		
		log.info(boardVO.toString());
		return boardVO;
	}

	@Override
	public void insert(BoardVO e) {
		boardMapper.insertBoard(e);
	}
	
	public ApiResponse insertBoard(BoardVO boardVO, HttpServletRequest request) {
		log.info(boardVO.toString());
		boardMapper.insertBoard(boardVO);
		return new ApiResponse(true, String.valueOf(boardVO.getIdx()));
	}

	@Override
	public void update(BoardVO e) {
		boardMapper.update(e);
	}
	
	public ApiResponse updateBoard(BoardVO e) {
		BoardVO boardVO = boardMapper.selectOne(e.getIdx());
		if (boardVO == null) throw new NotFoundException("게시물을 찾을 수 없습니다.");
		boardMapper.update(e);
		return new ApiResponse(true, "저장되었습니다.");
	}

	@Override
	public void delete(BoardVO e) {
		boardMapper.delete(e.getIdx());
	}
	
	public void delete(Long idx) {
		boardMapper.delete(idx);
	}

}
