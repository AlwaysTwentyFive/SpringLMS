<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/style.css">

	<!-- content -->
  	<form action="#">
	<div class="d-flex mb-3">
		<div class="col-7"></div>
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
	    	<button type="submit" class="btn btn-primary">검색</button>
		</div>
	</div>
  	</form>
	
	<div class="d-flex flex-column justify-content-center">
		<table class="table">
			<thead>
				<tr>
					<th class="col-1">name</th>
					<th class="col-2">date</th>
					<th class="col-3">arrive time</th>
					<th class="col-3">dapart time</th>
					<th class="col-2">status</th>
				</tr>
			</thead>
		</table>
		<table class="table table-bordered table-hover">
			<tbody>
				<tr>
					<td class="col-1">장현</td>
					<td class="col-2">1월 3일</td>
					<td class="col-3">N/A</td>
					<td class="col-3">N/A</td>
					<td class="col-2">무단결석</td>
				</tr>
				<tr>
					<td class="col-1">이예승</td>
					<td class="col-2">1월 3일</td>
					<td class="col-3">9:00</td>
					<td class="col-3">9:01</td>
					<td class="col-2">도망</td>
				</tr>
				<tr>
					<td class="col-1">신문영</td>
					<td class="col-2">1월 3일</td>
					<td class="col-3">9:00</td>
					<td class="col-3">18:00</td>
					<td class="col-2">출석</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="pager d-flex justify-content-center my-3">
		<div class="flex-fulfill"></div>
		<div class="pagingButtonSet d-flex justify-content-center col-5">
			<!-- <c:if test="${pager.pageNo > 1}"> -->
				<a href="Admin?pageNo=1" type="button" class="btn btn-muted shadow">처음으로</a>
			<!-- </c:if> -->
			
			<!-- <c:if test = "${pager.groupNo > 1}"> -->
				<a href="Admin?pageNo=${pager.startPageNo-1}" type="button" class="btn btn-muted shadow">앞으로</a>
			<!-- </c:if> -->
			
			<!-- <c:forEach var="i" begin="${pager.startPageNo}" end ="${pager.endPageNo}"> -->
				<!-- <c:if test="${pager.pageNo != i}"> -->
					<a href="Admin?pageNo=${i}" type="button" class="btn btn-dark shadow">${i}</a>
				<!-- </c:if> -->
				<!-- <c:if test="${pager.pageNo == i}"> -->
					<!-- <a href="Admin?pageNo=${i}" type="button" class="btn btn-white shadow">${i}</a> -->
				<!-- </c:if> -->
			<!-- </c:forEach> -->
			
			<!-- <c:if test = "${pager.groupNo < pager.totalGroupNo }"> -->
				<a href="Admin?pageNo=${pager.endPageNo+1}" type="button" class="btn btn-muted shadow">뒤로</a>
			<!-- </c:if> -->
			<a href="Admin?pageNo=${pager.totalPageNo}"type="button" class="btn btn-muted shadow">마지막으로</a>
		</div>
		<div class="flex-fulfill"></div>
	</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
		