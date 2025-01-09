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
            background-color: lightgray;
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
        
        #application-submit-tech{
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
				<h2 style="margin-top: 15px; margin-left: 16px; font-weight: bold; font-size: 30px;">국가전문자격 시험</h2>
	            <div id="application-submit-pro">
		            <table>
		                <thead>
		                    <tr>
		                        <th style="width: 345px">응시시험</th>
		                        <th style="width: 345px">접수기간</th>
		                        <th style="width: 130px">선택</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <!-- 전문자격증 반복-->
		                    <c:forEach items="${ proList }" var="c">
		                        <tr>
		                            <td>${c.examStartDate.substring(0, c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.profesionalQualification.qualificationName } 
		                            ${ c.round }회  
		                                <c:choose>
		                                    <c:when test="${ c.qualificationExam.qualificationRank eq 1}">
		                                    1차
		                                    </c:when>
		                                    <c:otherwise>
		                                    2차
		                                    </c:otherwise>
		                                </c:choose>
		                            </td>
		                            <td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
		                            <td>
	                                    <form action="/quali/taken_quali_exam/application_list/${c.examNo}" method="post">
	    	                                <input type="hidden" name="examNo" value="${ c.examNo }" />
			                                <button class="btn">접수하기</button>
                                	    </form>	
                                    </td>
		                        </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
	            </div>
	
				<h2 style="margin-top: 15px; margin-left: 16px; font-weight: bold; font-size: 30px;">국가기술자격 시험</h2>
				<div id="application-submit-tech">
		            <table>
		                <thead>
		                    <tr>
		                        <th style="width: 345px">응시시험</th>
		                        <th style="width: 345px">접수기간</th>
		                        <th style="width: 130px">선택</th>
		                    </tr>
		                </thead>
		                <tbody>
		                    <!-- 기술자격증 반복-->
							<c:forEach items="${ techList }" var="c">
								<tr id="quali">
									<td>${c.examStartDate.substring(0,c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.technicalQualification.qualificationName }
									${ c.round }회
										<c:choose>
											<c:when test="${ c.qualificationExam.qualificationRank eq 1 }">
											필기
											</c:when>
											<c:otherwise>
											실기
											</c:otherwise>
										</c:choose>
									</td>
									<td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
		                            <td>
	                                    <form action="/quali/taken_quali_exam/application_list/${c.examNo}" method="post">
	    	                                <input type="hidden" name="examNo" value="${ c.examNo }" />
			                                <button class="btn">접수하기</button>
                                	    </form>	
                                    </td>
								</tr>
							</c:forEach>
		                </tbody>
		            </table>
				</div>
        	</div>
        </div>

            
    </div>
        
        
	<script>

	
	</script>	




</body>
</html>