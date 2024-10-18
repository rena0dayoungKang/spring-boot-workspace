<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetail</title>
<style>
	h2,#commandCell { text-align: center; }
	table { margin: auto; width:450px; }
	.td_left { background: orange; width: 150px; }
	.td_right { background: skyblue; width: 300px; }
	#content { height:200px; }
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function() {
	$('#heart').click(function() {
		$.ajax({
			url:'heart',
			type:'post',
			async:true,
			dataType:'text',
			data:{num:${board.num}},
			success:function(result) {
				if(result == 'true') {
					$('#heart').attr('src','resources/image/redheart.png');
				} else {
					$('#heart').attr('src','resources/image/blackheart.png');
				}
			},
			error:function(err) {
				console.log(err);
			}
		})
	})	
})
</script>
</head>
<body>
<h2>게시판 글 상세</h2>
<table border="1">
	<tr>
		<td class="td_left"><label>글쓴이</label></td>
		<td class="td_right"><span>${board.writer }</span></td>
	</tr>
	<tr>
		<td class="td_left"><label>제목</label></td>
		<td class="td_right"><span>${board.subject }</span></td>
	</tr>
	<tr>
		<td class="td_left"><label>내용</label></td>
		<td class="td_right"><div id="content">${board.content }</div></td>
	</tr>
	<c:if test="${board.imgFileNum ne null}">
		<tr>
			<td class="td_left"><label>이미지</label></td>
			<td class="td_right"><img src="/image/${board.imgFileNum }" width="100px"></td>
			<!-- image 라는 컨트롤러를 CommonsController에 생성 -->
		</tr>
	</c:if >
	<c:if test="${board.uploadFileName ne null}">
		<tr>
			<td class="td_left"><label>파일다운로드</label></td>
			<td class="td_right"><a href="fileDown?file=${board.uploadFileName}">${board.uploadFileName }</a></td>
		</tr>
	</c:if >
</table>
<br>
<div id="commandCell">
	<a href="boardModify?num=${board.num }">수정</a>&nbsp;&nbsp;&nbsp;
	<a href="boardList">목록</a>&nbsp;&nbsp;&nbsp;
	<c:if test="${member.id ne null }">
		<c:choose>
			<c:when test="${heart ne null }">
				<img src="resources/image/redheart.png" width="25px" id="heart">
			</c:when>
			<c:otherwise>
				<img src="resources/image/blackheart.png" width="25px" id="heart">
			</c:otherwise>
		</c:choose>
		
	</c:if>
</div>
</body>
</html>