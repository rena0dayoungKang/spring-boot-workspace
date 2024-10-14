<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 등록</title>
<style type="text/css">
h2, #commandCel {
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
</head>
<body>
   <h2>게시판 글 등록</h2>
   <form action="boardWrite" method="post" enctype="multipart/form-data"> <!--enctype="multipart/form-data": form으로 사진데이터를 제출하기 위해 꼭 지정해줘야함  -->
      <table>
         <tr>
            <td class="td_left"><label for="writer">글쓴이</label></td>
            <td class="td_right"><input type="text" name="writer"
               id="writer"></td>
         </tr>
         <tr>
            <td class="td_left"><label for="subject">제목</label></td>
            <td class="td_right"><input type="text" name="subject"
               id="subject" required="required"></td>
            <!-- "required" : 입력하지 않으면 submit이 안된다 -->
         </tr>
         <tr>
            <td class="td_left"><label for="content">내용</label></td>
            <td class="td_right"><textarea id="content" name="content"
                  cols="40" rows="15"></textarea></td>
         </tr>
         <tr>
            <td class="td_left"><label for="file">이미지 파일 첨부</label></td>
            <td class="td_right"><input type="file" name="file" id="file"
               accept="image/*"></td>
         </tr>
      </table>
      <div id="commandCel">
         <input type="submit" value="등록">&nbsp;&nbsp; <input
            type="reset" value="다시쓰기">
      </div>
   </form>
</body>
</html>