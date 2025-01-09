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
확인서발급신청

<form action="confirmation-application" method="post">
	<div>
		자격구분<select name="quali_type" id="quali_type" onchange="sortChange();">
			<option value="none">선택</option>
			<option value="pro">국가전문자격</option>
			<option value="tech">국가기술자격</option>
		</select>
	</div>
	<div>
		시험구분<select id="sort-select" name="sort-select" onchange="typeChange();">
			<option value="none">선택</option>
			<option class="sort-select-option" value="none">시험검색이 되지 않았습니다</option>
		</select>
	</div>
	<div>
		확인서종류<select id="confirm-type" name="confirmationNo">
			<option>선택</option>
			<option id="confirm-type-option">발급가능 확인서가 없습니다</option>
		</select>
	</div>
	<button type="submit">확인서신청</button>
</form>


<script>
    function sortChange() {
        const qualiType = document.getElementById('quali_type').value;

        if (qualiType !== 'none') {
            // Ajax 요청
            $.ajax({
                url: 'confirmQualiType',
                type: 'get',
                data: {
                    selected: qualiType
                },
                success: function (list) {
                        console.log(list); 
                        $('.sort-select-option').hide();

                        $('.add-option').remove();
                        
                        const selectDiv = document.getElementById('sort-select');
                        var i = 0;
                        console.log(list[i]);
                        while(list[i]){
	                        selectDiv.innerHTML += `<option class="add-option" value="\${ list[i] }" >\${ list[i] }</option>`;
	                        i = i+1;
                        }
                }
            });
        } else{
        	$('.sort-select-option').show();
        	$('.add-option').remove();
        	$('.add-option-2').remove();
        	$('#confirm-type-option').show();
        }
    }
    
    function typeChange(){
    	const sortType = document.getElementById('sort-select').value;
    	
    	if(sortType !== 'none'){
    		$.ajax({
    			url : 'confirmType',
    			type : 'get',
    			data : {
    			},
    			success : function(list){
    				console.log(list);
    				$('#confirm-type-option').hide();
    				$('.add-option-2').remove();
    				
    				const confirmDiv = document.getElementById('confirm-type');
    				var i = 0;
    				while(list[i]){
    					confirmDiv.innerHTML += `<option class="add-option-2" value="\${ list[i].confirmationNo }">\${ list[i].confirmationName }</option>`;
    					i = i + 1;
    				}
    			}
    		
    		});
    	} else{
    		$('.add-option-2').remove();
    		$('#confirm-type-option').show();
    	}
    }
</script>
</div>
</div>
</body>
</html>