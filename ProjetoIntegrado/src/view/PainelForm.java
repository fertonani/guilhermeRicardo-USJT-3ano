package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

import controller.DebitoAutomaticoController;
import controller.SaldoController;

public class PainelForm extends JFrame implements Observer {
	private JLabel lNomeCliente;
	private JButton bSaque, bExtrato, bSaldo, bTransferencia, bDebitoAutomatico;
	private JPanel menu, nomeCliente;
	private BorderLayout layout;
	private ResourceBundle bn;

	public PainelForm(ResourceBundle bnn) {
		super(bnn.getString("painelForm.titulo"));
		bn = bnn;
		layout = new BorderLayout(6, 1);
		setLayout(layout);

		menu = new JPanel();
		menu.setLayout(new GridLayout(5, 1, 3, 3));

		nomeCliente = new JPanel();
		nomeCliente.setLayout(new FlowLayout(FlowLayout.CENTER));
		lNomeCliente = new JLabel("<<NOME DO CLIENTE>>");
		nomeCliente.add(lNomeCliente);
		add(nomeCliente, BorderLayout.NORTH);

		bSaque = new JButton(bn.getString("painelForm.saque"));
		menu.add(bSaque);
		bExtrato = new JButton(bn.getString("painelForm.extrato"));
		menu.add(bExtrato);
		bSaldo = new JButton(bn.getString("painelForm.saldo"));
		menu.add(bSaldo);
		bTransferencia = new JButton(bn.getString("painelForm.transferencia"));
		menu.add(bTransferencia);
		bDebitoAutomatico = new JButton(bn.getString("painelForm.debitoAutomatico"));
		menu.add(bDebitoAutomatico);

		add(menu, BorderLayout.CENTER);
		setSize(200, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String[] opcoes = { bn.getString("geral.bSim"), bn.getString("geral.bNao") };

				if (JOptionPane.showOptionDialog(null, bn.getString("geral.mensagem.confirmarSaida"),
						bn.getString("geral.mensagem.informacao"), JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]) == 0) {
					new IdiomaMain();
					dispose();
				}

			}
		});
	}
	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}
	public void setNome(String nome){
		lNomeCliente.setText(nome);
	}
	public void addSaqueListener(ActionListener e){
		bSaque.addActionListener(e);
	}
	public void addExtratoListener(ActionListener e){
		bExtrato.addActionListener(e);
	}
	public void addSaldoListener(ActionListener e){
		bSaldo.addActionListener(e);
	}
	public void addTransferenciaListener(ActionListener e){
		bTransferencia.addActionListener(e);
	}
	public void addDebitoAutomaticoListener(ActionListener e){
		bDebitoAutomatico.addActionListener(e);
	}

}
