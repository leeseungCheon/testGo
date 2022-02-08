package com.human.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.dto.HumanDTO;
import com.human.service.HumanService;

@Controller
@RequestMapping("/human/*")
public class HumanController {
	
	@Autowired
	private HumanService service;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public void readGet() throws Exception{
		
	}
	
	@RequestMapping(value="/select", method = RequestMethod.POST)
	public String read(@RequestParam("name") String name, Model model) throws Exception{
		
		model.addAttribute("humanDTO", service.read(name));
		model.addAttribute("type", "b");
		
		return "list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String registPOST(HumanDTO dto) throws Exception{
		service.write(dto);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void removeGET() throws Exception{
		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String remove(@RequestParam("name") String name) throws Exception{
		service.remove(name);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void modifyGET() throws Exception {
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String modifyPOST(HumanDTO dto) throws Exception {

		service.modify(dto);
		
		return "redirect:/";
	}
	
	
	
	
}
