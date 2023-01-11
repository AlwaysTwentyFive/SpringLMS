<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>

function ModalData(attendanceId, attendanceStatus) {
	if(attendanceStatus == "결근"){
		$("#arriveTime").empty();
		$("#departTime").empty();
		$("#attendStatus").empty();
		$("#attendStatus").text(attendanceStatus);
		$("#ExceptionFiles").empty();
		$("#exampleModal").modal("show");
	} else {
		
		$.ajax({
			url : "/myuniversity/attendance/view/"+ attendanceId,
			type : "get",
			
			success : function(attendance) {
				$("#arriveTime").empty();
				$("#departTime").empty();
				$("#attendStatus").empty();
				$("#ExceptionFiles").empty();
				if(attendanceStatus != "출근") {
					$("#ExceptionFiles").text('끼룩끼룩');	
				}
		
				$("#arriveTime").text(attendance.attendanceArriveTime);
				$("#departTime").text(attendance.attendanceDepartTime);
				$("#attendStatus").text(attendance.attendanceStatus);
				
				$("#exampleModal").modal("show");

	
			}

		});
		
	}
	

	
	
}

</script>
<form action="#">
	<div class="d-flex mb-3">
		<div class="d-flex align-items-end col-4">
			<h4 id="member">${member.memberName}님의 출석 현황</h4>
		</div>
		<div class="col-3"></div>
	</div>
</form>
<div class="d-flex align-items-center justify-content-around">
			<div id="attend">출근 : ${attCount}회</div>
			<div id="late">지각 : ${lateCount}회</div>
			<div id="absence">조퇴 : ${leaveCount}회</div>
			<div id="absence">공가 : ${vacationCount}회</div>
			<div id="absence">결근 : ${absCount}회</div>
</div>
<br/>
<table class="table table-bordered table-hover bg-white">
	<thead>
		<tr style="text-align:center">
			<th class="col-2">주차</th>
			<th class="col-2">월요일</th>
			<th class="col-2">화요일</th>
			<th class="col-2">수요일</th>
			<th class="col-2">목요일</th>
			<th class="col-2">금요일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="weekList" items="${totalList}" varStatus="status">
			<tr style="text-align:center">
				
				<td class="col-2" >${status.count}주차</td>
					<c:forEach var="attendance" items="${weekList}">
						<td class="col-2">
							<button type="button" class="btn 
								<c:if test="${attendance.attendanceStatus  eq '결근'}">
									btn-danger
								</c:if>
								<c:if test="${attendance.attendanceStatus  eq '출근'}">
									btn-success
								</c:if>
								<c:if test="${!empty attendance.attendanceStatus and attendance.attendanceStatus  ne '출근' and attendance.attendanceStatus ne '결근'}">
									btn-warning
								</c:if>
								"
								onclick = "ModalData(${attendance.attendanceId}, '${attendance.attendanceStatus}')" >${attendance.attendanceStatus}
							</button>
						
						</td>
					</c:forEach>
				</tr>
		</c:forEach>
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
			<form >
				<div class="modal-body">
					<div class="form-group">
						<table class="table table-bordered" style="text-align: center">
							<tr>
								<th>출근시간</th>
								<td id="arriveTime"></td>
							</tr>
							<tr>
								<th>퇴근시간</th>
								<td id="departTime"></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td id="ExceptionFiles"></td>
							</tr>
						</table>
					</div>

					<!-- 학생이랑 관리자가 보는 거 다름 -->
					<!-- 학생(수정불가) -->
					<div class="d-flex justify-content-center">
						<div id="attendStatus" class="w-btn-outline w-btn-yellow-outline mb-3"
							style="width: 150px; text-align: center;"></div>
					</div>
					<!-- 관리자는 수정 가능 -->
					<c:if test="${member.memberType eq 'admin'}">
						<div class="d-flex justify-content-center">
							<select style="width: 150px; text-align: center;">
								<option value="attend">출석</option>
								<option value="absence">결석</option>
								<option value="late">지각</option>
							</select>
						</div>
					</c:if>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
					<c:if test="${member.memberType eq 'admin'}">
						<button type="button" class="btn btn-primary">수정</button>
					</c:if>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
