<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
   text-decoration: none;
   margin-left: 10px;
   margin-right: 10px;
}

.container {
   width: 100%;
   height: 120px;
   background-color: navajowhite;
   padding: 5px;
}

h1 {
   text-align: center;
}
</style>
</head>
<body>
   <div class="container">
      <h1><i>kosta bank</i></h1>
      <div style="float: left;">
         <a href="makeAccount">계좌개설</a> 
         <a href="deposit">입금</a> 
         <a href="withdraw">출금</a>
         <a href="accountInfo">계좌조회</a> 
         <a href="allAccountInfo">전체계좌조회</a> 
         <a href="transfer">계좌이체</a>
      </div>
      <div style="float:right;">
      	 <c:choose>
      	 	<c:when test="${member == null}">
      	 		<a href="login">로그인</a> 
      	 	</c:when>
      	 	<c:otherwise>
      	 		<b><span>[${member.id}]</span></b>&nbsp;
      	 		<a href="logout">로그아웃</a>		
      	 	</c:otherwise>
      	 </c:choose>
         <a href="join">회원가입</a>
      </div>
   </div>
</body>
</html>