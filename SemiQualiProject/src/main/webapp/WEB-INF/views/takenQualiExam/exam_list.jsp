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
현재 날짜 기준 시행되는 시험 조회 리스트

<table>
	<thead>
		<tr>
			<th>응시시험</th>
			<th>접수기간</th>
			<th>보기</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ proList }" var="c">
			<tr>
				<td>${c.examStartDate.substring(0, c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.profesionalQualification.qualificationName } 
				${ c.round }회  
					<c:choose>
						<c:when test="${ c.qualificationExam.qualificationRank eq 1}">
						1차 
						</c:when>
						<c:otherwise>
						2차
						</c:otherwise>
					</c:choose>
				</td>
				<td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
				<td></td>
			</tr>
		</c:forEach>
		<c:forEach items="${ techList }" var="c">
			<tr>
				<td>${c.examStartDate.substring(0,c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.technicalQualification.qualificationName }
				${ c.round }회
					<c:choose>
						<c:when test="${ c.qualificationExam.qualificationRank eq 1 }">
						필기
						</c:when>
						<c:otherwise>
						실기
						</c:otherwise>
					</c:choose>
				</td>
				<td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</div>
</body>
</html>