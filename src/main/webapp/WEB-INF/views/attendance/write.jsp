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
<script>
   $(document).ready(function() {
       $(".file-delete").on("click", function(e) {
           e.preventDefault();
           deleteFile($(this));
       });
   })
    function addFile() {
        var str = "<div class='file-group'><input type='file' name='attendanceExceptionFiles'><a href='#this' name='file-delete'>삭제</a></div>";
        $("#file-list").append(str);
        $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
    }
 
    function deleteFile(obj) {
        obj.parent().remove();
    }
</script>
<div class="container" id="writeContainer">
	<h5>출결 사유서 작성</h5>
	<div class="card-body shadow bg-white rounded">
    	<form action="<c:url value='/attendance/write'/>" method="post" enctype="multipart/form-data">
    		<div class="row">
	        	<label class="col-sm-2" for="attendanceExceptionTitle">제목</label>
	        	<div class="col-sm-4"></div>
	        	<label class="col-sm-2 ml-4" for="attendanceExceptionStatus" >신청 타입</label>
				<select class="col-sm-3 mb-1" name="attendanceExceptionStatus" id="attendanceExceptionStatus" required>
				    <option value="공가" selected>공가</option>
				    <option value="조퇴">조퇴</option>
				    <option value="외출">외출</option>
				    <option value="병가">병가</option>
				</select>
	        
	        </div>
	        <input type="text" id="attendanceExceptionTitle" name="attendanceExceptionTitle" placeholder="title" required>
	
	        <label for="boardContent">내용</label>
	        <textarea id="attendanceExceptionContent" name="attendanceExceptionContent" placeholder="content" style="height:200px" required></textarea>
			<div class="d-flex">
				<div>
					<label for="date">신청날짜:</label>
					<input class="mb-1 ml-1" type="date" id="date" name="date" required>
				</div>
				<div class="d-flex ml-4">
					<label for="time">시간 선택: </label>
					<input type="time" id="time" name="time" class="form-control ml-3" style="width:150px; height:30px;" required>
				</div>
			</div>
			<div class="d-flex mt-2">
				<div class="d-flex flex-wrap align-content-center"><label for="file">첨부파일:</label></div>
				<div class="form-group" id="file-list">
                	<a href="#this" onclick="addFile()">파일추가</a>
                <div class="file-group">
                    <input type="file" name="attendanceExceptionFiles"><a href='#this' class='file-delete'>삭제</a>
                </div>
             </div>
			</div><br><br>
			
			<div class="d-flex justify-content-center">
		        <a href="#" class="mr-3 btn" id="cancle">취소</a>
		        <input class="ml-3" id="register" type="submit" value="등록">
			</div>
    	</form>
    </div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
