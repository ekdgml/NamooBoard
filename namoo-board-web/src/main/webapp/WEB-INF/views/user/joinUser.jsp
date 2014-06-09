<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입하기</title>
</head>
<body>
	<form action="./join.do" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="userEmail" /></td>
			</tr>
		</table>
		<input type="submit" value="등록" />
	</form>
</body>
</html>