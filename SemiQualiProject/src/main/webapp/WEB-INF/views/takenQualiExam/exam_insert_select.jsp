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
시행시험 인서트하는 페이지<br>

인서트 할 자격증 선택<br>

	<div>
		검색으로 찾기
		<form>
			<input id="searchInput" keyup="outToDiv();" placeholder="검색어입력">
			<button type="reset">x</button>
		</form>
		
		<button type="submit">검색</button>
		
	</div>

	<div>
		<table>
			<thead>
				<tr>
					<th>분류</th>
					<th>자격증</th>
				</tr>
			</thead>
			<tbody id="searchOutput">
			</tbody>
		</table>
	</div>

<script>

$(function(){
    $('#searchInput').keyup(function(){
		let searched = $('#searchInput').val();
		
		if( searched){
			$.ajax({
				url : "/quali/qualification/search",
				type : "get",
				data : {
					searched : searched
				},
				success : function(response){
					$('.addedProTr').remove();
					$('.addedTechTr').remove();
					console.log(response);
					var i = 0;
					while(response.data.proList[i]){
						$('#searchOutput').append(`<tr class="addedProTr"><th>국가전문자격</th>
								<th>\${response.data.proList[i].qualificationName}</th></tr>`);
						i = i + 1;
					}
					var i = 0;
					while(response.data.techList[i]){
						$('#searchOutput').append(`<tr class="addedTechTr"><th>국가기술자격</th>
								<th>\${response.data.techList[i].qualificationName}</th></tr>`);
						i = i + 1;
					}
	            }
	        });
			
		} else{
			$('.addedProTr').remove();
			$('.addedTechTr').remove();
		}
    });
});


$(document).on('click', '.addedTechTr th', function() {
    const qualificationName = $(this).text(); // 클릭한 셀의 텍스트 가져오기
    console.log(qualificationName);
    location.href = `/quali/taken_quali_exam/exam_insert_form/tech\${qualificationName}`;
});

$(document).on('click', '.addedProTr th', function() {
    const qualificationName = $(this).text(); // 클릭한 셀의 텍스트 가져오기
    console.log(qualificationName);
    location.href = `/quali/taken_quali_exam/exam_insert_form/pro\${qualificationName}`;
});

</script>

</div>
</div>

</body>
</html>