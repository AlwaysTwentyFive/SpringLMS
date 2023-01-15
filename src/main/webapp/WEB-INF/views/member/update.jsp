<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<!-- content -->
<div class="container">
	<div class="card mb-4 shadow bg-white rounded mx-auto mt-5" style="max-width: 540px;">
		<div class="row no-gutters">
			<div class="profile col-md-4">
				<div style="width: 150px" class=" mx-auto mt-4 mb-5">
					<img src="<c:url value="/images/profile.png"/>" class="card-img" width="100%">
				</div>
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">${member.memberName}</h5>
					<p class="email card-text">${member.memberEmail}</p>
					<p>${member.memberPhoneNumber}</p>
					<p class="card-text">
					<c:if test="${member.memberBirthday != null}">
					<small class="birth text-muted">${member.memberBirthday}</small>
					</c:if>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<hr/>
<div class="d-flex flex-column justify-content-start">
	<form action="<c:url value='/member/update'/>" method="post">
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">ID</div>
		<input type="text" name="memberId" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start mb-3">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Password</div>
		<input type="text" name="memberPassword" placeholder="" required>
	</div>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Password를 다시 입력</div>
		<input type="text" name="repassword" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Email</div>
		<input type="text" name="memberEmail" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Phone Number</div>
		<input type="text" name="memberPhoneNumber" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Birthday</div>
		<input type="date" name="memberBirthday" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Profile</div>
		<input type="file" name="file" required>
	</div>
	<hr/>
	<input type="hidden" name="memberName" value=""/>
	<input type="hidden" name="memberType" value=""/>
	<button type="submit" class="btn btn-lg btn-primary col-2">수정하기</button>
	</form>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>