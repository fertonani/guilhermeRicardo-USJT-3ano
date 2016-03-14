package login;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import codigoAcesso.CodigoAcessoController;

public class LoginController extends LoginForm {
	private Login login;
	private ResourceBundle bn;

	public LoginController(ResourceBundle bn) {
		super(bn);
		this.bn = bn;
		addLoginListener(new LoginListener());
	}

	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (getAgencia().equals("") || getConta().equals("")
					|| getSenha().equals("")) {
				JOptionPane.showMessageDialog(null,
						bn.getString("loginForm.acessoFalho"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					login = new Login(getAgencia(), getConta(), getSenha(),
							"000");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				login.efetuarLogin();
				boolean a, c, s;
				a = c = false;
				if (login.verificarAgencia()) {
					a = true;
					if (login.verificarConta() && a) {
						c = true;
						if (login.verificarSenha() && c) {
							new CodigoAcessoController(bn, login);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									bn.getString("loginForm.acessoFalho"),
									bn.getString("geral.mensagem.informacao"),
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								bn.getString("loginForm.acessoFalho"),
								bn.getString("geral.mensagem.informacao"),
								JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,
							bn.getString("loginForm.acessoFalho"),
							bn.getString("geral.mensagem.informacao"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
