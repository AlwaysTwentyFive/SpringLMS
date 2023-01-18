<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<style>
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
	.submitFile{
		width: 300px;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.stname{
	width:90px;
	}
	.stcontent{
	width:285ps;
	text-align: center;
	text-overflow: ellipsis;
	white-space: nowrap;	
	}
	.stinsert{
	width: 217px;
	text-align: center;
	}
	.stname, .stcontent, .stdate, .stscore, .stinsert{
	 display: table-cell; vertical-align: middle;
	 text-align: center;
	 
	}
	td{
		text-align: center;
	}
</style>

<script>
	function confirmDelete(studentsBoard){
		console.log(studentsBoard);
		if(studentsBoard === 0){
			var confirmflag = confirm("삭제 하시겠습니까?");
			if(confirmflag){
				form.action = "<c:url value='/board/delete'/>";
			}else{
				console.log("취소. 변화 없음");
			}
		} else{
			alert("등록된 과제글이 존재합니다.");
		}
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
	<form name="form">
	<c:if test="${board.memberId eq member.memberId}">
		<div class="d-flex justify-content-end">
			<input type="hidden" name="boardCategory" value="${board.boardCategory}">
			<input type="hidden" name="boardId" value="${board.boardId}">
			<input type="hidden" name="memberId" value="${board.memberId}">
			<input type="hidden" name="pageNo" value="${pageNo}">
			<button name="update" class="btn btn-sm btn-primary mx-2" formaction='<c:url value="/board/update/${board.boardId}"/>'>수정</button>
			<button name="delete" class="btn btn-sm btn-danger" onclick="confirmDelete(${studentsBoard.size()})">삭제</button>
		</div>
	</c:if>
	</form>
	<hr/>
	<div id="reportSubmitInfo" class="d-flex flex-column">
		<!-- For Admin -->
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="col-6">제출 기간</td>
					<td class="col-6">${board.reportDeadline} ${board.reportDeadlineTime}</td>
				</tr>
				<tr>
					<td class="col-6">참고 파일</td>
					<td id="attachment" class="d-flex flex-column">
						<c:if test="${!empty board.fileList}">
						<c:forEach var="file" items="${board.fileList}">
						<a href="<c:url value='/boardfile/${file.boardFileId}'/>">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
						</c:forEach>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<h4>[미반영 학생 리스트]</h4>
		<table class="table table-bordered mt-3">
			<thead>
				<tr>
					<th class="stname" style="width:20%">학생이름</th>
					<th class="stcontent" style="width:20%">과제</th>
					<th class="stdate" style="width:20%">제출 날짜</th>
					<th class="stscore" style="width:20%">점수</th>
					<th class="stinsert" style="width:10%">입력</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty studentsBoard}">
				<tr>
					<th class="stname" style="width:20%">-</th>
					<th class="stcontent" style="width:20%">-</th>
					<th class="stdate" style="width:20%">-</th>
					<th class="stscore" style="width:20%">-</th>
					<th class="stinsert" style="width:10%">-</th>
				</tr>
				</c:if>
				<c:if test="${!empty studentsBoard}">
				<c:forEach var="student" items="${studentsBoard}" varStatus="i">
				<c:if test="${student.submissionScore == 0}">
				<tr>
					<td>${student.memberName}</td>
					<td class="submitFile">
					<c:if test="${!empty student.fileList}">
					<c:forEach var="file" items="${student.fileList}">
						<a href="<c:url value='/boardfile/${file.boardFileId}'/>" class="stcontent">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
					</c:forEach>
					</c:if>
					</td>
					<td>${student.submissionSubmitDate}</td>
					<td>
					<form id="submissionScore${i.count}" action="<c:url value='/board/update/${student.boardId}'/>" method="post">
					<input type="number" name="submissionScore">
					<input type="hidden" name="pageNo" value="${pageNo}">
					<input type="hidden" name="reportNoticeId" value="${board.boardId}">
					</form>
					</td>
					<td><button form="submissionScore${i.count}" type="submit" class="btn btn-sm btn-warning mt-2">입력</button></td>
				</tr>
				</c:if>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<hr>
		<h4>[평가 완료]</h4>
		<table class="table table-bordered mt-3">
			<thead>
				<tr>
					<th class="stname" style="width:20%">학생이름</th>
					<th class="stcontent" style="width:20%">과제</th>
					<th class="stdate" style="width:20%">제출 날짜</th>
					<th class="stscore" style="width:20%">점수</th>
					<th class="stinsert" style="width:10%">입력</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty studentsBoard}">
				<tr>
					<th class="stname" style="width:20%">-</th>
					<th class="stcontent" style="width:20%">-</th>
					<th class="stdate" style="width:20%">-</th>
					<th class="stscore" style="width:20%">-</th>
					<th class="stinsert" style="width:10%">-</th>
				</tr>
				</c:if>
				<c:if test="${!empty studentsBoard}">
				<c:forEach var="student" items="${studentsBoard}" varStatus="i">
				<c:if test="${student.submissionScore != 0}">
				<tr>
					<td>${student.memberName}</td>
					<td class="submitFile">
					<c:if test="${!empty student.fileList}">
					<c:forEach var="file" items="${student.fileList}">
						<a href="<c:url value='/boardfile/${file.boardFileId}'/>" class="stcontent">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
					</c:forEach>
					</c:if>
					</td>
					<td>${student.submissionSubmitDate}</td>
					<td>${student.submissionScore}</td>
					<td>
					<form id="submissionScore${i.count}" action="<c:url value='/board/update/${student.boardId}'/>" method="post">
					<input type="number" name="submissionScore">
					<input type="hidden" name="pageNo" value="${pageNo}">
					<input type="hidden" name="reportNoticeId" value="${board.boardId}">
					</form>
					</td>
					<td><button form="submissionScore${i.count}" type="submit" class="btn btn-sm btn-warning mt-2">입력</button></td>
				</tr>
				</c:if>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- For Admin -->
		<div class="bttn">
	   		<button class="w-btn w-btn-attendance" type="button" onclick="location.href='<c:url value="/board/cat/2/${pageNo}"/>'" style="color: white;">목록</button>
		</div>		
	</div>
</div>


<jsp:include page="/WEB-INF/views/include/footer.jsp"/>