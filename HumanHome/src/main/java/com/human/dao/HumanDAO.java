package com.human.dao;

import java.util.ArrayList;

import com.human.dto.HumanDTO;

public interface HumanDAO {
	
	public void insert(HumanDTO dto) throws Exception;
	
	public void update(HumanDTO dto) throws Exception;
	
	public void delete(String name) throws Exception;
	
	public ArrayList<HumanDTO> select() throws Exception;
	
	public HumanDTO read(String name) throws Exception;
	
	
}
