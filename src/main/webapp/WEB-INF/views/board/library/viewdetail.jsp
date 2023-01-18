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
.rContent{
	word-wrap: break-word;
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
	//댓글 입력
	 function insertReply(){
		console.log("insertReply() 실행");
		
		var commentContent = $("#commentContent").val();
		var boardId = $("input[name=boardId]").val();
		var memberId =  $("input[name=memberId]").val();
		
		if(commentContent == ""){
			alert("내용을 입력해주세요.");
			return
		} 
		  $.ajax({
			url:"/myuniversity/board/reply",
			type:"POST",
			data : {
				memberId: memberId,
				commentContent: commentContent,
				boardId: boardId
			},
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			error : function() {
		            alert('통신실패!');

	         },
			success:function(result){
					$("#replyList").append(result);
					location.reload();
			}
			
		});   
	} 
	//댓글 수정
	function tryUpdateReply(commentContent, updatebtn, deletebtn, confirmbtn, canclebtn, updateArea){
		console.log("tryUpdateReply() 실행");
		let contentId = "#" + commentContent;
		let updatebtnId = "#" + updatebtn;
		let deletebtnId = "#" + deletebtn;
		let confirmbtnId = "#" + confirmbtn;
		let canclebtnId = "#" + canclebtn;
		let updateAreaId = "#" + updateArea;
		console.log($(contentId).text());
		
	
		$(updateAreaId).css('display','block');
		$(contentId).hide();
		
		$(updatebtnId).hide();
		$(deletebtnId).hide();
		$(confirmbtnId).show();
		$(canclebtnId).show();
	
	}
	// 수정 -> 확인 버튼 클릭시 데이터 전송
	function updateReply(updatebtn, deletebtn, confirmbtn, canclebtn, commentIdValue, updateArea){
		console.log("updateReply() 실행");
		let updatebtnId = "#" + updatebtn;
		let deletebtnId = "#" + deletebtn;
		let confirmbtnId = "#" + confirmbtn;
		let canclebtnId = "#" + canclebtn;
		let updateAreaId = "#" + updateArea;
		let commentId = commentIdValue;
		
		let boardId = ${board.boardId};
		let commentContent = $(updateAreaId).val();
		
		if(commentContent == ""){
			alert("내용을 입력해주세요.");
			return
		} 
		
		$.ajax({
			url:"/myuniversity/reply/update",
			type:"POST",
			data : {
				commentId: commentId,
				commentContent: commentContent,
				boardId: boardId
			},
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			error : function() {
		            alert('통신실패!');

	         },
			success:function(result){
					$("#replyList").html(result);
					$(updatebtnId).show();
					$(deletebtnId).show();
					$(confirmbtnId).hide();
					$(canclebtnId).hide();
			}
			
		});
		
		
		
	}
	function cancleUpdateReply(commentContent, updatebtn, deletebtn, confirmbtn, canclebtn, updateArea){
		console.log("cancleUpdateReply 실행");
		
		let contentId = "#" + commentContent;
		let updatebtnId = "#" + updatebtn;
		let deletebtnId = "#" + deletebtn;
		let confirmbtnId = "#" + confirmbtn;
		let canclebtnId = "#" + canclebtn;
		let updateAreaId = "#" + updateArea;

		$(updateAreaId).css('display','none');
		$(contentId).show();
		
		$(updatebtnId).show();
		$(deletebtnId).show();
		$(confirmbtnId).hide();
		$(canclebtnId).hide();
		
		let content =$(contentId).text();
		console.log(content);
	}
	
	
	//댓글 삭제
	function deleteReply(commentId, memberId, boardId){
		console.log("deleteReply() 실행");
		console.log(boardId);
		
		
		var confirmflag = confirm("삭제 하시겠습니까?");
		if(confirmflag){
			
			$.ajax({
				url:"/myuniversity/reply/delete",
				type:"GET",
				data:{
					commentId: commentId,
					memberId: memberId,
					boardId: boardId
				},
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				error : function() {
		            alert('통신실패!');
	
		         },
				success:function(result){
					$("#replyList").html(result);
				}
				
			});
			
			
		} else{
			console.log("실행 취소");
		}
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
	</div>
	<br>
	<br>
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
		<c:forEach var="acomment" items="${commentList}" varStatus="status">
			<div class="d-flex">
				<div class="col-2">${acomment.memberName}</div>
				<div class="col-8">
					<div class="rContent" id="${status.count}">${acomment.commentContent}</div>
					<textarea id="updateArea${status.count}" name="commentContent" rows="3" cols="150" style="width:450px; display:none;">${acomment.commentContent}</textarea>
				</div>
				<div class="col-2">
					<c:if test="${acomment.memberId eq sessionScope.member.memberId}">
						<div id="cover${status.count}">
							<a name="update" id="update${status.count}" onclick="tryUpdateReply(${status.count}, 'update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', 'updateArea${status.count}');">수정</a>
				 			<a name="confirm" id="confirm${status.count}" onclick="updateReply('update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', ${acomment.commentId}, 'updateArea${status.count}');" style="display: none;">확인</a> 
							<span>|</span>
							<a name="delete" id="delete${status.count}" onclick="deleteReply(${acomment.commentId}, '${acomment.memberId}', ${acomment.boardId});">삭제</a>
				 			<a name="cancle" id="cancle${status.count}" onclick="cancleUpdateReply(${status.count}, 'update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', 'updateArea${status.count}');" style="display: none;">취소</a> 
						</div>
					</c:if>
				</div>
			</div>
			<hr/>
		</c:forEach>
	</div>
	<br/>
	<br/>
	<div id="replyWrite" class="mb-5">
		<div class="d-flex">
			<div class="col-2">${sessionScope.member.memberName}</div>
			<textarea id="commentContent" rows="3" cols="150" ></textarea>
		</div>
		<div class="d-flex justify-content-end">
			<input type="hidden" name="boardId" value="${board.boardId}">
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