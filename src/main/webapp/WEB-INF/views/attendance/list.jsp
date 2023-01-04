<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<style>
.w-btn-yellow-outline {
	border: 3px solid #e0e0e0;
	color: #6e6e6e;
}

.w-btn-outline {
	position: relative;
	padding: 5px;
	border-radius: 15px;
	font-family: "paybooc-Light", sans-serif;
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}
</style>
<form action="#">
	<div class="d-flex mb-3">
		<div class="d-flex align-items-end col-3">
			<h5 id="member">신문영님의 출석 현황</h5>
		</div>

		<div class="d-flex align-items-center justify-content-around col-4">
			<div id="attend">출석 : 365회</div>
			<div id="late">지각 : 0회</div>
			<div id="absence">결근 : 0회</div>
		</div>

		<div class="d-flex align-items-end justify-content-around col-5">
			<div>
				<select id="year" name="year" class="custom-select">
					<option value="2023" selected>2023</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
				</select>
			</div>
			<h5>년</h5>
			<div>
				<select id="month" name="month" class="custom-select">
					<option value="1" selected>1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			<h5>월</h5>
			<div>
				<select id="day" name="day" class="custom-select">
					<option value="1" selected>1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			<h5>일</h5>
			<button type="submit" name="search" class="btn btn-primary">검색</button>
		</div>
	</div>
</form>
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
			<td class="col-2"><button type="button" class="btn btn-primary"
					data-toggle="modal" data-target="#exampleModal"
					data-whatever="@mdo">출석</button></td>
			<td class="col-2">결석</td>
			<td class="col-2">공가</td>
			<td class="col-2">병가</td>
			<td class="col-2">외출</td>
		</tr>
	</tbody>
</table>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">출결 현황</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form>
				<div class="modal-body">
					<div class="form-group">
						<table class="table table-bordered" style="text-align: center">
							<tr>
								<th>출근시간</th>
								<td>8:27</td>
							</tr>
							<tr>
								<th>퇴근시간</th>
								<td>18:27</td>
							</tr>
						</table>
					</div>

					<!-- 학생이랑 관리자가 보는 거 다름 -->
					<!-- 학생(수정불가) -->
					<div class="d-flex justify-content-center">
						<div class="w-btn-outline w-btn-yellow-outline mb-3"
							style="width: 150px; text-align: center;">출석</div>
					</div>
					<!-- 관리자는 수정 가능 -->
					<div class="d-flex justify-content-center">
						<select style="width: 150px; text-align: center;">
							<option value="attend">출석</option>
							<option value="absence">결석</option>
							<option value="late">지각</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary">수정</button>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
