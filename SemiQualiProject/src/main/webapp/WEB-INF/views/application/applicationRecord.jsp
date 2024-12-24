<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		#contentA{
			width: 1300px;
			height: auto;
			float: left;
			
			display: flex;
			justify-content: center;
			
		}
		
		
		#contentA-a{
			width: 180px;
		}
		
		#contentA-b{
			width : 950px;
		}
	</style>
</head>
<body>


	<jsp:include page="../common/header.jsp"></jsp:include>
	
	
	<div id="contentA">
		<div id="contentA-a">
			<jsp:include page="../common/leftbar.jsp"></jsp:include>
		</div>
		<div id="contentA-b">
			<jsp:include page="../content_right/application_record.jsp"></jsp:include>
		</div>
	</div>

	
	
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>