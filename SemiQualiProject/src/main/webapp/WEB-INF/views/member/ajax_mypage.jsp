<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

	<style>
		#contentA{
			width: 1300px;
			height: auto;
			margin: auto;
			
			display: flex;
			justify-content: center;
			
		}
		
		
		#contentA-a{
			width: 180px;
			margin-top: 20px;
		}
		
		#contentA-b{
			width : 950px;
			margin-top: 20px;
		}
		
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
	
	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<div id="contentA">
            <div id="content-left">
                <div id="leftbar-menuname">마이페이지</div>

                <div class="descript" style="color: white; background-color: rgb(137, 137, 137);">
                    <div class="descript-main1" >원서접수관리</div>
                </div>
                <div class="descript-menu" style="display: block;">
                    <li><a href="#" class="descript-menu-a" data-target="../content_right/application_record.jsp" data-main="descript-main1" style="color: skyblue;">원서접수내역</a></li>
                    <li><a href="application-apply" class="descript-menu-a" data-main="descript-main1">원서접수신청</a></li>
                    <li><a href="exam-results" class="descript-menu-a" data-main="descript-main1">시험결과보기</a></li>
                    <li><a href="refund-request" class="descript-menu-a" data-main="descript-main1">사후환불신청</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main2">응시자격</div>
                </div>
                <div class="descript-menu">
                    <li><a href="eligibility-check" class="descript-menu-a" data-main="descript-main2">응시자격자가진단</a></li>
                    <li><a href="available-exams" class="descript-menu-a" data-main="descript-main2">응시가능종목확인</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main3">자격증취득조회</div>
                </div>
                <div class="descript-menu">
                    <li><a href="certificate-verify" class="descript-menu-a" data-main="descript-main3">자격증 확인/발급</a></li>
                    <li><a href="certificate-status" class="descript-menu-a" data-main="descript-main3">자격증 발급 현황</a></li>
                </div>

                <div class="descript">
                    <div class="descript-main4">개인정보관리</div>
                </div>
                <div class="descript-menu">
                    <li><a href="#" class="descript-menu-a" data-target="/content_right/edit_profile.jsp" data-main="descript-main4">개인정보/학력수정</a></li>
                    <li><a href="account-deletee" class="descript-menu-a" data-main="descript-main4">회원탈퇴</a></li>
                    <li><a href="favorite-certificates" class="descript-menu-a" data-main="descript-main4">관심자격증등록</a></li>
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
		<div id="contentA-b">
		</div>
	</div>
	
	<script>
		$(document).ready(function () {
		    // a 태그 클릭 이벤트
		    $('#contentA').on('click', '.descript-menu-a', function (e) {
		        e.preventDefault(); // 기본 이벤트 방지
	
		        // data-target에서 JSP URL 가져오기
		        const targetUrl = $(this).data('target');
		        console.log("Loading content from:", targetUrl);
	
		        if (!targetUrl) {
		            console.error("Error: data-target 속성이 정의되지 않았습니다.");
		            return;
		        }
	
		        // JSP 파일을 #content-container에 로드
		        $('#content-container').load(targetUrl, function (response, status, xhr) {
		            if (status === "error") {
		                console.error("AJAX Error: " + xhr.status + " " + xhr.statusText);
		                $('#content-container').html("<p>콘텐츠를 불러오는데 실패했습니다. 경로를 확인하세요.</p>");
		            } else {
		                console.log("Content loaded successfully.");
		            }
		        });
		    });
		});
	</script>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	

</body>
</html>