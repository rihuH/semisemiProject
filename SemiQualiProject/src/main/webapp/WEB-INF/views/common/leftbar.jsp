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
                <div id="leftbar-menuname">메뉴 이름</div>

                <div class="descript" style="color: white; background-color: rgb(137, 137, 137);">
                    <div class="descript-main1" >상세 메뉴 이름1</div>
                </div>
                <div class="descript-menu" style="display: block;">
                    <li><a href="#" class="descript-menu-a" data-main="descript-main1" style="color: skyblue;">상세메뉴1</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main1">상세메뉴2</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main1">상세메뉴3</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main1">상세메뉴4</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main2">상세 메뉴 이름2</div>
                </div>
                <div class="descript-menu">
                    <li><a href="#" class="descript-menu-a" data-main="descript-main2">상세메뉴1</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main2">상세메뉴2</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main2">상세메뉴3</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main2">상세메뉴4</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main3">상세 메뉴 이름3</div>
                </div>
                <div class="descript-menu">
                    <li><a href="#" class="descript-menu-a" data-main="descript-main3">상세메뉴1</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main3">상세메뉴2</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main3">상세메뉴3</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main3">상세메뉴4</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main4">상세 메뉴 이름4</div>
                </div>
                <div class="descript-menu">
                    <li><a href="#" class="descript-menu-a" data-main="descript-main4">상세메뉴1</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main4">상세메뉴2</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main4">상세메뉴3</a></li>
                    <li><a href="#" class="descript-menu-a" data-main="descript-main4">상세메뉴4</a></li>
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



    </script>

    
</body>
</html>