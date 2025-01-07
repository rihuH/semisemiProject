<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        .selector-for-some-widget {
        box-sizing: content-box;
        }

        #login-form{
            width: 600px;
            margin: auto;
            
            margin-top: 20px;
            margin-bottom: 20px;

            box-shadow: 3px 3px 10px rgb(170, 170, 170);

            border-radius: 20px;

            padding-left: 20px;
            padding-right: 20px;
            padding-top: 20px;
        }


    </style>


</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

	<jsp:include page="../common/header.jsp"></jsp:include>

    <div id="login-background">
        <div id="login-form">
            <form action="login.me" method="post">
                <!-- Email input -->
                <div data-mdb-input-init class="form-outline mb-4">
                  <label class="form-label" for="form2Example1"">아이디</label>
                  <input type="text" id="form2Example1" class="form-control" name="memberId"/>
                </div>
              
                <!-- Password input -->
                <div data-mdb-input-init class="form-outline mb-4">
                  <label class="form-label" for="form2Example2">비밀번호</label>
                  <input type="password" id="form2Example2" class="form-control" name="memberPwd"/>
                </div>
              

              
                <!-- Submit button -->
                <button type="submit"  data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">로그인</button>
              
                <!-- Register buttons -->
                <div class="text-center" style="padding-bottom: 20px;">
                  <p>회원이 아닌가요? <a href="enrollform.me">회원가입</a></p> <p>비밀번호를 잊으셨나요? <a href="#!" >비밀번호찾기</a></p>

                </div>
              </form>

        </div>
    </div>
    
 		<jsp:include page="../common/footer.jsp"></jsp:include>
    

</body>
</html>