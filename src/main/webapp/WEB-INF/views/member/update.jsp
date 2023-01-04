<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<!-- content -->
<div class="container">
   <div class="card mb-4 shadow bg-white rounded mx-auto mt-5 "
      style="max-width: 540px;">
      <div class="row no-gutters">
         <div class="profile col-md-4">
            <div style="width: 150px" class=" mx-auto mt-4 mb-5">
               <img src="images/profile.png" class="card-img" width="100%">
            </div>
         </div>
         <div class="col-md-8">
            <div class="card-body">
               <h5 class="card-title">신문영</h5>
               <p class="email card-text">moon0129@gmail.com</p>
               <p>010-0000-1111</p>
               <p class="card-text">
                  <small class="birth text-muted">1997.1.29</small>
               </p>
            </div>
         </div>
      </div>
   </div>
</div>
<hr/>
<div class="d-flex flex-column justify-content-start">
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">ID</div>
		<input type="text" name="id" placeholder="">
	</div>
	<hr/>
	<div class="d-flex justify-content-start mb-3">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Password</div>
		<input type="text" name="password" placeholder="">
	</div>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Password를 다시 입력</div>
		<input type="text" name="repassword" placeholder="">
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Email</div>
		<input type="text" name="email" placeholder="">
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Phone Number</div>
		<input type="text" name="phoneNumber" placeholder="">
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Birthday</div>
		<input type="text" name="birthday" placeholder="">
	</div>
	<hr/>
	<div class="d-flex justify-content-start">
		<div class="badge badge-white col-2 mr-5" style="text-align: left;">Profile</div>
		<input type="file" name="birthday">
	</div>
	<hr/>
	<button type="submit" class="btn btn-lg btn-primary col-2">수정하기</button>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>