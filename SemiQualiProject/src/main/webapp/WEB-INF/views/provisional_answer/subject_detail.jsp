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
	<form action="/quali/provisional_answer/answer_enroll" method="post" enctype="multipart/form-data">
		<div>${title } ${period }교시</div>
		<input type="hidden" id="file1del" name="file1del" value=""/>
		<input type="hidden" id="file2del" name="file2del" value=""/>
		<input type="hidden" id="subjectNo" name="subjectNo" value="${subject.subjectNo }"/>

		가답안 등록하기
		가답안 있으면 보여주기
		
		${File1}
		${File2}
		<div class="upload1">
			파일A <br>
			<c:choose>
				<c:when test="${ not empty File0}">
					<img width="300" id="1_img" height="180" alt="1_img" src="${File0}"> 
				</c:when>
				<c:otherwise>
					<img width="300" id="1_img" height="180" alt="1_img">
				</c:otherwise>
			</c:choose>
			<p class="file1_text">파일첨부</p>
		</div>
					<button type="button" onclick="deleteFile('1');">등록된 파일 삭제</button>
		<div class="upload2">
			파일B <br>
			<c:choose>
				<c:when test="${ not empty File1}">
					<img width="300" id="2_img" height="180" alt="2_img" src="${File1}"> 
				</c:when>
				<c:otherwise>
					<img width="300" id="2_img" height="180" alt="2_img">
				</c:otherwise>
			</c:choose>
			<p class="file2_text">파일첨부</p>
		</div>
		<button type="button" onclick="deleteFile('2');">등록된 파일 삭제</button>
		<div id="file_area" style="display:none;">
			<input type="file" id="file1" name="upfiles" onchange="changeText1(this);" multiple>
			<input type="file" id="file2" name="upfiles" onchange="changeText2(this);" multiple>
		</div>
		
		<button type="submit">답안 등록</button>
		
	</form>
	
	<script>
		
		$(function(){
			
			$('.upload1').click(function(){
				$('#file1').click();
			});
			
			$('.upload2').click(function(){
				$('#file2').click();
			})
			
		});
		
		function changeText1(inputFile){
				let file1_text = ($("#file1").val()).substring(($("#file1").val()).lastIndexOf('\\') + 1);
				console.log(file1_text);
				$('.file1_text').html(file1_text);
				
				let reader = new FileReader();
        		
        			reader.readAsDataURL(inputFile.files[0]);
        		
        		// 해당 파일을 읽는 순단 파일만의 고유한 URL이 만들어짐
        		// URL을 미리보기 영역의 img 태그의 scr속성 값으로 부여할 것
        		// 파일 읽기가 완료되면 실행할 이벤트 핸들러를 정의
        		reader.onload = function(e){
        			console.log(e);
        			
        			const url = e.target.result;
        			
        			// 각 영역에 맞춰서 미리보기를 구현
        			// num 값에 따라서 각각 다른 img 요소의 src 속성의 값으로 대입
        			$('#1_img').attr('src', url);
			}
		}
		
		function changeText2(inputFile){
			let file2_text = ($("#file2").val()).substring(($("#file2").val()).lastIndexOf('\\') + 1);
			console.log(file2_text);
			$('.file2_text').html(file2_text);
			
			let reader = new FileReader();
			reader.readAsDataURL(inputFile.files[0]);
			reader.onload = function(e){
				console.log(e.target);
				const url = e.target.result;
				$('#2_img').attr('src', url);
			}
		}
		
		function deleteFile(e){
			
			if(e === '1'){
				$('#file1del')[0].defaultValue = 'delete';
				$('#1_img').attr('src', '');
			} else{
				$('#file2del')[0].defaultValue = 'delete';
				$('#2_img').attr('src', '');
			}
			
		}
	</script>
		
</div>
</div>		
		
</body>
</html>