<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

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
        
    
        #address{
            margin-left: 20px;
            margin-top: 10px;

            text-decoration: none;
        }

        #menuname{
            font-weight: bold;
            font-size: 42px;
        }

        .selectmenu{
            width: 250px;
            height: 60px;
            
            font-size: 18px;
            font-weight: 600;
            
            background-color: rgb(229, 229, 229);
            
            display: flex;
            float: left;
            align-items: center;
            justify-content: center;
        }

        #selectmenu-left{
            border-top-left-radius: 20px;
            border-bottom-left-radius: 20px;
        }

        #selectmenu-right{
            border-top-right-radius: 20px;
            border-bottom-right-radius: 20px;
        }

        li::marker{
            font-size: 8px;
        }

        li{
            font-size: 16px;
            font-weight: 600;
        }

        .form-check{
            width: 90px;
            display: flex;
            float: left;
        }

        .content-0{
            width: 900px;
            height: auto;
            margin-top: 10px;
            margin-left: 25px;

            background-color: rgb(245, 245, 245);

            display: flex;

            justify-content: center;
            align-items: center;
        }

        .content-1{
            width: 900px;
            height: auto;
            margin-left: 25px;

            background-color: rgb(245, 245, 245);

            display: flex;

            justify-content: center;
            align-items: center;
        }

        #content-1{
            width: 900px;
            height: 40px;
            
            background-color: rgb(227, 227, 227);

            display: flex;

            text-align: center;

        }

        #content-1-1{
            width: 150px;
            border-right: 1px solid rgb(230, 230, 230);
            padding-top: 10px;
        }
        #content-1-2{
            width: 650px;
            padding-top: 10px;
        }
        #content-1-3{
            width: 100px;
            border-left: 1px solid rgb(230, 230, 230);
            padding-top: 10px;

        }

        .content-2{
            width: 900px;
            height: 70px;

            display: flex;

            text-align: center;

            border-bottom: 1px solid rgb(230, 230, 230);
        }

        .content-2-1{
            width: 150px;
            height: 70px;
            border-right: 1px solid rgb(230, 230, 230);
        }
        .content-2-2{
            width: 650px;
            height: 70px;
        }
        .content-2-3{
            width: 100px;
            height: 70px;
            border-left: 1px solid rgb(230, 230, 230);

            
        }

        .selectbtn{
            width: 80px;
            background-color: rgb(229, 229, 229);
            margin-top: 5px;
            margin-bottom: 2px;

            margin-left: 10px;
            border-radius: 5px;
            
            border: 1px solid rgb(202, 202, 202);
        }


        

    </style>
</head>
<body>
    
    <div id="content-right">
        <!-- 이 부분 content영역은 메뉴마다 직접 값 적읍시다-->
            <div id="address">
                <a href="#">홈</a> > 
                <a href="#">메뉴 이름</a> > 
                <a href="#">상세 메뉴 이름1</a> > 
                <a href="#">상세메뉴1</a>
            </div>

            <div style="margin-left: 20px; margin-top: 10px;">
                <!-- 이 부분은 descropt-menu-a의 값임 -->
                <p id="menuname">상세메뉴1</p>
            </div>

            <div>
                <div style="display: flex; justify-content: center;">
                    <div class="selectmenu" id="selectmenu-left" style="background-color: black; color: white;"
                     onclick="location.href='이동할 url'">진행중인 접수내역</div>
                     
                    <div class="selectmenu"
                    onclick="location.href='이동할 url'">지난 접수내역</div>

                    <div class="selectmenu" id="selectmenu-right"
                    onclick="location.href='이동할 url'">취소/환불내역</div>
                </div>

                <div>
                    <ul style="margin-left: 15px; margin-top: 15px;">
                        <li>어쩌구저쩌구</li>
                        <li>어쩌구저쩌구</li>
                        <li>어쩌구저쩌구</li>
                    </ul>
                </div>

                <div style="margin-left: 30px;">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
                        <label class="form-check-label" for="flexRadioDefault1">
                          전체
                        </label>
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
                        <label class="form-check-label" for="flexRadioDefault2">
                          진행중
                        </label>
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3">
                        <label class="form-check-label" for="flexRadioDefault3">
                          접수완료
                        </label>
                      </div>
                </div>

                <div style="border-bottom: 2px solid gray; margin-top: 15px; margin-left: 25px; width: 900px; display: flex;"></div>
            </div>
            
            <div class="content-0">
                <div id="content-1">
                    <div id="content-1-1">응시시험</div>
                    <div id="content-1-2">접수상태</div>
                    <div id="content-1-3">선택</div>
                </div>
    
            </div>
            <!-- 여기는 반복문으로 해결할 것-->
            <div class="content-1">
                <div>
                    <div class="content-2">
                    <div class="content-2-1">원서접수타이틀</div>
                    <div class="content-2-2">
                        <div>24년 12월 2일 오전 12:34 접수신청됨</div>
                        <div>현재 접수 진행중</div>
                        </div>
                        <div class="content-2-3">
                            <div class="selectbtn" onclick="location.href='#'" >접수취소</div>
                            <div class="selectbtn" onclick="location.href='#'">상태보기</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content-1">
                <div>
                    <div class="content-2">
                    <div class="content-2-1">2025년 제 1회레고조립기술자 1급</div>
                    <div class="content-2-2">
                        <div>24년 12월 2일 오전 12:34 접수신청됨</div>
                        <div>현재 접수 진행중</div>
                        </div>
                        <div class="content-2-3">
                            <div class="selectbtn" onclick="location.href='#'" >접수취소</div>
                            <div class="selectbtn" onclick="location.href='#'">상태보기</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content-1">
                <div>
                    <div class="content-2">
                    <div class="content-2-1">2025년 제 1회레고조립기술자 1급</div>
                    <div class="content-2-2">
                        <div>24년 12월 2일 오전 12:34 접수신청됨</div>
                        <div>현재 접수 진행중</div>
                        </div>
                        <div class="content-2-3">
                            <div class="selectbtn" onclick="location.href='#'" >접수취소</div>
                            <div class="selectbtn" onclick="location.href='#'">상태보기</div>
                        </div>
                    </div>
                </div>
            </div>
            
    </div>
    <!--
        <div style="display: flex; justify-content: center;">
            <div class="selectmenu" id="selectmenu-left" style="background-color: black; color: white;">진행중인 접수내역</div>
            <div class="selectmenu">지난 접수내역</div>
            <div class="selectmenu" id="selectmenu-right">취소/환불내역</div>
        </div>
        -->
        
    <script>
        
        $('.selectmenu').click(function () {
            const selectMenuColor = $(this); // 클릭된 요소만 선택

            console.log(selectMenuColor);

            // 클릭된 요소의 배경색이 검정색이 아니라면 스타일 적용
            if (selectMenuColor.css('background-color') !== 'rgb(0, 0, 0)') {
                // 클릭된 요소에 스타일 적용
                selectMenuColor.css({
                    'background-color': 'black',
                    'color': 'white'
                });

                // 다른 .selectmenu 형제 요소에 스타일 초기화
                selectMenuColor.siblings('.selectmenu').css({
                    'background-color': 'rgb(229, 229, 229)',
                    'color': 'black'
                });
            }
        });

    </script>




</body>
</html>