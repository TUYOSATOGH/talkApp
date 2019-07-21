<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="logic.User,logic.Mutter,java.util.List"%>
<%
	User loginUser = (User) session.getAttribute("loginUser");
	List<Mutter> mutterList = (List<Mutter>) request.getAttribute("mutterList");
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トークアプリ</title>
</head>
<body>
	<h1>トークアプリ</h1>
	<%
		if (errorMsg != null) {
	%>
	<p>
		<font color="red"><%=errorMsg%></font>
	</p>
	<%
		}
	%>
	<p>
		<%=loginUser.getName()%>さん、ログイン中 <a href="/talkApp/LogoutServlet">ログアウト</a>
	</p>
	<p>
		<a href="/talkApp/MainServlet">更新</a>
	</p>
	<form action="/talkApp/MainServlet" method="post">
		<input type="text" name="text"> <input type="submit"
			value="つぶやく">
	</form>
	<form action="/talkApp/DelServlet" method="post">
		<input type="submit" value="削除">
		<%
			int i = 0;
		%>
		<%
			for (Mutter mutter : mutterList) {
		%>
		<p>
			<input type="radio" name="radio" value=<%=i%>> <input
				type="hidden" name="name<%=i%>" value=<%=mutter.getUserName()%>>
			<input type="hidden" name="text<%=i%>" value=<%=mutter.getText()%>>
			<input type="hidden" name="time<%=i%>" value=<%=mutter.getTime()%>>
			<%=mutter.getUserName()%>:<%=mutter.getText()%>(<%=mutter.getTime()%>)
		</p>
		<%
			i++;
		%>
		<%
			}
		%>
	</form>
</body>
</html>