<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.td1 {
	text-align: right;
}
</style>
</head>
<body>
메뉴
<a href='/ex/human/insert'>입력</a> 
<a href='/ex/human/update'>수정</a> 
<a href='/ex/human/select'>검색</a> 
<a href='/ex/human/delete'>삭제</a>
<a href='/ex'>리스트</a>
<form action="/ex/human/delete" method="post" >
	<table>
			<tr>
				<td class="td1">삭제할 이름</td>
				<td><input type="text" name="name"></td>
			</tr>

		</table>
	<input type="submit" value="삭제"><br>
</form>
</body>
</html>