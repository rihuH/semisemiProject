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
<style>
#leftbar{
width : 30%;
height : 100%; 
float : left;
}
#mainbar{
width : 50%;
height : 100%; 
float : left;
align : center;
margin-top : 100px;
}

</style>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div>
	<div id="leftbar">
		<jsp:include page="leftbar.jsp"></jsp:include>
	</div>
	<div id="mainbar">
		관리자페이지
		
		<a href="/quali/qualification/insert_form">자격증 인서트</a>
		<a href="/quali/qualification/qualiList">자격증 업데이트</a>
		<a href="/quali/qualification/select">자격증 조회하기</a>
		<br><br>
		
		<a href="/quali/taken_quali_exam/exam_insert_select">시행시험 인서트</a>
		<a href="/quali/taken_quali_exam/taken_quali_exam_list">시행시험 조회</a>
		
		<a href="/quali/taken_quali_exam/place_select_form">시험장소 등록</a> 
		
		<a href="/quali/provisional_answer/subject_list">가답안으로 만드는 보드보드</a>
	</div>
</div>
	
</body>
</html>