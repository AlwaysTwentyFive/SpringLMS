<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">

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
input[type=text], textarea[type=reportReply]{
	width: 100%;
	border-radius: 4px;
  	border: 1px solid #ccc;
  	
}
</style>
<script>
	//게시글 삭제 확인 받기
	function confirmDelete(){
		var confirmflag = confirm("삭제 하시겠습니까?");
		if(confirmflag){
			form.action = "<c:url value='/board/delete'/>";
		}else{
			console.log("취소. 변화 없음");
		}
	}
	
	 function insertReply(){
		console.log("insertReply() 실행");
		
		var boardTitle = $("input[name=boardTitle]").val();
		var boardContent = $("textarea[name=boardContent]").val();
		var reportNoticeId = $("input[name=reportNoticeId]").val();
		var memberId =  $("input[name=memberId]").val();
		var boardCategory =  $("input[name=boardCategory]").val();
		
		console.log(boardTitle,boardContent,reportNoticeId,memberId);
		
		  $.ajax({
			url:"/myuniversity/board/reply",
			type:"POST",
			data : {
				boardTitle: boardTitle,
				boardContent: boardContent,
				reportNoticeId: reportNoticeId,
				memberId: memberId,
				boardCategory: boardCategory
			},
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			error : function() {
		            alert('통신실패!');
		         },

			success:function(result){
				if(result ==1)	{
					$("#replyList").append(result);
					location.reload();
				} else if(result == 0){
					alert("댓글 작성에 실패하였습니다");

				}
			}
			
		});   
		
	} 

</script>

<div class="d-flex flex-column justify-content-center">
	<div id="reportTitle" class="d-flex">
		<div class="col-8">${board.boardTitle}</div>
		<div class="col-2">${board.memberId}</div>
		<div class="col-2">${board.boardDate}</div>
	</div>
	<hr/>
	<div id="reportContent">
		${board.boardContent}<br/>
		${board.memberName}<br/>
	</div>
	<hr/>
	<div id="attachment" class="d-flex flex-column">
		<c:if test="${!empty board.fileList}">
			<c:forEach var="file" items="${board.fileList}">
					<a href="<c:url value='/boardfile/${file.boardFileId}'/>">${file.boardFileName}(<fmt:formatNumber>${file.boardFileSize}</fmt:formatNumber>byte)</a>
			</c:forEach>
		<hr/>
		</c:if>
	</div>
	<form name="form">
		<c:if test="${board.memberId eq member.memberId}">
			<div class="d-flex justify-content-end">
				<input type="hidden" name="boardCategory" value="${board.boardCategory}">
				<input type="hidden" name="boardId" value="${board.boardId}">
				<input type="hidden" name="memberId" value="${board.memberId}">
				<input type="hidden" name="pageNo" value="${pageNo}">
				<button name="update" class="btn btn-sm btn-primary mx-2" formaction='<c:url value="/board/update/${board.boardId}"/>'>수정</button>
				<button name="delete" class="btn btn-sm btn-danger" onclick="confirmDelete()">삭제</button>
			</div>
		</c:if>
	</form>
	<hr/>
	<div id="replyList" class="d-flex flex-column">
		<div class="d-flex">
			<div class="col-2">이름</div>
			<div class="col-8">ReplyContent</div>
			<div class="col-2">2021-01-03</div>
		</div>
		<hr/>
		<div class="d-flex">
			<div class="col-2">이름</div>
			<div class="col-10">
				<div class="d-flex">
					<div class="col-9">
						<textarea name="reportReply" rows="3" cols="150" style="width:500px;">댓글 작성자 = 세션id일 때 수정, 삭제 가능</textarea>
					</div>
					<div class="col-3">
						<button name="update" class="btn btn-sm btn-primary mx-2">수정</button>
						<button name="delete" class="btn btn-sm btn-danger" ">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr/>
	<div id="replyWrite" class="mb-5">
			<div class="d-flex">
				<div class="col-2">ReplyWriter</div>
				<textarea name="boardContent" rows="3" cols="150" ></textarea>
			</div>
			<div class="d-flex justify-content-end">
				<input type="hidden" name="boardTitle" value="${board.boardId}의 댓글">
				<input type="hidden" name="reportNoticeId" value="${board.boardId}">
				<input type="hidden" name="boardCategory" value="4">
				<input type="hidden" name="memberId" value="${sessionScope.member.memberId}">
				<button type="submit" name="insert" value="insert" class="btn btn-sm btn-warning mt-3" onclick="insertReply();">댓글 입력</button>
			</div>
	</div>
	<br/>
	<br/>
	<div class="bttn">
   		<button class="w-btn w-btn-attendance" type="button">
   			<a href='<c:url value="/board/cat/1/${pageNo}"/>'>목록으로</a>
   		</button>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>