package com.human.service;

import java.util.List;

import com.human.dto.ReplyDTO;

import com.human.vo.PageMaker;

public interface ReplyService {

		  public void addReply(ReplyDTO vo) throws Exception;

		  public List<ReplyDTO> listReply(Integer bno) throws Exception;

		  public void modifyReply(ReplyDTO vo) throws Exception;

		  public void removeReply(Integer rno) throws Exception;

		  public List<ReplyDTO> listReplyPage(Integer bno, PageMaker pm) 
		      throws Exception;

		  public int count(Integer bno) throws Exception;

}
