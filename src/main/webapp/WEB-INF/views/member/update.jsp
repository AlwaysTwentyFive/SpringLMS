<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<!-- content -->
<div class="container">
	<div class="card mb-4 shadow bg-white rounded mx-auto mt-2" style="max-width: 540px;">
		<div class="row no-gutters">
			<div class="profile col-md-4" style="background: #34495e;">
				<div style="width: 150px" class=" mx-auto mt-4 mb-5" >
				<img src="<c:url value="/images/profile.png"/>" class="card-img" width="100%">
				</div>
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">${sessionScope.member.memberName}</h5>
					<p class="email card-text">${sessionScope.member.memberEmail}</p>
					<p>${sessionScope.member.memberPhoneNumber}</p>
					<p class="card-text">
					<small class="birth text-muted">${sessionScope.member.memberBirthday}</small>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<br/>
<hr/>
<div class="d-flex flex-column justify-content-start">
	<form action="<c:url value='/member/update'/>" method="post">
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">ID를 입력해주세요</div>
		<input type="text" name="memberId" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start mb-3">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">바꾸려는 Password</div>
		<input type="password" name="memberPassword" placeholder="" required>
	</div>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Password를 다시 입력</div>
		<input type="password" name="repassword" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">바꾸려는 Email</div>
		<input type="text" name="memberEmail" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">바꾸려는 Phone Number</div>
		<input type="text" name="memberPhoneNumber" placeholder="" required>
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">바꾸려는 Birthday</div>
		<input type="date" name="memberBirthday" placeholder="" required>
	</div>
	<hr/>
	<input type="hidden" name="memberName" value=""/>
	<input type="hidden" name="memberType" value=""/>
	<div style="text-align:center;margin-top:25px;">
		<button type="submit" class="btn btn-lg btn-primary col-2 mb-5">수정하기</button>
	</div>
	</form>
</div>
<c:if test="${message != null}">
<script type="text/javascript">
   $( function(){ 
    	$('#errorModal').modal('show');
    } )
</script>
	<div>
	<div id="errorModal" class="modal">
		<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content bg-white">
					<div class="modal-header">
						<h3 class="modal-title">회원정보 업데이트 실패</h3>
					</div>
					<div class="modal-body">
						<h1>${message}</h1>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>