<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<style>
h2, #paging, #tr_top {
	text-align: center;
}

table, #member, #paging {
	margin: auto;
	width: 800px;
}

form {
	margin: auto;
	width: 300px;
}

td, th {
	border: solid lightgray 1px;
}

td a {
	text-decoration: none;
}

#member {
	text-align: right;
}

#tr_top {
	background: orange
}

#paging a {
	display: inline-block;
	width: 20px;
	height: 20px;
	border: solid gray 1px;
	text-decoration: none;
}

#paging .select {
	background: lightblue;
}

#paging .btn {
	background: lightgray;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function() {
	$("#type").val("${type}").prop("selected", true);
})

function submit(page) {
	$("#page").val(page);
	$("#search").submit();
}
</script>

</head>
<body>
	<h2>
		글 목록&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${member ne null }">
			<a href="boardWrite">글쓰기</a>
		</c:if>
	</h2>
	<div id="member">
		<c:choose>
			<c:when test="${member eq null }">
				<a href="login">로그인</a>
			</c:when>
			<c:otherwise>
				<c:if test="${member.profileImageStr != null}">
					<img src="data:image/png;base64,${member.profileImageStr}"
						id="profile" width="20px" height="20px" />
				</c:if>
				<b>[ ${member.id } ]</b>&nbsp;&nbsp;<a href="logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
		&nbsp;&nbsp;&nbsp;<a href="join">회원가입</a>
	</div>
	<br>
	<form action="/boardList" method="get" id="search">
		<input type="hidden" name="page" value="1" id="page" /> 
		<select name="type" id="type">
			<option value="subject" selected="selected">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select> 
		<input type="text" name="word" value="${word}" /> 
		<input type="submit" value="검색" />
	</form>
	<br />
	<table>
		<tr id="tr_top">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${boardList }" var="board">
			<tr>
				<td>${board.num }</td>
				<td><a href="boardDetail?num=${board.num}">${board.subject }</a></td>
				<td><c:if test="${board.profileImage != null }">
						<img src="data:image/png;base64,${board.profileImage}"
							width="30px" height="30px" />&nbsp;&nbsp;	
				</c:if> ${board.nickname }</td>
				<td>${board.createDate }</td>
				<td>${board.viewCount }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div id="paging">
		<c:choose>
			<c:when test="${pageInfo.curPage > 1 }">
				<button onclick="submit(${pageInfo.curPage-1})">&lt;</button>
			</c:when>
			<c:otherwise>
				<a>&lt;</a>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }"
			var="i">
			<c:choose>
				<c:when test="${i eq pageInfo.curPage }">
					<button onclick="submit(${i })" class="select">${i }</button>
				</c:when>
				<c:otherwise>
					<button onclick="submit(${i })" class="btn">${i }</button>
				</c:otherwise>
			</c:choose>

		</c:forEach>
		<c:choose>
			<c:when test="${pageInfo.curPage<pageInfo.allPage }">
				<button onclick="submit(${pageInfo.curPage+1})">&gt;</button>
			</c:when>
			<c:otherwise>
				<a>&gt;</a>
			</c:otherwise>
		</c:choose>

	</div>
</body>
</html>