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

확인서발급신청

<form>
	<div>
		자격구분<select name="quali_type" id="quali_type" onchange="sortChange();">
			<option value="none">선택</option>
			<option value="pro">국가전문자격</option>
			<option value="tech">국가기술자격</option>
		</select>
	</div>
	<div>
		시험구분<select id="sort-select" name="sort-select">
			<option id="sort-select-select-option">선택</option>
			<option id="sort-select-option">시험검색이 되지 않았습니다</option>
		</select>
	</div>
	<div>
		확인서종류<select id="confirm-type" name="confirm-type">
			<option>선택</option>
			<option id="confirm-type-option">발급가능 확인서가 없습니다</option>
		</select>
	</div>

</form>

<!-- hide와 show -->
<script>
	function sortChange(){
	const qualiType = document.getElementById('quali_type').value;
	
	$.ajax({
		url : '/qualification/confirmQualiType',
		type : 'get',
		data : {
			typeValue : qualiType
		},
		success : {
			console.log('wk');
		}
	})
	
	}
</script>

</body>
</html>