<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">

<!-- content -->
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
</style>
<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-8">${board.boardTitle}</div>
		<div class="col-2">${board.memberId}</div>
		<div class="col-2">${board.boardDate}</div>
	</div>
	<hr/>
	<div id="reportContent">
		${board.boardContent}<br/>
		${board.memberName}<br/>
	</div>
	<hr/>
	<div id="attachment" class="d-flex flex-column">
		<c:if test="${!empty board.fileList}">
			<c:forEach var="file" items="${board.fileList}">
					<a href="<c:url value='/boardfile/${file.boardFileId}'/>">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
			</c:forEach>
		<hr/>
		</c:if>
	</div>
	<hr/>
	<div id="replyList" class="d-flex flex-column">
		<div class="d-flex">
			<div class="col-2">ReplyWriter</div>
			<div class="col-10">ReplyContent</div>
		</div>
		<c:if test="${board.memberId eq member.memberId}">
			<form>
			<input type="hidden" name="pageNo" value="${pageNo}">
			<div class="d-flex justify-content-end">
				<button name="boardId" class="btn btn-sm btn-primary mx-2" onclick="location.href='<c:url value="/board/library/update/${board.boardId}"/>'">수정</button>
				<button name="delete" class="btn btn-sm btn-danger" onclick="location.href='<c:url value="/board/library/delete"/>'">삭제</button>
			</div>
			</form>
		</c:if>
	</div>
	<hr/>
	<div id="replyWrite" class="mb-5">
		<form action="#">
			<div class="d-flex">
				<div class="col-2">ReplyWriter</div>
				<textarea name="reportReply" class="col-9" rows="3" cols="150"></textarea>
			</div>
			<div class="d-flex justify-content-end">
				<button type="submit" name="insert" value="insert" class="btn btn-sm btn-warning mt-3">댓글 입력</button>
			</div>
		</form>
	</div>
	<br/>
	<br/>
	<div class="bttn">
   		<button class="w-btn w-btn-attendance" type="button">
   			<a href='<c:url value="/board/cat/1/${pageNo}"/>'>목록으로</a>
   		</button>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>