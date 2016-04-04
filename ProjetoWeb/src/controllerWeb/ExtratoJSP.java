package controllerWeb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Conta;
import model.Extrato;
import model.Login;

/**
 * Servlet implementation class ExtratoJSP
 */
@WebServlet("/ExtratoJSP.do")
public class ExtratoJSP extends HttpServlet {
	private Extrato extrato;
	private Conta conta;
	private Login login;


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
			conta = new Conta(login);
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
		request.setAttribute("extrato", extrato);
		RequestDispatcher view = request.getRequestDispatcher("Saque.jsp");
		view.forward(request, response);
	}
}
