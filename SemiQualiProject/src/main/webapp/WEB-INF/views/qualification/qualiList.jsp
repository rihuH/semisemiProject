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
	자격증 업데이트 <br>
	조회할 자격증
	<select id="qualiList" onchange="qualiList();">
		<option value="none">선택</option>
		<option value="tech">국가기술자격</option>
		<option value="pro">국가전문자격</option>
	</select>
	
	<div>
		<div id="proList" style="display:none;">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>자격타입</th>
						<th>관련부처</th>
						<th>분류</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ proList }" var="c" varStatus="s">
						<tr>
							<td>${ s.count }</td>
							<td>${ c.profesionalDept.typeQualification.qualificationType }</td>
							<td>${ c.profesionalDept.relevantDepartment }</td>
							<td><a href="/quali/qualification/updateForm?type=${ c.profesionalDept.typeQualification.qualificationType }&dept=${ c.profesionalDept.relevantDepartment }&name=${ c.qualificationName }">${ c.qualificationName }</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="techList" style="display:none;" varStatus="s">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>자격타입</th>
						<th>직무뷴야</th>
						<th>분류</th>
						<th>시행종목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ techList }" var="c">
						<tr>
							<td>${ s.count }</td>
							<td>${ c.techCategory.technicalField.typeQualification.qualificationType }</td>
							<td>${ c.techCategory.technicalField.fieldName }</td>
							<td>${ c.techCategory.categoryName }</td>
							<td><a href="/quali/qualification/updateForm?type=${ c.techCategory.technicalField.typeQualification.qualificationType }&fieldName=${ c.techCategory.technicalField.fieldName }&category=${ c.techCategory.categoryName }&name=${ c.qualificationName }">${ c.qualificationName }</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<script>
		function qualiList(){
			let selected = document.getElementById('qualiList').value;
			let proDiv = document.getElementById('proList');
		    let techDiv = document.getElementById('techList');
			console.log(selected);
			if(selected === 'none'){
				proDiv.style.display='none';
				techDiv.style.display='none';
			} else if(selected === 'pro'){
				proDiv.style.display='block';
				techDiv.style.display='none';
			} else{
				proDiv.style.display='none';
				techDiv.style.display='block';
			}
			
		}
	</script>
	</div>
</div>
</body>
</html>