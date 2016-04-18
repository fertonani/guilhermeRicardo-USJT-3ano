<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Extrato"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Extratando</title>
</head>
<body>
	<%
		Extrato extrato = (Extrato) request.getAttribute("extrato");
	%>
	<table>
		<tr>
			<td>Transação</td>
			<td>Data</td>
			<td>Tipo</td>
			<td>Valor</td>
		</tr>
		<%
			for (int i = 0; i < extrato.getLista().size(); i++) {
		%>
		<tr>
			<td><%=extrato.getLista().get(i).getNumDoc() %></td>
			<td><%=extrato.getLista().get(i).getData() %></td>
			<td><%=extrato.getLista().get(i).getTipo() %></td>
			<td><%=extrato.getLista().get(i).getValor() %></td>
		</tr>

		<%
			}
		%>
	</table>
</body>

</html>