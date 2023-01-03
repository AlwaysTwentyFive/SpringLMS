<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
		<style>
			#topHeader{
				margin-top: 10px;
				background-color: #34495e;
				height: 55px;
			}
			
		</style>
	</head>
	<body>
		<div class="container-fluid" id="divHeaderWrapper">
			<header style="height:155px;">
				<div id="topHeader">
					<div class="d-flex justify-content-end">
						<img class="mb-1 p-2" src="images/userProfile.png" width="60">
						<div class="mr-5 pt-3 pl-0" id="studentForm" style="color: white;">신문영 학생</div>
					</div>
				</div>
				<div id="bottomHeader" class="d-flex justify-content-center">
					<img class="mt-3" src="images/logo.png" width="125" height="95"/>
				</div>
				<hr color = "#34495e" size = "3"/>
			</header>
		</div> <!-- divHeaderWrapper -->
