<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>

<style>
	table {
		border-collapse: collapse;
		border:1px solid black;
	}
	table th,td{
	border-collapse: collapse;
		border:1px solid black;
	}
</style>
</head>
<body>
	시험장소 등록
	
	<table>
	<thead>
		<tr>
			<th>응시시험</th>
			<th>접수기간</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ proList }" var="c">
			<tr onclick="selectExam(this);">
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
			</tr>
		</c:forEach>
		<c:forEach items="${ techList }" var="c">
			<tr onclick="selectExam(this);">
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
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
	function selectExam(e){
		console.log(e);
		//var firstTd = $('e  td');
		var firstTd = $(e).children().eq(0)[0].innerText; // \$('e')로 선택하면 선택이 안됨
		console.log(firstTd.substring(6, firstTd.length - 6));
		var secondTd = $(e).children().eq(1)[0].innerText;
		console.log(secondTd);
		$.ajax({
			url : "/quali/taken_quali_exam/selectPlace",
			type : "get",
			data : {
				exam : firstTd,
				receptionDate : secondTd
			},
			success : {
				
			}
		
		});
		
	}
</script>


</body>
</html>