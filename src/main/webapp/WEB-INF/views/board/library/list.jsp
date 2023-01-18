<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"/>
<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
  	<!-- content -->
  	<form action="<c:url value='/board/library/write/1'/>" method="GET">
	<div class="d-flex mb-3">
		<div class="col-7">
			<h1>강의자료실</h1>
		</div>
		<c:if test="${member.memberId == 'admin'}">
			<div class="d-flex align-items-end justify-content-end col-5">
				<button type="submit" formaction="<c:url value='/board/1/write'/>" class="btn btn-sm btn-warning">자료 등록</button>
			</div>
		</c:if>
	</div>
  	</form>
	
	<div class="d-flex flex-column justify-content-center">
		<table class="table">
			<thead>
				<tr>
					<th class="col-6">제목</th>
					<th class="col-3">날짜</th>
					<th class="col-3">글쓴이</th>
				</tr>
			</thead>
		</table>
		<table class="table table-hover">
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td class="col-6">
							<a href="<c:url value='/board/${board.boardId}/${pager.pageNo}'/>">
								<c:if test="${board.commentCount == 0}">
								${board.boardTitle}
								</c:if>
								<c:if test="${board.commentCount != 0}">
								${board.boardTitle} (${board.commentCount})
								</c:if>
							</a>
						</td>
						<td class="col-3">${board.boardDate}</td>
						<td class="col-3">${board.memberId}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${pager.totalRows > 0}">
	<div class="pager d-flex justify-content-center my-3">
		<div class="flex-fulfill"></div>
		<div class="pagingButtonSet d-flex justify-content-center col-5">
			<a href="<c:url value='/board/cat/1'/>" type="button" class="btn btn-muted shadow">처음으로</a>
			
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
	</c:if>
	<c:if test="${pager.totalRows == 0}">
	<div class="pager d-flex justify-content-center my-3">
	   	<div class="flex-fulfill"></div>
	   		<div>조회할 게시글이 없습니다</div>
		<div class="flex-fulfill"></div>
	</div>
	</c:if>
	
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	