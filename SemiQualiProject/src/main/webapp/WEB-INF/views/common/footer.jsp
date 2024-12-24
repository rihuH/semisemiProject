<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>

        #footer{
            border-top: 1px solid rgb(219, 219, 219);
            background-color: rgb(245, 245, 245);
            width: 100%;
            height: 300px;
            margin: auto;
            display: flex;
            justify-content: center;

        }

        #footer_sum{
            width: 100%;
            height: 100%;
        }

        #footer-left{
            width: 400px;
            height: 300px;
            display: flex;
            float: left;
        }


        #footer-center{
            width: 400px;
            height: 300px;
            display: flex;
            float: left;
            justify-content: center;
            text-align: center;
        }
        

        #footer-right{
            width: 400px;
            height: 300px;
            display: flex;
            float: left;

        }



    </style>


</head>
<body>

    <div id="footer">
        <div id="footer-sum">
            <div id="footer-left">
            
            </div>
            <div id="footer-center">
                <h1>footer영역</h1>
            </div>
            <div id="footer-right">

            </div>
        </div>
    </div>
    
</body>
</html>