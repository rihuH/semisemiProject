<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>
        <base href="http://localhost/quali/">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/alertifyjs/build/css/alertify.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/alertifyjs/build/css/themes/default.min.css">
	<script src="https://cdn.jsdelivr.net/npm/alertifyjs/build/alertify.min.js"></script>

    <style>
        
        #menubar{
            border-bottom: 0.5px solid black;
            width: 1200px;
            height: 100px;
            margin: auto;
        }
        
        #menubar>div{
            float: left;
        }
        
        #menubar-left{
            width: 80px;
            height: 100%;
            display: flex;
        }

        #menubar-center{
            width: 900px;
            height: 50px;
            display: flex;
            float: left;
        }

        #menubar-right{
            width: 200px;
            height: 50px;
            display: flex;
        }
        
        #menubar-category{
            width: 900px;
            color: black;
            background-color: rgb(255, 255, 255);
            text-align: center;
            display: flex;
            margin-top: 15px;
            margin-left: 40px;
            line-height: 20px;
        }

        #menubar-category>li{
            color: black;
            text-decoration: none;
            list-style-type: none;
            font-size: 18px;
            font-weight: bolder;
            padding-left: 35px;
            padding-right: 35px;
        }
        
        #menubar-category>li>a{
            text-decoration: none;
            list-style-type: none;
            color: black;
        }
        
        #menubar-category>li>ul{
            padding: 0px;
        }

        #menubar-category>li>ul>li{
            font-size: 12px;
            list-style-type: none;
        }

        #menubar-right-1>ul{
            display: flex;
            gap: 15px;
            padding-left: 12px;

            margin-top: 5px;
            margin-bottom: 10px;
        }

        #menubar-right-1>ul>li{
            align-items: center;
            justify-content: space-between;
            display: flex;
        }

        #menubar-right-1>ul>li>a{
            background-color: black;
            color: white;


            padding: 10px 20px;
            border-radius: 30px;
            
            font-size: 13px;
            font-weight: bold;

            text-decoration: none;
        }

        #menubar-right-2>ul{
            display: flex;
            gap: 15px;
            padding-left: 12px;

            margin-top: 5px;
            margin-bottom: 10px;
        }

        #menubar-right-2>ul>li{
            align-items: center;
            justify-content: space-between;
            display: flex;
        }

        #menubar-right-2>ul>li>a{
            background-color: black;
            color: white;

            padding: 10px 20px;
            border-radius: 30px;
            
            font-size: 13px;
            font-weight: bold;

            text-decoration: none;
        }

        #searchbar {
            width: 900px;
            height: 50px;
        }

        #searchbar > div > input {
            width: 500px;
            height: 32px;
            font-size: 15px;    
            border: 0;
            border-radius: 15px;
            outline: none;
            padding-left: 10px;
            background-color: rgb(233, 233, 233);
            margin-left: 250px;
            margin-top: 15px;
        }

        #searchbar>div{
            float: left;
        }

        #searchbar-div1 {
            width: 780px;
            height: 100%;
        }

        #searchbar-div2 {
            width: 50px;
            height: 100%;
        }

        #search-img {
            width: 30px;
            height: 30px;
            margin-top: 15px;
        }

        #hide-menu{
            width: 100%;
            height: 300px;
            display: none;

            background-color: white ;

            justify-content: center;
            text-align: center;
            align-items: center;

            position: absolute;
        }

        #hide-menu > div{
            width: 240px;
            height: 100%;
        }

        #hide-menu > div > ul {
            padding: 0px;
            line-height: 30px;
        }

        #hide-menu > div > ul > li{
            list-style-type: none;
        }
        
        #hide-menu > div > ul > li > a{
            text-decoration: none;
            list-style-type: none;
            color: rgb(68, 68, 68);
        }

        #hide-div1{
            border-right: 0.5px solid rgb(222, 222, 222);
        }
        #hide-div2{
            border-right: 0.5px solid rgb(222, 222, 222);
        }
        #hide-div4{
            border-left: 0.5px solid rgb(222, 222, 222);
        }
        #hide-div5{
            border-left: 0.5px solid rgb(222, 222, 222);
        }
    


    </style>

