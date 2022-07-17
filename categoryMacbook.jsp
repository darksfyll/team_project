<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애플 맥북</title>
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
		overflow: scroll;
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
	
	.img {
		width: 220px;
		height: 220px;
	}
	img{
		width: 210px;
		height: 210px;
	}
	
	.name {
		width: 460px;
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
	
	.price {
		width: 200px;
		height: 200px;
	}

	
	#category {
		width: 510px;
		display: inline-block;
		margin-left: 0px;
	}
	
	#category li {
		float: left;
		width: 100px;
		height: 25px;
		border: 1px solid black;
		background-color: white;
		text-align: center;
		margin-bottom: 10px;
	}
	
	#category a {
		color: black;
	}
	
	#right {
		float: right;
		width: 300px;
		height: 30px;
		display: inline-block;
		margin: 0px;
	}
	
	#right input[type="text"] {
		float: right;
		margin-right: 15px;
		height: 23px;
		margin-top: 24px;
		border:1px solid black;
	}
	
	#right input[type="button"] {
		float: right;
		width: 40px;
		height: 25px;
		margin-top: 24px;
		border:1px solid black;
	}
	
	#right input[type="button"]:hover {
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
	
	#reg{
		float: right;
		width: 40px;
		height: 26px;
		margin-top: 24px;
	}
	#reg:hover {
		background-color: darkgray;
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
				<h1>애플 맥북</h1>
				<ul id="category">
					<li><a href="category?productCategory=Macbook">신상품</a></li>
					<li><a href="orderBy?productCategory=Macbook&where=product_views&order=desc">인기상품</a></li>
					<li><a href="orderBy?productCategory=Macbook&where=product_price&order=asc">최저가</a></li>
					<li><a href="orderBy?productCategory=Macbook&where=product_price&order=desc">최고가</a></li>
				<c:if test="${memberId eq 'admin' }">
					<li><a href="writeProduct">상품등록</a></li>	
				</c:if>
				</ul>
				<div id="right">
					<input type="text" name="search"> 
					<input type="button"value="검색">
				</div>
				
				<c:choose>
					<c:when test="${memberId eq 'admin' }">
						<c:forEach var = "pdt" items="${productList }">
							<table>
								<tr>
									<td class="img" rowspan="3"><img src="productImage/${pdt.productImage }" alt="노트북 이미지" /></td>
									<td class="name"><a href="#">${pdt.productName }</a></td>
									<td class="price" rowspan="2">${pdt.productPrice }원</td>
									
								</tr>
								<tr>
									<td class="spec">${pdt.productDetail }</td>
								</tr>
								<tr>
									<td class="regdate">등록일자 ${pdt.productRegdate }</td>
									<td class="delete"><a href="#">삭제</a></td>
								</tr>
							</table>
							<br>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var = "pdt" items="${productList }">
							<table>
								<tr>
									<td class="img" rowspan="3"><img src="productImage/${pdt.productImage }" alt="노트북 이미지" /></td>
									<td class="name"><a href="productDetail?productNum=${pdt.productNum }">${pdt.productName }</a></td>
									<td class="price" rowspan="3">${pdt.productPrice }원</td>
								</tr>
								<tr>
									<td class="spec">${pdt.productDetail }</td>
								</tr>
								<tr>
									<td class="regdate">등록일자 ${pdt.productRegdate }</td>
								</tr>
							</table>
							<br>
						</c:forEach>
					</c:otherwise>	
				</c:choose>
				<div style="width:800px; text-align: center;">
					<c:forEach var="pgn" items="${pagination}">
						<c:choose>
							<c:when test="${views == 1 }">
								<a href="orderBy?productCategory=Macbook&where=product_views&order=desc&page=${pgn.pageNum }">
									<c:choose>
										<c:when test="${pgn.curPage }">
											<u>${pgn.display }</u>
										</c:when>
										<c:otherwise>
											${pgn.display }
										</c:otherwise>
									</c:choose>
								</a>
							</c:when>
							<c:when test="${lowest == 1 }">
								<a href="orderBy?productCategory=Macbook&where=product_price&order=asc&page=${pgn.pageNum }">
									<c:choose>
										<c:when test="${pgn.curPage }">
											<u>${pgn.display }</u>
										</c:when>
										<c:otherwise>
											${pgn.display }
										</c:otherwise>
									</c:choose>
								</a>
							</c:when>
							<c:when test="${highest == 1 }">
								<a href="orderBy?productCategory=Macbook&where=product_price&order=desc&page=${pgn.pageNum }">
									<c:choose>
										<c:when test="${pgn.curPage }">
											<u>${pgn.display }</u>
										</c:when>
										<c:otherwise>
											${pgn.display }
										</c:otherwise>
									</c:choose>
								</a>
							</c:when>
							<c:otherwise>
								<a href="category?productCategory=Macbook&page=${pgn.pageNum }">
									<c:choose>
										<c:when test="${pgn.curPage }">
											<u>${pgn.display }</u>
										</c:when>
										<c:otherwise>
											${pgn.display }
										</c:otherwise>
									</c:choose>
								</a>
							</c:otherwise>
						</c:choose>
						&nbsp;
					</c:forEach>
				</div>
			</div>
		</section>
	</div>
</body>
</html>