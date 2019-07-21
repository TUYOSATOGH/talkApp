<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="logic.User"%>
<%
	boolean result = (boolean) session.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トークアプリ</title>
</head>
<body>
	<h1>ユーザー登録解除</h1>
	<%
		if (result) {
	%>
	<p>ユーザー登録解除に成功しました</p>
	<%
		} else {
	%>
	<p>ユーザー登録解除に失敗しました</p>
	<%
		}
	%>
	<a href="/talkApp/ManageServlet">戻る</a><br>
	<a href="/talkApp/">TOPへ</a>
</body>
</html>