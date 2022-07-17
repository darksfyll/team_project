<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
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
		height: 100%;
		position: relative;
		background-color: lightgray;
		/*float: right;*/
		top: 0;
		left: 200px;
		overflow: hidden;
	}
	
	.main {
		width: 1120px;
		position: relative;
		margin: 50px auto;
		height: 399px;
	}
	
	.main img {
		width: 1120px;
	}
	
	button {
		width: 100px;
		height: 100px;
		background-color: #000;
		border: none;
		border-radius: 50%;
		color: #fff;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -50px;
		margin-top: -50px;
	}
	
	.banner {
		width: 1155px;
		overflow: hidden;
		margin: 30px auto;
		margin-top: 350px;
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
	
	.form {
		width: 90px;
		height: 20px;
		float: left;
		margin-left: 20px;
	}
	
	.form input[type="text"] {
		width: 90px;
		height: 20px;
		border: 1px solid #aaaaaa;
		background-color: #818181;
	}
	
	.search {
		display: inline-block;
		width: 50px;
		height: 20px;
		color: #818181;
		border: 1px solid #aaaaaa;
		font-size: 11px;
		position: absolute;
		bottom: 78px;
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
	
	.banner img {
		width: 344px;
		height: 350px;
		float: left;
		margin: 0 20px;
		margin-bottom: 40px;
		box-shadow: 3px 3px 5px darkgray;
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
			<div class="main">
				<img src="img/main01.png" alt="" />
			</div>
		
			<div class="banner">
			<c:forEach var="image" items="${imageList }">
				<a href="productDetail?productNum=${image.productNum }"><img src="productImage/${image.productImage }" alt="대체이미지" /></a>
			</c:forEach>
			</div>
		</section>
	</div>

<c:choose>
	<c:when test="${logout == 1 }">
		<script>
		alert('로그아웃 되었습니다.');
		</script>	
	</c:when>
	<c:when test="${delete == 1 }">
		<script>
		alert('회원탈퇴가 완료되었습니다.');
		</script>	
	</c:when>
</c:choose>
</body>
</html>