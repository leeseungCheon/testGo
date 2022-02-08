package com.human.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.dto.BoardDTO;
import com.human.service.BoardService;
import com.human.vo.PageMaker;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardContrller {
	// Autowired : 필요한 의존 객체의 "타입"에 해당하는 빈을 찾아 주입한다. (생성자 setter 필드) 
	// @Autowired 어노테이션을 부여하면 각 상황의 타입에 맞는 IoC 컨테이너 안에 존재하는 Bean을 자동으로 주입하게됨. 
	@Autowired
	  private BoardService bm;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(PageMaker pm, Model model) throws Exception {  // PageMaker에서 page,perPageNum,searchType,keyword 가져와 
		// 저렇게 PageMaker의 pm을 만들면 PageMaker에 있는 여러개의 필드값과 set,get 함수들이 Form태그에서 날라온 값들에 맞춰서 자동 셋팅함. 
		// 다시 설명하면 사용자가 주소 "/sboard/*"를 입력하면 list에 들어오고 쿼리스트링으로 PageMaker에 일치하는게 있는지 찾고 일치하는게 없으면 page는1 perpagenum은 10으로 셋팅
		// 나머지는 null처리하고 있으면 일치하는지 보고 일치하면 form에서 날라온 값에 맞춰 자동 셋팅  
		logger.info("listPage");
		System.out.println(pm);
		//PageMaker [page=1, perPageNum=10, searchType=t, keyword=888, totalCount=0, startPage=0, endPage=0, prev=false, next=false]
		model.addAttribute("list",bm.listSearchCriteria(pm)); // 위에 설명한대로 자동 셋팅된 값이 매개변수로 오고 BoardServiceImpl에 listSearchCriteria 함수 실행
		// listSearchCriteria 구현부는 boardMapper.xml에 select id = "listSearch" 부분임 (이름이 다르니 햇갈림 주의) 따라서 listSearchCriteria 결과
		// 검색 결과들이 list 라는 이름으로 model에 담겨요. 
		pm.setTotalCount(bm.listSearchCount(pm)); // 전체 카운트를 pm에 담고있고 그 상태로 setTotalCount 함수 처리. 
		//model.addAttribute("pageMaker",pm);  // 위에 매개변수 있어서 주석처리함. PageMaker pm을 클래스에서 매개변수로하면 model .addAttribute 처리한거랑 동일 
		// sboard에 가고있는 데이터는 list 데이터랑 PageMaker의 pm 데이터임. 두 개모두 model에 담겨서 가는 것임. 
		//return "sboard/list";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)// 요청하면 사용자가 글을 입력하도록 하는 함수 
	public void writeGet() throws Exception {
		//return "/sboard/write
	
	}
	
	/*	  $('.writeBtn').on("click",function(event){
		  location.href="/ex/sboard/write";
	  	  });
		  $('#newBtn').on("click",function(event){
		  self.location="write";
	       });
	 *  list.jsp에서 위에처럼 버튼을 누르면 일단 get방식으로 writeGet()함수로 와서 view화면 보여주고 sboard의write.jsp페이지를 보여주는 것임. 
	 *   그리고 데이터를 입력하면 form태그의 post 방식으로 writePost로 와서 DB 처리하고 list.jsp로 return 함. 
	 * 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST) // 입력된 값 데이터를 받아서 처리함. 
	public String writePost(BoardDTO board,RedirectAttributes rttr) throws Exception {
		// BoradDTO의 db값을 board에 담아서 bm.write() 처리함. (db에 글쓰기 정보 저장) 
		bm.write(board); // 사용자가 입력한 값 DB에 저장 
		//rttr.addAttribute("msg","success");
		rttr.addFlashAttribute("msg","success"); // DB 저장했으니 성공메세지 보냄 addFlashAttribute() : 1회용 전달값(새로고침하면 사라짐) 
		return "redirect:/sboard/list";		 // 이동해! 
		// list.jsp로 이동을하고 그전에 SearchBoardController의 list()함수 실행. 그리고 list.jsp로 이동 
	}
	// 글을 읽을때 RequestParam은 bno임. 즉 bno로 온 애를 int bno에 넣음.  PageMaker 관련된건 pm에 넣고 model에 담기. 
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,PageMaker pm,Model model) throws Exception {
		System.out.println(pm);
		model.addAttribute(bm.read(bno)); // 데이터를 model에 담는데 어떤 데이터냐면 변수 bno를 가지고 특정 bno에 대한 bno,title,writer,regdate,viewcnt 데이터 읽어옴.
		// 이렇게 be.read(bno) 처럼 값만 담으면 class로 key값이 되는 것임. 즉 key는 BoardDTO랑 PageMakger 값임. 
		//return "/sboard/read
	}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	// get 방식이 없는 이유는 read.jsp에서 from 태그의 action이 post로 설정되어있고 자바스크립트에서 따로 get으로 구현하지는 않았음.
	// 그리고 read.jsp에서 설정되 boardDTO.bno값들을 가지고 여기 컨트롤러의 remove()로 와야 그 특정글을 bno로 설정하고 그 글을 삭제할 수 있음. 
	// 그리고 @RequestParam("bno")는 어떤 글인지 알아야 하니깐 가져온거고 PageMaker pm은 페이지정보를 알아야 지운 정보를 가지고 list로 가서
	// 지운 글 작업한 위치 가려고 매개변수로 넣은 것임. 
	public String remove(@RequestParam("bno") int bno,PageMaker pm,Model model,RedirectAttributes rttr) throws Exception {
		bm.remove(bno); //DB에서 삭제처리. 
		
		// rtrr.addAttribute로 4개의 변수 처리한건 다시 list 페이지로 돌아갈때 작업했던 페이지로 돌아가기 위해서임. 작업하고 있던 곳 알려면 저 4개 변수필요. 
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
		return "redirect:/sboard/list";	
	}
	
	//modify.jsp에서 사용자가 form태그에서 post방식으로 전송하기 전 get방식으로 한번옴.(즉 modify.jsp로 오기전에 이 메소드 한번 실행함.) 
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bno") int bno,PageMaker pm,Model model) throws Exception {
		System.out.println(pm);
		// model에 읽어오고 담았음. 그리고 modify.jsp로 이동한것임. 
		model.addAttribute(bm.read(bno));
	}


	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardDTO board,PageMaker pm,Model model,RedirectAttributes rttr) throws Exception {
		// BoardDTO board는 사용자가 form태그에 입력한 정보(dto 데이터) 
		// PageMakger pm은 페이지정보 
		// RedirectAttributes rttr은 페이지정보(pageMaker) 전송해주려고 쓴 것임. 
		bm.modify(board); // 여기서 db작업 수정처리 (board 정보 수정) 
		System.out.println(pm);
		// 이전 수정작업대로 이동하려고 rttr에 담음 (페이지 정보) 
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
		// RedirectAttributes rttr로 추가정보 담아서 list로 가자 
		return "redirect:/sboard/list";	 // list가면서 success도 가지고감.
	}

	
}









