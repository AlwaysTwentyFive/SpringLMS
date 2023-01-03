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
	color: #3bc9db;

}

.birth {
	color: #E6E6E6;
}
  
.w-btn {
    position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none; 
    font-weight: 600;
    transition: 0.25s;
}

.w-btn-attendance {
    background-color: #f1c40f;
    color: #34495e;
    display :inline-block;
}

.w-btn:active {
    transform: scale(1.5);
}

.bttn{
text-align: center;

}

.w-btn-yellow-outline {
    border: 3px solid #e0e0e0;
    color: #6e6e6e;
}

.w-btn-outline {
    position: relative;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
  
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

</style>

<script type="text/javascript">
    function showClock()
    {
        var currentDate=new Date();
        var divClock=document.getElementById("divClock");
        var apm=currentDate.getHours();
        
        var msg = "현재시간 : "+apm +":";
        msg += currentDate.getMinutes() + ":";
        msg += currentDate.getSeconds();
        
        divClock.innerText=msg;
        
        setTimeout(showClock,1000);
    }
</script>


</head>
<body onload="showClock()">


	<div class="container">
		<div class="card mb-4 shadow bg-white rounded mx-auto" style="max-width: 540px;">
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
						<p>010-0000-1111</p>
						<p class="card-text">
							<small class="birth text-muted">1997.1.29</small>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class=" d-flex  justify-content-center">
			<div id="divClock" class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2 " style="width:210px;text-align: center;">
			</div>
			<div class="clock w-btn-outline w-btn-yellow-outline mb-3 mr-2" style="width:210px;text-align: center;">
			 출근시간 : 8:47
			</div>
			<div class="clock w-btn-outline w-btn-yellow-outline mb-3 " style="width:210px;text-align: center;">
			퇴근시간 : 18:20
			</div>
		</div>
		<div class="bttn">
			 <button class="w-btn w-btn-attendance" type="button">
		        출근하기
		    </button>
	    </div>
  </div>
</body>
</html>