</head>
<body>

	<c:if test="${ not empty sessionScope.alertMsg }">
		<script>
			alertify.alert('메세지', '${alertMsg}', 
				function(){alertify.success('요청성공')});
		</script>
		<c:remove var="alertMsg" scope="session"/>
	</c:if>


    <div id="menubar">
        <div id="menubar-left"></div>
        <div id="searchbar">
            <div id="searchbar-div1">
                <input type="text">
                <span></span>
            </div>
            <div id="searchbar-div2">
                <img id="search-img" src="https://cdn-icons-png.flaticon.com/512/107/107122.png" alt="">
            </div>
        </div>
        
        <div id="menubar-center">
            <ul id="menubar-category">
                <li>
                    <a href="taken_quali_exam/application_list">
                        국가자격시험
                    </a>
                </li>
                <li>
                    <a href="certification-info.do">
                        자격정보
                    </a>
                </li>                
                <li>
                    <a href="certificates.do">
                        자격증, 확인서
                    </a>
                </li>                
                <li>
                	<c:choose>
                		<c:when test="${ not empty sessionScope.loginMember }">
		                    <a href="mypage.me">
		                        마이페이지
		                    </a>
                		</c:when>
                		<c:otherwise>
                			<a href="login.do">
		                        마이페이지
		                    </a>
                		</c:otherwise>
                	</c:choose>
                </li>                
                <li>
                    <a href="notices">
                        고객지원
                    </a>
                </li>
            </ul>
        </div>
        <div id="menubar-right">
            <c:choose>
            	<c:when test="${ empty sessionScope.loginMember }">
		            <!-- 로그인 전에 보여줄것 -->
	                <div id="menubar-right-1">
	                    <ul>
	                        <li>
	                            <a href="login.do">
	                                SignIn
	                            </a>
	                        </li>
	                        <li>
	                            <a href="enrollform.me">
	                                SignUp
	                            </a>
	                        </li>
	                    </ul>
	                </div>
            	</c:when>
            	<c:otherwise>
		            <!-- 로그인 후에 보여줄것-->
		            <div id="menubar-right-2">
		                <ul>
		                    <li>
		                        <a href="application.me">
		                            MyPage
		                        </a>
		                    </li>
		                    <li>
		                        <a href="logout.me">
		                            Logout
		                        </a>
		                    </li>
		                </ul>
		            </div>
            	</c:otherwise>
            </c:choose>
                
        </div>
    </div>
    </div>
    <div id="hide-menu">
        <div id="hide-div1">
            <h3>국가자격시험</h3>
            <ul>
                <li><a href="application-history.do">원서접수내역</a></li> 
                <li><a href="application-apply.do">원서접수신청</a></li>
                <li><a href="exam-results.do">시험결과보기</a></li> 
                <li><a href="refund-request.do">사후환불신청</a></li>
            </ul>
        </div>
        <div id="hide-div2">
            <h3>자격정보</h3>
            <ul>
                <li><a href="eligibility-check.do">응시자격자가진단</a></li> 
                <li><a href="available-exams.do">응시가능종목확인</a></li>
            </ul>
        </div>
        <div id="hide-div3">
            <h3>자격증, 확인서</h3>
            <ul>
                <li><a href="certificate-verify.do">자격증 확인/발급</a></li>
                <li><a href="certificate-status.do">자격증발급현황</a></li> 
            </ul>
        </div>
        <div id="hide-div4">
            <h3>마이페이지</h3>
            <ul>
                <li><a href="edit-profile">개인정보, 학력/경력수정</a></li>
                <li><a href="edit-profile">회원탈퇴</a></li>
                <li><a href="favorite-certificates.me">관심자격증등록</a></li>
            </ul>
        </div>
        <div id="hide-div5">
            <h3>고객지원</h3>
            <ul>
                <li><a href="notices">공지사항</a></li>
                <li><a href="exam-guide">수험생가이드</a></li>
                <li><a href="refund-info">환불안내</a></li>
                <li><a href="report-misconduct">부정신고센터</a></li>
                <li><a href="faq.do">자주묻는질문</a></li>
            </ul>
        </div>
    </div>


    <script>
        $(function(){

            $('#menubar-category').mouseover(function(){
                $('#hide-menu').css('display', 'flex');
            })

            $('#searchbar').mouseover(function(){
                $('#hide-menu').css('display', 'none');
            })
            $('#menubar-left').mouseover(function(){
                $('#hide-menu').css('display', 'none');
            })
            $('#menubar-right').mouseover(function(){
                $('#hide-menu').css('display', 'none');
            })

            $('#hide-menu').mouseleave(function(){
                $('#hide-menu').css('display', 'none');
            })

        });


    </script>



</body>
</html>