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
	<form action="/quali/provisional_answer/answer_enroll" method="post" enctype="multipart/form-data">
		<div>${title } ${period }교시</div>
		
		
		가답안 등록하기
		가답안 있으면 보여주기
		
		
		<div>
			파일1
			<c:choose>
			<c:when test="${ not empty file1 }">
			<div>
				📧 <p class="file1_text">파일수정</p>
			</div>
			</c:when>
			<c:otherwise>
			<div id="upload1">
				📭 <p class="file1_text">파일없음</p>
			</div>
			</c:otherwise>
			</c:choose>
		</div>
		<div>
			파일2
			<c:choose>
			<c:when test="${ not empty file1 }">
			<div>
				📧 
				<p class="file1-text">파일수정</p>
			</div>
			</c:when>
			<c:otherwise>
			<div id="upload2">
				📭 <p class="file1-text">파일없음</p>
			</div>
			</c:otherwise>
			</c:choose>
		</div>
		
		<!-- style="display:none;"-->
		<div id="file-area" >
			<input type="file" id="file1" name="upfile1" onchange="test();">
			<input type="file" id="file2" name="upfile2">
		</div>
		
	</form>
	
	<script>
		$(function(){
			
			$('#upload1').click(function(){
				$('#file1').click();
			});
			
			$('#upload2').click(function(){
				$('#file2').click();
			})
			
		})
		
		function test(){
				let file1_text = $("#file1").val();
				console.log(file1_text);
				$('.file1_text').html(file1_text);
		}
	</script>
		
		
		
	</form>
</body>
</html>