package controllerWeb;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conta;
import model.Login;
import model.Saque;

/**
 * Servlet implementation class SaqueJSP
 */
@WebServlet("/SaqueJSP.do")
public class SaqueJSP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaqueJSP() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			saque = new Saque(conta, valor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		saque.funcaoSacar();
		request.setAttribute("saque", saque);

		RequestDispatcher view = request.getRequestDispatcher("SaqueJSP.jsp");
		view.forward(request, response);
	}

}
