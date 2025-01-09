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
 				<tr>
 					<td>${s.count }</td>
 					<td onclick="detail('${c.subjectNo}', '${c.subjectPeriod }', this);">제${c.takenQualiExam.round }회 ${c.takenQualiExam.qualificationExam.profesionalQualification.qualificationName } 
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
 					
 					
 					
 					
 					
 					
 					
 					
 					<c:choose>
            <c:when test="${ c.provisionalAnswers.size() == 0 }">
                <td>💤</td>
                <td>💤</td>
            </c:when>

            <c:when test="${ c.provisionalAnswers.size() == 1 }">
                <c:choose>
                    <c:when test="${ (not empty c.provisionalAnswers[0]) and c.provisionalAnswers[0].changedFileNo == 0 }">
                        <td><a href="${ c.provisionalAnswers[0].filePath }" download="${c.provisionalAnswers[0].originalFileName }">📧</a></td>
                		<td>💤</td>
                    </c:when>
                    <c:otherwise>
                        <td>💤</td>
                        <td><a href="${ c.provisionalAnswers[0].filePath }" download="${c.provisionalAnswers[0].originalFileName }">📧</a></td>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:when test="${ c.provisionalAnswers.size() >= 2 }">
                <c:forEach items="${c.provisionalAnswers}" var="answer" varStatus="idx">
                    <td>
                        <c:choose>
                            <c:when test="${ not empty answer }">
                                <a href="${ answer.filePath }" download="${answer.originalFileName }">📧</a>
                            </c:when>
                            <c:otherwise>
                                💤
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </c:when>
        </c:choose>
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 				</tr>
 			</c:forEach>
 		</tbody>
 		<tbody id="techBody" style="display:none;">
 			<c:forEach items="${ techSubjectList }" var="c" varStatus="s">
 				<tr>
 					<td>${s.count }</td>
 					<td onclick="detail('${c.subjectNo}', '${c.subjectPeriod }', this);">${c.takenQualiExam.round }회 ${c.takenQualiExam.qualificationExam.technicalQualification.qualificationName } 
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
		 							<a href="${ c.provisionalAnswers[i].filePath }" download="${c.provisionalAnswers[i].originalFileName }">📧</a>
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
 		
 		function detail(no, period, e){
 			let firstTd = $(e).text().replace(/\s+/g, " ").trim();
 			console.log(firstTd);
 			let secondTd = $(e).next().text().replace(/\s+/g, " ").trim();
 			location.href=`/quali/provisional_answer/subject_detail/\${firstTd}/\${period}/\${no}`;
 		}
 	</script>
 	
 	</div>
</div>
</body>
</html>