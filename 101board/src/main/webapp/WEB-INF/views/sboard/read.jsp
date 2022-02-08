<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<style>
#modDiv {
	width: 380px;
	height: 150px;
	background-color: gray;
	position: fixed;
	top: 20%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
</style>
<script>
	var bno="${boardDTO.bno}";
	var page=1;
	$(document).ready(function() {
		//리플작업시작
		//read페이지는 특정글을 읽을때 들어와서 bno정보를 가지고 있다.
		//처음 read페이지에 들어 왔을때 page는 1이 된다.
		//bno page
	
		getPageList(page);
		$("#replyModBtn").on("click",function(){
			  var rno = $(".modal-title").html();
			  var replytext = $("#replytext").val();
			  $.ajax({
					type:'put',
					url:'/ex/replies/'+rno,
					headers: { 
					      "Content-Type": "application/json"},
					data:JSON.stringify({replytext:replytext}), 
					dataType:'text', 
					success:function(result){
						console.log("result: " + result);
						if(result == 'SUCCESS'){
							alert("수정 되었습니다.");
							 $("#modDiv").hide("slow");
							//getAllList();
							 getPageList(page);
						}
				}});
		});
		$("#closeBtn").on("click", function() {
			$("#modDiv").hide("slow");
		});
		
		$("#replyDelBtn").on("click", function() {
			var rno = $(".modal-title").html();
			//var replytext = $("#replytext").val();
			$.ajax({
				type : 'delete',
				url : '/ex/replies/' + rno,
				headers : {
					"Content-Type" : "application/json"
				},
				dataType : 'text',
				success : function(result) {
					console.log("result: " + result);
					if (result == 'SUCCESS') {
						alert("삭제 되었습니다.");
						$("#modDiv").hide("slow");
						//getAllList();
						getPageList(page);
					}
				}
			});
		});
		
		$("#replies").on("click",".replyLi button",function(){
			var reply=$(this).parent();
			var rno=reply.attr("data-rno");
			var replytext=reply.text();
			$(".modal-title").html(rno);
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");
		})
		
		$(".pagination").on("click","a",function(event){
			event.preventDefault();
			page=$(this).attr("href");
			getPageList(page);
		})
		$("#replyAddBtn").on("click", function() {
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			$.ajax({
				type : 'post',
				url : '/ex/replies',
				headers : {
					"Content-Type" : "application/json"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					replytext : replytext
				}),
				success : function(result) {
					if (result == 'SUCCESS') {
						alert("등록 되었습니다.");
						//getAllList();
						getPageList(page);
					}
				}
			});
		});

		//리플작업종료
		var fObject = $(".form");

		$(".btnList").on("click", function() {

			fObject.attr("method", "get");
			fObject.attr("action", "/ex/sboard/list");
			fObject.submit();

		})
		$(".btnRemove").on("click", function() {
			fObject.attr("action", "/ex/sboard/remove");
			fObject.submit();
		})
		$(".btnModify").on("click", function() {
			fObject.attr("method", "get");
			fObject.attr("action", "/ex/sboard/modify");
			fObject.submit();
		})
		
		
		
	});
	/* private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;

	private Date regdate;
	private Date updatedate; */
	function getPageList(page){
		$.getJSON("/ex/replies/"+bno+"/"+page,function(data){ // $.getJSON : 아작스 대신에 씀. 
			console.log(data.list.length);
			var str="";  // 댓글을 문자열로 만들어줘야지.
			$(data.list).each(function(){
				str+="<li data-rno='"+this.rno+"' class='replyLi'>"
				+this.rno+":"+this.replytext+" <button>Mod</button></li>"
			})
			$("#replies").html(str);
			printPaging(data.pageMaker)
		})
	}
function printPaging(pageMaker){
	
	var str = "";  
	
	if(pageMaker.prev){
		str += "<a href='"+(pageMaker.startPage-1)+"'> << </a>";
	}
	
	for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){				
			var strClass= pageMaker.page == i?'class=active':'';
		  str += "<a  "+strClass+" href='"+i+"'>"+i+"</a>";
	}
	
	if(pageMaker.next){
		str += "<a href='"+(pageMaker.endPage + 1)+"'> >> </a>";
	}
	$('.pagination').html(str);	
	
}


</script>

<div class="main">

	<form class="form" method="post">

		<input type='hidden' name='bno' value="${boardDTO.bno}"> <input
			type='hidden' name='page' value="${pageMaker.page}"> <input
			type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
		<input type='hidden' name='searchType' value="${pageMaker.searchType}">
		<input type='hidden' name='keyword' value="${pageMaker.keyword}">


	</form>
	<h1>read</h1>
	<h2>
		<input type="text" name='title' style="width: 100%"
			value="${boardDTO.title}" readonly="readonly">
	</h2>

	<h2>
		Content
		<textarea style="width: 100%" name="content" rows="3"
			readonly="readonly">${boardDTO.content}</textarea>
	</h2>

	<h2>
		Writer <input type="text" name="writer" style="width: 100%"
			value="${boardDTO.writer}" readonly="readonly">
		<h2>

			<!-- /.box-body -->
			<div class="box-footer">
				<button type="submit" class="btnModify">Modify</button>
				<button type="submit" class="btnRemove">REMOVE</button>
				<button type="submit" class="btnList">LIST ALL</button>
			</div>

			<div id='modDiv' style="display: none;">
				<div class='modal-title'></div>
				<div>
					<input type='text' id='replytext'>
				</div>
				<div>
					<button type="button" id="replyModBtn">Modify</button>
					<button type="button" id="replyDelBtn">DELETE</button>
					<button type="button" id='closeBtn'>Close</button>
				</div>
			</div>
			<h2>Ajax Test Page</h2>
			<div>
				<div>
					REPLYER <input type='text' name='replyer' id='newReplyWriter'>
				</div>
				<div>
					REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
				</div>
				<button id="replyAddBtn">ADD REPLY</button>
			</div>
			<ul id="replies">
			</ul>
			<div class='pagination'></div>
</div>

<%@include file="../include/footer.jsp"%>


