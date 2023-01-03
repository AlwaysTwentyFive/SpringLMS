<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
	        width: 1200px;
	        margin: 0px auto;
	    }
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/sidebar.jsp"></jsp:include>
	<div class="wrapper d-flex flex-column justify-content-center">

		<div class="">
			<table class="table table-bordered table-hover bg-white">
				<thead>
					<tr>
						<th class="col-2">주차</th>
						<th class="col-2">월요일</th>
						<th class="col-2">화요일</th>
						<th class="col-2">수요일</th>
						<th class="col-2">목요일</th>
						<th class="col-2">금요일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="col-2">1주차</td>
						<td class="col-2">출석</td>
						<td class="col-2">결석</td>
						<td class="col-2">공가</td>
						<td class="col-2">병가</td>
						<td class="col-2">외출</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>