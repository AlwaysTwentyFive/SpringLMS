<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/home.css">
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript">
		   function showClock() {
		      var currentDate = new Date();
		      var divClock = document.getElementById("divClock");
		      var apm = currentDate.getHours();
		
		      var msg = "현재시간 : " + apm + ":";
		      msg += currentDate.getMinutes() + ":";
		      msg += currentDate.getSeconds();
		
		      divClock.innerText = msg;
		
		      setTimeout(showClock, 1000);
		   }
		</script>
		<style>
			#topHeader {
				margin-top: 10px;
				background-color: #34495e;
				height: 55px;
			}
			* {
				padding: 0px;
				margin: 0px;
				/*border: 1px solid black;*/
			}
			.wrapper {
			    width: 1400px;
			    margin: 0px auto;
			}
			a {
				color: black;
			}
		</style>
	</head>
	<body onload="showClock()">
		<div class="container-fluid">
			<div id="divHeaderWrapper">
				<header style="height:155px;">
					<div id="topHeader">
						<div class="d-flex justify-content-end">
							<img class="mb-1 p-2" src="images/userProfile.png" width="60">
							<div class="mr-5 pt-3 pl-0" id="studentForm" style="color: white;">신문영 학생</div>
						</div>
					</div>
					<div id="bottomHeader" class="d-flex justify-content-center">
						<img class="mt-3" src="images/logo.png" width="125" height="105"/>
					</div>
					<hr color = "#34495e" size = "3"/>
				</header>
			</div>
			<br/><br/><br/>
			<div class="container row wrapper" id="totalWrapper">
				<div id="sidebar" class="col-sm-2 col-md-2 d-none d-xl-block">
					<div class="d-flex justify-content-center shadow rounded align-content-center flex-wrap" style="background-color: #c6d2df; height:48px;">
						<h5 class="text-left" style="text-align: center;">My page</h5>
					</div>
					<div class="d-flex flex-column mt-3">
						<div class="d-flex flex-column my-2">
							<a class="text-left" data-toggle="collapse" href=".collapseOne"
								style="font-size: large; color: black;">Lecture</a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseOne text-left">
									<a href="#" style="color: black;">Lecture Material</a>
								</div>
								<div class="collapse collapseOne text-left">
									<a href="#" style="color: black;">Report</a>
								</div>
							</div>
						</div>
				
						<div class="d-flex flex-column my-2">
							<a class="text-left " data-toggle="collapse" href=".collapseTwo"
								style="font-size: large; color: black;">Attendance</a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseTwo text-left">
									<a href="#" style="color: black;">My Attendance</a>
								</div>
								<div class="collapse collapseTwo text-left">
									<a href="#" style="color: black;">Attendance Exception</a>
								</div>
							</div>
						</div>
					</div>
				</div>				
				<div class="col-sm-10 col-md-10" id="contentWrapper">
				<!-- 카드 내용 -->
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
	                  <div class=" d-flex  justify-content-center">
	                     <div id="divClock"
	                        class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2 "
	                        style="width: 210px; text-align: center;"></div>
	                     <div class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2"
	                        style="width: 210px; text-align: center;">출근시간 : 8:47</div>
	                     <div class="clock w-btn-outline w-btn-yellow-outline mb-3 "
	                        style="width: 210px; text-align: center;">퇴근시간 : 18:20</div>
	                  </div>
	                  <div class="bttn">
	                     <button class="w-btn w-btn-attendance" type="button">출근하기</button>
	                  </div>
	               </div>
	               <!-- 카드 내용 -->				
				</div> <!-- divcontentWrapper -->
			</div> <!-- divtotalWrapper -->
		</div> <!-- divHeaderWrapper --> 
	</body>
</html>