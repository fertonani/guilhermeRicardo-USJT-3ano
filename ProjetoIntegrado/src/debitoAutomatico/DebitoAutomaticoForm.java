package debitoAutomatico;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

import calendar.JCalendar;
import model.Conta;
import painel.PainelController;

public class DebitoAutomaticoForm extends JFrame implements Observer {
	private JLabel lNomeCliente, lCodOperadora, lData, lCodConsumidor;
	private JTextField fCodOperadora, fCodConsumidor;
	private JButton bCadastrar;
	private JPanel nomeCliente, pDados;
	private JComboBox ccb;
	private ResourceBundle bn;
	private Conta conta;
	public DebitoAutomaticoForm(ResourceBundle bnn, final Conta conta) {
		super(bnn.getString("debitoAutomaticoForm.titulo"));
		setLayout(new BorderLayout(3, 2));
		bn = bnn;
		this.conta = conta;
		ccb = new JCalendar(true);
		nomeCliente = new JPanel();
		nomeCliente.setLayout(new FlowLayout(FlowLayout.CENTER));
		lNomeCliente = new JLabel("<<NOME DO CLIENTE>>");
		nomeCliente.add(lNomeCliente);
		add(nomeCliente, BorderLayout.NORTH);
		pDados = new JPanel();
		pDados.setLayout(new GridLayout(3, 2, 3, 3));
		lCodOperadora = new JLabel((bn.getString("debitoAutomaticoForm.codOperadora")));
		lData = new JLabel(bn.getString("debitoAutomaticoForm.dataDebito"));
		lCodConsumidor = new JLabel((bn.getString("debitoAutomaticoForm.codConsumidor")));
		fCodOperadora = new JTextField(12);
		fCodConsumidor = new JTextField(12);

		pDados.add(lCodOperadora);
		pDados.add(fCodOperadora);
		pDados.add(lData);
		pDados.add(ccb);
		pDados.add(lCodConsumidor);
		pDados.add(fCodConsumidor);
		
		bCadastrar = new JButton((bn.getString("debitoAutomaticoForm.bCadastrar")));
		add(pDados, BorderLayout.CENTER);
		add(bCadastrar, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
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
	public void addCadastrarListener(ActionListener e){
		bCadastrar.addActionListener(e);
	}
	public String getCodOp(){
		return fCodOperadora.getText().trim();
	}
	public Date getData(){
		GregorianCalendar gc = (GregorianCalendar) ccb.getSelectedItem();
		return gc.getTime();
	}
	public String getCodCo(){
		return fCodConsumidor.getText().trim();
	}
	public void setNome(String nome){
		lNomeCliente.setText(nome);
	}
}
