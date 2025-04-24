<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 디테일</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<ul style="color: red; list-style: none; margin: 0; padding: 0;">

<li>id-<%=articleRow.get("id") %>, 작성날짜-<%=articleRow.get("regDate")%>, 수정날짜-<%=articleRow.get("updateDate")%>, 제목-<%=articleRow.get("title")%>, 내용-<%=articleRow.get("body")%></li>
	</ul>
</body>
</html>