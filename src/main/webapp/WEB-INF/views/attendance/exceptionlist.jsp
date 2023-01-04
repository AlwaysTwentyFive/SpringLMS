<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<div class="container">
  <h3>결석 신청서</h3>
  <br>
  <table class="table table-hover shadow">
    <thead style="background-color: #c6d2df;">
      <tr>
        <th>No. </th>
        <th style="text-align: center;">제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td style="text-align: center;">2022년 1월 10일 공가 신청합니다.
        	<img src="images/paperclip.png" width="18" class="mb-2"/>
       	</td>
        <td>강지성</td>
        <td>2022-01-04</td>
      </tr>
    </tbody>
  </table>
</div>

<!-- 페이징처리 -->
<%-- <div class="pager d-flex justify-content-center my-3">
   <div class="flex-fulfill"></div>
   <div class="pagingButtonSet d-flex justify-content-center col-5">
      <c:if test="${pager.pageNo > 1}"> 
         <a href="Admin?pageNo=1" type="button" class="btn btn-muted shadow">처음으로</a>
      </c:if>  
      
      <c:if test = "${pager.groupNo > 1}">
         <a href="Admin?pageNo=${pager.startPageNo-1}" type="button" class="btn btn-muted shadow">앞으로</a>
      </c:if> 
      
      <c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}">
         <c:if test="${pager.pageNo != i}">
            <a href="Admin?pageNo=${i}" type="button" class="btn btn-dark shadow">${i}</a>
         </c:if>
         <c:if test="${pager.pageNo == i}">
            <a href="Admin?pageNo=${i}" type="button" class="btn btn-white shadow">${i}</a>
         </c:if>
      </c:forEach>
      
      <c:if test = "${pager.groupNo < pager.totalGroupNo }">
         <a href="Admin?pageNo=${pager.endPageNo+1}" type="button" class="btn btn-muted shadow">뒤로</a>
      </c:if>
      <a href="Admin?pageNo=${pager.totalPageNo}"type="button" class="btn btn-muted shadow">마지막으로</a>
   </div>
   <div class="flex-fulfill"></div>
</div> --%>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
