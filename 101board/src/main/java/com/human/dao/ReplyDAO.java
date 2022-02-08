package com.human.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.human.dto.ReplyDTO;

import com.human.vo.PageMaker;
// DTO 만들면 DAO도 있어야함. 
public interface ReplyDAO {

	  public List<ReplyDTO> list(Integer bno) throws Exception;

	  public void insert(ReplyDTO vo) throws Exception;

	  public void update(ReplyDTO vo) throws Exception;

	  public void delete(Integer rno) throws Exception;
	  
	  public void deleteBno(Integer bno) throws Exception;

	  public List<ReplyDTO> listPage(
	      @Param("bno") Integer bno, @Param("pm") PageMaker pm) throws Exception;

	  public int count(Integer bno) throws Exception;

	}





