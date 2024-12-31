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
	자격증 정보 상세조회
	<div>
		<div>
		    <span onclick="typeSelect(this);">국가기술자격</span> 
		    <span onclick="typeSelect(this);">국가전문자격</span>
		</div>
		<div>
			<div id="field" style="width : 40%; float:left;">
				<hr>
				<div id="fieldName"></div>
				<hr>
			</div>
			<div id="category" style="width:30%; float:left;">
			되나?
			</div>
		</div>
	</div>

<script>
    function typeSelect(e) {
    	const qualiType = e.textContent;
    	$.ajax({
    		url : "typeSelect.quali",
    		type : "get",
    		data : {
    			qualiType : qualiType
    		},
    		success : function(fieldList){
    			$('.addedSpan').remove();
    			var i = 0;
				const fieldDiv = document.getElementById('field');    
				if(qualiType === '국가전문자격'){
    				$('#fieldName').html('관련부처');
    			while(fieldList[i]){
					fieldDiv.innerHTML += `<div class="addedSpan" onclick="fieldSelect(this, '\${qualiType}');">\${fieldList[i].relevantDepartment}</div>`;
					i = i + 1;
    			}
				} else{
					$('#fieldName').html('직무분야');
					while(fieldList[i]){
						fieldDiv.innerHTML += `<div class="addedSpan" onclick="fieldSelect(this, '\${qualiType}');">\${fieldList[i].fieldName}</div>`;
						i = i + 1;
	    			}
				}
				
				
    		}
    	})
    };
    
    function fieldSelect(e, qualiType){
    	const fieldSelect = e.textContent;
    	$.ajax({
    		url : "fieldSelect.quali",
    		type : "get",
    		data : {
    			qualiType : qualiType,
    			fieldSelect : fieldSelect
    		},
    		success : function(list){
    			$('.addDiv2').remove();
    			var i = 0;
    			console.log(list);
    		}
    	})
    }
</script>
</body>
</html>