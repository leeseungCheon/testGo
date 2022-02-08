package com.human.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.dao.HumanDAO;
import com.human.dto.HumanDTO;

@Service
public class HumanServiceImpl implements HumanService{
	
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void write(HumanDTO dto) throws Exception {
		HumanDAO dao = sqlSession.getMapper(HumanDAO.class);
		dao.insert(dto);
	}

	@Override
	public void modify(HumanDTO dto) throws Exception {
		HumanDAO dao = sqlSession.getMapper(HumanDAO.class);
		dao.update(dto);
	}

	@Override
	public void remove(String name) throws Exception {
		HumanDAO dao = sqlSession.getMapper(HumanDAO.class);
		dao.delete(name);
	}

	@Override
	public ArrayList<HumanDTO> search() throws Exception {
		HumanDAO dao = sqlSession.getMapper(HumanDAO.class);
		return dao.select();
	}

	@Override
	public HumanDTO read(String name) throws Exception {
		HumanDAO dao = sqlSession.getMapper(HumanDAO.class);
		return dao.read(name);
	}
	
	
}
