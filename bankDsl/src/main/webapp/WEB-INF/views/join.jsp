<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
<!-- 다음 지도 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- jquery의 ajax로 비동기 방식의 아이디 중복처리를 위해 연동   -->
<script src= "http://code.jquery.com/jquery-latest.min.js"></script> 

<script type="text/javascript">
 //script위치를 위에 두려면  window.onready 해줘야함
 	$(function() {  //window.onreay
 		$('#doubleId').click(function(e){
 			e.preventDefault(); // form의 action으로 넘어가는 기능을 막기위해 prevent처리 
 			$.ajax({ // 비동기방식으로 중복체크하기위해 사용, ajax가 가지고있는 기본 속성들이 있음(url,data,datatype 등)
 				url:'memberDoubleId', //controller 서블릿의 매핑된 주소
 				type:'post',
 				axync :true,  // 동기?비동기? 어떻게 처리할 지 정함 ,deault:true로 기본적으로 비동기로 처리함 
 				dataType:'text', // 응답받을 데이터 타입-jason,text,html,xml 등이 있다.
 				data:{id:$("#id").val()}, // id라는 key를 사용하여 데이터를 넘김 
 				success: function(result){ // 성공 시 처리할 구문
 					console.log(result);
 					if(result =="true") {
 						alert("사용중인 아이디 입니다.");
 					}else{
 						alert("사용가능한 아이디 입니다."); //사용가능하면 false
 					}
 				},
 				
 				error: function(err){
 					console.log(err);
 				}, /* complete 속성도 있다 : ajax 작업 완료 후 처리할 구문   */
 			})
 		})
 	})
 
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<form action="join" method="post">
		<div><h3 class="header">회원가입</h3></div>
		<div class="wrap">
			<div class="row">  
				<div class="title">아이디</div>
				<div class="input"><input type="text" name="id" id="id"/></div>
				<div class="input">&nbsp;<button id="doubleId">중복</button></div>
			</div>
			<div class="row">  
				<div class="title">이름</div>
				<div class="input"><input type="text" name="name"/></div>
			</div>
			<div class="row">  
				<div class="title">비밀번호</div>
				<div class="input"><input type="text" name="password"/></div>
			</div>
			<div class="row">  
				<div class="title">이메일</div>
				<div class="input"><input type="text" name="email"/></div>
			</div>
			<div class="row">  
				<div class="title">주소</div> 
				<div class="input"><input type="button"  onclick="daumPostcode()" value="주소찾기">
				<input type="text" name="address" id="address" placeholder="주소"/></div>
			</div>
			<div>
				<input type="submit" value="회원가입"/>
			</div>
		</div>
	</form>
	

<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.address; // 도로명 주소 변수
        


                // 우편번호와 주소 정보를 해당 필드에 넣는다.
               
                document.getElementById("address").value = roadAddr;
                document.getElementById("detailAddress").value = data.jibunAddress;
         
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
	
	
</body>
</html>