<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

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
							<td class="col-3">${board.reportDeadline}</td>
							<td class="col-2">${board.isSubmit}</td>
							<c:if test="${board.isSubmit eq '제출'||board.isSubmit eq '지각'}">
								<td class="col-2">${board.submissionScore}점</td>
							</c:if>
							<c:if test="${board.isSubmit eq '미제출'}">
								<td class="col-2">-</td>
							</c:if>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
	<c:if test="${pager.totalRows > 0}">
	<div class="pager d-flex justify-content-center my-3">
		<div class="flex-fulfill"></div>
		<div class="pagingButtonSet d-flex justify-content-center col-7">
			<a href="<c:url value='/board/cat/2'/>" type="button" class="btn btn-muted shadow">처음으로</a>
			
			<c:if test = "${pager.groupNo > 1}">
				<a href="<c:url value='/board/cat/2/${pager.startPageNo-1}'/>" type="button" class="btn btn-muted shadow">앞으로</a>
			</c:if>
			
			<c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}">
				<c:if test="${pager.pageNo != i}">
						<a href="<c:url value='/board/cat/2/${i}'/>" type="button" class="btn btn-white shadow">${i}</a>
				</c:if>
				<c:if test="${pager.pageNo == i}">
						<a href="<c:url value='/board/cat/2/${i}'/>" type="button" class="btn btn-dark shadow">${i}</a>
				</c:if>
			</c:forEach>
			
			<c:if test = "${pager.groupNo < pager.totalGroupNo }">
				<a href="<c:url value='/board/cat/2/${pager.endPageNo+1}'/>" type="button" class="btn btn-muted shadow">뒤로</a>
			</c:if>
			<a href="<c:url value='/board/cat/2/${pager.totalPageNo}'/>"type="button" class="btn btn-muted shadow">마지막으로</a>
		</div>
		<div class="flex-fulfill"></div>
	</div>
	</c:if>
	<c:if test="${pager.totalRows == 0}">
	<div class="pager d-flex justify-content-center my-3">
	   	<div class="flex-fulfill"></div>
	   		<div>조회할 게시글이 없습니다</div>
		<div class="flex-fulfill"></div>
	</div>
	</c:if>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>