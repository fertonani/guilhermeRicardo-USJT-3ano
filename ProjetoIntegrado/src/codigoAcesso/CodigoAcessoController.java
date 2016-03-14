package codigoAcesso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import login.Login;
import model.Conta;
import painel.PainelController;

public class CodigoAcessoController extends CodigoAcessoForm {
	private ResourceBundle bn;
	private Login login;
	private int[] senhaDigitada, senha;
	private int contador, contaCod, cod;

	public CodigoAcessoController(ResourceBundle bn, Login login) {
		super(bn);
		this.bn = bn;
		this.login = login;
		senhaDigitada = new int[6];
		senha = new int[8];
		contador = 0;
		contaCod = 0;
		addAcessarListener(new AcessarListener());
		addBut1Listener(new But1Listener());
		addBut2Listener(new But2Listener());
		addBut3Listener(new But3Listener());
		addBut4Listener(new But4Listener());
		addBut5Listener(new But5Listener());
	}

	class AcessarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Conta conta = null;
			if (!(senhaDigitada[0] == -1)) {
				try {
					conta = new Conta(login);
					boolean validaParaExibicao = false;
					for (int i = 0; i < 8; i++) {
						if (conta.getCod() == codigoAcesso()[i]) {
							validaParaExibicao = true;
							new PainelController(bn, conta);
							dispose();
						}
					}
					if(validaParaExibicao == false){
						JOptionPane.showMessageDialog(null,
								bn.getString("codigoAcessoForm.codErrado"),
								bn.getString("geral.mensagem.informacao"),
								JOptionPane.ERROR_MESSAGE);
						contador = 0;
						contaCod = 0;
						senha = new int[8];
						ativarBotoes();
						setCodigo(bn.getString("codigoAcessoForm.instrucao"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.codErrado"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.ERROR_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));
			}

		}
	}

	public void addSenha(int digito) {
		char[] valor = String.valueOf(digito).toCharArray();
		int a = Integer.parseInt(valor[0] + "");
		int b = Integer.parseInt(valor[1] + "");
		senhaDigitada[contaCod] = a;
		contaCod++;
		senhaDigitada[contaCod] = b;
		contaCod++;
		contador++;
	}

	public int[] codigoAcesso() {
		String x1 = String.valueOf(senhaDigitada[0])
				+ String.valueOf(senhaDigitada[2]
						+ String.valueOf(senhaDigitada[4]));
		String x2 = String.valueOf(senhaDigitada[0])
				+ String.valueOf(senhaDigitada[2]
						+ String.valueOf(senhaDigitada[5]));
		String x3 = String.valueOf(senhaDigitada[0])
				+ String.valueOf(senhaDigitada[3]
						+ String.valueOf(senhaDigitada[4]));
		String x4 = String.valueOf(senhaDigitada[0])
				+ String.valueOf(senhaDigitada[3]
						+ String.valueOf(senhaDigitada[5]));
		String x5 = String.valueOf(senhaDigitada[1])
				+ String.valueOf(senhaDigitada[2]
						+ String.valueOf(senhaDigitada[4]));
		String x6 = String.valueOf(senhaDigitada[1])
				+ String.valueOf(senhaDigitada[2]
						+ String.valueOf(senhaDigitada[5]));
		String x7 = String.valueOf(senhaDigitada[1])
				+ String.valueOf(senhaDigitada[3]
						+ String.valueOf(senhaDigitada[4]));
		String x8 = String.valueOf(senhaDigitada[1])
				+ String.valueOf(senhaDigitada[3]
						+ String.valueOf(senhaDigitada[5]));
		senha[0] = Integer.parseInt(x1);
		senha[1] = Integer.parseInt(x2);
		senha[2] = Integer.parseInt(x3);
		senha[3] = Integer.parseInt(x4);
		senha[4] = Integer.parseInt(x5);
		senha[5] = Integer.parseInt(x6);
		senha[6] = Integer.parseInt(x7);
		senha[7] = Integer.parseInt(x8);
		return senha;
	}

	class But1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton aux = getBut1();
			aux.setEnabled(false);
			setBut1(aux);
			if (contador == 0) {
				setCodigo("*");
				addSenha(12);
			} else if (contador > 0 && contador < 3) {
				setCodigo("*" + getCodigo());
				addSenha(12);
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.cheio"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.WARNING_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));

			}
		}
	}

	class But2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton aux = getBut2();
			aux.setEnabled(false);
			setBut2(aux);
			if (contador == 0) {
				setCodigo("*");
				addSenha(34);
			} else if (contador > 0 && contador < 3) {
				setCodigo("*" + getCodigo());
				addSenha(34);
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.cheio"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.WARNING_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));
			}
		}
	}

	class But3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton aux = getBut3();
			aux.setEnabled(false);
			setBut3(aux);
			if (contador == 0) {
				setCodigo("*");
				addSenha(56);
			} else if (contador > 0 && contador < 3) {
				setCodigo("*" + getCodigo());
				addSenha(56);
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.cheio"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.WARNING_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));
			}
		}
	}

	class But4Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton aux = getBut4();
			aux.setEnabled(false);
			setBut4(aux);
			if (contador == 0) {
				setCodigo("*");
				addSenha(78);
			} else if (contador > 0 && contador < 3) {
				setCodigo("*" + getCodigo());
				addSenha(78);
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.cheio"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.WARNING_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));
			}
		}
	}

	class But5Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton aux = getBut5();
			aux.setEnabled(false);
			setBut5(aux);
			if (contador == 0) {
				setCodigo("*");
				addSenha(90);
			} else if (contador > 0 && contador < 3) {
				setCodigo("*" + getCodigo());
				addSenha(90);
			} else {
				JOptionPane.showMessageDialog(null,
						bn.getString("codigoAcessoForm.cheio"),
						bn.getString("geral.mensagem.informacao"),
						JOptionPane.WARNING_MESSAGE);
				contador = 0;
				contaCod = 0;
				senha = new int[8];
				ativarBotoes();
				setCodigo(bn.getString("codigoAcessoForm.instrucao"));
			}
		}
	}

	public void ativarBotoes() {
		getBut1().setEnabled(true);
		setBut1(getBut1());

		getBut2().setEnabled(true);
		setBut2(getBut2());

		getBut3().setEnabled(true);
		setBut3(getBut3());

		getBut4().setEnabled(true);
		setBut4(getBut4());

		getBut5().setEnabled(true);
		setBut5(getBut5());
	}

}
