package saldo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

import model.Conta;
import painel.PainelForm;
import painel.PainelController;

public class SaldoForm extends JFrame implements Observer{
	private JLabel nome, saldo, valor, consulta, data;
	private JButton imprimir;
	private JPanel pTopo, pBaixo, pMeio;
	private ResourceBundle bn;
	private Conta conta;
	public SaldoForm(ResourceBundle bnn, final Conta conta) {
		super(bnn.getString("saldoForm.titulo"));
		bn = bnn;
		this.conta = conta;
		setLayout(new BorderLayout());
		pTopo = new JPanel();
		pTopo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTopo.add(nome = new JLabel("<<NOME DO CLIENTE>>"));

		pMeio = new JPanel();
		pMeio.setLayout(new GridLayout(2, 2));
		saldo = new JLabel(bn.getString("saldoForm.saldo"));
		valor = new JLabel("R$0.00");
		consulta = new JLabel(bn.getString("saldoForm.data"));
		Date d = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat(); 
		data = new JLabel(format.format(d));
		pMeio.add(saldo);
		pMeio.add(valor);
		pMeio.add(consulta);
		pMeio.add(data);

		pBaixo = new JPanel();
		pBaixo.setLayout(new FlowLayout(FlowLayout.RIGHT));
		imprimir = new JButton(bn.getString("geral.bImprimir"));
		pBaixo.add(imprimir);

		add(pTopo, BorderLayout.NORTH);
		add(pMeio, BorderLayout.CENTER);
		add(pBaixo, BorderLayout.SOUTH);

		setSize(250, 200);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		// Ao fechar a janela, volta ao painel de menus
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        addWindowListener( new WindowAdapter() {
		             @Override
		             public void windowClosing(WindowEvent we) {
		                 new PainelController(bn, conta);
		                 dispose();
		             }
		         } );
	}
	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}
	public String getSaldo(){
		return valor.getText();
	}
	public void setValor(String saldo){
		valor.setText(saldo);
	}
	public String getNome(){
		return nome.getText();
	}
	public void setNome(String nome){
		this.nome.setText(nome);
	}
}
