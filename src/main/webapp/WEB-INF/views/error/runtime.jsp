<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
response.setStatus(200);
%>
<!DOCTYPE html>
<html>
	<body>
		<div class="container">
		<div class="content">
			<div class="jumbotron">
				<h2 style="color:red;"><fmt:message key="${message}"/></h2>
				<p>
				 
					Failed URL: ${url}
					Exception: ${exception.message}
					<c:forEach items="${exception.stackTrace}" var="ste">${ste}
					</c:forEach>
					
				
				</p>
				<p><a class="btn btn-primary btn-lg" href='<c:url value="/"/>' role="button"><fmt:message key="HOME"/></a></p>
			</div>
		</div>
		</div>
	</body>
</html>