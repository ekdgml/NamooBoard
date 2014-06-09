<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록보기</title>
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
		<table>
			<thead>
				<tr>
					<th>No.</th>
					<th>게시판 이름</th>
					<th>관리자Id</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boards}">
					<tr>
						<td>${board.boardNo}</td>
						<td>${board.boardSubject}</td>
						<td>${board.manager.userId}</td>
						<td><button onclick="location.href='${ctx}/board/articleList.do?boardNo=${board.boardNo}'">게시물목록</button></td>
						<td><button onclick="location.href='${ctx}/board/removeBoard.do?boardNo=${board.boardNo}'">삭제하기</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>