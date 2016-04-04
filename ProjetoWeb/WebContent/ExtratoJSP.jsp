<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="to.ExtratoTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ExtratoTO extrato = (ExtratoTO) request.getAttribute("extrato");
	%>
	<table>
		<tr>
			<td>Transacao</td>
			<td>Data</td>
			<td>Tipo</td>
			<td>Valor</td>
		</tr>
		<%
			for (int i = 0; i < extrato.getListaExtrato().size(); i++) {
		%>
		<tr>
			<td><%=extrato.getListaExtrato().get(i).getNumDoc() %></td>
			<td><%=extrato.getListaExtrato().get(i).getData() %></td>
			<td><%=extrato.getListaExtrato().get(i).getTipo() %></td>
			<td><%=extrato.getListaExtrato().get(i).getValor() %></td>
		</tr>

		<%
			}
		%>
	</table>
</body>

</html>