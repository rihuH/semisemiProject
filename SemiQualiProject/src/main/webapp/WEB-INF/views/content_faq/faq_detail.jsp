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

        #commentAreaOut{
            background-color:rgb(247, 245, 245);
            width: 900px;
            height: 200px;
            display: flex;
            margin: auto;
            margin-bottom: 30px;

        }

        #commentAreaIn{
            background-color: white;
            width: 700px;
            height: 100px;

            display: none;

            align-items: center;
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

            <a class="btn btn-secondary" style="float:right;" href="help/ask">목록으로</a>
            <br><br>

            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ answer.answerTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ answer.memNo }</td>
                    <th>작성일</th>
                    <td>${ answer.answerCreatedAt.substring(0, 10) }</td>
                </tr>
                <tr>
                    <th>질문내용</th>
                    <td colspan="1">
                    <th>수정일</th>
                    <td>${ answer.answerCreatedAt.substring(0, 10) }</td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ answer.answerContent }</p></td>
                </tr>
                
                
            </table>
            <div align="center">
                <c:if test="${ answer.memNo eq sessionScope.loginMember.memberId }">
                    <a class="btn btn-primary" onclick="postSubmit(1);">수정하기</a>
                    <a class="btn btn-danger" onclick="postSubmit(2);">삭제하기</a>
                </c:if>
                <c:if test="${ sessionScope.loginMember.status == 'A' }">
                    <a class="btn btn-danger" onclick="postSubmit(2);">질문삭제하기</a>
                    <a class="btn btn-secondary" onclick="insertCommentArea();">답변작성,수정</a>
                </c:if>
            </div>
            <br>
            
            
            <c:if test="${ comment.commentStatus == 'N' }">
          		<table id="contentArea" align="center" class="table">
                    <tr>
                        <th>작성자</th>
                            <td>${ comment.memNo }</td>
                            <th>작성일</th>
                            <td>${ comment.commentCreatedAt.substring(0, 10) }</td>
                        </tr>
                        <th>답변내용</th>
                        <td colspan="1">
                            <th>수정일</th>
                            <td>${ comment.commentUpdatedAt.substring(0, 10) }</td>
                            <tr>
                            </tr>
                            <tr>
                                <td colspan="4"><p style="height:150px;">${ comment.commentContent }</p></td>
                        </tr>
                    </table>
                    <div align="center">
                        <c:if test="${ sessionScope.loginMember.status == 'A' }">
                            <a class="btn btn-danger" onclick="commentSubmit(2);">답변삭제하기</a>
                        </c:if>
                    </div>
            </c:if>
            
            <br>
            
            
            <br>

            
            <script>
				function postSubmit(num){
					if(num == 1){
						$('#postForm').attr('action', '/quali/faq/answer-update').submit();
					} else{
						if(confirm("질문을 삭제 하시겠습니까?")){
						$('#postForm').attr('action', '/quali/answer/delete').submit();
						}
					}
				}

                function insertCommentArea(){
                    if($('#commentAreaIn').css('display') === 'none'){
                        $('#commentAreaIn').css('display', 'flex');
                    } else {
                        $('#commentAreaIn').css('display', 'none');
                    }
                }
            
            </script>
            
            
           	<div id="commentAreaIn">
	            <form action="" method="post" id="postForm">
	            	<input type="hidden" name="answerNo" value="${ answer.answerNo }"/>
	            	<input type="hidden" name="memNo" value="${ answer.memNo }" />
		        	<input type="text" name="commentContent" style="width: 600px; height: 70px;" required>
	            </form>
                <div style="height: 100%; align-items: center;">
                    <a class="btn btn-secondary" onclick="insertComment();" style="width: 60px;height: 35px; margin-left: 10px; margin-top: 5px;">작성</a>
                    <a class="btn btn-primary" onclick="commentSubmit(1);" style="width: 60px;height: 35px; margin-left: 10px; margin-top: 5px;">수정</a>
                </div>
		       		
	        </div>
        
        <script>
        	function insertComment(){
        		
        		const commentNull = ${comment == null};
        		
        		if(commentNull){
					$('#postForm').attr('action', '/quali/faq/comment-insert').submit();
        		} else {
					alert('이미 답변이 게시되어있습니다.');        			
        		}
        	}
        	
			function commentSubmit(num){
				if(num == 1){
					$('#postForm').attr('action', '/quali/faq/comment-update').submit();
				} else{
					if(confirm("답변을 삭제 하시겠습니까?")){
						$('#postForm').attr('action', '/quali/comment/delete').submit();
					}
				}
			}
        
        </script>
    </div>
    
    <!--
        <div>
            <div id="commentAreaOut">
                <div id="commentAreaIn">
                    <form action="" method="post">
                        <input type="text" style="width: 600px; height: 100px;">
                    </form>
                    <a class="btn btn-secondary onclick="insertComment();" style="height: 40px; margin-left: 10px;">답변달기</a>
                </div>
            </div>
        </div>
    -->
    
</body>
</html>