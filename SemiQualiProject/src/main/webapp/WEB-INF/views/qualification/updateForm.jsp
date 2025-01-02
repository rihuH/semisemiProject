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
자격증이름 업데이트
	
	<div>
	<c:choose>
		<c:when test="${ empty dept }">
			<form action="/quali/qualification/updateTech" method="post">
				<input type="hidden" name="qualiName" value="${ name }"/>
				<div style="width:70%;">
					<div style="width:20%; float:left;">
						<div>자격타입</div>
						<div>직무분야</div>
						<div>분류</div>
						<div>시행종목</div>
					</div>
					<div style="width:50%; float:left;">
						<div>${ type }</div>
						<div>${ fieldName }</div>
						<div>${ category }</div>
						<div><input name="updatedQualiName" value="${ name }"></div>
					</div>
				</div>
				<button type="submit">수정</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="/quali/qualification/updatePro" method="post">
				<input type="hidden" name="qualiName" value="${ name }"/>
				<div style="width:70%;">
					<div style="width:20%; float:left;">
						<div>자격타입</div>
						<div>관련부처</div>
						<div>분류</div>
					</div>
					<div style="width:50%; float:left;">
						<div>${ type }</div>
						<div>${ dept }</div>
						<div><input name="updatedQualiName" value="${ name }"></div>
					</div>
				</div>
				<button type="submit">수정</button>
			</form>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>