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
<script>
	$(document).ready(function() {
	    $(".file-delete").on("click", function(e) {
	        e.preventDefault();
	        deleteFile($(this));
	    });
	    
	})

	 function addFile() {
        var str = "<div class='file-group'><input type='file' name='files'><a href='#this' name='file-delete'>삭제</a></div>";
        $("#file-list").append(str);
        $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
    }
 
    function deleteFile(obj) {
        obj.parent().remove();
    }
 /*    
    function checkFileSize(){
    	const maxSize = 10*1024*1024;
    	
    	for(let i = 0; i <= index; i++){
    		let fileSize = $("#fileTag" + i)[0].files[0].size;
    		console.log(file.size);
        	
        	if(fileSize > maxSize){
        		alert("10MB 이내로 등록 가능합니다.");
        		return false;
        	}  		
    	}
    } */
    
</script>

<div class="container" id="writeContainer">
	<h5>과제실 작성하기</h5>
	<div class="container">
		<div class="card-body shadow bg-white rounded">
	    	<form  method="POST" enctype="multipart/form-data" name="form">
				<input type="hidden" name="boardCategory" value="${categoryType}">
		        <input type="text" id="writeTitle" name="boardTitle" placeholder="title" required>
		        <label for="boardContent">내용</label>
		        <textarea id="writeContent" name="boardContent" placeholder="content" style="height:200px" required></textarea>
				<!-- 파일 태그 -->
				<div class="form-group" id="file-list">
			        <a href="#this" onclick="addFile()">파일추가</a>
			        <div class="file-group">
			            <input type="file" name="files"><a href='#this' class='file-delete'>삭제</a>
			        </div>
			    </div>				
				<!-- 파일 태그 -->
 				<br>
 				<input type="hidden" name ="memberId" value="${member.memberId}">
 				
 				<label for="reportDeadline">제출 기한:</label>
 				<input name="reportDeadline" type="date" required>
				<input name="time"  type="time">
<!-- 				<input name="reportDeadlineTime"  type="time"> -->
				
				
				<div class="d-flex justify-content-center">
			        <input class="ml-3 btn btn-md " id="cancle" type="reset" value="취소">
			        <input class="ml-3 btn btn-md " id="register" type="submit" value="등록"  formaction='<c:url value="/board/report/write"/>'>
				</div>
	    	</form>
	    </div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>