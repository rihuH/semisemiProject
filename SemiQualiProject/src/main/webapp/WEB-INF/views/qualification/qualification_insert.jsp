<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

자격증인서트

	<div>
		<div>
			<select id="type" onchange="formView();">
				<option value="pro">국가전문자격</option>
				<option value="tech">국가기술자격</option>
			</select>
			
			<div id="proDiv">
				<form action="/quali/qualification/insert_pro" method="post">
				<div>
				관련부처 <input name="relevantDepartment">
				</div>
				<div>
				분류 <input name="qualificationName">
				</div>
				<button type="submit">자격증 등록</button>
				</form>
			</div>
			<div id="techDiv" style="display:none;">
				<form action="/quali/qualification/insert_tech" method="post">
				<div>
					직무분야 <input name="fieldName">
				</div>
				<div>
					분류 <input name="categoryName">
				</div>
				<div>
					시행종목 <input name="qualificationName">
				</div>
				<button type="submit">자격증 등록</button>
				</form>
			</div>
		</div>
	</div>
	

<script>
	function formView(){
		let selectDivValue = document.getElementById('type').value;
		if(selectDivValue === 'pro'){
			$('#proDiv').show();
			$('#techDiv').hide();
		} else{
			$('#proDiv').hide();
			$('#techDiv').show();
		}
		
	}
</script>

</body>
</html>