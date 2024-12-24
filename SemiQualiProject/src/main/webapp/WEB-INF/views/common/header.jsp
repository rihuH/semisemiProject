<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
    

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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

        #menubar-right>ul{
            display: flex;
            gap: 15px;
            padding-left: 12px;
            margin-top: 15px;
        }

        #menubar-right>ul>li{
            align-items: center;
            justify-content: space-between;
            display: flex;
        }

        #menubar-right>ul>li>a{
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
                    <a href=""> <!-- 공백 채울것 -->
                        국가자격시험
                    </a>
                </li>
                <li>
                    <a href=""> <!-- 공백 채울것 -->
                        자격정보
                    </a>
                </li>                
                <li>
                    <a href=""> <!-- 공백 채울것 -->
                        자격증, 확인서
                    </a>
                </li>                
                <li>
                    <a href=""> <!-- 공백 채울것 -->
                        마이페이지
                    </a>
                </li>                
                <li>
                    <a href=""> <!-- 공백 채울것 -->
                        고객지원
                    </a>
                </li>
            </ul>
        </div>
        <div id="menubar-right">
            <ul>
                <li>
                    <a href=""> <!-- 공백 채울것 -->
                        로그인
                    </a>
                </li>
                <li>
                    <a href="">
                        회원가입
                    </a>
                </li>
            </ul>
        </div>
    </div>
    </div>
    <div id="hide-menu">
        <div id="hide-div1">
            <h3>국가자격시험</h3>
            <ul>
                <li><a href="">원서접수내역</a></li> <!-- 공백 채울것 -->
                <li><a href="">원서접수신청</a></li> <!-- 공백 채울것 -->
                <li><a href="">시험결과보기</a></li> <!-- 공백 채울것 -->
                <li><a href="">사후환불신청</a></li> <!-- 공백 채울것 -->
            </ul>
        </div>
        <div id="hide-div2">
            <h3>자격정보</h3>
            <ul>
                <li><a href="">응시자격자가진단</a></li> <!-- 공백 채울것 -->
                <li><a href="">응시가능종목확인</a></li> <!-- 공백 채울것 -->
            </ul>
        </div>
        <div id="hide-div3">
            <h3>자격증, 확인서</h3>
            <ul>
                <li><a href="">자격증 확인/발급</a></li> <!-- 공백 채울것 -->
                <li><a href="">자격증발급현황</a></li> <!-- 공백 채울것 -->
            </ul>
        </div>
        <div id="hide-div4">
            <h3>마이페이지</h3>
            <ul>
                <li><a href="">개인정보수정</a></li> <!-- 공백 채울것 -->
                <li><a href="">학력/경력수정</a></li> <!-- 공백 채울것 -->
                <li><a href="">회원탈퇴</a></li> <!-- 공백 채울것 -->
                <li><a href="">관심자격증등록</a></li> <!-- 공백 채울것 -->
            </ul>
        </div>
        <div id="hide-div5">
            <h3>고객지원</h3>
            <ul>
                <li><a href="">공지사항</a></li> <!-- 공백 채울것 -->
                <li><a href="">수험생가이드</a></li> <!-- 공백 채울것 -->
                <li><a href="">환불안내</a></li> <!-- 공백 채울것 -->
                <li><a href="">부정신고센터</a></li> <!-- 공백 채울것 -->
                <li><a href="">자주묻는질문</a></li> <!-- 공백 채울것 -->
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