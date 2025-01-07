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
	<form enctype="multipart/form-data">
		<div>${title } ${period }교시</div>
		
		
		가답안 등록하기
		가답안 있으면 보여주기
		
		<label for="upfile">첨부파일</label>
		<input type="file" name="upfile">
		
		</form>
		
		
		
	</form>
</body>
</html>