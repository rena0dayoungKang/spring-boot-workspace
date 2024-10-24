<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/common.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#doubledId").click(function(e) {
			e.preventDefault();
			$.ajax({
				url:'memberDoubldId',
				type:'post',
				async:true,
				dataType:'text',
				data:{id:$("#id").val()},
				success:function(result) {
					if(result=='true') {
						alert("사용중인 아이디입니다")
					} else {
						alert("사용가능한 아이디입니다")
					}
				},
				error:function(err) {
					console.log(err)
				}
			})
		})
	})	
	
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
	<form action="join" method="post" enctype="multipart/form-data">
		<div><h3 class="header">회원가입</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">아이디</div>
				<div class="input">
					<input type="text" name="id" id="id" style="width:120px"/>
				</div>
				<div class="input">&nbsp;<button id="doubledId">중복</button>
				</div>
			</div>
			<div class="row">  
				<div class="title">이름</div>
				<div class="input">
					<input type="text" name="name"/>
				</div>
			</div>
			<div class="row">  
				<div class="title">닉네임</div>
				<div class="input"><input type="text" name="nickname"/></div>
			</div>
			<div class="row">  
				<div class="title">비밀번호</div>
				<div class="input"><input type="password" name="password"/></div>
			</div>
			<div class="row">  
				<div class="title">이메일</div>
				<div class="input"><input type="text" name="email"/></div>
			</div>
			<div class="row">  
				<div class="title">주소</div>
				<div class="input"><input type="text" name="address"/></div>
			</div>
			<div class="row">  
				<div class="title">사진</div>
				<div class="input">
					<img src="<c:url value="resources/image/person.png"/>" alt="이미지 선택" id="preview" width="80px"
					onclick="document.getElementById('file').click();">
					<input type="file" name="file" id="file" accept="image/*" onchange="readURL(this);"
					style="display:none">
				</div>
			</div>
			<br>
			<div>
				<input type="submit" value="회원가입"/>
			</div>
		</div>
	</form>
</body>
</html>