<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

	<style>
		#contentA{
			width: 1300px;
			height: auto;
			margin: auto;
			
			display: flex;
			justify-content: center;
			
		}
		
		
		#contentA-a{
			width: 180px;
			margin-top: 20px;
		}
		
		#contentA-b{
			width : 950px;
			margin-top: 20px;
		}
	</style>



</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<div id="contentA">
		<div id="contentA-a">
			<jsp:include page="../notice/leftbar-notice.jsp"></jsp:include>
		</div>
		<div id="contentA-b">
			<jsp:include page="../content_application/application_insert.jsp"></jsp:include>
		</div>
	</div>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>


</body>
</html>