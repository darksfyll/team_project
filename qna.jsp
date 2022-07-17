<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA</title>
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
		width: 700px;
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
	}
	
	.num {
		width: 75px;
	}
	
	.title {
		width: 325px;
		height: 40px;
		
	}
	
	.writer {
		width: 125px;
		height: 40px;
	}
	
	.regdate {
		width: 175px;
		height: 40px;
	}
	
	.views {
		width: 75px;
		height: 40px;
	}
	
	input[type="button"] {
		width: 100px;
		height: 27px;
		border: 1px solid black;
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
				<h1>Q&#38;A</h1>

				<table>
					<tr>
						<th class="num">번호</th>
						<th class="title">제목</th>
						<th class="writer">작성자</th>
						<th class="regdate">작성시간</th>
						<th class="views">조회수</th>
					</tr>
					<c:forEach var="qna" items="${qnaList }">
						
						<tr>
							<td class="num">${qna.qnaNum }</td>
							<c:choose>
								<c:when test="${qna.parentNum > 0 }">
									<td class="title"><a href="selectQnA?qnaNum=${qna.qnaNum }"><span style="padding-left: 40px;">${qna.qnaTitle }</span></a></td>
								</c:when>	
								<c:otherwise>
									<td class="title"><a href="selectQnA?qnaNum=${qna.qnaNum }">${qna.qnaTitle }</a></td>
								</c:otherwise>
							</c:choose>
							
							<td class="writer">${qna.qnaWriter }</td>
							<td class="regdate">${qna.qnaRegdate }</td>
							<td class="views">${qna.qnaViews }</td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<input type="button" value="글쓰기" onclick="goWrite()">
			</div>
			<div style="width:800px; text-align: center;">
						<c:forEach var="pgn" items="${pagination }">
							<a href="qnaList?page=${pgn.pageNum }">
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
		</section>
	</div>
</body>
<script>
	
	function goWrite(){
		
		if(${memberId eq null}){
			alert("로그인을 해주세요.");
		}else{
			location.href='writeQnA';
		}
	}
</script>
</html>