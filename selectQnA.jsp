<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 보기</title>
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

table, tr , th, td{
	border: 1px solid black;
	border-collapse: collapse;
}
table{
	width: 900px;
	background-color: white;
}
th{
	width: 250px;
	height: 40px;
}
td{
	width: 650px;
}
h1 {
		font-size: 250%;
		text-align: center;
		margin-bottom: 50px;
	}
input[type="text"]{
	width: 630px;
	height: 25px;
	margin-left: 10px;
}

textarea{
	width: 630px;
	height: 450px;
	margin: 10px;
}

input[type="button"] {
	width: 100px;
	height: 27px;
	margin: 0px;
	border: 1px solid black;
	float: right;
	margin-right: 20px;
}

input[type="button"]:hover {
	background-color: darkgray;
}
img{
	width:400px;
	height: 400px;
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
				<h1>QnA 보기</h1>
				<table>
					<tr>
						<th>제목</th>
						<td>${selectQnA.qnaTitle }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${selectQnA.qnaWriter }</td>
					</tr>
					<tr>
						<th>작성일자</th>
						<td>${selectQnA.qnaRegdate }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${selectQnA.qnaViews }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${selectQnA.qnaContent }</td>
					</tr>
				</table>
				<br>
				<c:if test="${memberId eq 'admin'}">
					<input type="button" value="답글쓰기" onclick="location.href='writeQnA?parentNum=${selectQnA.qnaNum}'">
				</c:if>
				<c:if test="${selectQnA.qnaWriter == memberId }">
					<input type="button" value="수정하기" onclick="location.href='writeQnA?qnaNum=${selectQnA.qnaNum}'">
					<input type="button" value="삭제하기" onclick="location.href='deleteQnA?qnaNum=${selectQnA.qnaNum}'">
				</c:if>
				<input class="btn" type="button" value="목록으로" onclick="location.href='qnaList'">
			</div>
		</section>
	</div>
</body>
</html>