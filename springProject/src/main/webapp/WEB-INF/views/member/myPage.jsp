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
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }
    </style>
</head>
<body>
    
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <form action="update.me" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <!-- Id는 변경 안되도록 readonly -->
                    <input type="text" class="form-control" id="userId" value="${ sessionScope.loginUser.userId }" name="userId" readonly> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" value="${ sessionScope.loginUser.userName }" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" value="${ sessionScope.loginUser.email }" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" value="${ sessionScope.loginUser.age }" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" value="${ sessionScope.loginUser.phone }" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" value="${ sessionScope.loginUser.address }" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                    
                    <script>
                    	$(function(){
                    		// gender에 값이 없다!
                    		// 실제 DB의 컬럼에 담긴 값은 null이지만
                    		// EL구문의 특성 상 값이 없을 경우 빈 문자열이 출력되므로 빈 문자열과 비교해야함
                   			/*
                   				if(${sessionScope.loginUser.gender})는 그냥 컬럼의 값 M이나 F
                   					그래서 문자열로 묶기위해 if('${sessionScope.loginUser.gender}' != '') {
                   				}
                   			*/
                   			if('${sessionScope.loginUser.gender}' != '') {
                   				$('input[value="${loginUser.gender}"]').attr('checked', true);                  			
                    		}
                    		// 내가 사용하는 환경에서 값을 어떻게 쓰는지 생각해야함!
                    		// ☆☆☆☆☆☆☆☆ 값이 뭐지 타입이 뭐지 어떻게 표현하지
                    		
                    	});
                    </script>
                    <!--
                    	JS내에서 EL구문을 사용할 때 조심해야 할 경우
                    -->
                    
                    
                    
                    
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.me" method="post">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger">탈퇴하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>