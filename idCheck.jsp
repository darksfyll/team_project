<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>아이디 중복 확인</h4>
	<br>
	<form name="checkIdForm">
		<input type="text" name="memberId" value="${memberId }"  disabled>
		<c:choose>
		<c:when test="${idCheck == 1 }">
			<p>이미 사용 중인 아이디입니다.</p>
			<input type="hidden" name="checkResult" value="N">
		</c:when>
		<c:when test="${idCheck == 0 }">
			<p>사용가능한 아이디입니다.</p>
			<input type="hidden" name="checkResult" value="Y">
		</c:when>
		</c:choose>
		
		<input type="button" onclick="window.close()" value="취소"/><br>
		<input type="button" onclick="sendCheck()" value="사용하기"/>
	
	</form>


</body>

<script>

	function sendCheck(){
		
		var openJoinForm = opener.document.joinForm;
	
		if(document.checkIdForm.checkResult.value=="N"){
			alert("다른 아이디를 입력해주세요.");
			openJoinForm.memberId.focus();
			window.close();
		}else {
			openJoinForm.idDup.value = "idCheck";
			openJoinForm.idCheckOk.disabled=true;
			openJoinForm.idCheckOk.style.opacity=0.6;
			/*openJoinForm.idCheckOK.style.cursor="default";*/
			window.close();
		}
		
	}
</script>
</html>