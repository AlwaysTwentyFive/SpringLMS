<%@ page language="java" contentType="text/html; charset= UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="acomment" items="${commentList}" varStatus="status">
	<div class="d-flex">
		<div class="col-2">${acomment.memberName}</div>
		<div class="col-8">
			<div class="rContent" id="${status.count}">${acomment.commentContent}</div>
			<textarea id="updateArea${status.count}" name="commentContent" rows="3" cols="150" style="width:450px; display:none;" required></textarea>
		</div>
		<div class="col-2">
			<c:if test="${acomment.memberId eq sessionScope.member.memberId}">
				<div id="cover${status.count}">
					<a name="update" id="update${status.count}" onclick="tryUpdateReply(${status.count}, 'update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', 'updateArea${status.count}');">수정</a>
		 			<a name="confirm" id="confirm${status.count}" onclick="updateReply('update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', ${acomment.commentId}, 'updateArea${status.count}');" style="display: none;">확인</a> 
					<span>|</span>
					<a name="delete" id="delete${status.count}" onclick="deleteReply();">삭제</a>
		 			<a name="cancle" id="cancle${status.count}" onclick="cancleUpdateReply(${status.count}, 'update${status.count}', 'delete${status.count}', 'confirm${status.count}', 'cancle${status.count}', 'updateArea${status.count}');" style="display: none;">취소</a> 
				</div>
			</c:if>
		</div>
	</div>
	<hr/>
<input type="hidden" name="memberIdR" value="${sessionScope.member.memberId}">
<input type="hidden" name="commentIdR" value="${acomment.commentId}">
<input type="hidden" name="boardIdR" value="${acomment.boardId}">
</c:forEach>

