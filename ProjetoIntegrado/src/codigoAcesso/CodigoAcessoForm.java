package codigoAcesso;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.*;


public class CodigoAcessoForm extends JFrame implements Observer {
	private JLabel jCodigo;
	private JButton but1, but2, but3, but4, but5, bAcessar;
	private BorderLayout layout;
	private JPanel pEsquerda, pDireita, pMeio, pTopo;
	private ResourceBundle bn;

	public CodigoAcessoForm(ResourceBundle bn) {
		super(bn.getString("codigoAcessoForm.titulo"));
		this.bn = bn;
		layout = new BorderLayout();
		setLayout(layout);

		pEsquerda = new JPanel(new GridLayout(3, 1));
		pDireita = new JPanel(new GridLayout(3, 1));
		pMeio = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pTopo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jCodigo = new JLabel(bn.getString("codigoAcessoForm.instrucao"));

		but1 = new JButton("1-2");
		but2 = new JButton("3-4");
		but3 = new JButton("5-6");
		but4 = new JButton("7-8");
		but5 = new JButton("9-0");
		bAcessar = new JButton(bn.getString("geral.bLogin"));
		pEsquerda.add(but1);
		pEsquerda.add(but2);
		pEsquerda.add(but3);
		pDireita.add(but4);
		pDireita.add(but5);
		pTopo.add(jCodigo);
		pMeio.add(bAcessar);

		// painel do JFrame
		this.setLayout(new BorderLayout());
		this.getContentPane().add(pDireita, BorderLayout.EAST);
		this.getContentPane().add(pEsquerda, BorderLayout.WEST);
		this.getContentPane().add(pMeio, BorderLayout.SOUTH);
		this.getContentPane().add(pTopo, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 250);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(null);
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}

	public void addAcessarListener(ActionListener e) {
		bAcessar.addActionListener(e);
	}

	public void addBut1Listener(ActionListener e) {
		but1.addActionListener(e);
	}

	public void addBut2Listener(ActionListener e) {
		but2.addActionListener(e);
	}

	public void addBut3Listener(ActionListener e) {
		but3.addActionListener(e);
	}

	public void addBut4Listener(ActionListener e) {
		but4.addActionListener(e);
	}

	public void addBut5Listener(ActionListener e) {
		but5.addActionListener(e);
	}
	public void setCodigo(String texto){
		jCodigo.setText(texto);
	}
	public String getCodigo(){
		return jCodigo.getText();
	}
	public JButton getBut1(){
		return but1;
	}
	public void setBut1(JButton b){
		but1 = b;
	}

	public JButton getBut2() {
		return but2;
	}

	public void setBut2(JButton but2) {
		this.but2 = but2;
	}

	public JButton getBut3() {
		return but3;
	}

	public void setBut3(JButton but3) {
		this.but3 = but3;
	}

	public JButton getBut4() {
		return but4;
	}

	public void setBut4(JButton but4) {
		this.but4 = but4;
	}

	public JButton getBut5() {
		return but5;
	}

	public void setBut5(JButton but5) {
		this.but5 = but5;
	}
}