<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<Style>
* {
	padding: 0;
	margin: 0;
}

img {
	display: block;
}

li {
	list-style: none;
}

body {
	background-color: #4d4d4d;
}

#wrap {
	width: 1600px;
	overflow: hidden;
	margin: 0 auto;
	position: relative;
}

nav {
	width: 200px;
	height: 100%;
	position: fixed;
	background-color: #000;
}

section {
	width: 1400px;
	height: 980px;
	position: relative;
	background-color: lightgray;
	/*float: right;*/
	top: 0;
	left: 200px;
	overflow: hidden;
}

#header {
	color: white;
	text-align: center;
	margin: 35px;
	font-size: 30px;
}

ul {
	margin-left: 35px;
	margin-top: 20px
}

ul a {
	display: inline-block;
	text-decoration: none;
	color: silver;
	font-size: 15px;
	font-weight: bold;
	margin-bottom: 20px;
}

ul a:hover {
	color: white;
}

.menu01 {
	color: white;
	display: inline-block;
	font-size: 22px;
	font-weight: bold;
	margin-bottom: 20px;
}

#main:link {
	text-decoration-line: none;
}

#main:visited {
	color: white;
}

section div {
	width: 700px;
	margin: 100px auto;
}

h1 {
	font-size: 250%;
	text-align: center;
	margin-bottom: 50px;
}

#info {
	margin: 0px auto;
	margin-top: 20px;
	width: 600px;
	height: 600px;
	padding: 0px;
}

input[type="button"] {
	float: right;
	margin-right: 25px;
	margin-top: 10px;
}

input[type="button"] {
	width: 100px;
	height: 28px;
	border: 1px solid black;
}

input[type="button"]:hover {
	background-color: darkgray;
}

input[type="submit"] {
	float: right;
	margin-right: 25px;
	margin-top: 10px;
}

input[type="submit"] {
	width: 100px;
	height: 28px;
	border: 1px solid black;
}

input[type="submit"]:hover {
	background-color: darkgray;
}
#btn {
	margin-right: 49px;
}
#plzLogin{
	margin-top: 300px;
}

table{
	width:600px;
	height: 600px;
	background-color: white;
	color: black;
	text-align: center;
}

table, td, th{
	border: 1px solid black;
	border-collapse: collapse;
}

td{
	height: 60px;
	width: 450px;
}
.td01{
	width: 100px;
}
</Style>
</head>

