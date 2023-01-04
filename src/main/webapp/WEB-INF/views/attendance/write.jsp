<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<style>
#writeTitle, textarea {
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
  width:56px;
  height:35px;
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
	<h5>결석 사유 작성</h5>
	<div class="card-body shadow bg-white rounded">
    	<form action="#">
    		<div class="row">
	        	<label class="col-sm-2" for="title">제목</label>
	        	<div class="col-sm-4"></div>
	        	<label class="col-sm-2 ml-4" for="exceptionType" >신청 타입</label>
				<select class="col-sm-3 mb-1" name="exceptionType" id="exceptionType">
				    <option value="absent">공가</option>
				    <option value="earlyLive">조퇴</option>
				    <option value="outside">외출</option>
				</select>
	        
	        </div>
	        <input type="text" id="writeTitle" name="title" placeholder="title">
	
	        <label for="content">내용</label>
	        <textarea id="writeContent" name="content" placeholder="content" style="height:200px"></textarea>
			<div class="d-flex">
				<div>
					<label for="appointmentDate">신청날짜:</label>
					<input class="mb-1 ml-1" type="date" id="appointmentDate" name="appointmentDate">
				</div>
				<div class="d-flex ml-4">
					<label for="time">시간 선택: </label>
					<input type="time" id="time" name="time" class="form-control ml-3" style="width:150px; height:30px;">
				</div>
			</div>
			<div class="d-flex mt-2">
				<div class="d-flex flex-wrap align-content-center"><label for="file">첨부파일:</label></div>
				<input type="file" id="file" name="file" style="width:230px; margin-left: 10px;">
			</div><br><br>
			
			<div class="d-flex justify-content-center">
		        <a href="#" class="mr-3 btn" id="cancle">취소</a>
		        <input class="ml-3" id="register" type="submit" value="등록">
			</div>
    	</form>
    </div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
