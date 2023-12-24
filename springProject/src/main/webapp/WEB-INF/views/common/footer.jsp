<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>footer</title>
    <style>
        #footer {
            width:80%;
            height:200px; 
            margin:auto;
            margin-top:50px;
        }
        #footer-1 {
            width:100%;
            height:20%;
            border-top:1px solid lightgray;
            border-bottom:1px solid lightgray;
        }
        #footer-2 {width:100%; height:80%;}
        #footer-1, #footer-2 {padding-left:50px;}
        #footer-1>a {
            text-decoration:none;
            font-weight:600;
            margin:10px;
            line-height:40px;
            color:black;
        }
        #footer-2>p {
            margin:0;
            padding:10px;
            font-size:13px;
        }
        #p2 {text-align:center;}
    </style>
</head>
<body>
    <div id="footer">
        <div id="footer-1">
            <a href="#">이용약관</a> | 
            <a href="#">개인정보취급방침</a> | 
            <a href="#">인재채용</a> | 
            <a href="#">고객센터</a>
        </div>

        <div id="footer-2">
            <p id="p1">
				푸터푸터푸터푸터푸터푸터푸터<br>
				푸터푸터푸터푸터푸터푸터푸터<br>
				푸터푸터푸터푸터푸터푸터푸터<br>
            </p>
            <p id="p2">Copyright © 1998-2023 푸터푸터</p>
        </div>
    </div>
</body>
</html>