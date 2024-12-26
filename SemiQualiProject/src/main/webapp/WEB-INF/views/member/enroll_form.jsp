<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <style> 
        .content { 
            background-color:rgb(247, 245, 245);
            width:80%; 
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }
    </style>
</head>
<body>
    
    <!-- 메뉴바 -->
	<jsp:include page="../common/header.jsp"></jsp:include>

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>
            
            <script>
            	$(function(){
            		// 아이디 중복체크
            		// 같은 화면 안에 혹여나 같은 id값을 가진 객체가 있을 수 있으니
            		// 대상을 명확히 지정해줘야됨, 이 경우엔 modal과 id인풋개체의 id값이 같았다.
            		const $idInput = $('#enroll-form > #memberId');
            		const $checkResult = $('#check-result');
            		const $joinBttn = $('#join-btn');
            		
            		//console.log($idInput);
            		
            		$idInput.keyup(function(){
            			// 값을 입력 할 때마다 어떤 값인지 받기
            			//console.log($idInput.val());
            			
            			if($idInput.val().length >= 5){
            				
            				$.ajax({
            					url : 'idcheck',
            					type : 'get',
            					data : {
            						userId : $idInput.val()
            					},
            					success : function(result){
            						// ajax를 사용할 땐 꼭 결과 한번씩 찍어보자.
            						//console.log(result);
            						// NNNNN 이라면 사용불가능, NNNNY라면 가능
            						
            						if(result.substr(4) === 'N'){ //중복이다
            							$checkResult.show().css('color', 'crimson').text('사용할 수 없는 아이디입니다.');
            							$joinBttn.attr('disabled', true);
            						} else{ //중복이 아니다
            							$checkResult.show().css('color', 'lightgreen').text('사용 가능합니다.');
            							$joinBttn.removeAttr('disabled');
            						}
            					}
            				});
            			}
            		});
            	});
            
            
            
            </script>

            <form action="sign-up.me" method="post">
                <div class="form-group" id="enroll-form">
                    <label for="userId">* ID : </label>												<!-- userId == getUserId() 똑같이 작성할것 -->
                    <input type="text" class="form-control" id="memberId" placeholder="Please Enter ID" name="memberId" required> <br>
					<div id="check-result" style="font-size:0.7em; display:none"></div>
					<br>
					
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="memberPwd" placeholder="Please Enter Password" name="memberPwd" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="memberName" placeholder="Please Enter Name" name="memberName" required> <br>

                    <label for="email"> &nbsp; Birth : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary disabled" id="join-btn">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>

    <!-- 푸터바 -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>