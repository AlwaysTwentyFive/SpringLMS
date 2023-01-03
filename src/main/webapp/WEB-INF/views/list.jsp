<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
		<style>
			header{
				height:140px;
			}
			#topHeader{
				margin-top: 10px;
				background-color: #34495e;
				height: 47px;
			}
			#studentForm{
				color: white
				
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div id="divHeaderWrapper">
				<header>
					<div id="topHeader">
						<div class="d-flex justify-content-end">
							<div class="mr-2 p-2" id="profileImage">
								<img class="mr-2 p-2 rounded-circle" src="${pageContext.request.contextPath}/resources/images/userProfile.png" width="100%">
							</div>
							<div class="mr-5 p-2 pt-3" id="studentForm"> 신문영 학생</div>
						</div>
					</div>
					<div class="row">
						<div class="col-3">
							<div id="hereIsImage" class="bg-danger">이미지</div>
						</div>
						<div class="col-9 bg-warning" style="font-weight: bold;">마이페이지</div>
					</div>
				</header>
			</div> <!-- id=divHeaderWrapper -->
		</div>
		<div class="container-fluid"> <!-- 사이드바 및 내용 -->
			<div class="col-md-6"></div>
			<div class="col-md-6"></div>
		</div>
		
	</body>
</html>