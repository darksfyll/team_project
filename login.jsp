<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	* {
		padding: 0;
		margin: 0;
	}

	#login {
		margin: 300px auto;
		padding-top: 15px;
		width: 300px;
		height: 300px;
		background-color: lightgray;
		border: 1px solid black;
	}
	
	h1 {
		text-align: center;
		margin-bottom: 10px;
	}
	
	#login input[type="text"], input[type="password"], input[type="submit"]
		{
		width: 220px;
		height: 30px;
		margin-top: 10px;
		margin-left: 40px;
		border: 1px solid black;;
	}
	
	input[type="submit"]:hover {
		background-color: darkgray;
	}
	
	input[type="checkbox"] {
		margin-left: 40px;
		margin-bottom: 12px;
	}
	
	p {
		margin-top: 20px;
		margin-left: 35px;
	}
	
	a:link {
		text-decoration-line: none;
		color: black;
	}
	
	a:visited {
		color: black;
	}
</style>
</head>
<body>
	<div id="login">
		<h1>로그인</h1>
		<form action="memberLogin" method="post">
			<div>
				<input type="text" name="memberId" placeholder=" ID"><br> <input
					type="password" name="memberPwd" placeholder=" PASSWORD">
			</div>
			<br> 
			<%-- 
			<input type="checkbox" name="autoLogin"> 로그인 상태 유지<br>
			--%>
			<input type="submit" value="로그인"><br>
			<p>
				<a href="search.jsp">아이디/비밀번호찾기</a>&nbsp;&nbsp;&nbsp;<a
					href="agree.jsp">회원가입</a>
			</p>
		</form>
	</div>
	<c:if test="${failed == 0}">
		<script>
			alert("아이디 혹은 비밀번호가 틀립니다.")
		</script>
	</c:if>
</body>
</html>