<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<%@include file="../include/header.jsp"%>
<script>
$(document).ready(function() {
	var fObject=$(".form");
	
	$(".btnCancel").on("click",function(){

		fObject.attr("method","get");
		fObject.attr("action","/ex/sboard/list");
		fObject.submit();
		
	})			
	$(".btnSave").on("click",function(){
		fObject.attr("method","post");
		fObject.attr("action","/ex/sboard/modify");
		fObject.submit();
	})
	
});
</script>
			
<div class="main">
  
<form class="form" method="post">

					<input type='hidden' name='bno' value="${boardDTO.bno}"> <input
						type='hidden' name='page' value="${pageMaker.page}"> <input
						type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
					<input type='hidden' name='searchType' value="${pageMaker.searchType}">
					<input type='hidden' name='keyword' value="${pageMaker.keyword}">



	<h1>modify</h1>
	<h2>
		<input type="text"
			name='title'  style="width:100%" value="${boardDTO.title}"></h2>
	
		<h2>
		Content
		<textarea  style="width:100%" name="content" rows="3">${boardDTO.content}</textarea>	</h2>
	
		<h2>
		Writer <input type="text"
			name="writer" style="width:100%" value="${boardDTO.writer}">
		<h2>
</form>
<!-- /.box-body -->
<div class="box-footer">
	<button type="submit" class="btnSave">Save</button>
	<button type="submit" class="btnCancel">Cancel</button>
</div>

</div>

<%@include file="../include/footer.jsp"%>


