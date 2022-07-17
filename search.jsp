<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기</title>
<style>
* {
	padding: 0;
	margin: 0;
}

#wrap {
	margin: 200px auto;
	padding-top: 15px;
	width: 500px;
	height: 450px;
	background-color: lightgray;
	border: 1px solid black;
}

h1 {
	margin-top: 15px;
	text-align: center;
}

#wrap div {
	width: 450px;
	margin-left: 45px;
	margin-top: 30px;
}

table {
	font-weight: 700;
}

.btn01 {
	float: right;
	margin-right: 10px
}

input[type="text"], input[type="password"] {
	width: 200px;
	height: 25px;
	border: 1px solid black;
}

input[type="button"] {
	width: 100px;
	height: 27px;
	border: 1px solid black;
}

input[type="button"]:hover {
	background-color: darkgray;
}

input[type="date"] {
	width: 200px;
	height: 25px;
	border: 1px solid black;
}

select {
	width: 100px;
	height: 27px;
	border: 1px solid black;
}

#cancel {
	float: right;
	margin-right: 20px;
	margin-top: 35px;
}
</style>
</head>

<body>
	<div id="wrap">
		<h1>SEARCH</h1>
		<div>
			<h3>아이디 찾기</h3>
			<br>
			<form action="searchId" method="post">
				<table>
					<tr>
						<td>이름</td>
						<td><input type="text" name="memberName"></td>
						<td>&nbsp;<input type="submit" value="찾기"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="memberPhone"></td>
					</tr>
				</table>
			</form>
			<c:if test="${findId == 0 }">
				<script>
					alert("올바른 정보를 입력해주세요.")
				</script>
			</c:if>
			<c:if test="${findId == 1 }">
				<script>
					alert("회원님의 아이디는 <c:out value = "${memberId}"/> 입니다.")
				</script>
			</c:if>
			<br> <br>
			<h3>비밀번호 찾기</h3>
			<br>
			<form action="searchPwd" method="post">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId"></td>
						<td>&nbsp;<input type="submit" value="찾기"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="memberPhone"></td>
					</tr>
				</table>
			</form>
			<c:if test="${findPwd == 0 }">
				<script>
					alert("올바른 정보를 입력해주세요.")
				</script>
			</c:if>
			<c:if test="${findPwd == 1 }">
				<script>
					alert("회원님의 비밀번호는 <c:out value = "${memberPwd}"/> 입니다.")
				</script>
			</c:if>
			<br> <input id="cancel" type="button" value="취소"
				onclick="location.href='index'">
		</div>
	</div>
</body>
</html>