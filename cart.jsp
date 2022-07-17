<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
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

#main:visited {
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

.img {
	width: 220px;
	height: 220px;
}

img{
	width: 210px;
	height: 210px;
}

.name {
	width: 420px;
	height: 30px;
}

.spec {
	width: 420px;
	height: 140px;
}

.regdate {
	width: 420px;
	height: 30px;
}
.amount{
	width: 100px;
}

.price {
	width: 170px;
	height: 200px;
}

.chk {
	width: 70px;
	height: 200px;
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
	font-size: 120%;
	text-align: center;
	font-weight: bold;
}
#plzLogin{
	margin-top: 300px;
}
</Style>
</head>
<body>

	<div id="wrap">
		<nav>
			<h1 id="header"><a id="main" href="index.jsp">LAPTOP<br />ZONE</a></h1>
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
						<h1>장바구니</h1>
						<div id="btn">
							 <input type="button" value="전체 삭제" onclick="location.href='deleteCartAll?&memberId=${memberId }'"> 
							 <input type="button" value="전체 구매" onclick="location.href='orderCart?memberId=${memberId}'">
						</div>
						
						<c:forEach var="cart" items="${cartList }">
							<table>
								<tr>
									<td class="img" rowspan="3"><img src="productImage/${cart.productImage }" alt="노트북 이미지" /></td>
									<td class="name"><a href="#">${cart.productName}</a></td>
									<td class="amount" rowspan="3">수량 ${cart.amount }</td>
									<td class="price" rowspan="3">${cart.productPrice * cart.amount}원</td>
									<td class="chk" rowspan="3">
										<input type="button" value="삭제" onclick="location.href='deleteCart?cartNum=${cart.cartNum}&memberId=${memberId }'">
									</td>
								</tr>
								<tr>
									<td class="spec">${cart.productDetail}</td>
								</tr>
								<tr>
									<td class="regdate">${cart.productRegdate}</td>
								</tr>
							</table>
							<br>
						</c:forEach>
						<br>
						<p>총 상품금액 = ${sum }원</p>
					</div>
					
					<div style="width:800px; text-align: center;">
						<c:forEach var="pgn" items="${pagination }">
							<a href="cartList?memberId=${memberId }&page=${pgn.pageNum }">
								<c:choose>
									<c:when test="${pgn.curPage }">
										<u>${pgn.display }</u>
									</c:when>
									<c:otherwise>
										${pgn.display }
									</c:otherwise>
								</c:choose>
							</a>&nbsp;	
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<h1 id="plzLogin">로그인을 해주세요.</h1>
				</c:otherwise>
			</c:choose>

		</section>
	</div>
</body>
</html>