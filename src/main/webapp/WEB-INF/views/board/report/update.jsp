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
</script>

<div class="container" id="writeContainer">
	<h5>과제실 글 수정하기</h5>
	<div class="container">
		<div class="card-body shadow bg-white rounded">
	    	<form  method="POST" enctype="multipart/form-data" name="form">
		        <input type="text" name="boardTitle"  value="${board.boardTitle}"  placeholder="title" required>
		        <label for="boardContent">내용</label>
		        <textarea id="writeContent" name="boardContent" style="height:200px" required>${board.boardContent}</textarea>
				<!-- 파일 태그 -->
				<div class="form-group" id="file-list">
			        <a href="#this" onclick="addFile()">파일추가</a>
			        <div class="file-group">
			            <input type="file" name="files"><a href='#this' class='file-delete'>삭제</a>
			        </div>
			    </div>				
				<!-- 파일 태그 -->
 				<br>
				<input type="hidden" name="boardCategory" value="${categoryType}">
				<input type="hidden" name ="memberId" value="${member.memberId}">
				<input type="hidden" name ="boardId" value="${board.boardId}">
				<input type="hidden" name ="memberName" value="${member.memberName}">
				<input type="hidden" name="pageNo" value="${pageNo}">
 				<label for="reportDeadline">제출 기한:</label>
 				<input name="reportDeadline" type="date" value="${board.reportDeadline}" required>
				<input name="time"  type="time" value="${board.reportDeadlineTime}">
<!-- 				<input name="reportDeadlineTime"  type="time"> -->
				
				
				<div class="d-flex justify-content-center">
			        <input class="ml-3 btn btn-md " id="cancle" type="reset" value="취소">
			        <input class="ml-3 btn btn-md " id="register" type="submit" value="수정"  formaction='<c:url value="/board/report/update"/>'>
				</div>
	    	</form>
	    </div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>