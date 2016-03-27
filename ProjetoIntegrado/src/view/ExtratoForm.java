package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import calendar.JCalendar;
import controller.PainelController;
import model.Conta;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ExtratoForm extends JFrame implements Observer {
	private JLabel lNomeCliente, lInstrucao, l7Dias, l15Dias, lDe, lAte, lListagem;
	private JButton bImprimir, bAnterior, bProximo;
	private JRadioButton r7Dias, r15Dias, rOutro;
	private JTable tabela;
	private JPanel topo, abaixo, meio, listagem, esquerdo, direito;
	private String[] colunas;
	private JScrollPane bar;
	private ButtonGroup gData;
	private JComboBox calendarioDe, calendarioAte;
	private ResourceBundle bn;
	private Conta conta;
	private DefaultTableModel modelo;

	public ExtratoForm(ResourceBundle bnn, final Conta conta) {
		super(bnn.getString("extratoForm.titulo"));
		this.bn = bnn;
		this.conta = conta;
		setLayout(new BorderLayout());
		colunas = new String[5];
		colunas[0] = bn.getString("extratoForm.data");
		colunas[1] = bn.getString("extratoForm.tipo");
		colunas[2] = bn.getString("extratoForm.nDoc");
		colunas[3] = bn.getString("extratoForm.valor");
		colunas[4] = bn.getString("extratoForm.saldo");
		calendarioDe = new JCalendar(true);
		calendarioAte = new JCalendar(true);
		lNomeCliente = new JLabel("<<NOME DO CLIENTE>>");
		bImprimir = new JButton(bn.getString("geral.bImprimir"));
		topo = new JPanel();
		topo.setLayout(new GridLayout(1, 2));
		topo.add(lNomeCliente);
		topo.add(bImprimir);

		abaixo = new JPanel();
		abaixo.setLayout(new BorderLayout());
		listagem = new JPanel();
		listagem.setLayout(new FlowLayout(FlowLayout.CENTER));

		bAnterior = new JButton(bn.getString("extratoForm.anterior"));
		lListagem = new JLabel(bn.getString("extratoForm.listagem"));
		bProximo = new JButton(bn.getString("extratoForm.proximo"));
		listagem.add(lListagem);
		abaixo.add(bAnterior, BorderLayout.WEST);
		abaixo.add(listagem, BorderLayout.CENTER);
		abaixo.add(bProximo, BorderLayout.EAST);

		meio = new JPanel();
		meio.setLayout(new BorderLayout(2, 2));
		lInstrucao = new JLabel(bn.getString("extratoForm.instrucao"));
		meio.add(lInstrucao, BorderLayout.NORTH);
		esquerdo = new JPanel();

		l7Dias = new JLabel(bn.getString("extratoForm.7dias"));
		l15Dias = new JLabel(bn.getString("extratoForm.15dias"));
		lDe = new JLabel(bn.getString("extratoForm.de"));
		lAte = new JLabel(bn.getString("extratoForm.ate"));
		gData = new ButtonGroup();
		r7Dias = new JRadioButton();
		r15Dias = new JRadioButton();
		rOutro = new JRadioButton();
		gData.add(r7Dias);
		gData.add(r15Dias);
		gData.add(rOutro);
		esquerdo.add(r7Dias);
		esquerdo.add(l7Dias);
		esquerdo.add(r15Dias);
		esquerdo.add(l15Dias);
		esquerdo.add(rOutro);
		esquerdo.add(lDe);
		esquerdo.add(calendarioDe);
		esquerdo.add(lAte);
		esquerdo.add(calendarioAte);
		direito = new JPanel();
		modelo = new DefaultTableModel(colunas, 0);
		tabela = new JTable(modelo) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		bar = new JScrollPane(tabela);
		direito.add(bar);
		meio.add(esquerdo, BorderLayout.WEST);
		meio.add(direito, BorderLayout.SOUTH);

		add(topo, BorderLayout.NORTH);
		add(meio, BorderLayout.CENTER);
		add(abaixo, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		// Ao fechar a janela, volta ao painel de menus
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				new PainelController(bn, conta);
				dispose();
			}
		});
	}

	public String[] getColunas() {
		return colunas;
	}

	public void update(Observable arg0, Object arg1) {
		System.out.println("-- Update --\nARG0: " + arg0 + "\nARG1: " + arg1);
	}

	public void setNome(String text) {
		lNomeCliente.setText(text);
	}

	public void add7DiasListener(ActionListener e) {
		r7Dias.addActionListener(e);
	}

	public void add15DiasListener(ActionListener e) {
		r15Dias.addActionListener(e);
	}

	public void addOutroListener(ActionListener e) {
		rOutro.addActionListener(e);
	}

	public JComboBox getCalenderDe() {
		return calendarioDe;
	}

	public JComboBox getCalenderAte() {
		return calendarioAte;
	}

	public JTable getTable() {
		return tabela;
	}

	public void setTable(JTable table) {
		tabela = table;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}
}
