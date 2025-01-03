<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


    <style>
        
        div{
		overflow:hidden;
	    }
	    
	    #content-right{
            width: 950px;
            height: 100%;

            margin: auto;
        }

        #update-div-outer{
            width: 900px;
            height: auto;
            background-color: white;

            box-shadow: 3px 3px 10px rgb(170, 170, 170);

            margin: auto;

            margin-top: 15px;
            margin-bottom: 10px;
            margin-left : 10px;
            
            border-radius: 20px;

            justify-content: center;
        }

        #update-div-member{
            width: 950px;
            height: auto;


            display: flex;
            margin: auto;

            justify-content: center;
        }
        #update-div-education{

            height: auto;

            display: flex;
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

        #password-result{
            display: flex;
            float: inline-end;
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

    <!-- 주민등록번호 데이터를 세션에서 가져오기 -->
    <c:set var="rrn" value="${sessionScope.loginMember.memberRrn}" />
    <c:set var="rrnFirst" value="${fn:substringBefore(rrn, '-')}" />
    <c:set var="rrnSecond" value="${fn:substringAfter(rrn, '-')}" />
    
    <c:set var="email" value="${ sessionScope.loginMember.email }" />
	<c:set var="emailId" value="${fn:substringBefore(email, '@')}" />
	<c:set var="emailDomain" value="${fn:substringAfter(email, '@')}" />

    <div id="content-right">
        <div id="update-div-outer">
            <div id="update-div-member">
                <form action="edit-profile" method="post" style="display: block;">
                    <div class="input-title" style="font-weight: bold; margin-top: 30px;">
                        아이디
                    </div><br>
                    <!-- 아이디는 변경 불가능, readonly속성 추가-->
                    <input type="text" class="input-detail" name="memberId" value="${ sessionScope.loginMember.memberId }" required readonly>  <br>
                    <div class="input-title" style="font-weight: bold;">
                        비밀번호
                    </div><br>
                    <!-- 비밀번호는 변경 가능, 기존 비밀번호는 DB에서 불러와서 value속성에 넣는다. -->
                    <input type="password" class="input-detail" id="password" value="${ sessionScope.loginMember.memberPwd }" required>  <br>
                    <div class="input-title" style="width: 300px; font-weight: bold;">
                        비밀번호 확인 <div id="password-result"></div>
                    </div><br>
                    <!-- 비밀번호 확인을 입력하고, 현재 비밀번호와 같아야 정보수정가능 ajax 사용하면 바로 처리가능 -->
                    <input type="password" class="input-detail"  id="check-password" name="memberPwd" value="${ sessionScope.loginMember.memberPwd }" required> 
                    <button type="button" id="check-password-btn">확인</button><br>
                    <div class="input-title" style="font-weight: bold;">
                        이름
                    </div><br>
                    <!-- 이름 변경 가능 DB에서 가져온 값 value에 넣기 -->
                    <input type="text" class="input-detail" name="memberName" value="${ sessionScope.loginMember.memberName }" required>  <br>
                    <div class="input-title" style="font-weight: bold;">
                        주민등록번호
                    </div><br>
                    <!-- 주민등록번호 변경 불가능 -->
                    <input type="text" id="rrn-first" class="rrn-class" maxlength="6" value="${ rrnFirst }" readonly> -
                    <input type="password" id="rrn-second" class="rrn-class" maxlength="7" value="${ rrnSecond }" readonly> <br>
                    <input type="hidden" id="rrn_full" name="memberRrn">

		          			<input type="hidden" name="createDate" value="${ sessionScope.loginMember.createDate }"/>
	  	        			<input type="hidden" name="status" value="${ sessionScope.loginMember.status }"/>

                    <div class="input-title">
                        이메일
                    </div><br>
                    <!-- 이메일 수정 가능 -->
                    <input type="text" id="email_id" value="${ emailId }"  maxlength="18" /> @ 
                    <input type="text" id="email_domain" value="${ emailDomain }"  maxlength="18"/>
                    <input type="hidden" id="email_full" name="email"> 
                    <select class="select" id="select_domain" title="이메일 도메인 주소 선택">
                        <option value="">-선택-</option>
                        <option value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="korea.com">korea.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="yahoo.com">yahoo.com</option>
                    </select> <br>
                    <div class="input-title" style="font-weight: bold;" required>
                        핸드폰번호
                    </div><br>
                    <!-- 핸드폰번호 수정 가능 -->
                    <input type="text" class="input-detail" maxlength="13" value="${ sessionScope.loginMember.phone }" placeholder="예시) 010-0000-0000" name="phone">


                    <div class="btns">
                        <button type="submit" class="btn btn-primary" style="margin-right: 10px;">정보수정</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                    </div>
                </form>
            </div>
            
            

            <div style="border-top: 1px solid rgb(175, 175, 175); width: 800px; margin:auto; display: flex; ">

            </div>

            <div id="update-div-education">
                <form action="edit-education" method="post">
                    <div style="margin-top: 20px;">
                        학교명 <br>
                        <input type="text" id="school-name" name="schoolName" class="input-detail">
                    </div>
                    <div>
                        학력상태 <br>
                        <input type="text" id="education" name="education" style="width: 380px;" readonly>
                        <select class="select" id="select-education" title="학력상태 선택">
                            <option value="">-선택-</option>
                            <option value="중학교 졸업">중학교 졸업</option>
                            <option value="중학교 중퇴">중학교 중퇴</option>
                            <option value="고등학교 졸업">고등학교 졸업</option>
                            <option value="고등학교 중퇴">고등학교 중퇴</option>
                            <option value="대학 2년제 졸업">대학 2년제 졸업</option>
                            <option value="대학 4년제 졸업">대학 4년제 졸업</option>
                            <option value="대학교 중퇴">대학교 중퇴</option>
                        </select> <br>
                    </div>
                    <div>
                        졸업일 <br>
                        <input type="date" id="graduation-date" name="graduationDate" class="input-detail">
                    </div>
                    <div>
                        전공 <br>
                        <input type="text" id="major" name="major" placeholder="직접 기재해주세요" class="input-detail">
                    </div>

                    <div class="btns">
                        <button type="submit" class="btn btn-primary" style="margin-right: 10px;">정보수정</button>
                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">초기화</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
                <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.me" method="post">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="memberPwd" name="memberPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger">탈퇴하기</button>
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
                $('#password-result').html("통과입니다.").css('color', 'green');
            } else {
                $('#password-result').text("다시한번 확인해주세요.").css('color', 'red');
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

        // 학력상태 선택 후 입력 script
        $(function() {
            $('#select-education').on('change', function () {
                const education = $('#education').val().trim();
                const selectEducation = $(this).val();

                if (selectEducation) {
                    $('#education').val(selectEducation); 
                } else {
                    $('#email_domain').val(''); 
                }
            });
        });
        

        $('form').on('submit', function(){
            const rrnFirst = $('#rrn-first').val().trim();
            const rrnSecond = $('#rrn-second').val().trim();
            if (rrnFirst.length === 6 && rrnSecond.length === 7) {
                $('#rrn_full').val(`${rrnFirst}-${rrnSecond}`);
            } else {
                alert('주민등록번호를 올바르게 입력해주세요.');
                e.preventDefault(); 
                return;
            }

            const emailId = $('#email_id').val().trim();
            const emailDomain = $('#email_domain').val().trim();
            if (emailId && emailDomain) {
                $('#email_full').val(`${emailId}@${emailDomain}`);
            } else {
                alert('이메일을 올바르게 입력해주세요.');
                e.preventDefault(); 
                return;
            }

        });


    </script>



</body>
</html>