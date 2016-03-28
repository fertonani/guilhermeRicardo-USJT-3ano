package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

import controller.PainelController;
import model.Conta;

public class TransferenciaForm extends JFrame implements Observer {
	private JLabel nome, agencia, conta, valor;
	private JTextField fAgencia, fConta, fValor;
	private JButton transferir;
	private JPanel pTopo, pBaixo, pCentro;
	private ResourceBundle bn;
	private Conta contas;

	public TransferenciaForm(ResourceBundle bnn, final Conta contas) {
		super(bnn.getString("transferenciaForm.titulo"));
		bn = bnn;
		this.contas = contas;
		setLayout(new BorderLayout());
		pTopo = new JPanel();
		pTopo.setLayout(new FlowLayout(FlowLayout.CENTER));
		nome = new JLabel("<<NOME DO CLIENTE>>");
		pTopo.add(nome);

		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(3, 2, 3, 4));
		agencia = new JLabel(bn.getString("transferenciaForm.agencia"));
		conta = new JLabel(bn.getString("transferenciaForm.conta"));
		valor = new JLabel(bn.getString("transferenciaForm.valor"));
		fAgencia = new JTextField(10);
		fConta = new JTextField(10);
		fValor = new JTextField(10);
		pCentro.add(agencia);
		pCentro.add(fAgencia);
		pCentro.add(conta);
		pCentro.add(fConta);
		pCentro.add(valor);
		pCentro.add(fValor);

		pBaixo = new JPanel();
		pBaixo.setLayout(new FlowLayout(FlowLayout.RIGHT));
		transferir = new JButton(bn.getString("transferenciaForm.bTransferir"));
		pBaixo.add(transferir);

		add(pTopo, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pBaixo, BorderLayout.SOUTH);

		// Ao fechar a janela, volta ao painel de menus
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				new PainelController(bn, contas);
				dispose();
			}
		});
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}
	public void addTransferirListener(ActionListener e){
		transferir.addActionListener(e);
	}
	public void setNome(String nome){
		this.nome.setText(nome);
	}
	public String getAgencia(){
		return fAgencia.getText();
	}
	public String getConta(){
		return fConta.getText();
	}
	public String getValor(){
		return fValor.getText();
	}
	public void setValor(String texto){
		fValor.setText(texto);
	}
}
