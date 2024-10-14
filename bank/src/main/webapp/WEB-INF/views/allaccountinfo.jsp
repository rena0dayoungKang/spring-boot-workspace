<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="java.util.List"%>
<%@ page import="dto.Account"%>
<%
request.setCharacterEncoding("utf-8");
List<Account> accs = (List<Account>) (request.getAttribute("accs"));
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>


<style>
.wrap {
   margin: 0 auto;
   border: 1px solid;
   width: 700px;
}

.row {
   height: 35px;
   border-top: 1px solid;
   border-bottom: 1px solid;
}

.title {
   font-weight: bold;
   background-color: lightgray;
}

.column {
   width: 98px;
   float: left;
   line-height: 35px;
   text-align: center;
   border-left: 1px solid;
   border-right: 1px solid;
}
</style>

</head>
<body>
	<%@ include file="header.jsp" %>
   <div>
      <h3 style="text-align: center;">전체계좌조회</h3>
   </div>
   <div class="wrap">
      <div class="row">
         <div class="column title">순서</div>
         <div class="column title">계좌번호</div>
         <div class="column title">이름</div>
         <div class="column title">입금액</div>
         <div class="column title">종류</div>
         <div class="column title">등급</div>
      </div>
      <c:forEach items="${accs }" var="acc" varStatus="status">
         <div class="row">
            <div class="column">${status.count }</div>
            <div class="column">${acc.id }</div>
            <div class="column">${acc.name }</div>
            <div class="column">${acc.balance }</div>
            <div class="column">${acc.type }</div>
            <div class="column">
               <c:if test="${acc.type eq 'special' }">
                  ${acc.grade }
               </c:if>
            </div>
         </div>
      </c:forEach>
   </div>
</body>
</html>