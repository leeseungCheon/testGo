package com.human.service;
// @serice 구현 클래스는  하나만 있어야함. 두개 있으면 어떤것을 controller에 주입할 것인지 애매해짐. 
// service는 실제 DB 작업하는 부분 
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.human.dao.BoardDAO;
import com.human.dao.ReplyDAO;
import com.human.dto.BoardDTO;
import com.human.vo.PageMaker;
@Service
public class BoardServiceImpl implements BoardService {
	// DB를 사용해야 하니깐 SqlSession 객체를 생성한 것임. (주의! Sqlsession을 root-context.xml에 db작업, sqlsessionFactory, scan 등록하는 것이 우선임)
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(BoardDTO board) throws Exception {
		System.out.println(sqlSession);
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		dao.create(board);

	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);

		return dao.read(bno);
	}

	@Override
	public void modify(BoardDTO board) throws Exception {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		dao.update(board);
	}
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void remove(int bno) throws Exception {
		ReplyDAO rDao = sqlSession.getMapper(ReplyDAO.class);
		BoardDAO bDao = sqlSession.getMapper(BoardDAO.class);
		rDao.deleteBno(bno);
		if(true)throw new Exception("error");
		bDao.delete(bno);
	}

	@Override
	public List<BoardDTO> listSearchCriteria(PageMaker pm) throws Exception {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		return dao.listSearch(pm);
	}

	@Override
	public int listSearchCount(PageMaker pm) throws Exception {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		return dao.listSearchCount(pm);
	}

}
