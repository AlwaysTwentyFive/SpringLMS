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
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="wrapper row">
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp"></jsp:include>
		<div class="content col-sm-10 col-md-10">
			<!-- content -->
			<div class="d-flex flex-column justify-content-center">
				<table class="table">
					<thead>
						<tr class="d-flex justify-content-center">
							<th class="col-1">name</th>
							<th class="col-2">date</th>
							<th class="col-3">arrive time</th>
							<th class="col-3">dapart time</th>
							<th class="col-2">status</th>
						</tr>
					</thead>
				</table>
				<table class="table table-bordered table-hover">
					<tbody>
						<tr class="d-flex justify-content-center">
							<td class="col-1">장현</td>
							<td class="col-2">1월 3일</td>
							<td class="col-3">N/A</td>
							<td class="col-3">N/A</td>
							<td class="col-2">무단결석</td>
						</tr>
						<tr class="d-flex justify-content-center">
							<td class="col-1">이예승</td>
							<td class="col-2">1월 3일</td>
							<td class="col-3">9:00</td>
							<td class="col-3">9:01</td>
							<td class="col-2">도망</td>
						</tr>
						<tr class="d-flex justify-content-center">
							<td class="col-1">신문영</td>
							<td class="col-2">1월 3일</td>
							<td class="col-3">9:00</td>
							<td class="col-3">18:00</td>
							<td class="col-2">출석</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>