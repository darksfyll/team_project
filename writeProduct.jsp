<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
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
				<c:choose>
					<c:when test="">	
						<h1>상품 수정</h1>
					</c:when>
					<c:otherwise>
						<h1>상품 등록</h1>
					</c:otherwise>
				</c:choose>
				
				<form action="${action }" method="post" name="writeForm" enctype="multipart/form-data">
				<table>
					<tr>
						<th>카테고리</th>
						<td>
							<select name="productCategory">
								<option value="Macbook">애플 맥북</option>
								<option value="Ultrabook">울트라북</option>
								<option value="Gaming">게이밍 노트북</option>
								<option value="Office">사무용 노트북</option>
								<option value="Etc">주변기기</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>제품명</th>
						<td><input type="text" name="productName" value="${productDetail.productName }"></td>
					</tr>
					<tr>
						<th>제조사</th>
						<td><input type="text" name="productCompany" value="${productDetail.productCompany }"></td>
					</tr>
					<tr>
						<th>사진첨부</th>
						<td><input type="file" name="productImage" value=""></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="text" name="productPrice" value="${productDetail.productPrice }"></td>
					</tr>
					<tr>
						<th>수량</th>
						<td><input type="text" name="productStock" value="${productDetail.productStock }"></td>
					</tr>
					<tr>
						<th>제품상세</th>
						<td><textarea rows="30" name="productDetail">${productDetail.productDetail }</textarea></td>
					</tr>
					<tr>
						<td><input type="hidden" name="originalImage" value="${productDetail.productImage }"></td>
					</tr>
				</table>
				<br>
				<input type="button" value="작성완료" onclick="insert()">
				<input class="btn" type="button" value="취소" onclick="history.back()">
				</form>
			</div>
		</section>
	</div>
</body>
<script>
	
	var form = document.writeForm;
	
	function insert(){
		
		if(!form.productName.value){
			alert("상품명을 입력하세요.");
			form.productName.focus();
			return;
			
		}else if(!form.productCompany.value){
			alert("제조사를 입력하세요.");
			form.productPrice.focus();
			return;
		
		}else if(!form.productPrice.value){
			alert("가격을 입력하세요.");
			form.productPrice.focus();
			return;
			
		}else if(!form.productStock.value){
			alert("수량을 입력하세요.");
			form.productStock.focus();
			return;	
			
		}else if(!form.productDetail.value){
			alert("제품상세를 입력하세요.");
			form.productDetail.focus();
			return;	
			
		}else{
			form.submit();
		}
	}
	
</script>
</html>