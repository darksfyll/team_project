<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문완료</title>
<style>
	* {
		padding: 0;
		margin: 0;
	}
	
	#wrap {
		margin: 100px auto;
		padding-top: 15px;
		width: 900px;
		height: 900px;
		background-color: lightgray;
		border: 1px solid black;
	}
	
	h1 {
		margin-top: 15px;
		text-align: center;
	}
	
	#wrap div {
		width: 900px;
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
</style>
</head>

<body>
	<div id="wrap">
		<h1>회원가입</h1>
		<div>
			<form action="memberJoin" method="post" name="joinForm">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="memberId" onkeydown="inputIdCheck()" id="memberId"></td>
						<td>
							<input type="button" name="idCheckOk"  value="중복체크" onclick="idCheck()">
							<input type="hidden" name="idDup" value="idUnCheck">
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="memberPwd"></td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td><input type="password" name="pwdCheck"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="memberName"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="memberPhone"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" name="memberZipcode" class="postcodify_postcode5"></td>
						<td><input type="button" id="postcodify_search_button" value="검색"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="memberAddress" class="postcodify_address"></td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td><input type="text" name="memberAddressDetail" class="postcodify_details"></td>
					</tr>
					<tr>
						<td>기타</td>
						<td><input type="text" name="memberAddressEtc" class="postcodify_extra_info"></td>
					</tr>
				</table>
				<br> <input class="btn01" type="button" value="회원가입" onclick="join()">
				<input class="btn01" type="button" value="취소"
					onclick="location.href='index.jsp'">
			</form>
		</div>
	</div>
</body>
</html>