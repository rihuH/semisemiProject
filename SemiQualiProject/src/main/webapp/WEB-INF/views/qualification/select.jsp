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
	자격증 정보 상세조회
	
	<div>
	    <span onclick="typeSelect(this);">국가기술자격</span> 
	    <span onclick="typeSelect(this);">국가전문자격</span>
	</div>

<script>
    function typeSelect(e) {
    	const qualiType = e.textContent;
    	$.ajax({
    		url : "typeSelect.quali",
    		type : "get",
    		data : {
    			qualiType : qualiType
    		},
    		success : function(list){
    			console.log(list)
    		}
    	})
    }
</script>
</body>
</html>