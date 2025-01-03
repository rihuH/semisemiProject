<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

진짜 자격증 인서트 페이지

<c:choose>
<c:when test="${ empty tech }">
	
	
	<form>
		
	</form>
</c:when>
<c:otherwise>
	<div>
	<form action="/quali/taken_quali_exam/exam_insert" method="post">
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
		시험시작일<input name="examStartDate" placeholder="yyyyMMdd"><br>
		시험종료일<input name="examFinalDate" placeholder="yyyyMMdd"><br>
		시험접수시작일<input name="receptionStartDate" placeholder="yyyyMMdd"><br>
		시험접수종료일<input name="receptionEndDate" placeholder="yyyyMMdd"><br>
		의견제시시작일<input name="opinionStartDate" placeholder="yyyyMMdd"><br>
		의견제시종료일<input name="opinionEndDate" placeholder="yyyyMMdd"><br>
		<button type="submit">시험 등록</button><br>
		<input type="hidden" name="qualificationName" value="${ tech.qualificationName }">
		
		
	</form>
	</div>
</c:otherwise>
</c:choose>
</body>
</html>