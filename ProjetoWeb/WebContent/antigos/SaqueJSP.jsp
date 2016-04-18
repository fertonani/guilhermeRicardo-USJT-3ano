<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Saque"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Saqueando via corrupção by Lula</title>
</head>
<body>
	<%
		Saque saque = (Saque) request.getAttribute("saque");
	%>
	<h1>SAQUE EFETUADO</h1>
	<br>Valor do saque: R$<%=saque.getValor()%>
</body>
</html>