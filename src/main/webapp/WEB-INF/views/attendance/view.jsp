<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<style>
#writeContainer {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

#box {
	width:100%;
	overflow:hidden;
	word-wrap:break-word;
}

.w-btn-yellow-outline {
	border: 3px solid #e0e0e0;
	color: #6e6e6e;
}

.w-btn-outline {
	position: relative;
	padding: 5px ;
	border-radius: 15px;
	font-family: "paybooc-Light", sans-serif;
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
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
<div class="container" id="writeContainer">
	<h2 class="ml-4"><span class="badge badge-primary">${attendanceException.attendanceExceptionStatus}</span></h2>
	<div class="container">
		<div class="card-body shadow bg-white rounded">
			<div class="d-flex">
				<div class="pl-3 col-8"><h4>${attendanceException.attendanceExceptionTitle}</h4></div>
				<div class="col-2" style="color:grey">${attendanceException.memberName}</div>
				<div class="col-2" style="color:grey">${attendanceException.attendanceExceptionDate}</div>
			</div>
			<hr/>
			<div class="d-flex flex-column">	
				<c:forEach var="attendanceExceptionFile" items="${attendanceExceptionFiles}">
				<div style="color:grey">
				<a href="<c:url value="/exceptionfile/${attendanceExceptionFile.attendanceExceptionFileId}"/>">${attendanceExceptionFile.attendanceExceptionFileName} (${attendanceExceptionFile.attendanceExceptionFileSize}KB)</a>
				</div>
				</c:forEach>
			</div>
			<br/><br/>
			<div id="box">${attendanceException.attendanceExceptionContent}</div>
			<hr/>
			<div>예외 인정 적용 될 날짜: <fmt:formatDate value="${attendanceException.attendanceExceptionApplyDate}" pattern="yyyy-MM-dd hh:mm:ss"/></div>
			<hr/>
			<div class="d-flex justify-content-end">
				<c:if test="${attendanceException.attendanceExceptionApproved != null}">
				<c:if test="${attendanceException.attendanceExceptionApproved == true}">
				<img class="mt-1 mr-2" src="<c:url value='/images/check.png'/>" height="34px"> 
				</c:if>
				<c:if test="${attendanceException.attendanceExceptionApproved == false}">
				<img class="mt-1 mr-2" src="<c:url value='/images/deny.png'/>" height="34px"> 
				</c:if>
				</c:if>
				<c:if test="${attendanceException.attendanceExceptionApproved == null}">
				<img class="mt-1 mr-2" src="<c:url value='/images/clock.png'/>" height="34px"> 
				</c:if>
				<div class="w-btn-outline w-btn-yellow-outline mr-2" style="width: 150px; text-align: center;">
					<c:if test="${attendanceException.attendanceExceptionApproved != null}">
						<c:if test="${attendanceException.attendanceExceptionApproved == true}">
						승인
						</c:if>
						<c:if test="${attendanceException.attendanceExceptionApproved == false}">
						반려
						</c:if>
					</c:if>
					<c:if test="${attendanceException.attendanceExceptionApproved == null}">
					결재 중
					</c:if>
				</div>
				<form action="<c:url value='/attendance/manage'/>" method="post">
				<c:if test="${attendanceException.attendanceId == null}">
				<input type="hidden" name="attendanceId" value="-1"/>		
				</c:if>
				<c:if test="${attendanceException.attendanceId != null}">
				<input type="hidden" name="attendanceId" value="${attendanceException.attendanceId}"/>		
				</c:if>
				<input type="hidden" name="memberId" value="${attendanceException.memberId}"/>
				<input type="hidden" name="attendanceExceptionStatus" value="${attendanceException.attendanceExceptionStatus}"/>
				<input type="hidden" name="attendanceExceptionId" value="${attendanceException.attendanceExceptionId}"/>
				<input type="hidden" name="attendanceExceptionDate" value="${attendanceException.attendanceExceptionDate}"/>
				<input type="hidden" name="attendanceExceptionApplyDate" value="${attendanceException.attendanceExceptionApplyDate}"/>
				<c:if test="${attendanceException.attendanceExceptionApproved == null}">
				<button type="submit" name="attendanceExceptionApproved" value="true"  class="btn btn-sm btn-primary mx-2">승인</button>
				<button type="submit" name="attendanceExceptionApproved" value="false" class="btn btn-sm btn-danger">거절</button>
				</c:if>
				<c:if test="${attendanceException.attendanceExceptionApproved != null}">
				<h4 class="ml-4"><span class="badge badge-warning">처리됨</span></h4>
				</c:if>
				</form>
			</div>
		</div>
	</div>
</div>
<br/><br/>
<div class="bttn">
<a href="<c:url value="/attendance/exceptionlist"/>" class="w-btn w-btn-attendance" type="button">목록으로</a>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
