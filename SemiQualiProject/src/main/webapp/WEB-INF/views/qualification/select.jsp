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
				<hr>
				<div id="categoryName"></div>
				<hr>
			</div>
			<div id="qualiList" style="width:30%; float:left;">
				<hr>
				<div id="qualiName"></div>
				<hr>
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
				$('.addDiv2').remove();
				$('.addDiv3').remove();
    			var i = 0;
				const fieldDiv = document.getElementById('field');    
				if(qualiType === '국가전문자격'){
    				$('#fieldName').html('관련부처');
    				$('#qualiName').html('');
    			while(fieldList[i]){
					fieldDiv.innerHTML += `<div class="addedSpan" onclick="categorySelect(this, 'pro');">\${fieldList[i].relevantDepartment}</div>`;
					i = i + 1;
    			}
				} else{
					$('#fieldName').html('직무분야');
					while(fieldList[i]){
						fieldDiv.innerHTML += `<div class="addedSpan" onclick="fieldSelect(this);">\${fieldList[i].fieldName}</div>`;
						i = i + 1;
	    			}
				}
				
				
    		}
    	})
    };
    
    function fieldSelect(e){
    	const fieldSelect = e.textContent;
    	$.ajax({
    		url : "fieldSelect.quali",
    		type : "get",
    		data : {
    			fieldSelect : fieldSelect
    		},
    		success : function(techCategoryList){
    			$('.addDiv2').remove();
    			var i = 0;
    			const categoryDiv = document.getElementById('category');   
    			$('#categoryName').html('분류');
				while(techCategoryList[i]){
					categoryDiv.innerHTML += `<div class="addDiv2" onclick="categorySelect(this, 'tech');">\${techCategoryList[i].categoryName}</div>`;
					i = i + 1;
    			}
    		}
    	})
    };
    
    function categorySelect(e, s){
    	const categorySelect = e.textContent;
    	//console.log(s);
    	$.ajax({
    		url : "categorySelect.quali",
    		type : "post",
    		data : {
    			categorySelect : categorySelect,
    			typeStr : s
    		},
    		success : function(qualiList){
    			var i = 0;
    			$('.addDiv3').remove();
    			if(s === 'pro'){
    				$('#categoryName').html('분류');
    				$('#qualiName').html('');
    				const categoryDiv = document.getElementById('category');  
    				while(qualiList[i]){
    					categoryDiv.innerHTML += `<div class="addDiv3" onclick="qualiSelect(this, 'pro', this.dataset.field);">\${qualiList[i].qualificationName}</div>`;
    					i = i + 1;
        			}
    			} else{
    				$('#qualiName').html('시행종목');
    				const qualiDiv = document.getElementById('qualiList');  
    				while(qualiList[i]){
    					qualiDiv.innerHTML += `<div class="addDiv3" onclick="qualiSelect(this, 'tech');">\${qualiList[i].qualificationName}</div>`;
    					i = i + 1;
        			}
    			}
    		}
    	
    	})
    };
    
    function qualiSelect(e, s, v){
    	const qualiName = e.textContent;
    	 const dataField = JSON.parse(field); // JSON 문자열을 객체로 변환
    	    console.log(dataField);
    	// 누르면 시험
    }
</script>
</body>
</html>