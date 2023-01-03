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
				<div id="title" class="d-flex">
					<div class="col-8">Title</div>
					<div class="col-2">Writer</div>
					<div class="col-2">Date</div>
				</div>
				<hr/>
				<div id="content">
					과제 제출 하세요
					1월 10일까지
					pdf 파일로 제출하세요
				</div>
				<hr/>
				<div id="submit" class="d-flex flex-column">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td>제출 기간</td>
								<td>2023-01-10 23:59:59</td>
							</tr>
							<tr>
								<td>제출한 날짜</td>
								<td>미제출</td>
							</tr>
							<tr>
								<td>제출 과제</td>
								<td>미제출</td>
							</tr>
							<tr>
								<td>과제 점수</td>
								<td>미제출</td>
							</tr>
						</tbody>
					</table>
				</div>
				<hr/>
				<div id="replyList" class="d-flex flex-column">
					<div class="d-flex">
						<div class="col-2">ReplyWriter</div>
						<div class="col-10">ReplyContent</div>
					</div>
					<div class="d-flex justify-content-end">
						<input type="button" name="update" value="update" placeholder="수정" class="btn btn-sm btn-primary mx-2"/>
						<input type="button" name="delete" value="delete" placeholder="삭제" class="btn btn-sm btn-danger"/>
					</div>
				</div>
				<hr/>
				<div id="replyWrite">
					<div class="d-flex">
						<div class="col-2">ReplyWriter</div>
						<textarea class="col-9" rows="3" cols="150"></textarea>
					</div>
					<div class="d-flex justify-content-end">
						<input type="button" name="insert" value="insert" placeholder="쓰기" class="btn btn-sm btn-warning"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>