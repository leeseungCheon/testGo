package com.human.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int page=1;//현재 페이지 (사용자가 입력한 페이지) 
	private int perPageNum=10;//페이지당 데이터 개수
	private String searchType;//검색할 컬럼 (keyword)  안넣으면 전체 데이터 
	private String keyword;//검색 내용
	
	private int totalCount; // 전체 데이터 개수 (모든 페이지 전체 데이터 개수) 
	private int totalStartPage=1;  // 전체 데이터 맨처음 시작페이지 (무조건 1) 
	private int totalEndPage;  // 마지막 페이지 총 8000개 데이터면  800페이지정도  
	private int startPage;//pageMake에서 시작 페이지 번호 16페이지 기준으로 11 
	private int endPage;//pageMaker에서 마지막 페이지 번호 16페이지 기준으로 20 
	// prev랑 next는 startPage로 값을 알 수 있음. startPage - 1 하면 prev이고 +1하면 next임 
	private boolean prev;//이전 페이지 번호 목록 이동 , 왜 boolean? start페이지랑 endpage로 값을 알 수 있어서. 1페이지면 없어야하니깐 넣은거임. true false가 맞음. 
	private boolean next;//다음 페이지 목록 이동
	//pageMaker에서 사용자에게 제공하는 한 화면에서 보여줄 페이지 개수
	private int displayPageNum = 10; // 한페이지에 몇페이지씩 보여줄 수 있는지 (데이터개수 아님) 
	
	
	public int getTotalStartPage() {
		return totalStartPage;
	}
	public void setTotalStartPage(int totalStartPage) {
		this.totalStartPage = totalStartPage;
	}
	public int getTotalEndPage() {
		return totalEndPage;
	}
	public void setTotalEndPage(int totalEndPage) {
		this.totalEndPage = totalEndPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page<=0) {
			page=1;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0||perPageNum>100) {
			perPageNum=10;
		}
		this.perPageNum = perPageNum;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//전체 개수를 설정한다음 페이징에 필요한 데이터 값을 생성
		//할 수 있다.
		calcData();
	}
	//totalEndPage 구하기 
	private void calcData() {
		totalStartPage=1;
		totalEndPage=(int)Math.ceil(totalCount/(double)perPageNum); // ceil : 올림처리 (1.6페이지면 2페이지까지 가야함) 
//		private int endPage;//pageMaker에서 마지막 페이지 번호
	    endPage = (int) (//올림 floor 내림 round 반올림
	    		Math.ceil(page /(double) displayPageNum)
	    		* displayPageNum);
//	    private int startPage;//pageMake에서 시작 페이지 번호
	    startPage=endPage-displayPageNum+1;
	    if(totalEndPage < endPage) {  // 이 조건문은 마지막 페이지가 16이면 20으로 설정하면 안되니깐 16으로 맞춘거임 endPage=totalEndPage로 
	    	endPage=totalEndPage;
	    }
	    // 시작페이지는 무조건 1임. 1 이상이면 1로 처리 
	    if(startPage<1) {
	    	startPage=1;
	    }
	    if(startPage==1) {
	    	prev=false; // 1이면 이전으로 못가니깐 false 처리해서 앞으로 가게하는 화살표 없애거나 false 처리 
	    }else {
	    	prev=true;
	    }
	    if(endPage==totalEndPage) {
	    	next=false;  // 마지막 페이지랑 토탈 페이지랑 같아버리면 앞으로 못가게 해야해서 false 처리 
	    }else {
	    	next=true;
	    }

//		private boolean prev;//이전 페이지 번호 목록 이동
//		private boolean next;//다음 페이지 목록 이동
		
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "PageMaker [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", keyword="
				+ keyword + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + "]";
	}
	
	/* Springframework의 UriComponentsBuilder을 사용하자
	 * 컨트롤러에서 Redirect시 여러 파라미터들을 일일 다 addAttribute를 사용하기에는 좀 불편하지.
	 * 이를 해결하기 위해 UriComponentsBuilder를 사용한다. 
	 * 이는 여러 개 파라미터들을 연결하여 하나의 URL 링크로 만들어서 반환해준다. 
	 * UriComponentsBuilder 객체 생성 후 파라미터들을 만들어주고 Uri 문자열로 반환시켜준다. 
	 */
	
	public String makeSearch() {
		UriComponents u=UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", perPageNum)
				.queryParam("searchType", searchType)
				.queryParam("keyword", keyword)
				.build();
		return u.toUriString();		
	}
	
	public String makeSearch(int page) {
		UriComponents u=UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", perPageNum)
				.queryParam("searchType", searchType)
				.queryParam("keyword", keyword)
				.build();
		return u.toUriString();		
	}
	
	
	public String makePage(int page) {
		UriComponents u=UriComponentsBuilder.newInstance()
				.queryParam("page", page) // key, value 셋팅하고 문자열로 만들어서 return 
				.queryParam("perPageNum", perPageNum)
				.build(); // build() 하면 위에 key, value 가지고 쿼리스트링 만들어줌. 
		return u.toUriString();		
	}
}
















