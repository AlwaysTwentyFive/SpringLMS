<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
<style>
.profile {
	background: #34495e;
}

.email {
	color: #6DB3FA;

}

.birth {
	color: #E6E6E6;
}
	
	
	
</style>
</head>
<body>
	<div class="card mb-3" style="max-width: 540px;">
		<div class="row no-gutters">
			<div class="profile col-md-4">
				<div style="width:150px" class=" mx-auto mt-4 mb-5">
					<img src="images/profile.png" class="card-img" width="100%"  >
				</div>
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">신문영</h5>
					<p class="email card-text">moon0129@gmail.com</p>
					<p class="card-text">
						<small class="birth text-muted">1997.1.29</small>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>