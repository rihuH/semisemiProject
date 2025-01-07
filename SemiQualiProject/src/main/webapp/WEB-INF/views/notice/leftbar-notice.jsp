<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        div{
		overflow:hidden;
	    }

        #content{
            width: 180px;
            height: auto;
            margin: auto;

        }

        #content-left{
            width: 180px;
            height: 100%;
            float: left;
        }

        #leftbar-menuname{
            width: 100%;
            height: 120px;
            background-color: rgb(0, 0, 0);

            display: flex;
            justify-content: center;
            align-items: center;

            font-size: 30px;
            font-weight: bold;
            color: rgb(255, 255, 255);

            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }

        .descript{
            width: 100%;
            height: 60px;
            background-color: rgb(233, 233, 233);

            display: flex;
            justify-content: left;
            align-items: center;

            font-weight: bold;
            font-size: 18px;
        }

        .descript > div{
            margin-left: 15px;
        }


        .descript-menu{
            width: 100%;
            height: auto;
            display: none;
            margin-left: 10px;

            font-weight: bold;
            color: rgb(161, 161, 161);

            line-height: 30px;
        }

        li::marker{
            font-size: 8px;
        }

        .descript-menu > li > a{
            color: rgb(161, 161, 161);
            text-decoration: none;
        }


        


        

    </style>
</head>
<body>

    <div id="all">
        <div id="content">
            <div id="content-left">
                <div id="leftbar-menuname">고객지원</div>

                <div class="descript" style="color: white; background-color: rgb(137, 137, 137);">
                    <div class="descript-main1" >게시판</div>
                </div>
                <div class="descript-menu" style="display: block;">
                    <li><a href="notices" class="descript-menu-a" data-main="descript-main1" style="color: skyblue;">공지사항</a></li>
                    <li><a href="help/ask" class="descript-menu-a" data-main="descript-main1">직접질문하기</a></li>
                    <li><a href="help/faq" class="descript-menu-a" data-main="descript-main1">자주하는질문</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main2">수험자가이드</div>
                </div>
                <div class="descript-menu">
                </div>

                <div class="descript">
                    <div class="descript-main3">환불안내</div>
                </div>
                <div class="descript-menu">
                </div>

                <div class="descript">
                    <div class="descript-main4">부정신고센터</div>
                </div>
                <div class="descript-menu">
                </div>

            </div>
        </div>
    </div> 

    <script>
        // 왼쪽div - div 관련 스크립트
        $('.descript').click(function(){
            const descriptSlide = $(this).next();

            if(descriptSlide.css('display') == 'none'){
                descriptSlide.slideDown();

                descriptSlide.siblings('.descript-menu').slideUp();
            }
        });

        $('.descript').click(function(){
            const descriptBackgroundColor = $(this);

            if(!(descriptBackgroundColor.css('background-color') == 'rgb(137, 137, 137)')){
                descriptBackgroundColor.css('background-color', 'rgb(137, 137, 137)');
                descriptBackgroundColor.css('color', 'white');

                descriptBackgroundColor.siblings('.descript').css('background-color', 'rgb(233, 233, 233)');
                descriptBackgroundColor.siblings('.descript').css('color', 'black');
            }
        });

        
        
        
        // descript-menu - a태그 색변경 스크립트

        // 마우스 오버 이벤트
        $('.descript-menu > li > a')
            .mouseover(function() {
                if (!$(this).data('clicked')) { // 클릭 상태가 아니면 실행
                    $(this).css('color', 'skyblue');
                }
            })
            .mouseout(function() {
                if (!$(this).data('clicked')) { // 클릭 상태가 아니면 실행
                    $(this).css('color', '');
                }
        });

        // 클릭 이벤트
        $('.descript-menu-a').click(function () {
            $('.descript-menu-a').data('clicked', false).css('color', 'rgb(161, 161, 161)');
            
            $(this).data('clicked', true).css('color', 'skyblue');
        });

        // 오른쪽div - 현재위치 관련 스크립트

        $('.descript-menu-a').click(function() {
            const mainMenu = $('#menuname').text();
            const mainSelector = $(this).data('main');
            const mainValue = $(`.${mainSelector}`).text();

            const subValue = $(this).text();

            $('#address2').text(mainMenu);
            $('#address3').text(mainValue);
            $('#address4').text(subValue);

            $('#address2').attr('href', `#${mainMenu}`);
            $('#address3').attr('href', `#${mainSelector}`);
            $('#address4').attr('href', `#${subValue}`);
        });



    </script>

    
</body>
</html>