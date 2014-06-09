<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록보기</title>
<style type="text/css">
table,td,th {
	border: 1px solid #222;
}

table {
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>회원목록</h2>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>이메일</th>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.name}</td>
						<td>${user.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>