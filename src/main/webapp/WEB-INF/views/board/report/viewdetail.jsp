<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<style>
	#writeContent, #writeTitle{
		width: 90%;
		border-radius: 4px;
  		box-sizing: border-box;
		
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
<!-- content -->
<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-7">Title: ${board.boardTitle}</div>
		<div class="col-2">Writer: ${member.memberName}</div>
		<div class="col-3">Date: ${board.boardDate}</div>
	</div>
	<hr/>
	<div id="reportContent">
		${board.boardContent}
	</div>
	<hr/>
	<div id="reportSubmitInfo" class="d-flex flex-column">
		<table class="table table-bordered">
			<tbody>
				<!-- For Admin -->
				<c:if test="${member.memerId == 'admin'}">
					<tr>
						<td class="col-6">제출 기간</td>
						<td class="col-6">${board.reportDeadline} ${board.reportDeadLineTime}</td>
					</tr>
					<div id="reportBoardList">
						<div class="reportBoard mt-3">
						<form action="#">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>학생이름</th>
										<th>과제</th>
										<th>제출 날짜</th>
										<th>부여 점수</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>신문영</td>
										<td><a href="#">report.pdf</a></td>
										<td>2023-01-03 21:23:51</td>
										<td>
											<input type="text" placeholder="0~100" style="width: 100%;"/>
										</td>
									</tr>
								</tbody>
							</table>
							<h5>Feedback</h5>
							<textarea name="reportFeedback" placeholder="피드백 작성란" class="col-12" rows="3" cols="150"></textarea>
							<div class="d-flex justify-content-end">
								<button type="submit" class="btn btn-sm btn-warning">과제 평가 완료하기</button>
							</div>
						</form>
						</div>
						<hr>
						<div class="reportBoard mt-3">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>학생이름</th>
										<th>과제</th>
										<th>제출 날짜</th>
										<th>부여 점수</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>신문영</td>
										<td><a href="#">report.pdf</a></td>
										<td>2023-01-03 21:23:51</td>
										<td>100</td>
									</tr>
								</tbody>
							</table>
							<h5>Feedback</h5>
							<p id="reportBoardContent" class="border">
							~~학생의 과제는 ~~고 ~~해서 ~~점수를 주었습니다<br/>
							***교수
							</p>
						</div>
					</div>
				</c:if>
				<!-- For Admin -->
				<!-- For Student -->
				<c:if test="${member.memerId != 'admin'}">
				<form>
					<tr>
						<td class="col-6">제출 기간</td>
						<td class="col-6">${board.reportDeadline} ${board.reportDeadLineTime}</td>
					</tr>
					<!-- 제출 했을 경우 -->
					<c:if test="${!empty board.submissionSubmitDate}">
						<tr>
							<td class="col-6">제출한 날짜</td>
							<td class="col-6">${board.submissionSubmitDate}</td>
						</tr>
							<tr>
							<td class="col-6">제목</td>
							<td class="col-6">제목</td>
						</tr>	
						<tr>
							<td class="col-6">내용</td>
							<td class="col-6">내용</td>
						</tr>	
						<tr>
							<td class="col-6">제출 과제</td>
							<div id="attachment" class="d-flex flex-column">
								<c:if test="${!empty board.fileList}">
									<c:forEach var="file" items="${board.fileList}">
											<a href="<c:url value='/boardfile/${file.boardFileId}'/>">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
									</c:forEach>
								</c:if>
							</div>
						</tr>
						<tr>
							<td class="col-6">과제 점수</td>
							<td class="col-6">${board.submissionScore}</td>
						</tr>	
					</c:if>
					<!-- 제출 안했을 경우 -->
					<c:if test="${empty board.submissionSubmitDate}">
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
					<div class="d-flex justify-content-end">
						<c:if test="${empty board.submissionSubmitDate}">
							<button name="update" value="update" class="btn btn-sm btn-warning mx-2">과제 등록</button>
						</c:if>
						<c:if test="${!empty board.submissionSubmitDate}">
							<button name="update" value="update" class="btn btn-sm btn-primary mx-2">과제 수정</button>
							<button name="delete" value="delete" class="btn btn-sm btn-danger">과제 삭제</button>
						</c:if>
					</div>
				</form>
			</tbody>
		</table>
			</div>
			<hr/>
			<div id="replyList" class="d-flex flex-column">
				<div class="d-flex">
					<div class="col-2">ReplyWriter</div>
					<div class="col-10">ReplyContent</div>
				</div>
				<form action="#">
				<div class="d-flex justify-content-end">
					<button type="submit" name="update" value="update" class="btn btn-sm btn-primary mx-2">수정</button>
					<button type="submit" name="delete" value="delete" class="btn btn-sm btn-danger">삭제</button>
				</div>
				</form>
			</div>
			<hr/>
			<div id="replyWrite" class="mb-5">
				<form action="#">
				<div class="d-flex">
					<div class="col-2">ReplyWriter</div>
					<textarea name="reportReply" class="col-9" rows="3" cols="150"></textarea>
				</div>
				<div class="d-flex justify-content-end">
					<button type="submit" name="insert" value="insert" class="btn btn-sm btn-warning">쓰기</button>
				</div>
				</form>
			</div>
					
				</c:if>

</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>