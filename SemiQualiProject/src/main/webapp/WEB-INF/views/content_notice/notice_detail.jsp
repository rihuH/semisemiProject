<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style> 
        .content {
            background-color:rgb(247, 245, 245);
            width:900px;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:850px;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="notices">목록으로</a>
            <br><br>

            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ noticeList.noticeTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ noticeList.memNo }</td>
                    <th>작성일</th>
                    <td>${ noticeList.noticeCreateAt.substring(0, 10) }</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ noticeList.noticeContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <c:if test="${ noticeList.memNo eq sessionScope.loginMember.memberId }">
	                <a class="btn btn-primary" onclick="postSubmit(1);">수정하기</a>
	                <a class="btn btn-danger" onclick="postSubmit(2);">삭제하기</a>
                </c:if>
            </div>
            
            <script>
				function postSubmit(num){
					if(num == 1){
						$('#postForm').attr('action', '/quali/notice/notice-update').submit();
					} else{
						$('#postForm').attr('action', '/quali/notice/delete').submit();
					}
				}
            
            </script>
            
            <form action="" method="post" id="postForm">
            	<input type="hidden" name="noticeNo" value="${ noticeList.noticeNo }"/>
            	<input type="hidden" name="memNo" value="${ noticeList.memNo }" />
            	<!-- 
            	<input type="hidden" name="userId" value="${ loginUser.userId }" />
            	 -->
            </form>
            
            
            <br><br>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
             <!--
                <table id="replyArea" class="table" align="center">
                    <thead>
                        <tr>
                            <th colspan="2">
                                <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
                            </th>
                            <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
                        </tr>
                        <tr>
                            <td colspan="3">댓글(<span id="rcount">3</span>)</td>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>user02</th>
                        <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ꿀잼</td>
                        <td>2023-03-12</td>
                    </tr>
                    <tr>
                        <th>user01</th>
                        <td>재밌어요</td>
                        <td>2023-03-11</td>
                    </tr>
                    <tr>
                        <th>admin</th>
                        <td>댓글입니다!!</td>
                        <td>2023-03-10</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <br><br>
        -->
        
    </div>
    
</body>
</html>