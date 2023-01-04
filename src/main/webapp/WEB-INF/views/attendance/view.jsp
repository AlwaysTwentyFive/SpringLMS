<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="css/style.css">

<style>
#writeContainer {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

#box {
	width:100%;
	overflow:hidden;
	word-wrap:break-word;
}

.w-btn-yellow-outline {
	border: 3px solid #e0e0e0;
	color: #6e6e6e;
}

.w-btn-outline {
	position: relative;
	padding: 5px ;
	border-radius: 15px;
	font-family: "paybooc-Light", sans-serif;
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

.bttn {
	text-align: center;
}

.w-btn {
	position: relative;
	border: none;
	display: inline-block;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: "paybooc-Light", sans-serif;

	text-decoration: none;

	transition: 0.25s;
}

.w-btn-attendance {
	background-color: #34495e;
	color: white;
	display: inline-block;
}
</style>


<div class="container" id="writeContainer">
	<h5 class="ml-4">공가</h5>
	<div class="container">
		<div class="card-body shadow bg-white rounded">
			<div class="d-flex">
				<div class="pl-3 col-8"><h4>1월 2일 사유서 제출합니다</h4></div>
				<div class="col-2" style="color:grey">장 현</div>
				<div class="col-1" style="color:grey">2023.01.04</div>
			</div>
			<hr/>
	    	<div class="d-flex">
				<div class="col-2">첨부 파일:</div>
				<div class="col-10" style="color:grey">으에에에에에에에에에에에에에에에에에에에엥엑.ppt</div>
 			</div>
 			<br/><br/>
 			<div id="box">aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>	
 			<br/><br/>
 			<div class="d-flex justify-content-end">
 			 <img class="mt-1 mr-2" src="images/deny.png" height="34px"> <div class="w-btn-outline w-btn-yellow-outline mr-2"
	                        style="width: 150px; text-align: center;">거절</div>
			<button type="submit" name="update" value="update"  class="btn btn-sm btn-primary mx-2">승인</button>
			<button type="submit" name="delete" value="delete" class="btn btn-sm btn-danger">거절</button>
		</div>		
	    </div>
	</div>
</div>
<br/><br/>
 <div class="bttn">
   <button class="w-btn w-btn-attendance" type="button">목록으로</button>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
