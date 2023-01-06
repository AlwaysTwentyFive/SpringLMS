<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/style.css">

<style>

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

#cancle, #register {
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
#cancle{
  background-color: #E0E0E0;
}
#cancle:hover {
  background-color: #5D6770;
}
#register{
  background-color: #f1c40f;
}
#register:hover {
  background-color: #AE8B00;
}

#writeContainer {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>


<div class="container" id="writeContainer">
	<h5>자료실 작성하기</h5>
	<div class="container">
		<div class="card-body shadow bg-white rounded">
	    	<form action="#">
		        <label for="title">제목</label>
		        <input type="text" id="writeTitle" name="title" placeholder="title">
		
		        <label for="content">내용</label>
		        <textarea id="writeContent" name="content" placeholder="content" style="height:200px"></textarea>
				<label for="myfile">첨부 파일:</label>
 				<input class="btn btn-sm" type="file" id="myfile" name="myfile"><br><br>
				
				<div class="d-flex justify-content-center">
			        <a href="#" class="mr-3 btn btn-md" id="cancle">취소</a>
			        <input class="ml-3 btn btn-md" id="register" type="submit" value="등록">
				</div>
	    	</form>
	    </div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
