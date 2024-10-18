<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2, #commandCell {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	background: orange;
}

.td_right {
	background: skyblue;
}
</style>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	function readDFile(input) {
		if (input.files && input.files[0]) {
			document.getElementById('dFileName').innerText = input.files[0].name;
		}
	}
</script>
</head>
<body>
	<h2>게시판 글 수정</h2>
	<form action="boardModify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${board.num }">
		<table>
			<tr>
				<td class="td_left"><label for="writer">글쓴이</label></td>
				<td class="td_right"><input type="text" name="writer"
					id="writer" readonly="readonly" value="${board.writer }"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="subject">제목</label></td>
				<td class="td_right"><input type="text" name="subject"
					id="subject" required="required" value="${board.subject }">
				</td>
			</tr>
			<tr>
				<td class="td_left"><label for="content">내용</label></td>
				<td class="td_right"><textarea name="content" cols="40"
						rows="15" id="content" required="required">${board.content }</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_left"><label>이미지 파일 첨부</label></td>
				<td class="td_right" onclick="document.getElementById('file').click();">
					<c:choose>
						<c:when test="${board.filename eq null}">
							<img src="resources/image/plus.png" width="50px" id="preview">	
						</c:when>
						<c:otherwise>
							<img src="image/${board.filename }" width="100px" id="preview">
						</c:otherwise>
					</c:choose>
					</td>
			</tr>
			<tr>
				<td class="td_left"><label>파일 첨부</label></td>
				<td class="td_right" onclick="document.getElementById('dfile').click();"><span id="dFileName">${board.dfilename }</span></td>
			</tr>
		</table>
		<input type="file" name="file" id="file" accept="image/*" onchange="readURL(this);" style="display: none"> 
		<input type="file" name="dfile" id="dfile" onchange="readDFile(this);" style="display: none">
		<div id="commandCell">
			<input type="submit" value="수정">&nbsp;&nbsp; 
			<input type="reset" value="다시쓰기">
		</div>
	</form>
</body>
</html>