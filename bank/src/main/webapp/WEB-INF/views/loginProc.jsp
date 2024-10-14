<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	String type=request.getParameter("type");
	
	if(id.equals("java") && password.equals("1234")){
		session.setAttribute("id", id);	
		pageContext.forward("loginSuccess.jsp");
	}else {
		pageContext.forward("loginFail.jsp");
	}
	
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	아이디 : <%=id %><br>
	비밀번호 : <%=password %><br>
	<h1>로그인 성공</h1>
</body>
</html>