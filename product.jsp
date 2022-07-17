<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세</title>
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


table {
	width: 900px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: white;
	text-align: center;
	clear: both;
}

.img {
	width: 500px;
	height: 500px;
}
img{
	width: 490px;
	height: 490px;
}

.name {
	width: 400px;
	height: 100px;
}

td {
	height: 80px;
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

input[type="button"] {
	width: 100px;
	height: 27px;
	margin: 0px;
	border: 1px solid black;
}

input[type="button"]:hover {
	background-color: darkgray;
}
input[type="submit"] {
	width: 100px;
	height: 27px;
	margin: 0px;
	border: 1px solid black;
}

input[type="submit"]:hover {
	background-color: darkgray;
}

#detail {
	height: 50px;
}

.detail {
	height: 200px;
}

#review {
	height: 50px;
}

#num {
	width: 80px;
	height: 40px;
}

#title {
	width: 400px;
	height: 40px;
}

#writer {
	width: 150px;
	height: 40px;
}

#regdate {
	width: 230px;
	height: 40px;
}

#views {
	width: 80px;
	height: 40px;
}
.review{
	height: 40px;
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
			<form  action="addCart?productNum=${productDetail.productNum}&memberId=${memberId}" method="post" name="addCartForm">
				<table>
					<tr>
						<td class="img" rowspan="6"><img src="productImage/${productDetail.productImage }" alt="노트북 이미지" /></td>
						<td class="name">${productDetail.productName }</td>
					</tr>
					<tr>
						<td>${productDetail.productPrice }원</td>
					</tr>
					<tr>
						<td>무료배송</td>
					</tr>
					<tr>
						<td>수량 <input type="number" name="amount" value="1"></td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="바로구매" onclick="javascript: form.action='order?memberId=${memberId}&productNum=${productDetail.productNum }'"> 
							<input type="button" value="장바구니" onclick="plzLogin()">
						</td>
					</tr>
				</table>
			</form>	
				<br> <br>
				<table>
					<tr>
						<th id="detail">제품상세</th>
					</tr>
					<tr>
						<td class="detail">${productDetail.productDetail }</td>
					</tr>
				</table>
				<br> <br>
				<table>
					<tr>
						<th colspan="5" id="review">리뷰</th>
					</tr>
					<tr>
						<th id="title">제목</th>
						<th id="writer">작성자</th>
						<th id="regdate">작성일자</th>
						<th id="views">조회수</th>
					</tr>
					<c:forEach var="review" items="${reviewList }">
					<tr>
						<td class="review"><a href="selectReview?reviewNum=${review.reviewNum }">${review.reviewTitle }</a></td>
						<td class="review">${review.reviewWriter }</td>
						<td class="review">${review.reviewRegdate }</td>
						<td class="review">${review.reviewViews }</td>
					</tr>
					</c:forEach>
				</table>
				<br> <input type="button" value="글쓰기" onclick="goWrite()">
				<br>
				<div style="width:800px; text-align: center;">
				<c:forEach var="pgn" items="${pagination }">
					<a href="productDetail?productNum=${productDetail.productNum }&page=${pgn.pageNum }">
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
			</div>
		</section>
	</div>
</body>
<script>

	function plzLogin(){
		
		if(${memberId eq null}){
			alert("로그인을 해주세요.");
		}else{
			document.addCartForm.submit();
		}
	}
	
	function goWrite(){
		
		if(${memberId eq null}){
			alert("로그인을 해주세요.");
		}else{
			location.href='writeReview?productNum=${productDetail.productNum}';
		}
	}

</script>
</html>