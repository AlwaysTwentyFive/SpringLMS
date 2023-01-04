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
		<!-- content -->
		<div class="d-flex flex-column justify-content-center">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="col-1">name</th>
						<th class="col-2">date</th>
						<th class="col-3">arrive time</th>
						<th class="col-3">dapart time</th>
						<th class="col-2">status</th>
					</tr>
				</thead>
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
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>