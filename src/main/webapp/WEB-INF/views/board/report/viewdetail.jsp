<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">

<!-- content -->
<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-8">Title</div>
		<div class="col-2">Writer</div>
		<div class="col-2">Date</div>
	</div>
	<hr/>
	<div id="reportContent">
		과제 제출 하세요<br/>
		1월 10일까지<br/>
		pdf 파일로 제출하세요<br/>
	</div>
	<hr/>
	<div id="reportSubmitInfo" class="d-flex flex-column">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="col-6">제출 기간</td>
					<td class="col-6">2023-01-10 23:59:59</td>
				</tr>
				<tr>
					<td class="col-6">제출한 날짜</td>
					<td class="col-6">미제출</td>
				</tr>
				<tr>
					<td class="col-6">제출 과제</td>
					<td class="col-6">미제출</td>
				</tr>
				<tr>
					<td class="col-6">과제 점수</td>
					<td class="col-6">미제출</td>
				</tr>
			</tbody>
		</table>
	</div>
	<form action="#">
	<div class="d-flex justify-content-end">
		<button type="submit" name="update" value="update" class="btn btn-sm btn-primary mx-2">과제 수정</button>
		<button type="submit" name="delete" value="delete" class="btn btn-sm btn-danger">과제 삭제</button>
	</div>
	</form>
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
	<!-- For Admin -->
	<div id="reportBoardList">
		<div class="reportBoard mt-3">
		<form action="#">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>학생이름</th>
						<th>과제</th>
						<th>제출 날짜</th>
						<th>부여 점수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>신문영</td>
						<td><a href="#">report.pdf</a></td>
						<td>2023-01-03 21:23:51</td>
						<td>
							<input type="text" placeholder="0~100" style="width: 100%;"/>
						</td>
					</tr>
				</tbody>
			</table>
			<h5>Feedback</h5>
			<textarea name="reportFeedback" placeholder="피드백 작성란" class="col-12" rows="3" cols="150"></textarea>
			<div class="d-flex justify-content-end">
				<button type="submit" class="btn btn-sm btn-warning">과제 평가 완료하기</button>
			</div>
		</form>
		</div>
		<hr>
		<div class="reportBoard mt-3">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>학생이름</th>
						<th>과제</th>
						<th>제출 날짜</th>
						<th>부여 점수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>신문영</td>
						<td><a href="#">report.pdf</a></td>
						<td>2023-01-03 21:23:51</td>
						<td>100</td>
					</tr>
				</tbody>
			</table>
			<h5>Feedback</h5>
			<p id="reportBoardContent" class="border">
			~~학생의 과제는 ~~고 ~~해서 ~~점수를 주었습니다<br/>
			***교수
			</p>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>