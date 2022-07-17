<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 주문</title>
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
	height: auto;
	min-height: 980px;
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
	#main:visited{
		color: white;
	}

section div {
	width: 900px;
	margin: 100px auto;
}

h1 {
	font-size: 250%;
	text-align: center;
	margin-bottom: 50px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: white;
	text-align: center;
	clear: both;
}

.th01 {
	width: 150px;
	height: 150px;
}

.th02 {
	width: 450px;
	height: 40px;
}
img{
	width: 145x;
	height: 145px;
}

input[type="button"] {
	width: 80px;
	height: 26px;
	border: 1px solid black;
}

input[type="button"]:hover {
	background-color: darkgray;
}

table a {
	font-weight: bold;
	color: black;
}

table a:link {
	text-decoration-line: none;
}

table a:visited {
	color: black;
}



#btn {
	width: 400px;
	height: 26px;
	margin: 0px;
	margin-bottom: 10px;
	margin-top: 10px;
}

p {
	font-size: 110%;
	text-align: center;
	font-weight: bold;
}

.order {
	width: 900px;
}

.order td {
	height: 40px;
}

.td01 {
	width: 350px;
}

input {
	width: 350px;
	height: 30px;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid black;;
}

input[type="button"] {
	float: right;
	width: 110px;
	margin-right: 5px;
	margin-top: 7px;
}


#zip{
	margin-left: 0px;
}
.td02{
	width:120px;
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
			<div>
				<h1>제품 주문</h1>
				<form action="insertCartOrder?memberId=${memberId }&totalPrice=${sum}" method="post">
					<table>
						<c:forEach var="cart" items="${cartList }">
							<tr>
								<th class="th01"><img src="productImage/${cart.productImage }" /></th>
								<th class="th02">${cart.productName}</th>
								<th class="th01">수량 ${cart.amount }</th>
								<th class="th01">${cart.productPrice * cart.amount}원</th>
							</tr>
						</c:forEach>
							<tr>
								<th class="th02" colspan="4">총 상품금액 = ${sum }원</th>
							</tr>
					</table>
					<br>
					<table class="order">
						<tr>
							<td colspan="3">주문자 정보
							</td>
						</tr>
						<tr>
							<td class="td01">아이디</td>
							<td>${memberId }</td>
							<td class="td02" rowspan="7"><input type="button" id="postcodify_search_button" value="검색"></td>
						</tr>
						<tr>
							<td class="td01">수취인</td>
							<td><input type="text" name="receiverName" value="${memberInfo.memberName }"></td>
						</tr>
						<tr>
							<td class="td01">수취인 전화번호</td>
							<td><input type="text" name="receiverPhone" value="${memberInfo.memberPhone }"></td>
						</tr>
						<tr>
							<td class="td01">우편번호</td>
							<td>
								<input type="text" name="zipcode" class="postcodify_postcode5" value="${memberInfo.memberZipcode}">
							</td>
						</tr>
						<tr>
							<td class="td01">주소</td>
							<td><input type="text" name="address" class="postcodify_address" value="${memberInfo.memberAddress}"></td>
						</tr>
						<tr>
							<td class="td01">상세주소</td>
							<td><input type="text" name="addressDetail" class="postcodify_details" value="${memberInfo.memberAddressDetail}"></td>
						</tr>
						<tr>
							<td class="td01">기타주소</td>
							<td><input type="text" name="addressEtc" class="postcodify_extra_info" value="${memberInfo.memberAddressEtc}"></td>
						</tr>

					</table>
					<input class="order" type="submit" value="주문하기"> 
					<input type="button" value="취소" onclick="history.back()">
				</form>
			</div>
		</section>
	</div>
</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>


</html>