package com.semiProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.semiProject.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// CRUD
	void insertBoard(BoardVO boardVO);
	
	List<BoardVO> selectList(BoardVO boardVO);
	
	BoardVO selectOne(Long idx);
	
	void update(BoardVO boardVO);
	
	void delete(Long idx);
	
}
