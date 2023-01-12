<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<style>
	#writeContent, #writeTitle{
		width: 90%;
		border-radius: 4px;
  		box-sizing: border-box;
		
	}
	.bttn {
	   text-align: center;
	}
	
	.w-btn {
	   position: relative;
	   border: none;
	   display: inline-block;
	   padding: 15px 30px;
	   border-radius: 15px;
	   font-family: "paybooc-Light", sans-serif;
	
	   text-decoration: none;
	
	   transition: 0.25s;
	}
	
	.w-btn-attendance {
	   background-color: #34495e;
	   color: white;
	   display: inline-block;
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
	function confirmDelete(){
		var confirmflag = confirm("삭제 하시겠습니까?");
		if(confirmflag){
			form.action = "<c:url value='/board/delete'/>";
		}else{
			console.log("취소. 변화 없음");
		}
	}
</script>

<!-- content -->
<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-7">Title: ${board.boardTitle}</div>
		<div class="col-2">Writer: ${board.memberName}</div>
		<div class="col-3">Date: ${board.boardDate}</div>
	</div>
	<hr/>
	<div id="reportContent">
		${board.boardContent}
	</div>
	<hr/>
	<form method="post" name="form" enctype="multipart/form-data">
		<div id="reportSubmitInfo" class="d-flex flex-column">
			<table class="table table-bordered">
				<tbody>
						<tr>
							<td class="col-6">제출 기간</td>
							<td class="col-6">${board.reportDeadline} ${board.reportDeadlineTime}</td>
						</tr>
						<!-- 제출 했을 경우 -->
						<!-- For Student -->
					<c:if test="${reportBoard.memberId eq member.memberId}">
						<c:if test="${!empty reportBoard.submissionSubmitDate}">
							<tr>
								<td class="col-6">제출한 날짜</td>
								<td class="col-6">${reportBoard.submissionSubmitDate}</td>
							</tr>
								<tr>
								<td class="col-6">제목</td>
								<td class="col-6">${reportBoard.boardTitle}</td>
							</tr>	
							<tr>
								<td class="col-6">내용</td>
								<td class="col-6">${reportBoard.boardContent}</td>
							</tr>	
							<tr>
								<td class="col-6">제출 과제</td>
								<td id="attachment" class="d-flex flex-column">
									<c:if test="${!empty reportBoard.fileList}">
										<c:forEach var="file" items="${reportBoard.fileList}">
												<a href="<c:url value='/boardfile/${file.boardFileId}'/>">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
										</c:forEach>
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="col-6">과제 점수</td>
								<c:if test="${empty reportBoard.submissionScore}">
									<td class="col-6">채점중</td>
								</c:if>
								<c:if test="${!empty reportBoard.submissionScore}">
									<td class="col-6">${reportBoard.submissionScore}</td>
								</c:if>
							</tr>	
						</c:if>
					</c:if>
						<!-- 제출 안했을 경우 -->
						<c:if test="${empty reportBoard.submissionSubmitDate}">
							<tr>
								<td class="col-6">제출한 날짜</td>
								<td class="col-2">미제출</td>
							</tr>
							<tr>
								<td class="col-6">제목</td>
								<td class="col-6">
									<input id="writeTitle" type="text" name="boardTitle">
								</td>
							</tr>	
							<tr>
								<td class="col-6">내용</td>
								<td class="col-6">
									<textarea id="writeContent" name="boardContent"></textarea>
								</td>
							</tr>	
							<tr>
								<td class="col-6">제출 과제</td>
								<td class="col-6">
									<div class="form-group" id="file-list">
								        <a href="#this" onclick="addFile()">파일추가</a>
								        <div class="file-group">
								            <input type="file" name="files"><a href='#this' class='file-delete'>삭제</a>
								        </div>
								    </div>		
								</td>
							</tr>
							<tr>
								<td class="col-6">과제 점수</td>
								<td class="col-6">미제출</td>
							</tr>	
						</c:if>
				</tbody>
			</table>
			<input type="hidden" name="boardCategory" value="${board.boardCategory}">
			<input type="hidden" name="reportNoticeId" value="${board.boardId}">
			<input type="hidden" name="pageNo" value="${pageNo}">
			<input type="hidden" name="memberId" value="${member.memberId}">
			<input type="hidden" name="boardId" value="${reportBoard.boardId}">
			<div class="d-flex justify-content-end">
				<c:if test="${empty reportBoard.submissionSubmitDate}">
					<button name="insert" value="insert" class="btn btn-sm btn-warning mx-2" onclick="javascript: form.action='<c:url value="/board/report/submit"/>'">과제 등록</button>
				</c:if>
				<c:if test="${!empty reportBoard.submissionSubmitDate}">
					<button name="update" value="update" class="btn btn-sm btn-primary mx-2">과제 수정</button>
					<button name="delete" value="delete" class="btn btn-sm btn-danger" onclick="confirmDelete()">과제 삭제</button>
				</c:if>
			</div>
		</form>
   		<div class="bttn">
   			<button class="w-btn w-btn-attendance" type="button" onclick="location.href='<c:url value="/board/cat/2/${pageNo}"/>'" style="color: white;">목록</button>
		</div>
	</div>



<jsp:include page="/WEB-INF/views/include/footer.jsp"/>