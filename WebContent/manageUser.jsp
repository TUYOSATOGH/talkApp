<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トークアプリ</title>
</head>
<body>
	<h1>ユーザー管理</h1>
	<%
		if (errorMsg != null) {
	%>
	<p>
		<font color="red"><%=errorMsg%></font>
	</p>
	<%
		}
	%>
	<form method="post">
		ユーザー名：<input type="text" name="name"> <br> パスワード：<input
			type="password" name="pass"> <br> <input type="submit"
			value="登録" formaction="/talkApp/RegistServlet"> <input
			type="submit" value="登録解除" formaction="/talkApp/RevokeServlet">
	</form>
	<a href="/talkApp/">戻る</a>
</body>
</html>