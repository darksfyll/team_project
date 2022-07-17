<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
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
	height: auto; min-height : 980px;
	position: relative;
	background-color: lightgray;
	/*float: right;*/
	top: 0;
	left: 200px;
	overflow: hidden;
	min-height: 980px;
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

.reg{
	width: 200px;
	height: 40px;
}
.name{
	width: 400px;
	height: 40px;
}
.price{
	width: 200px;
	height: 40px;
}
.amount{
	width: 100px;
	height: 40px;
}
td{
	height: 40px;
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

#plzLogin {
	margin-top: 300px;
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
						<h1>주문 목록</h1>
						<table>
							<tr>
								<th class="reg">주문일자</th>
								<th class="name">상품명</th>
								<th class="price">가격</th>
								<th class="amount">수량</th>
							</tr>
							<c:forEach var="orderList" items="${orderList }">
								<tr>
									<td>${orderList.orderRegdate }</td>
									<td>${orderList.productName}</td>
									<td>${orderList.productPrice }</td>
									<td>${orderList.amount}</td>		
								</tr>
							</c:forEach>
						</table>
					</div>
					<div style="width:800px; text-align: center;">
						<c:forEach var="pgn" items="${pagination }">
							<a href="orderList?memberId=${memberId }&page=${pgn.pageNum }">
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