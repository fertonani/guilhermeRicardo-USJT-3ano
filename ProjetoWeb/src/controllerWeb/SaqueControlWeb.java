package controllerWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conta;
import model.Login;
import model.Saque;

@WebServlet("/Saque.do")
public class SaqueControlWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Saque saque;
	private Conta conta;
	private Login login;
	private double valor;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

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
		try {
			valor = Double.parseDouble(request.getParameter("valor"));
			System.out.println("valor" + valor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			saque = new Saque(conta, valor);
			System.out.println("saque");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saque.funcaoSacar();
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Saque efetuado</title></head><body>");
		out.println("<h1>SAQUE EFETUADO</h1>");
		out.println("Nome: " + conta.getNome() + "<br>");
		out.println("Saldo: " + conta.getSaldo() + "<br>");
		out.println("Valor do saque: R$" + valor);
		out.println("</body></html>");

	}

}
