package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;

import controller.PainelController;
import model.Conta;

public class SaqueForm extends JFrame implements Observer{
	private JLabel nome, valor;
	private JButton vlr1, vlr2, vlr3, vlr4, vlr5, vlr6, outro;
	private JPanel pEsquerdo, pDireito, pMeio, pTopo, pBaixo;
	private ResourceBundle bn;
	private Conta conta;
	public SaqueForm(final ResourceBundle bn, final Conta conta) {
		super(bn.getString("saqueForm.titulo"));
		this.bn = bn;
		this.conta = conta;
		setLayout(new BorderLayout());

		nome = new JLabel("<<NOME DO CLIENTE>>");
		valor = new JLabel(bn.getString("saqueForm.instrucao"));

		vlr1 = new JButton("R$ 10,00");
		vlr2 = new JButton("R$ 20,00");
		vlr3 = new JButton("R$ 50,00");
		vlr4 = new JButton("R$ 100,00");
		vlr5 = new JButton("R$ 200,00");
		vlr6 = new JButton("R$ 500,00");
		outro = new JButton(bn.getString("saqueForm.outro"));
		pEsquerdo = new JPanel();
		pEsquerdo.setLayout(new GridLayout(3, 1, 3, 1));
		pDireito = new JPanel();
		pDireito.setLayout(new GridLayout(3, 1, 3, 1));
		pTopo = new JPanel();
		pTopo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTopo.add(nome);
		pBaixo = new JPanel();
		pBaixo.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBaixo.add(outro);

		pEsquerdo.add(vlr1);
		pEsquerdo.add(vlr2);
		pEsquerdo.add(vlr3);
		pDireito.add(vlr4);
		pDireito.add(vlr5);
		pDireito.add(vlr6);

		pMeio = new JPanel();
		pMeio.setLayout(new FlowLayout(FlowLayout.CENTER));
		pMeio.add(valor);
		add(pTopo, BorderLayout.NORTH);
		add(pEsquerdo, BorderLayout.WEST);
		add(pMeio, BorderLayout.CENTER);
		add(pDireito, BorderLayout.EAST);
		add(pBaixo, BorderLayout.SOUTH);

		setSize(400, 180);
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
	public void addOutroListener(ActionListener e){
		outro.addActionListener(e);
	}
	public void add10Listener(ActionListener e){
		vlr1.addActionListener(e);
	}
	public void add20Listener(ActionListener e){
		vlr2.addActionListener(e);
	}
	public void add50Listener(ActionListener e){
		vlr3.addActionListener(e);
	}
	public void add100Listener(ActionListener e){
		vlr4.addActionListener(e);
	}
	public void add200Listener(ActionListener e){
		vlr5.addActionListener(e);
	}
	public void add500Listener(ActionListener e){
		vlr6.addActionListener(e);
	}
	public void setNome(String nome){
		this.nome.setText(nome);
	}

}