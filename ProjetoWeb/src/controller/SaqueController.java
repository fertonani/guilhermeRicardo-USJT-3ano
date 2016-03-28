package controller;

import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.Conta;
import model.Saque;
import view.SaqueForm;

public class SaqueController extends SaqueForm {
	ResourceBundle bn;
	private Conta conta;
	private Saque saque;

	public SaqueController(ResourceBundle bn, Conta conta) {
		super(bn, conta);
		this.bn = bn;
		this.conta = conta;
		super.setNome(conta.getNome());
		addOutroListener(new OutroListener());
		add10Listener(new Valor10Listener());
		add20Listener(new Valor20Listener());
		add50Listener(new Valor50Listener());
		add100Listener(new Valor100Listener());
		add200Listener(new Valor200Listener());
		add500Listener(new Valor500Listener());
	}

	class OutroListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double valor = 0;
			try {
				valor = Double.parseDouble(JOptionPane.showInputDialog(bn.getString("saqueForm.digiteValor")));
			} catch (Exception ev) {
				JOptionPane.showMessageDialog(null, bn.getString("geral.erro.numeroInteiro"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
				new SaqueController(bn, conta);
				dispose();
			}
			Saque saque;
			try {
				saque = new Saque(conta, valor);
				if (saque.verificarSaldo()) {
					try {
						if (saque.verificarNotas()) {
							if (saque.sacar()) {
								JOptionPane.showMessageDialog(null,
										bn.getString("saqueForm.saqueEfetuadoValor") + valor,
										bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
								new PainelController(bn, conta);
							} else {
								JOptionPane.showMessageDialog(null, bn.getString("geral.erro.numeroInteiro"),
										bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
									bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
							bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}

	}

	class Valor10Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 10.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "10,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class Valor20Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 20.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "20,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class Valor50Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 50.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "50,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class Valor100Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 100.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "100,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class Valor200Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 200.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "200,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class Valor500Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Saque saque = null;
			try {
				saque = new Saque(conta, 500.);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if (saque.verificarSaldo()) {
				try {
					if (saque.verificarNotas()) {
						try {
							saque.sacar();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saqueEfetuadoValor") + "500,00",
								bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						new PainelController(bn, conta);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, bn.getString("saqueForm.notasInsuficiente"),
								bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, bn.getString("saqueForm.saldoInsuficiente"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
