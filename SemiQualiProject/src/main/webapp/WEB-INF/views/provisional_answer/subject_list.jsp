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
 서브젝트 리스트
 
 <button onclick="propro();">전문자격버튼</button>
 <button onclick="techtech()">기술자격버튼</button>
 	<table>
 		<thead>
 			<tr>
 				<th>게시글번호</th>
 				<th>시험명</th>
 				<th>교시</th>
 				<th>답안A</th>
 				<th>답안B</th>
 			</tr>
 		</thead>
 		<tbody id="proBody">
 			<c:forEach items="${ proSubjectList }" var="c" varStatus="s">
 				<tr onclick="detail('${c.subjectNo}', '${c.takenQualiExam.round }', this);">
 					<td>${s.count }</td>
 					<td>제${c.takenQualiExam.round }회 ${c.takenQualiExam.qualificationExam.profesionalQualification.qualificationName } 
 						<c:choose>
 							<c:when test="${c.takenQualiExam.qualificationExam.qualificationRank eq 1}">
 								1차
 							</c:when>
 							<c:otherwise>
 								2차
 							</c:otherwise> 
 						</c:choose>
 					</td>
 					<td>${c.subjectPeriod }교시</td>
 					<c:forEach begin="0" end="1" var="i">
 						<td>
	 						<c:choose>	
		 						<c:when test="${ not empty c.provisionalAnswers[i] }">
		 							📧
		 						</c:when>
		 						<c:otherwise>
		 							💤 						
		 						</c:otherwise>
	 						</c:choose>
 						</td>
 					</c:forEach>
 				</tr>
 			</c:forEach>
 		</tbody>
 		<tbody id="techBody" style="display:none;">
 			<c:forEach items="${ techSubjectList }" var="c" varStatus="s">
 				<tr onclick="detail('${c.subjectNo}', '${c.takenQualiExam.round }', this);">
 					<td>${s.count }</td>
 					<td>${c.takenQualiExam.round }회 ${c.takenQualiExam.qualificationExam.technicalQualification.qualificationName } 
 						<c:choose>
 							<c:when test="${c.takenQualiExam.qualificationExam.qualificationRank eq 1}">
 								필기
 							</c:when>
 							<c:otherwise>
 								실기
 							</c:otherwise> 
 						</c:choose>
 					</td>
 					<td>${c.subjectPeriod }교시</td>
 					<c:forEach begin="0" end="1" var="i">
 						<td>
	 						<c:choose>	
		 						<c:when test="${ not empty c.provisionalAnswers[i] }">
		 							📧
		 						</c:when>
		 						<c:otherwise>
		 							💤 						
		 						</c:otherwise>
	 						</c:choose>
 						</td>
 					</c:forEach>
 				</tr>
 			</c:forEach>
 		</tbody>
 	</table>
 	
 	<script>
 		
 	
 		function propro(){
 			$('#proBody').show();
 			$('#techBody').hide();
 		}
 		function techtech(){
 			$('#proBody').hide();
 			$('#techBody').show();
 		}
 		
 		function detail(no, round, e){
 			console.log(e);
 			let firstTd = $(e).children().eq(1)[0].innerText;
 			let secondTd = $(e).children().eq(2)[0].innerText;
 			location.href=`/quali/provisional_answer/subject_detail/\${firstTd}/\${secondTd}/\${no}`;
 		}
 	</script>
</body>
</html>