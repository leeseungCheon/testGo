package com.human.ex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.human.dto.ReplyDTO;
import com.human.service.ReplyService;
import com.human.vo.PageMaker;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Inject
	private ReplyService rm;
	// 6번글이면 6번글에 대한 모든 글에 대한 GET 방식 요청 
	@RequestMapping(value="/all/{bno}",method=RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bno") Integer bno){
		// @PathVariable("bno") Integer bno는 만약 bno가 6이면 6값을 읽어서 int bno에 저장 
		ResponseEntity<List<ReplyDTO>> entity=null;

	    try {
	     
	    	entity = new ResponseEntity<>(rm.listReply(bno), HttpStatus.OK);  // HttpStatus.Ok는 정상도작 
	    	// entity는 return 되는 값  rm.listReply(bno)는 bno에 대한 글들이 리턴됨. <list><ReplyDTO>가 리던됨. 
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // HttpStatus.BAD_REQUEST는 비정상동작 그래서 catch문에 씀. 잘못된 요청 처리임. 
	    }
		return entity; // 제이슨으로 받아 문자열로 리턴 
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	  public ResponseEntity<String> register(@RequestBody ReplyDTO vo) { //Post 방식이니깐 사용자가 입력한 데이터 어디선가 받아서 어디서 처리해야겠지. @RequestBody는 사용자가 JSon 형태로 보내는거라고 생각하면됨. 그 데이터를 ReplyDTO vo에 담음. 

	    ResponseEntity<String> entity = null;
	    try {
	    	rm.addReply(vo);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }
	// RequestMethod.PUT : 업데이트 요청임. 
	  @RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	  public ResponseEntity<String> update(@PathVariable("rno") Integer rno // @PathVariable("rno") = 수정할 데이터 (update 요청이니깐) 
			  , @RequestBody ReplyDTO vo) { // @RequestBody : 수정할 데이터가 들어오면 ReplyDTO vo에 담아라 

	    ResponseEntity<String> entity = null;
	    try {
	      vo.setRno(rno); // 새로운 rno 글 셋팅 
	      rm.modifyReply(vo); // 새로운 글 번호로 수정 

	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK); // 업데이트라서 return 될 데이터 없어서 성공 메시지로 던짐. 
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	  @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

	    ResponseEntity<String> entity = null;
	    try {
	      rm.removeReply(rno);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }
// GET이니깐 읽는거에요. 
	  // 댓글이 많으면 paging 처리 해야할거 아니에요. bno가 5 page1 이면 5번글에 1번 페이지에요. 이해됐어요? 
	  @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> listPage(
	      @PathVariable("bno") Integer bno, // 글번호 bno랑 원하는 페이지page 선택해서 PageMaker에 객체 생성해서 setPage(page)넣고 rm.listReplyPage(bno,pm)처리해야죠. 
	      @PathVariable("page") Integer page) {

	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
	      PageMaker pm = new PageMaker();
	      pm.setPage(page);


	      Map<String, Object> map = new HashMap<String, Object>();
	      List<ReplyDTO> list = rm.listReplyPage(bno, pm); // 해당 페이지랑 글번호를 List<ReplyDTO>list로 받고있죠. 

	      map.put("list", list);

	      int replyCount = rm.count(bno);
	      pm.setTotalCount(replyCount);

	      map.put("pageMaker", pm); // 댓글에 대한 pageMaker 만들기. 받은 데이터들을 map형태의 제이슨 처리. 

	      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	
}













