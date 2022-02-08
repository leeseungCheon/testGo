package com.human.service;

import java.util.List;

import com.human.dto.BoardDTO;
import com.human.vo.PageMaker;
// DAO, vo 들을 실제 코드해서 사용할 수 있도록 서비스를 만들었음. 
public interface BoardService {
	public void write(BoardDTO board) throws Exception;
	public BoardDTO read(int bno) throws Exception;
	public void modify(BoardDTO board) throws Exception;
	public void remove(int bno) throws Exception;
	
	public List<BoardDTO> listSearchCriteria(PageMaker pm) throws Exception;
	public int listSearchCount(PageMaker pm) throws Exception;
	
}
