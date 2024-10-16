<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String message = (String)request.getAttribute("message"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<% if(message !=null){  %>
	<h1><%= message %></h1>
	<%} %>
	<form action="login" method="post">
		<div><h3 class="header">로그인</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">아이디</div>
				<div class="input"><input type="text" name="id"/></div>
			</div>
			<div class="row">  
				<div class="title">비밀번호</div>
				<div class="input"><input type="text" name="password"/></div>
			</div>
			<div class="row">  
				<input type="checkbox" name="type" class="title"><b>자동로그인</b>
			</div>
			<div>
				<input type="submit" value="로그인"/>
			</div>
		</div>
	</form>
</body>
</html>