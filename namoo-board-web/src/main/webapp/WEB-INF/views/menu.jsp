<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	<c:if test="${loginUser.name != null}">
		<h3>${loginUser.name}님이 로그인 중입니다.</h3>
	</c:if>
	<table>
		<c:if test="${loginUser.name == null}">
			<tr>
				<td><button onclick="location.href='${ctx}/user/goLogin.do'">로그인</button></td>
			</tr>
		</c:if>
		<c:if test="${loginUser.name != null}">
			<tr>
				<td><button onclick="location.href='${ctx}/user/logout.do'">로그아웃</button></td>
			</tr>
		</c:if>
		<c:if test="${loginUser.name == null}">
		<tr>
			<td><button onclick="location.href='${ctx}/user/goJoin.do'">사용자
					등록</button></td>

		</tr>
		</c:if>
		<tr>
			<td><button onclick="location.href='${ctx}/user/userList.do'">사용자
					목록</button></td>
		</tr>
		<c:if test="${loginUser.name != null}">
		<tr>
			<td><button onclick="location.href='${ctx}/board/goCreate.do'">게시판
					등록</button></td>
		</tr>
		</c:if>
		<tr>
			<td><button onclick="location.href='${ctx}/board/boardList.do'">게시판
					목록</button></td>
		</tr>
	</table>
</body>
</html>