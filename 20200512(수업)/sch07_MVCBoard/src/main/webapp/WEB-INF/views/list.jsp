<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"   %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<!-- <style type="text/css">
	body {  background-color: #FFF0B5; font-size: 9pt; }
	table { background-color: yellow; width: 100%;}
	tr:hover { background-color: ornage; }
	td { background-color: #FFE271; text-align: center; padding: 10px;}
	.left { text-align: left; }
</style>  -->
</head>
<body>
	<table> 
	<!-- <table width="500" cellpadding="0" cellspacing="0" border="1"> -->
		<tr>
			<td>번호1</td><td>이름1</td><td>제목</td><td>날짜</td><td>조회수</td>
		</tr>
		
	  <c:forEach items="${list}" var="board">
		<tr>
			<td>${board.bId}</td>
			<td>${board.bName}</td>
			<td>
				<c:forEach begin="1" end="${board.bIndent}">-</c:forEach>
				<a href="content_view?bId=${board.bId}">${board.bTitle}</a></td>
			<td>${board.bDate}</td>
			<td>${board.bHit}</td>
		</tr>
	 </c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
   </table>
</body>
</html>