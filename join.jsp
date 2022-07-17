<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	* {
		padding: 0;
		margin: 0;
	}
	
	#wrap {
		margin: 200px auto;
		padding-top: 15px;
		width: 500px;
		height: 420px;
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script>

	var form = document.joinForm;
	
	function join(){
		
		let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		let hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if(!form.memberId.value){
			alert("아이디를 입력해주세요.");
			form.id.focus();
			return;
		
		}else if(form.memberId.value.length <= 4 || form.memberId.value.length >= 16){
			alert("아이디는 4자이상 16자 이하로 입력해주세요.")
			form.id.focus();
			return;
		}else if(form.idDup.value != "idCheck"){
			alert("아이디 중복체크를 해주세요.")
			return;
			
		}else if(!form.memberPwd.value){
			alert("비밀번호를 입력해주세요.")
			form.pwd.focus();
			return;
		
		}else if(!reg.test(form.memberPwd.value)){
			alert("비밀번호는 8자리 이상, 대문자, 소문자, 숫자 ,특수문자가 모두 포함되어야 합니다.");
			form.pwd.focus();
			return;
		
		}else if(/(\w)\1\1\1/.test(form.memberPwd.value)){
			alert("같은 문자를 4번이상 연속 사용하실 수 없습니다")
			form.pwd.focus();
			return;
		
		}else if(form.memberPwd.value.search(form.memberId.value) != -1){
			alert("비밀번호에 아이디를 포함할 수 없습니다");
			form.pwd.focus();
			return;
		
		}else if(hangleCheck.test(form.memberPwd.value)){
			alret("비밀번호에 한글을 사용할 수 없습니다");
			form.pwd.focus();
			return;
		
		}else if(form.memberPwd.value.search(/\s/) != -1){
			alert("비밀번호에 공백 없이 입력해주세요");
			form.pwd.focus();
			return;
			
		}else if(form.memberPwd.value != form.pwdCheck.value){
			alert("비밀번호를 확인해주세요");
			form.pwd.focus();
			return;
			
		}else if(!form.memberName.value){
			alert("이름을 입력해주세요.");
			form.name.focus();
			return;
		
		}else if(!form.memberPhone.value){
			alert("전화번호를 입력해주세요.");
			form.phone.focus();
			return;
			
		}else if(!form.memberZipcode.value){
			alert("우편번호와 주소를 입력해주세요.")
			form.zipcode.focus();
			return;
			
		}else {
			form.submit();
		
		}	
		
	}
	
	function idCheck(){
		var memberId = joinForm.memberId.value;
		if(memberId.length==0 || memberId==""){
			alert("아이디를 입력해주세요.");
			form.memberId.focus();
		}else{
			window.open("idCheck?memberId="+memberId,"","width=500, height=300");
		}
	}
	
	function inputIdCheck(){
		var check = document.joinForm.idCheckOK;
		form.idDup.value="idUnCheck";
		form.idCheckOk.disabled=false;
		form.idCheckOk.style.opacity=1;
		/*form.idCheckOk.style.cursor="pointer";*/
	}


</script>
</html>