<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 완료</title>
<style>
* {
	margin: 0;
	padding: 0;
}

#wrap {
	margin: 150px auto;
	width: 1000px;
	text-align: center;
	background-color: lightgray;
	border: 1px solid black;
	height: 625px;
}

table, tr, td {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: white;
}

table {
	width: 900px;
	margin-left: 50px;
}

td {
	height: 40px;
}

.td01 {
	width: 350px;
}

h1 {
	margin-top: 50px;
	margin-bottom: 50px;
}

input[type="button"] {
	width: 110px;
	height: 26px;
	border: 1px solid black;
	float: right;
	margin-top: 25px;
}

.btn01 {
	margin-right: 25px;
}

.btn02 {
	margin-right: 50px;
}

input[type="button"]:hover {
	background-color: darkgray;
}
</style>
</head>

<body>
	<div id="wrap">
		<h1>주문 완료</h1>
		<table>
			<tr>
				<td colspan="2">주문 상세</td>
			</tr>
			<tr>
				<td class="td01">주문번호</td>
				<td>${orderDetail.orderNum }</td>
			</tr>
			<tr>
				<td class="td01">주문일자</td>
				<td>${orderDetail.orderRegdate }</td>
			</tr>
			<tr>
				<td class="td01">총 가격</td>
				<td>${orderDetail.totalPrice }</td>
			</tr>
			<tr>
				<td class="td01">수취인</td>
				<td>${orderDetail.receiverName }</td>
			</tr>
			<tr>
				<td class="td01">수취인 전화번호</td>
				<td>${orderDetail.receiverPhone }</td>
			</tr>
			<tr>
				<td class="td01">우편번호</td>
				<td>${orderDetail.zipcode }</td>
			</tr>
			<tr>
				<td class="td01">주소</td>
				<td>${orderDetail.address }</td>
			</tr>
			<tr>
				<td class="td01">상세주소</td>
				<td>${orderDetail.addressDetail }</td>
			</tr>
			<tr>
				<td class="td01">기타주소</td>
				<td>${orderDetail.addressEtc }</td>
			</tr>

		</table>
		<input class="btn02" type="button" value="메인으로" onclick="location.href='index'"> 
		<input class="btn01" type="button" value="주문목록" onclick="location.href='orderList?memberId=${memberId}'">
	</div>
</body>
</html>