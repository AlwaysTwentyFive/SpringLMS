<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
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
<div class="wrapper row">
	<jsp:include page="/WEB-INF/views/include/sidebar.jsp"></jsp:include>
	<div class="content col-sm-10 col-md-10">
		<div class="">
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
						<td class="col-2">출석</td>
						<td class="col-2">결석</td>
						<td class="col-2">공가</td>
						<td class="col-2">병가</td>
						<td class="col-2">외출</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>