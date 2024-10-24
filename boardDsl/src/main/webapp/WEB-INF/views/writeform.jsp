<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2,#commandCell { text-align: center; }
	table { margin: auto; width:450px; }
	.td_left { background: orange; }
	.td_right { background: skyblue; }
</style>
<script>
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		} 
	}
</script>
</head>
<body>
<h2>게시판 글 등록</h2>
<form action="boardWrite" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td class="td_left"><label for="writer">글쓴이</label></td>
			<td class="td_right"><input type="text" name="writer" id="writer" value="${member.id }" readonly></td>
			<!-- input 태그 안에 disabled 라고 적어 놓으면 데이터를 전송하지 못하기 때문에 readonly를 사용  -->
		</tr>
		<tr>
			<td class="td_left"><label for="subject">제목</label></td>
			<td class="td_right"><input type="text" name="subject" id="subject" required="required"></td>
		</tr>
		<tr>
			<td class="td_left"><label for="content">내용</label></td>		
			<td class="td_right"><textarea id="content" name="content" cols="40" rows="15"></textarea></td>
		</tr>
		<tr>
			<td class="td_left"><label for="file">이미지 파일 첨부</label></td>
			<td class="td_right">
				<img src="<c:url value="resources/image/plus.png"/>" alt="이미지 선택" id="preview" width="50px"
					onclick="document.getElementById('file').click();">
				<input type="file" name="file" id="file" accept="image/*" onchange="readURL(this);"
					style="display:none">
			</td>
		</tr>
		<tr>
			<td class="td_left"><label for="dfile">파일 첨부</label></td>
			<td class="td_right">
				<input type="file" name="dfile" id="dfile">
			</td>
		</tr>
	</table>
	<div id="commandCell">
		<input type="submit" value="등록">&nbsp;&nbsp;
		<input type="reset" value="다시쓰기">&nbsp;&nbsp;
		<a href="boardlist">목록</a>
	</div>
</form>
</body>
</html>