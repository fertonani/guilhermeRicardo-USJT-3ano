package controllerWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conta;
import model.Extrato;
import model.Login;

/**
 * Servlet implementation class ExtratoControlWeb
 */
@WebServlet("/Extrato.do")
public class ExtratoControlWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Extrato extrato;
	private Conta conta;
	private Login login;

	public ExtratoControlWeb() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			login = new Login(request.getParameter("agencia"), request.getParameter("conta"), "1111", "111", true);
			System.out.println("login");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			conta = new Conta(login, true);
			System.out.println("conta");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Calendar agora = Calendar.getInstance();
		Calendar aux = Calendar.getInstance();
		aux.add(Calendar.DATE, -360);
		try {
			extrato = new Extrato(conta, aux.getTime(), agora.getTime());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Extratando</title></head><body>");
		out.println("<table><tr><td>Transacao</td> <td> Data</td> <td> Tipo </td><td> Valor </td></tr>");

		for (int i = 0; i < extrato.getLista().size(); i++) {
			out.println("<tr><td>" + extrato.getLista().get(i).getNumDoc() + "</td><td>"
					+ extrato.getLista().get(i).getData() + "</td><td>" + extrato.getLista().get(i).getTipo()
					+ "</td><td>" + extrato.getLista().get(i).getValor() + "</td></tr>");
		}
		out.println("</table></body><html>");
	}

}
