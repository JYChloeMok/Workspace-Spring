<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입 양식</title>
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
    
    <!-- 메뉴바 -->
    <jsp:include page="../common/header.jsp" />
​
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>
​
            <form action="insert.me" method="post" id="enroll-form">
                <div class="form-group">
                	<!-- name속성의 userId => setUserId() 카멜케이스를 적용해서 setter찾음 / 그래서 핸들러에 작성 시 여기 name속성값이랑 필드명 맞춰야함 -->
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" required> <br>
	​					<div id="checkResult" style="font-size:0.7em; display:none;" >
							<!-- 중복체크 결과가 뜰 영역 / display none상태 -->
						</div>

                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="userPwd" required> <br>
​
                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>
​
                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="userName" required> <br>
​
                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>
​
                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>
​
                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>
    </div>
​
	<script>
	// 아이디 중복체크
	$(() => {
		const $idInput = $('#enroll-form #userId');
		const $checkResult = $('#checkResult');
		const $enrollFormSubmit = $('#enroll-form :submit');
		
		$idInput.keyup(() => {
			// 5글자 이상일 때만 ajax 요청
			if($idInput.val().length >= 5) {
				$.ajax(() => {
					url : 'idCheck.me',
					data : { checkId : $idInput.val() },
					method : 'GET',
					sucess : result => {
						if(result.slice(-1) == 'N') { // substr의 경우 deprecated
							$checkResult.show().css('color', 'crimson').text('중복된 아이디가 존재합니다');
							$enrollFormSubmit.attr('disabled', true);
						}
						else {
							$checkResult.show().css('color', 'lightgreen').text('등록 가능한 아이디입니다');
							$enrollFormSubmit.removeAttr('disabled');
						}
					},
					error : () => {
						alert('아이디 중복체크 실패');
					}
				});//
			}
			else {
				$checkResult.show().css('color', 'grey').text('5글자 이상 입력해주세요');
				$enrollFormSubmit.attr('disabled', true);
			}
		});
		
	});
	</script>	
	


    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp" />
​
</body>
</html>