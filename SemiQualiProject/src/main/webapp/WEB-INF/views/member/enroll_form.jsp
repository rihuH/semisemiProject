<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



    <!--
        memberId

    -->

    <style>
        
        div{
		overflow:hidden;
	    }

        #enroll-div-outer{
            width: 100%;
            height: 600px;
        }

        #enroll-div-inner{
            width: 800px;
            height: auto;
            background-color: white;

            box-shadow: 3px 3px 10px rgb(170, 170, 170);

            display: flex;
            margin: auto;

            margin-top: 15px;
            border-radius: 20px;

            justify-content: center;
        }

        .input-title{
            width: 120px;

            display: inline-block;
        }

        .input-detail{
            width: 470px;
            
            display: inline-block;
        }
        
        .rrn-class{
            width: 178px;
        }

        .ckeckForm{
            display: flex;
            float: right;
        }

        .btns{
            display: flex;
            align-items: center;
            justify-content: center;

            margin-top: 20px;
            margin-bottom: 20px;
        }

    </style>

</head>
<body>
	
	<jsp:include page="../common/header.jsp"></jsp:include>
	
         <script>
        	$(function(){
        		// 아이디 중복체크
        		// 같은 화면 안에 혹여나 같은 id값을 가진 객체가 있을 수 있으니
        		// 대상을 명확히 지정해줘야됨, 이 경우엔 modal과 id인풋개체의 id값이 같았다.
        		const $idInput = $('#enroll-div-inner > #memberId');
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

    <div id="content">
        <div id="enroll-div-outer">
            <div id="enroll-div-inner">
                <form action="sign-up.me" method="post" style="display: block;">
                    <div class="input-title" style="font-weight: bold; margin-top: 30px;">
                        아이디
                    </div><br>
                    <div id="check-result" style="font-size:0.7em; display:none"></div>
                    <input type="text" class="input-detail" name="memberId" required>  <br>
                    <div class="input-title" style="font-weight: bold;">
                        비밀번호
                    </div><br>
                    <input type="password" class="input-detail" id="password" required>  <br>
                    <div class="input-title" style="width: 300px; font-weight: bold;">
                        비밀번호 확인 <div id="password-result" class="ckeckForm"></div>
                    </div><br>
                    <input type="password" class="input-detail"  id="check-password" name="memberPwd" required> <br>
                    <div class="input-title" style="font-weight: bold;">
                        이름
                    </div><br>
                    <input type="text" class="input-detail" name="memberName" required>  <br>
                    <div class="input-title" style="font-weight: bold;">
                        주민등록번호
                    </div><br>
                    <input type="text" id="rrn-first" class="rrn-class" maxlength="6" placeholder="예시) 000000"> -
                    <input type="password" id="rrn-second" class="rrn-class" maxlength="7" placeholder="예시) 1111111"> <br>
                    <input type="hidden" id="rrn_full" name="memberRrn">

                    <div class="input-title">
                        이메일
                    </div><br>
                    <input type="text" id="email_id" value=""  placeholder="이메일" maxlength="18" /> @ 
                    <input type="text" id="email_domain" value="" placeholder="이메일 도메인" maxlength="18"/>
                    <input type="hidden" id="email_full" name="email"> 
                    <select class="select" id="select_domain" title="이메일 도메인 주소 선택">
                        <option value="">-선택-</option>
                        <option value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="korea.com">korea.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="yahoo.com">yahoo.com</option>
                    </select> <br>
                    <div class="input-title" style="width: 300px; font-weight: bold;">
                        핸드폰번호 <div id="phone-result" class="ckeckForm"></div>
                    </div><br>
                    <input type="text" id="phone" class="input-detail" maxlength="13" placeholder="예시) 010-0000-0000" name="phone" required>


                    <div class="btns">
                        <button type="submit" id="join-btn" class="btn btn-primary" style="margin-right: 10px;">회원가입</button>
                        <button type="reset" class="btn btn-danger">초기화</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    

    <script>
        
        // 비밀번호 확인 script
        $('#check-password').keyup(function() {
            const pwd = $('#password').val().trim();
            const checkpwd = $('#check-password').val().trim();

            if (pwd === checkpwd) {
                $('#password-result').html("일치합니다.").css('color', 'green');
                $('#join-btn').prop('disabled', false);

            } else {
                $('#password-result').html("다시한번 확인해주세요.").css('color', 'red');
                $('#join-btn').prop('disabled', true);

            }
        });

        // 도메인 선택 후 입력 script
        $(function() {
            $('#select_domain').on('change', function () {
                const emailId = $('#email_id').val().trim();
                const emailDomain = $('#email_domain').val().trim();
                const selectDomain = $(this).val();

                if (selectDomain) {
                    $('#email_domain').val(selectDomain); 
                } else {
                    $('#email_domain').val(''); 
                }
            });
        });

        
        $('form').on('submit', function(e){
            const rrnFirst = $('#rrn-first').val().trim();
            const rrnSecond = $('#rrn-second').val().trim();
            
            
            
            if (rrnFirst.length === 6 && rrnSecond.length === 7) {
            	const fullRrn = rrnFirst + '-' + rrnSecond;
                $('#rrn_full').val(fullRrn);
            } else {
                alert('주민등록번호를 올바르게 입력해주세요.');
                e.preventDefault(); 
                return;
            }

            const emailId = $('#email_id').val().trim();
            const emailDomain = $('#email_domain').val().trim();
            
            
            if (emailId && emailDomain) {
            	const fullEmail = emailId + '@' + emailDomain;
                $('#email_full').val(fullEmail);
            } else {
                alert('이메일을 올바르게 입력해주세요.');
                e.preventDefault(); 
                return;
            }
        });
        
        const regExp = /^\d{3}-\d{3,4}-\d{4}$/;

        $('#phone').keyup(function(){
            if (regExp.test($('#phone').val())) {
                $('#phone-result').html("양식과 일치합니다.").css('color', 'green');
                $('#join-btn').prop('disabled', false);
            } else {
                $('#phone-result').html("양식에 맞춰주세요.").css('color', 'red');
                $('#join-btn').prop('disabled', true);
            }
        })
        

    </script>


	<jsp:include page="../common/footer.jsp"></jsp:include>


</body>
</html>