<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<Map<String, Object>> memberRows = (List<Map<String, Object>>) request.getAttribute("memberRows"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
// 아이디 중복 체크
function checkLoginIdDup() {
    var loginId = $('#loginId').val().trim();
    if (loginId.length == 0) {
        alert('아이디를 입력해주세요.');
        return;
    }

    $.get('../member/checkLoginIdDup?loginId=' + encodeURIComponent(loginId), function(data) {
        if (data.trim() == 'Y') {
            alert('이미 사용 중인 아이디입니다.');
        } else {
            alert('사용 가능한 아이디입니다.');
        }
    });
}

// 폼 전송 전 검사
function validateForm() {
    var loginId = $('#loginId').val().trim();
    var loginPw = $('#loginPw').val();
    var loginPwCheck = $('#loginPwCheck').val();
    var name = $('#name').val().trim();

    if (loginId.length == 0 || loginPw.length == 0 || loginPwCheck.length == 0 || name.length == 0) {
        alert('모든 항목을 입력해주세요.');
        return false;
    }

    if (loginPw !== loginPwCheck) {
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    }

    return true;
}
</script>
</head>
<body>
	<h2>회원가입</h2>
	<a href="../home/main">메인으로 이동</a>

	<form action="doJoin" method="POST" onsubmit="return validateForm();">
		<div>
			아이디 : 
			<input type="text" id="loginId" placeholder="아이디" name="loginId" />
			<button type="button" onclick="checkLoginIdDup()">중복확인</button>
		</div> 

		<div>
			비밀번호 : 
			<input type="password" id="loginPw" placeholder="비밀번호" name="loginPw" />
		</div>

		<div>
			비밀번호 확인 : 
			<input type="password" id="loginPwCheck" placeholder="비밀번호 확인" />
		</div>

		<div>
			이름 : 
			<input type="text" id="name" placeholder="이름" name="name" />
		</div>

		<button type="submit">작성완료</button>
	</form>
</body>
</html>
