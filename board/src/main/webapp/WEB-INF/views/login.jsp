<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="resources/css/common.css" rel="stylesheet">
</head>
<body>
	<%
	if (message != null) {
	%>
	<h1><%=message%></h1>
	<%
	}
	%>
	<form action="login" method="post">
		<div>
			<h3 class="header">로그인</h3>
		</div>
		<div class="wrap">
			<div class="row">
				<div class="title">아이디</div>
				<div class="input">
					<input type="text" name="id" />
				</div>
			</div>
			<div class="row">
				<div class="title">비밀번호</div>
				<div class="input">
					<input type="text" name="password" />
				</div>
			</div>
			<div class="row">
				<input type="checkbox" name="autologin" class="title"
					value="${autoLogin }" ${autoLogin ne null ? "checked" : "" }><b>자동로그인</b>
			</div>
			<div>
				<input type="submit" value="로그인" /><br> 
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=d7025e5c18416e2e0239db5affbce95f&redirect_uri=http://localhost:8080/board/kakao&response_type=code">
					<img src='<c:url value="resources/image/kakao_login_medium_narrow.png"/>' />
					</a> <br> 
					<a href="https://nid.naver.com/oauth2.0/authorize?client_id=nQOdhXOX9jDuuJZSwGsv&redirect_uri=http://localhost:8080/board/naver&response_type=code&state=1357">
					<img style="width: 195px" src='<c:url value="resources/image/naver_login.png"/>' />
					</a>
			</div>
		</div>
	</form>



</body>
</html>