<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 수정</h1>
	<p>
		전화번호 수정 화면입니다. <br>
		아래 항목을 기입하고 "수정" 버튼을 클릭하세요.
	</p>
	
	<form action="/phonebook3/update" method="get">
		이름: <input type="text" name="name" value="${personVo.name }"> <br>
		핸드폰: <input type="text" name="hp" value="${personVo.hp }"> <br>
		회사: <input type="text" name="company" value="${personVo.company }"> <br>
		<input type="text" name="personId" value="${personVo.personId }"> <br>
		<button type="submit">수정</button>
	</form>
	
	<a href="/phonebook3/list">리스트 바로가기</a>

</body>
</html>