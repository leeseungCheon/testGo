package com.human.dao;
// DAO는 테이블에 있는 데이터 가져오는 것임. 가져오면 create 라는 메소드를 실행할때 DTO의 생성자 하나 가져와서 실행. 
import java.util.List;

import com.human.dto.BoardDTO;
import com.human.vo.PageMaker;

public interface BoardDAO {
	  public void create(BoardDTO vo) throws Exception;   // BoardDTO : 데이터베이스에 데이터를 얻어오는 매개체 (board_tbl에 하나의 데이터를 담을 수 있는 클래스)
	  public BoardDTO read(int bno) throws Exception;

	  public void update(BoardDTO vo) throws Exception;

	  public void delete(int bno) throws Exception;

	//  
	  public List<BoardDTO> listSearch(PageMaker pm)throws Exception; // BoardDTO(board_tbl)데이터를 여러개 받을 수 있는 List 형식의 listSearch 함수 
	  // 페이지 메이커는 어떤걸 검색하고 어떤걸 검색 키워드를 쓸거고 현재 페이지는 몇인지 전체 데이터 개수는 몇인지 등등을 나타냄.. 그것을 쿼리스트링으로 사용해야해서 PageMaker 클래스로 만든것임.
	
	  public int listSearchCount(PageMaker pm)throws Exception;

}
