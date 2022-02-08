<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ page session="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	margin: 0;
	padding: 0;
}

.main {
	width: 500px;
	text-align: center;
}

table {
	width: 500px;
	border: 1px solid #444444;
	border-collapse: collapse;
	font-size: 13px;
}

th, tr, td {
	border: 1px solid #444444;
}

th {
	background-color: #E7EAED;
}
</style>
</head>
<body>
	<div class="main">
		<a href='/ex/human/insert'>입력</a> 
		<a href='/ex/human/update'>수정</a> 
		<a href='/ex/human/select'>검색</a> 
		<a href='/ex/human/delete'>삭제</a> 
		<a href='/ex'>리스트</a>
			
		<table id='humanInfo'>
			<tr>
				<th style="width: 80px">이름</th>
				<th style="width: 80px">나이</th>
				<th style="width: 80px">키</th>
				<th style="width: 100px">생일</th>
			</tr>
			
			<c:choose>
				<c:when test="${type eq 'b' }">
					<tr>
						<td>${humanDTO.name}</td>
						<td>${humanDTO.age}</td>
						<td>${humanDTO.height}</td>						
						<td><fmt:formatDate value="${humanDTO.birthday}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="humanDTO">
						<tr>
							<td>${humanDTO.name}</td>
							<td>${humanDTO.age}</td>
							<td>${humanDTO.height}</td>
							<td><fmt:formatDate value="${humanDTO.birthday}" pattern="yyyy-MM-dd"/></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</table>
	</div>
</body>
</html>