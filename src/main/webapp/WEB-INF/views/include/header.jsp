<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
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
	<body>
	<div class="container-fluid">
			<div id="divHeaderWrapper">
				<header style="height:230px;">
					<div id="topHeader">
							<div class="d-flex justify-content-end">
								<div class="mb-1 mr-3" style="height:30px;align-self:center">
									<a class="btn btn-info btn-sm" href="<c:url value="/member/logout"/>">로그아웃</a>
								</div>
								<img class="mb-1 p-2" src="<c:url value="/images/userProfile.png"/>" width="60">
								<div class="mr-5 pt-3 pl-0" id="studentForm" style="color: white;">${sessionScope.member.memberName}</div>
							</div>
						</div>
					<div id="bottomHeader" class="d-flex justify-content-center">
						<a href="<c:url value="/home"/>"><img class="mt-3" src="<c:url value="/images/logo.png"/>" width="230" height="200"/></a>
					</div>
				</header>
			<br/><br/>
			<hr color = "#34495e" size = "3"/>
			</div>
			<br/>
			<div class="container row wrapper" id="totalWrapper">
				<div id="sidebar" class="col-sm-2 col-md-2 d-none d-xl-block">
					<div class="d-flex justify-content-center shadow rounded align-content-center flex-wrap" style="background-color: #c6d2df; height:48px;">
						<h5 class="text-left" style="text-align: center;">My page</h5>
					</div>
				
					<div class="d-flex flex-column mt-3">
						<div class="d-flex flex-column my-2">
							<a class="text-left" data-toggle="collapse" href=".collapseZero"
								style="font-size: 23px;"><b>MEMBER</b></a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseZero text-left mb-2">
									<a href="<c:url value='/member/list'/>">학생 명단</a>
								</div>
								<c:if test="${sessionScope.member.memberType != 'admin'}">
								<div class="collapse collapseZero text-left">
									<a href="<c:url value='/member/update'/>">개인정보 수정</a>
								</div>
								</c:if>
							</div>
						</div>
						
				
						<div class="d-flex flex-column my-2">
							<a class="text-left" data-toggle="collapse" href=".collapseOne"
								style="font-size: 23px;"><b>LECTURE</b></a>
							<hr />
							<div class="d-flex flex-column">
								<div class="collapse collapseOne text-left mb-2">
									<a href="<c:url value='/board/cat/1'/>">강의 자료</a>
								</div>
								<div class="collapse collapseOne text-left">
									<a href="<c:url value='/board/cat/2'/>">과제</a>
								</div>
							</div>
						</div>
				
						<div class="d-flex flex-column my-2">
							<a class="text-left " data-toggle="collapse" href=".collapseTwo"
								style="font-size: 23px;"><b>ATTENDANCE</b></a>
							<hr />
							<div class="d-flex flex-column">
								<c:if test="${sessionScope.member.memberType != 'admin'}">
								<div class="collapse collapseTwo text-left mb-3">
									<a href="<c:url value='/attendance/totalList/${sessionScope.member.memberId}'/>">나의 출결 현황</a>
								</div>
								</c:if>
								<c:if test="${sessionScope.member.memberType != 'admin'}">
								<div class="collapse collapseTwo text-left mb-3">
									<a href="<c:url value='/attendance/write'/>">사유서 신청하기</a>
								</div>
								</c:if>
								<div class="collapse collapseTwo text-left mb-3">
									<a href="<c:url value='/attendance/list'/>">전체 출결 조회</a>
								</div>
								<div class="collapse collapseTwo text-left">
									<a href="<c:url value='/attendance/exceptionlist'/>">사유서 조회</a>
								</div>
							</div>
						</div>
					</div>
				</div>						
				<div class="col-sm-10 col-md-10" id="contentWrapper">
				
