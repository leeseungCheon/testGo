package com.human.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.dao.ReplyDAO;
import com.human.dto.ReplyDTO;
import com.human.vo.PageMaker;

@Service
public class ReplyServiceImpl implements ReplyService {


	@Autowired
	private SqlSession sqlSession;
	
  @Override
  public void addReply(ReplyDTO vo) throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);
	  dao.insert(vo);
  }

  @Override
  public List<ReplyDTO> listReply(Integer bno) throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);
    return dao.list(bno);
  }

  @Override
  public void modifyReply(ReplyDTO vo) throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);

    dao.update(vo);
  }

  @Override
  public void removeReply(Integer rno) throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);
    dao.delete(rno);
  }
  @Override
  public List<ReplyDTO> listReplyPage(Integer bno, PageMaker cri) 
      throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);
    return dao.listPage(bno, cri);
  }
  @Override
  public int count(Integer bno) throws Exception {
	  ReplyDAO dao=sqlSession.getMapper(ReplyDAO.class);
    return dao.count(bno);
  }
}
