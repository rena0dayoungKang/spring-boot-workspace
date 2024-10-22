<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
	<form action="#" method="post">
		<div><h3 class="header">출금</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">계좌번호</div>
				<div class="input"><input type="text" name="id"/></div>
			</div>
			<div class="row">  
				<div class="title">출금액</div>
				<div class="input"><input type="text" name="money" required="required"/></div>
			</div>
			<div>
				<input type="submit" value="출금"/>
			</div>
		</div>
	</form>	
</body>
</html>