<body>
	<div id="wrap">
		<nav>
			<h1 id="header"><a id="main" href="index">LAPTOP<br />ZONE</a></h1>
			<ul>
				<li><p class="menu01">MEMBER</p></li>
				<c:choose>
					<c:when test="${memberId ne null}">
						<li><a href="memberLogout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="agree.jsp">회원가입</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${memberId eq 'admin' }">
						<li><a href="memberList">회원관리</a>
						<li><a href="qnaList">Q&#38;A</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="cartList?memberId=${memberId }">장바구니</a></li>
						<li><a href="orderList?memberId=${memberId }">주문목록</a></li>
						<li><a href="memberInfo?memberId=${memberId }">회원정보</a></li>
						<li><a href="qnaList">Q&#38;A</a></li>
					</c:otherwise>
				</c:choose>
				<li><p class="menu01">CATEGORY</p></li>
				<li><a href="categoryAll">노트북 전체</a></li>
				<li><a href="category?productCategory=Macbook">애플 맥북</a></li>
				<li><a href="category?productCategory=Ultrabook">울트라북</a></li>
				<li><a href="category?productCategory=Gaming">게이밍 노트북</a></li>
				<li><a href="category?productCategory=Office">사무용 노트북</a></li>
				<li><a href="category?productCategory=Etc">노트북 주변기기</a></li>
			</ul>
		</nav>

		<section>
			<c:choose>
				<c:when test="${memberId ne null }">
					<div>
					
					<c:choose>
						<c:when test="${updateCheck == 1 }">
							<h1>회원정보수정</h1>
							<form action="memberUpdate?memberId=${updateInfo.memberId}" method="post" name="updateForm">
							<div id="info">
								<hr>
								
								<table>
									<tr>
										<td class="td01">아이디</td>
										<td><input type="text" name="memberId" value="${updateInfo.memberId}" readonly></td>
									</tr>
									<tr>
										<td class="td01">비밀번호</td>
										<td><input type="password" name="memberPwd" value="${updateInfo.memberPwd }"></td>
									</tr>
									<tr>
										<td class="td01">이름</td>
										<td><input type="text" name="memberName" value="${updateInfo.memberName }"></td>
									</tr>
									<tr>
										<td class="td01">전화번호</td>
										<td><input type="text" name="memberPhone" value="${updateInfo.memberPhone }"></td>
									</tr>
									<tr>
										<td class="td01">우편번호</td>
										<td><input type="text" name="memberZipcode" class="postcodify_postcode5" value="${updateInfo.memberZipcode }"></td>
										<td><input type="button" id="postcodify_search_button" value="검색"></td>
									</tr>
									<tr>
										<td class="td01">주소</td>
										<td><input type="text" name="memberAddress" class="postcodify_address" value="${updateInfo.memberAddress }"></td>
									</tr>
									<tr>
										<td class="td01">상세주소</td>
										<td><input type="text" name="memberAddressDetail" class="postcodify_details" value="${updateInfo.memberAddressDetail }"></td>
									</tr>
									<tr>
										<td class="td01">기타주소</td>
										<td><input type="text" name="memberAddressEtc" class="postcodify_extra_info" value="${updateInfo.memberAddressEtc }"></td>
									</tr>
									<tr>
										<td class="td01">가입일자</td>
										<td>${updateInfo.regdate }</td>
									</tr>
								</table>
							</div>
							<input id="btn" type="button" value="수정완료" onclick="memberUpdate()"> 
							<input type="button" value="취소" onclick="location.href='memberInfo?memberId=${updateInfo.memberId}'">
							</form>
						</c:when>
						<c:otherwise>
							<h1>회원정보</h1>
							<div id="info">
								<hr>
								<table>
									<tr>
										<td class="td01">아이디</td>
										<td>${memberInfo.memberId}</td>
									</tr>
									<tr>
										<td class="td01">비밀번호</td>
										<td>${memberInfo.memberPwd }</td>
									</tr>
									<tr>
										<td class="td01">이름</td>
										<td>${memberInfo.memberName }</td>
									</tr>
									<tr>
										<td class="td01">전화번호</td>
										<td>${memberInfo.memberPhone }</td>
									</tr>
									<tr>
										<td class="td01">우편번호</td>
										<td>${memberInfo.memberZipcode }</td>
									</tr>
									<tr>
										<td class="td01">주소</td>
										<td>${memberInfo.memberAddress }</td>
									</tr>
									<tr>
										<td class="td01">상세주소</td>
										<td>${memberInfo.memberAddressDetail }</td>
									</tr>
									<tr>
										<td class="td01">기타주소</td>
										<td>${memberInfo.memberAddressEtc }</td>
									</tr>
									<tr>
										<td class="td01">가입일자</td>
										<td>${memberInfo.regdate }</td>
									</tr>
								</table>
							</div>
							<input id="btn" type="button" value="회원정보수정"
								onclick="location.href='updateInfo?memberId=${memberInfo.memberId}'"> <input
								type="button" value="회원탈퇴"
								onclick="location.href='memberDelete?memberId=${memberInfo.memberId}'">
						</c:otherwise>
					</c:choose>
					</div>
				</c:when>
				<c:otherwise>
					<h1 id="plzLogin">로그인을 해주세요.</h1>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
<c:if test="${update == 1 }">
	<script>
		alert('회원정보가 수정되었습니다.');
	</script>
</c:if>	
</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script>
	
	var form = document.updateForm;

	function memberUpdate(){
		
		let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		let hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if(!form.memberPwd.value){
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
			
		}else if(!form.memberName.value ){
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

</script>
</html>