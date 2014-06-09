<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 목록보기</title>
<style type="text/css">
table,th,td {
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
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>날짜</th>
				<th>작성자</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td>${article.articleNo}</td>
					<td>${article.articleSubject}</td>
					<td>${article.content}</td>
					<td>${article.articleDate}</td>
					<td>${article.author.userId}</td>
					<td><button
							onclick="location.href='${ctx}/board/removeArticle.do?articleNo=${article.articleNo}&boardNo=${boardNo}'">삭제하기</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="location.href='${ctx}/board/goPost.do?boardNo=${boardNo}'">게시글 등록</button>
	<button onclick=history.back();>뒤로가기</button>
</body>
</html>