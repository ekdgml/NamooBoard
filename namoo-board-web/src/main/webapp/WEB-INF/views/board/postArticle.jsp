<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 등록하기</title>
</head>
<body>
	<form action="./post.do" method="post">
		<input type="hidden" name="boardNo" value="${boardNo}" />
		<input type="text" name="subject" />
		<input type="submit" value="등록" /> <br/>
		<textarea rows="20" cols="30" name="content"></textarea>
	</form>
</body>
</html>