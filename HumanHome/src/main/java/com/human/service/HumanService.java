package com.human.service;

import java.util.ArrayList;

import com.human.dto.HumanDTO;

public interface HumanService {
	
	public void write(HumanDTO dto) throws Exception;
	
	public void modify(HumanDTO dto) throws Exception;
	
	public void remove(String name) throws Exception;
	
	public ArrayList<HumanDTO> search() throws Exception;
	
	public HumanDTO read(String name) throws Exception;
	
	
}
