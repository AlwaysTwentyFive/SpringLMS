<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class="container">
<h3>결석 신청서</h3>
<br>
	<table class="table table-hover shadow">
		<thead style="background-color: #c6d2df;">
			<tr>
				<th>No. </th>
				<th style="text-align: center;">제목</th>
				<th>작성자</th>
				<th>작성날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="attendanceException" items="${attendanceExceptionList}">
			<tr>
				<td>1</td>
				<td style="text-align: center;"><a href="<c:url value='/attendance/exception/${attendanceException.attendanceExceptionId}'/>">${attendanceException.attendanceExceptionTitle}</a><img src="images/paperclip.png" width="18" class="mb-2"/></td>
				<td>${attendanceException.memberName}</td>
				<td>${attendanceException.attendanceExceptionDate}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<c:if test="${pager.totalRows > 0}">
<div class="pager d-flex justify-content-center my-3">
	<div class="flex-fulfill"></div>
	<div class="pagingButtonSet d-flex justify-content-center col-5">
	<a href="<c:url value='/attendance/exceptionlist/1'/>" type="button" class="btn btn-muted shadow">처음으로</a>
	
	<c:if test = "${pager.groupNo > 1}">
	<a href="<c:url value='/attendance/exceptionlist/${pager.startPageNo-1}'/>" type="button" class="btn btn-muted shadow">앞으로</a>
	</c:if> 
	
	<c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}">
	<c:if test="${pager.pageNo != i}">
	<a href="<c:url value='/attendance/exceptionlist/${i}'/>" type="button" class="btn btn-white shadow">${i}</a>
	</c:if>
	<c:if test="${pager.pageNo == i}">
	<a href="<c:url value='/attendance/exceptionlist/${i}'/>" type="button" class="btn btn-dark shadow">${i}</a>
	</c:if>
	</c:forEach>
	
	<c:if test = "${pager.groupNo < pager.totalGroupNo }">
	<a href="<c:url value='/attendance/exceptionlist/${pager.endPageNo+1}'/>" type="button" class="btn btn-muted shadow">뒤로</a>
	</c:if>
	<a href="<c:url value='/attendance/exceptionlist/${pager.totalPageNo}'/>"type="button" class="btn btn-muted shadow">마지막으로</a>
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
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
