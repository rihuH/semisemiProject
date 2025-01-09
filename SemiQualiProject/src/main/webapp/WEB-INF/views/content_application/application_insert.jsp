<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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

        
        #application-submit-outter{
            width: 900px;
            height: auto;
            margin: auto;
            padding-bottom: 30px;
            background-color: rgb(239, 239, 239);
        }
        #application-submit-inner{
        	width: 850px;
            height: auto;
            margin: auto;
            margin-top: 20px;
            padding-bottom: 30px;
            background-color: white;
        }

        #application-submit-pro{
            width: 850px;
            height: auto;
            display: flex;
            margin: auto;
            align-items: center;
            background-color: white;
            text-align: center;
            justify-content: center;
            
        }
        
        #exam_place{
            width: 850px;
            height: auto;
            display: flex;
            margin: auto;
            align-items: center;
            background-color: white;
            text-align: center;
            justify-content: center;
        }
        
       	table {
			border-collapse: collapse;
			border:1px solid black;
		}
		
		table th,td{
            height: 60px;
			border-collapse: collapse;
			border:1px solid black;
		}
		
		table th{
			background-color: rgb(227, 227, 227);
		}
		
		table td{
            background-color: rgb(253, 253, 253);
        }

        .btn{
            width: 100px;
            height: 40px;
            border-radius: 7px;
            border: 1px solid lightgray;
        }
        
        .modal{
            position: absolute;
            display: none;
            justify-content: center;

            top: 0;
            left: 0;

            width: 100%;
            height: 100%;
            
            background-color: none;
            background-color: rgba(0, 0, 0, 0.5);
            
        }
        
        .modal-body{
            position: absolute;
            top: 50%;

            background-color: white;
            
            transform: translateY(-50%);
            
            width: 500px;
            height: 700px;
            
            border-radius: 10px;
            text-align: center;
        }

    </style>
</head>
<body>
    
    <div id="content-right">
        <div id="address">
            <a href="#">홈</a> > 
            <a href="#">메뉴 이름</a> > 
            <a href="#">상세 메뉴 이름1</a> > 
            <a href="#">상세메뉴1</a>
        </div>

        <div style="margin-left: 20px; margin-top: 10px;">
            <p id="menuname">원서접수</p>
        </div>

        <div style="border-bottom: 2px solid gray; margin-top: 15px; margin-left: 25px; width: 900px; display: flex;"></div>
        
        <div id="application-submit-outter">
        	<div id="application-submit-inner">
                <div>
                    <h4 style="margin-left: 15px; margin-top: 10px; font-weight: bold;">
                    	${ takenQualiExam.examStartDate.substring(0, takenQualiExam.examStartDate.indexOf('-'))}년 
                    	${ qualificationName } 
                        ${ round }회  
                            <c:choose>
                                <c:when test="${ qualificationExam.qualificationRank eq 1}">
                                1차
                                </c:when>
                                <c:otherwise>
                                2차
                                </c:otherwise>
                            </c:choose>
                            시험장소
                    </h4>
                    
                    <div id="exam_place">
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 690px;">장소</th>
                                    <th style="width: 130px;">선택</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ examPlaceList }" var="p">
                                    <tr>
                                        <td>
                                            ${p.place.district.cityName } ${ p.place.district.district } ${ p.place.locationName }
                                        </td>
                                        <td>
							               <button 
							                    type="button" 
							                    class="btn" 
							                    id="open-modal"
							            		data-examLocationNo="${p.examLocationNo }"
							                    data-toggle="modal" 
							                    data-target="#modal-out">
							                    접수하기
							                </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    
                    
                </div>
        	</div>

        </div>


        <div class="modal" id="modal-out">
            <div class="modal-body">
                <div class="modal-header">
                    약관에 동의하셔야 접수가 가능합니다.
                </div>
                <div class="modal-content">
                    <label><input type="checkbox" class="agree-checkbox"> 약관내용 1</label>
                    <label><input type="checkbox" class="agree-checkbox"> 약관내용 2</label>
                    <label><input type="checkbox" class="agree-checkbox"> 약관내용 3</label>
                    <label><input type="checkbox" class="agree-checkbox"> 약관내용 4</label>
                    <label><input type="checkbox" class="agree-checkbox"> 약관내용 5</label>
                </div>
                <div class="modal-footer">
                	<form action="/quali/application_record/${ sessionScope.loginMember.memberId }" method="post">
               			<input type="hidden" name="examNo" value="${ takenQualiExam.examNo }">
               			<input type="hidden" name="examLocationNo" value="">
               			<input type="hidden" name="memNo" value="${ sessionScope.loginMember.memberNo }">
               			<input type="hidden" name="memberId" value="${ sessionScope.loginMember.memberId }">
	                    <button class="btn" id="agree-btn" disabled>완료</button>
                	</form>
                </div>
            </div>
        </div>
            
    </div>
        
        
	<script>

		$(document).ready(function () {
		    // 모달 열기 버튼 클릭 이벤트
		    $(document).on("click", "#open-modal", function () {
		        // 버튼의 data-* 속성 값 가져오기
		        var cityName = $(this).data("cityname");
		        var district = $(this).data("district");
		        var location = $(this).data("location");
	
		        // hidden input에 값 설정
		        $("#data-cityName").val(cityName);
		        $("#data-district").val(district);
		        $("#data-location").val(location);
	
		        // 모달 표시 (display를 flex로 변경)
		        $("#modal-out").css("display", "flex");
		    });
	
		    // 약관 체크박스 상태 변경 이벤트
		    $(document).on("change", ".agree-checkbox", function () {
		        // 모든 체크박스가 선택되었는지 확인
		        var allChecked = $(".agree-checkbox").length === $(".agree-checkbox:checked").length;
		        // 완료 버튼 활성화 또는 비활성화
		        $("#agree-btn").prop("disabled", !allChecked);
		    });
		    
           // 버튼 클릭 이벤트
           $(document).on('click', '#open-modal', function () {
               // 버튼의 data-examLocationNo 값을 가져옴
               const examLocationNo = $(this).data('examlocationno');

               // Modal 내 input에 값 설정
               $('#modal-out input[name="examLocationNo"]').val(examLocationNo);
           });
           
           
		});

        

        
	</script>	




</body>
</html>