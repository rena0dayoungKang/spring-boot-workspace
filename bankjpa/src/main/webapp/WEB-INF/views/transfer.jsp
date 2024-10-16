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
	<form action="transfer" method="post">
		<div><h3 class="header">계좌이체</h3></div>
		<div class="wrap" style="width:305px;">
			<div class="row">  
				<div class="title" style="width:120px">보내는계좌번호</div>
				<div class="input"><input type="text" name="sid"/></div>
			</div>
			<div class="row">  
				<div class="title" style="width:120px">받는계좌번호</div>
				<div class="input"><input type="text" name="rid"/></div>
			</div>
			<div class="row">  
				<div class="title" style="width:120px">송금액</div>
				<div class="input"><input type="text" name="money"/></div>
			</div>
			<div>
				<input type="submit" value="이체"/>
			</div>
		</div>
	</form>	
</body>
</html>