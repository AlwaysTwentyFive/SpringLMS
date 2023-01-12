<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/home.css"/>">
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
							<div class="mb-1 mr-3" style="height:30px;align-self:center">
								<a class="btn btn-info btn-sm" href="<c:url value="/member/logout"/>">로그아웃</a>
							</div>
							<img class="mb-1 p-2" src="<c:url value="/images/userProfile.png"/>" width="60">
							<div class="mr-5 pt-3 pl-0" id="studentForm" style="color: white;">${member.memberName}</div>
						</div>
					</div>
					<div id="bottomHeader" class="d-flex justify-content-center">
						<img class="mt-3" src="<c:url value="/images/logo.png"/>" width="125" height="105"/>
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
							<a class="text-left" data-toggle="collapse" href=".collapseZero"
								style="font-size: large;">Member</a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseZero text-left">
									<a href="<c:url value='/member/list'/>">Member List</a>
								</div>
								<div class="collapse collapseZero text-left">
									<a href="<c:url value='/member/update'/>">Update Info</a>
								</div>
							</div>
						</div>
						
				
						<div class="d-flex flex-column my-2">
							<a class="text-left" data-toggle="collapse" href=".collapseOne"
								style="font-size: large;">Lecture</a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseOne text-left">
									<a href="<c:url value='/board/cat/1'/>">Lecture Material</a>
								</div>
								<div class="collapse collapseOne text-left">
									<a href="<c:url value='/board/cat/2'/>">Report</a>
								</div>
							</div>
						</div>
				
						<div class="d-flex flex-column my-2">
							<a class="text-left " data-toggle="collapse" href=".collapseTwo"
								style="font-size: large;">Attendance</a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseTwo text-left">
									<a href="<c:url value='/attendance/totalList/${member.memberId}'/>">My Attendance</a>
								</div>
								<div class="collapse collapseTwo text-left">
									<a href="<c:url value='/attendance/write'/>">Apply Attendance Exception</a>
								</div>
								<div class="collapse collapseTwo text-left">
									<a href="<c:url value='/attendance/exceptionlist'/>">Attendance Exception List(Exclusive Admin)</a>
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
	                              <img src="<c:url value="/images/profile.png"/>" class="card-img" width="100%">
	                           </div>
	                        </div>
	                        <div class="col-md-8">
	                           <div class="card-body">
	                              <h5 class="card-title">${member.memberName}</h5>
	                              <p class="email card-text">${member.memberEmail}</p>
	                              <p>${member.memberPhoneNumber}</p>
	                              <p class="card-text">
	                                 <small class="birth text-muted">${member.memberBirthday}</small>
	                              </p>
	                           </div>
	                        </div>
	                     </div>
	                  </div>
	                  <div class=" d-flex  justify-content-center">
	                     <div id="divClock"
	                        class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2 "
	                        style="width: 210px; text-align: center;"></div>
	                        <!-- 만약에 출근을 했다면 (c:if) 출근시간 el로 뽑기 안했다면 -로 시간 나타냄-->
	                     <div class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2"
	                        style="width: 210px; text-align: center;">출근시간 : 
	                        <c:if test="${arriveTime != null}">
	                        <fmt:formatDate pattern="HH:mm:ss" value="${arriveTime}"/>
	                        </c:if>
	                        <c:if test="${arriveTime == null}">
	                        -
	                        </c:if>
	                        </div>
	                     <div class="clock w-btn-outline w-btn-yellow-outline mb-3 "
	                        style="width: 210px; text-align: center;">퇴근시간 : 
	                        <c:if test="${departTime != null}">
	                        <fmt:formatDate pattern="hh:mm:ss" value="${departTime}"/>
	                        </c:if>
	                        <c:if test="${departTime == null}">
	                        -
	                        </c:if>
	                        </div>
	                  </div>
	                
		                  <div class="bttn">
		                  	<!-- 출근을 안했다면 c:if~~ 했다면 퇴근하기 버튼 -->
		                  	<c:if test="${day != 1 and day != 7 and departTime == null}">
			                  	<c:if test="${isAttend == false}">
				                  	 <form class="bttn" action="<c:url value='/attendance/attend'/>" method="post">
				                    	<button class="w-btn w-btn-attendance" type="submit">출근하기</button>
				                     </form>
			                  	</c:if>
			                  	<c:if test="${isAttend == true}">
			                  	    <form class="bttn" action="<c:url value='/attendance/leave'/>" method="post">
			                    		<button class="w-btn w-btn-attendance" type="submit">퇴근하기</button>
			                    	</form>
			                  	</c:if>
		                  	</c:if>
		                  </div>
	                 
	               </div>
	               <!-- 카드 내용 -->				
				</div> <!-- divcontentWrapper -->
			</div> <!-- divtotalWrapper -->
		</div> <!-- divHeaderWrapper --> 
	</body>
</html>