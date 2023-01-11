<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">

	<!-- content -->
  	<form action="<>">
	<div class="d-flex mb-3">
		<div class="col-7">
			<h1>과제</h1>
		</div>
		<c:if test="${member.memberId == 'admin'}">
			<div class="d-flex align-items-end justify-content-end col-5">
				<button formaction="<c:url value='/board/2/write'/>" name="writeReport" value="writeReport" class="btn btn-sm btn-warning">과제 등록</button>
			</div>
		</c:if>
	</div>
  	</form>
	
	<div class="d-flex flex-column justify-content-center">
		<table class="table">
			<thead>
				<tr>
					<th class="col-5">과제</th>
					<th class="col-3">제출기한</th>
					<th class="col-2">제출</th>
					<th class="col-2">점수</th>
				</tr>
			</thead>
		</table>
		<table class="table table-bordered table-hover">
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td class="col-5">
							<a href="<c:url value='/board/${board.boardId}/${pager.pageNo}'/>">
								${board.boardTitle}
							</a>
						</td>
						<c:if test="${!empty board.reportDeadline} ">
							<td class="col-3">${board.reportDeadline}${board.reportDeadLineTime} 23:59:59</td>
						</c:if>
						<c:if test="${empty board.reportDeadline}">
							<td class="col-3"> - </td>
						</c:if>
						<c:if test="${!empty board.submissionSubmitDate}">
							<td class="col-2">${board.submissionSubmitDate}</td>
						</c:if>
						<c:if test="${empty board.submissionSubmitDate}">
							<td class="col-2">미제출</td>
						</c:if>
						<c:if test="${board.submissionScore != 0}">
							<td class="col-2">${board.submissionScore}점</td>
						</c:if>
						<c:if test="${empty board.submissionSubmitDate && board.submissionScore==0}">
							<td class="col-2">미확정</td>
						</c:if>
						<c:if test="${!empty board.submissionSubmitDate && board.submissionScore==0}">
							<td class="col-2">0점</td>
						</c:if>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
	
	<div class="pager d-flex justify-content-center my-3">
		<div class="flex-fulfill"></div>
		<div class="pagingButtonSet d-flex justify-content-center col-5">
			<c:if test="${pager.pageNo > 1}">
				<a href="<c:url value='/board/cat/1'/>" type="button" class="btn btn-muted shadow">처음으로</a>
			</c:if>
			
			<c:if test = "${pager.groupNo > 1}">
				<a href="<c:url value='/board/cat/1/${pager.startPageNo-1}'/>" type="button" class="btn btn-muted shadow">앞으로</a>
			</c:if>
			
			<c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}">
				<c:if test="${pager.pageNo != i}">
						<a href="<c:url value='/board/cat/1/${i}'/>" type="button" class="btn btn-white shadow">${i}</a>
				</c:if>
				<c:if test="${pager.pageNo == i}">
						<a href="<c:url value='/board/cat/1/${i}'/>" type="button" class="btn btn-dark shadow">${i}</a>
				</c:if>
			</c:forEach>
			
			<c:if test = "${pager.groupNo < pager.totalGroupNo }">
				<a href="<c:url value='/board/cat/1/${pager.endPageNo+1}'/>" type="button" class="btn btn-muted shadow">뒤로</a>
			</c:if>
			<a href="<c:url value='/board/cat/1/${pager.totalPageNo}'/>"type="button" class="btn btn-muted shadow">마지막으로</a>
		</div>
		<div class="flex-fulfill"></div>
	</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>