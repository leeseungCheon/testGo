package com.human.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.human.dto.BoardDTO;
import com.human.service.BoardService;
import com.human.service.BoardServiceImpl;
import com.human.vo.PageMaker;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	  private BoardService bm;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//write
//		BoardDTO board=new BoardDTO();
//		board.setTitle("0810title");
//		board.setContent("0810content");
//		board.setWriter("0810writer");
//		bm.write(board);
		
		//20501  read
//		BoardDTO dto=bm.read(20501);
//		System.out.println(dto);
		
		//remove
		//bm.remove(20501);

		//20484 modify
//		BoardDTO board=new BoardDTO();
//		board.setBno(20484);
//		board.setTitle("0810title");
//		board.setContent("0810content");
//		board.setWriter("0810writer");
//		bm.modify(board);
		
//		PageMaker pm=new PageMaker();
//		
//		System.out.println(bm.listSearchCount(pm));

		
//		PageMaker pm=new PageMaker();
//		
//		System.out.println(bm.listSearchCriteria(pm));
		
		//PageMaker [page=2, perPageNum=10, searchType=t,
		//keyword=888, totalCount=0, startPage=0, endPage=0,
		//prev=false, next=false]
//		PageMaker pm=new PageMaker();
//		pm.setPage(2);
//		pm.setPerPageNum(10);
//		pm.setSearchType("t");
//		pm.setKeyword("888");
//		PageMaker pm=new PageMaker();
//		pm.setPage(2);
//		pm.setPerPageNum(10);
		
//		System.out.println(bm.listSearchCriteria(pm));
		
		//model.addAttribute("list",bm.listSearchCriteria(pm));
		return "redirect:sboard/list";
		//return "home";
	}
	
}
