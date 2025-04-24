<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List<Map<String,Object>> articleRows =(List<Map<String,Object>>)request.getAttribute("articleRows");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
<h1>게시글 목록</h1>
<ul style="color: red; list-style: none; margin: 0; padding: 0;">
<%for(int i =0; i <= articleRows.size()-1; i++) {%>
<li>id-<%=articleRows.get(i).get("id") %>번, 작성날짜-<%=articleRows.get(i).get("regDate")%>, 제목-<%=articleRows.get(i).get("title")%>, 내용-<%=articleRows.get(i).get("body")%></li>
<%}%>
</ul>
</body>
</html>