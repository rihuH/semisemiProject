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
진짜 자격증 인서트 페이지

<c:choose>
<c:when test="${ empty tech }">
	
	<form action="/quali/taken_quali_exam/pro_exam_insert" method="post">
		<table>
			<thead>
				<tr>
					<th></th>
					<th>관련부처</th>
					<th>분류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>국가전문자격</td>
					<td>${ pro.profesionalDept.relevantDepartment }</td>
					<td>${ pro.qualificationName }</td>
				</tr>
			</tbody>
		</table>
		
		<br>
		
		시험 입력<br>
		1차, 2차 여부 <select name="qualificationRank">
			<option value="1">1차</option>
			<option value="2">2차</option>
		</select>
		시험시작일<input type="date" name="examStartDate" placeholder="yyyyMMdd" required><br>
		시험종료일<input type="date" name="examFinalDate" placeholder="yyyyMMdd" required><br>
		시험접수시작일<input type="date" name="receptionStartDate" placeholder="yyyyMMdd"><br>
		시험접수종료일<input type="date" name="receptionEndDate" placeholder="yyyyMMdd"><br>
		의견제시시작일<input type="date" name="opinionStartDate" placeholder="yyyyMMdd"><br>
		의견제시종료일<input type="date" name="opinionEndDate" placeholder="yyyyMMdd"><br>
		<button type="submit">시험 등록</button><br>
		<input type="hidden" name="qualificationName" value="${ pro.qualificationName }">
		
		
	</form>
</c:when>
<c:otherwise>
	<div>
	<form action="/quali/taken_quali_exam/tech_exam_insert" method="post">
		<table>
			<thead>
				<tr>
					<th></th>
					<th>직무분야</th>
					<th>분류</th>
					<th>시행종목</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>국가기술자격</td>
					<td>${ tech.techCategory.technicalField.fieldName }</td>
					<td>${ tech.techCategory.categoryName }</td>
					<td>${ tech.qualificationName }</td>
				</tr>
			</tbody>
		</table>
		
		<br>
		
		시험 입력<br>
		1차, 2차 여부 <select name="qualificationRank">
			<option value="1">1차</option>
			<option value="2">2차</option>
		</select>
		시험시작일<input type="date" name="examStartDate" placeholder="yyyyMMdd" required><br>
		시험종료일<input type="date" name="examFinalDate" placeholder="yyyyMMdd" required><br>
		시험접수시작일<input type="date" name="receptionStartDate" placeholder="yyyyMMdd"><br>
		시험접수종료일<input type="date" name="receptionEndDate" placeholder="yyyyMMdd"><br>
		의견제시시작일<input type="date" name="opinionStartDate" placeholder="yyyyMMdd"><br>
		의견제시종료일<input type="date" name="opinionEndDate" placeholder="yyyyMMdd"><br>
		<button type="submit">시험 등록</button><br>
		<input type="hidden" name="qualificationName" value="${ tech.qualificationName }">
		
		
	</form>
	</div>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>