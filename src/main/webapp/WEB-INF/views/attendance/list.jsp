<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
function dismiss() {

	$("#exampleModal").modal("hide");
}

function updateStatus(buttonId, attendanceId,date,id){
	let status = $("select[name=admin_select]").val();
	let button = "#" + buttonId;
	console.log(status);
	console.log(attendanceId);
	console.log(date);
	
	 $.ajax({
		url : "/myuniversity/attendance/update" ,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		type : "post",
		data : {
			status : status,
			attendanceId : attendanceId,
			date : date,
			memberId : id
		},
		success : function(result){
			var count = 0;
			switch($("#attendStatus").text()){
				case '출근':
					count = Number($("#attend").text()) - 1;
					$("#attend").text(count);
					break;
				
				case '지각':
					count = Number($("#late").text()) - 1;
					$("#late").text(count);
					break;
					
				case '조퇴':
					count = Number($("#leave").text()) - 1;
					$("#leave").text(count);
					break;
					
				case '병가':
					count = Number($("#sick").text()) - 1;
					$("#sick").text(count);
					break;
					
				case '외출':
					count = Number($("#goOut").text()) - 1;
					$("#goOut").text(count);
					break;
					
				case '공가':
					count = Number($("#vacation").text()) - 1;
					$("#vacation").text(count);
					break;
					
				case '결근':
					count = Number($("#absence").text()) - 1;
					$("#absence").text(count);
					break;
				}
			
			switch(result){
			case '출근':
				count = Number($("#attend").text()) + 1;
				$("#attend").text(count);
				break;
			
			case '지각':
				count = Number($("#late").text()) + 1;
				$("#late").text(count);
				break;
				
			case '조퇴':
				count = Number($("#leave").text()) + 1;
				$("#leave").text(count);
				break;
				
			case '병가':
				count = Number($("#sick").text()) + 1;
				$("#sick").text(count);
				break;
				
			case '외출':
				count = Number($("#goOut").text()) + 1;
				$("#goOut").text(count);
				break;
				
			case '공가':
				count = Number($("#vacation").text()) + 1;
				$("#vacation").text(count);
				break;
				
			case '결근':
				count = Number($("#absence").text()) + 1;
				$("#absence").text(count);
				break;
			}
			
			$("#attendStatus").empty();
			$("#attendStatus").text(result);
			console.log(result);		
			$(button).removeClass('btn-danger');
			$(button).removeClass('btn-success');
			$(button).removeClass('btn-warning');
			$(button).text(result);
			if(result == "출근"){
				$(button).addClass('btn-success');	
			} else if(result == "결근"){
				$(button).addClass('btn-danger');	
			} else {
				$(button).addClass('btn-warning');	
			}
			
		} 
		
		});  
}

function viewReason(i){
	
	$.ajax({
		url : "/myuniversity/attendance/checkException/" + i,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		type : "get",
		success : function(result){
			console.log(result);
			if(result !== "fail"){
				location.href="/myuniversity/attendance/readException/" + i;
			} 
		}
		});
}

function modalData(buttonId, attendanceId, memberType, attendanceDate, memberId) {
	console.log(attendanceDate);
	let date = "'"+attendanceDate + "'";
	let id = "'" + memberId + "'";
	let button = "#" + buttonId;

	console.log( $(button).text());
	let attendanceStatus = $(button).text();
	
	console.log("바뀐 status"+attendanceStatus);
	
	if(memberType == "admin"){
		console.log(memberType);
		let adminSelect = '<select name="admin_select" style="width: 150px; text-align: center;">'
		+'<option value="출근">출근</option>'
		+'<option value="결근">결근</option>'
		+'<option value="조퇴">조퇴</option>'
		+'<option value="외출">외출</option>'
		+'<option value="공가">공가</option>'
		+'<option value="병가">병가</option>'
		+'</select>'
		+'<button type="button" onclick="updateStatus('+ buttonId +','+ attendanceId+','+ date +', '+ id +')" class="btn btn-primary ml-3">수정</button>';

		
		$("#adminSelect").html(adminSelect);
	
	}

	
	if(attendanceStatus == "결근" ){
		$("#arriveTime").empty();
		$("#departTime").empty();
		$("#attendStatus").empty();
		$("#attendStatus").text("결근");
		$("#ExceptionFiles").empty();
		$("#exampleModal").modal("show");
		
	} else {
		
		$.ajax({
			url : "/myuniversity/attendance/view/"+ attendanceId,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type : "post",
			data : {
				
				attendanceStatus : attendanceStatus
			},
			
			success : function(attendance) {		
				
				$("#arriveTime").empty();
				$("#departTime").empty();
				$("#attendStatus").empty();
				$("#ExceptionFiles").empty();
				
				console.log('controller에 다녀온 attendance: '+attendance.attendanceStatus);
				
				
				if(attendance.attendanceStatus != "출근" && attendance.attendanceStatus != "결근" && attendance.attendanceStatus != "") {
					var str = "/myuniversity/attendance/readException/" + attendance.attendanceId;
					let param1 = '<button type="button" onclick="viewReason('+ attendance.attendanceId +')" class="btn btn-info">사유서 바로가기</button>';
					$("#ExceptionFiles").html(param1);	
					$("#attendStatus").text(attendance.attendanceStatus);
				} else if(attendance.attendanceStatus == "출근") {
					$("#attendStatus").text(attendance.attendanceStatus);
				} else {
					$("#attendStatus").text("결근");
				}
		
				$("#arriveTime").text(attendance.attendanceArriveTime);
				$("#departTime").text(attendance.attendanceDepartTime);
				
				
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
	<div >출근 : <span id="attend">${attCount}</span>회</div>
	<div >지각 : <span id="late">${lateCount}</span>회</div>
	<div>조퇴 : <span  id="leave">${leaveCount}</span>회</div>
	<div>병가 : <span  id="sick">${sickCount}</span>회</div>
	<div>외출 : <span  id="goOut">${goOutCount}</span>회</div>
	<div >공가 : <span id="vacation">${vacationCount}</span>회</div>
	<div >결근 : <span id="absence">${absCount}</span>회</div>
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
					<c:forEach var="attendance" items="${weekList}" varStatus="i">
						<td  id="bttn" class="col-2">
							<button id="${(status.count-1)*5 + i.count}" type="button" class="btn 
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
								onclick = "modalData(${(status.count-1)*5 + i.count}, ${attendance.attendanceId},'${sessionScope.member.memberType}', '${attendance.attendanceDate}', '${member.memberId}')" >
								${attendance.attendanceStatus}
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
						
						</table>
						<div style="text-align:center;" id="ExceptionFiles"></div>
					</div>

					<!-- 학생이랑 관리자가 보는 거 다름 -->
					<!-- 학생(수정불가) -->
					<div class="d-flex justify-content-center">
						<div id="attendStatus" class="w-btn-outline w-btn-yellow-outline mb-3"
							style="width: 150px; text-align: center;"></div>
					</div>
					<!-- 관리자는 수정 가능 -->
						<div id="adminSelect" class="d-flex justify-content-center">		
						</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						onclick="dismiss()">닫기</button>
					<div id="adminBttn">				
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
