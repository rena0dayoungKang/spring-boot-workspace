<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kosta.bank.dto.Account" %>
<%-- <% Account acc = (Account)request.getAttribute("acc"); %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
<%-- 	<form action="#" method="post">
		<div><h3 class="header">계좌조회</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">계좌번호</div>
				<div class="input"><input type="text" disabled value="<%=acc.getId()%>"/></div>
				<div class="input"><input type="text" disabled value="${requestScope.acc.id }"/></div>
			</div>
			<div class="row">  
				<div class="title">이름</div>
				<div class="input"><input type="text" disabled value="<%=acc.getName()%>"/></div>
				<div class="input"><input type="text" disabled value="${acc.name }"/></div>
			</div>
			<div class="row">  
				<div class="title">잔액</div>
				<div class="input"><input type="text" disabled value="<%=acc.getBalance()%>"/></div>
			</div>
			<div class="row">  
				<div class="title">종류</div>
				<div class="input"><input type="text" disabled value="<%=acc.getType()%>"/></div>
			</div>
			<%if(acc.getType().equals("special")) {%>
			<div class="row">  
				<div class="title">등급</div>
				<div class="input"><input type="text" disabled value="<%=acc.getGrade()%>"/></div>
			</div>
			<%} %>
		</div>
	</form>
	 --%>
	<!--form 태그가 없어도 가져올 수 있음  -->
		<div><h3 class="header">계좌조회</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">계좌번호</div>
				<div class="input"><input type="text" disabled value="${requestScope.acc.id }"/></div>
			</div>
			<div class="row">  
				<div class="title">이름</div>
			
				<div class="input"><input type="text" disabled value="${acc.name }"/></div>
			</div>
			<div class="row">  
				<div class="title">잔액</div>
				<div class="input"><input type="text" disabled value="${acc.balance }"/></div>
			</div>
			<div class="row">  
				<div class="title">종류</div>
				<div class="input"><input type="text" disabled value="${acc.type }"/></div>
			</div>
			
			
			<c:if test="${acc.type eq 'special'} ">
			<div class ="row">
				<div class="title">등급</div>
				<div class="input"><input type="text" disabled value="${acc.grade}"/></div>
			</div>
			</c:if>
			
		</div>

	
</body>
</html>