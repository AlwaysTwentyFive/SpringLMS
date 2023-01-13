<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
	width: 400px;
	padding: 8%  0;
	margin: auto;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}

.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #34495e;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

.form button:hover, .form button:active, .form button:focus {
	background: #f1c40f;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

.form .register-form {
	display: none;
}

.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

img {
	 opacity: 0.5;

}

body {
	background: #e0e0e0; /* fallback for old browsers */
	background: #c6d2df;
	background: linear-gradient(90deg, #e0e0e0 0%,
		#34495e 70%);
	font-family: "Roboto", sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}
</style>
</head>
<body>
	<div class="login-page d-flex flex-column">
		<div class="form">	
			<form action="<c:url value='/member/login'/>" method="post" class="login-form">
				<input name="memberId" type="text" placeholder="아이디를 입력해주세요" /> 
				<input name="password" type="password" placeholder="비밀번호를 입력해주세요" />
				<button type="submit">login</button>        
			</form>
		</div>
		<div style="text-align:center">
			<img src="<c:url value="/images/logo.png"/>" width="300px" class="mx-auto"/>
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
								<h3 class="modal-title">로그인에 실패하셨습니다</h3>
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
		
	</div>
</body>
</html>