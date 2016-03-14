package login;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

public class LoginForm extends JFrame implements Observer {
	private JLabel lAgencia, lSenha, lConta;
	private JTextField fAgencia, fConta;
	private JPasswordField pSenha;
	private JButton bLogin;
	private JPanel pMeio, pBaixo, pAux;
	private ResourceBundle bn;
	private Login l;

	public LoginForm(ResourceBundle bn) {
		super(bn.getString("loginForm.titulo"));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.bn = bn;
		pAux = new JPanel();
		pAux.setLayout(new BorderLayout());
		pBaixo = new JPanel();
		pBaixo.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pMeio = new JPanel();
		pMeio.setLayout(new GridLayout(3, 1, 5, 5));

		lAgencia = new JLabel(bn.getString("loginForm.agencia"));
		pMeio.add(lAgencia);
		fAgencia = new JTextField(10);
		pMeio.add(fAgencia);
		fAgencia.requestFocus();

		lConta = new JLabel(bn.getString("loginForm.conta"));
		pMeio.add(lConta);
		fConta = new JTextField(10);
		pMeio.add(fConta);

		lSenha = new JLabel(bn.getString("loginForm.senha"));
		pMeio.add(lSenha);
		pSenha = new JPasswordField(10);
		pMeio.add(pSenha);

		bLogin = new JButton(bn.getString("geral.bLogin"));
		pBaixo.add(bLogin);

		pAux.add(pMeio, BorderLayout.CENTER);
		pAux.add(pBaixo, BorderLayout.SOUTH);
		add(pAux);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setResizable(false);
		setVisible(true);
	}

	public void addLoginListener(ActionListener e) {
		bLogin.addActionListener(e);
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}

	public String getAgencia(){
		return fAgencia.getText().trim();
	}
	public String getConta(){
		return fConta.getText().trim();
	}
	public String getSenha(){
		return pSenha.getText().trim();
	}
	
}
