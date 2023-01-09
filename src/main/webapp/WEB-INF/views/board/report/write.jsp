<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
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
	<h5>과제실 작성하기</h5>
	<input type="hidden" name="boardCategory" value="${categoryType}">
	<div class="container">
		<div class="card-body shadow bg-white rounded">
	    	<form action="<c:url value='/board/write/'/>" method="POST" enctype="multipart/form-data">
	        	<label for="boardTitle">제목</label>
		        <input type="text" id="writeTitle" name="boardTitle" placeholder="title">
		        <label for="boardContent">내용</label>
		        <textarea id="writeContent" name="boardContent" placeholder="content" style="height:200px"></textarea>
				<label for="fileList">첨부 파일:</label>
 				<input class="btn btn-sm fileList" type="file" name="fileList">
 				<c:if test="">
 					<input class-"">
 				</c:if>
 				<br><br>
 				<label for="reportDeadline">제출 기한:</label>
 				<input name="reportDeadline" type="date">
				<input name="reportDeadlineTime" type="time">
				<div class="d-flex justify-content-center">
			        <input class="ml-3 btn btn-md" id="register" type="reset" value="취소">
			        <input class="ml-3 btn btn-md" id="register" type="submit" value="등록">
				</div>
	    	</form>
	    </div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>