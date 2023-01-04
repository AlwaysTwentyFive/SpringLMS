<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- content -->
<style>
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
<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-8">Title</div>
		<div class="col-2">Writer</div>
		<div class="col-2">Date</div>
	</div>
	<hr/>
	<div id="reportContent">
		1월 10일에 쓸 강의 자료입니다<br/>
		***교수님<br/>
	</div>
	<hr/>
	<div id="attachment" class="d-flex flex-column">
		<a href="#">강의자료1.pptx (1.5MB)</a>
		<a href="#">강의자료2.pptx (1.0MB)</a>
		<a href="#">강의자료3.pptx (2.5MB)</a>
	</div>
	<hr/>
	<hr/>
	<div id="replyList" class="d-flex flex-column">
		<div class="d-flex">
			<div class="col-2">ReplyWriter</div>
			<div class="col-10">ReplyContent</div>
		</div>
		<form action="#">
		<div class="d-flex justify-content-end">
			<button type="submit" name="update" value="update" class="btn btn-sm btn-primary mx-2">수정</button>
			<button type="submit" name="delete" value="delete" class="btn btn-sm btn-danger">삭제</button>
		</div>
		</form>
	</div>
	<hr/>
	<div id="replyWrite" class="mb-5">
		<form action="#">
		<div class="d-flex">
			<div class="col-2">ReplyWriter</div>
			<textarea name="reportReply" class="col-9" rows="3" cols="150"></textarea>
		</div>
		<div class="d-flex justify-content-end">
			<button type="submit" name="insert" value="insert" class="btn btn-sm btn-warning">쓰기</button>
		</div>
		</form>
	</div>
	<br/>
	<br/>
	<div class="bttn">
   		<button class="w-btn w-btn-attendance" type="button">목록으로</button>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>