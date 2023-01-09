<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<style>
tr, th, td {
	text-align: center;
}

</style>

<div class="container">
	<h3 style="text-align:center;">전체 출결 목록</h3>
	<br>
	<table class="table">
		<thead>
			<tr>
				<th>No. </th>
				<th>이름</th>
				<th>날짜</th>
				<th>출근 시간</th>
				<th>퇴근 시간</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="attendance" items="${attendanceList}" varStatus="i">
			<tr>
				 <td>${i.count + (pager.pageNo - 1) * 10}</td>
				 <td>${attendance.memberName}</td>
				 <td><fmt:formatDate pattern="yyyy-MM-dd" value="${attendance.attendanceDate}"/></td>
				 <td><fmt:formatDate pattern="hh:mm:ss" value="${attendance.attendanceArriveTime}"/></td>
				 <td><fmt:formatDate pattern="hh:mm:ss" value="${attendance.attendanceDepartTime}"/></td>
				 <td>${attendance.attendanceStatus}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="pager d-flex justify-content-center my-3">
   	<div class="flex-fulfill"></div>
   		<div class="pagingButtonSet d-flex justify-content-center col-5">
	        <a href="<c:url value='/member/list/1'/>" type="button" class="btn btn-muted shadow">처음으로</a>
	        
	      	<c:if test="${pager.groupNo > 1}">
	        <a href="<c:url value='/member/list/${pager.startPageNo - 1}'/>" type="button" class="btn btn-muted shadow">앞으로</a>
	      	</c:if>
	      	
	      	<c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}">
	        <c:if test="${pager.pageNo != i}">
	        <a href="<c:url value='/member/list/${i}'/>" type="button" class="btn btn-white shadow">${i}</a>
	        </c:if>
	        <c:if test="${pager.pageNo == i}">
	        <a href="<c:url value='/member/list/${i}'/>" type="button" class="btn btn-dark shadow">${i}</a>
	        </c:if>
	      	</c:forEach>
	      	
	      	<c:if test="${pager.groupNo < pager.totalGroupNo }">
	        <a href="<c:url value='/member/list/${pager.endPageNo + 1}'/>" type="button" class="btn btn-muted shadow">뒤로</a>
	      	</c:if>
	      	
	   		<a href="<c:url value='/member/list/${pager.totalPageNo}'/>"type="button" class="btn btn-muted shadow">마지막으로</a>
		</div>
	<div class="flex-fulfill"></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
