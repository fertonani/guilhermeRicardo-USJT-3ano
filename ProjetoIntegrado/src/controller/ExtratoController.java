package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Conta;
import model.Extrato;
import to.ExtratoTO;
import view.ExtratoForm;

public class ExtratoController extends ExtratoForm {
	private ResourceBundle bn;
	private Conta conta;
	private Extrato extrato;
	private ExtratoTO to;
	public ExtratoController(ResourceBundle bn, Conta conta) {
		super(bn, conta);
		this.bn = bn;
		this.conta = conta;
		setNome(conta.getNome());
		extrato = null;
		to = null;
		add7DiasListener(new Dias7Listener());
		add15DiasListener(new Dias15Listener());
		addOutroListener(new DiaOutroListener());
	}

	class Dias7Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calendar agora = Calendar.getInstance();
			Calendar aux = Calendar.getInstance();
			aux.add(Calendar.DATE, -7);
			try {
				extrato = new Extrato(conta, aux.getTime(), agora.getTime());
				to = extrato.criar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JTable tabela = getTable();
			DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
			modelo.setRowCount(0);
			for (int i = 0; i < extrato.getLista().size(); i++) {
				DecimalFormat fmt = new DecimalFormat("#,###.00");
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String data = format.format(extrato.getLista().get(i).getData());
				char c = extrato.getLista().get(i).getTipo();
				int numDoc = extrato.getLista().get(i).getNumDoc();
				String valor = "R$" + fmt.format(extrato.getLista().get(i).getValor());
				String saldo = "R$" + fmt.format(extrato.getSaldo(0, i + 1));
				modelo.addRow(new Object[] { data, c, numDoc, valor, saldo });
			}
		}
	}

	class Dias15Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calendar agora = Calendar.getInstance();
			Calendar aux = Calendar.getInstance();
			aux.add(Calendar.DATE, -15);
			try {
				extrato = new Extrato(conta, aux.getTime(), agora.getTime());
				to = extrato.criar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JTable tabela = getTable();
			DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
			modelo.setRowCount(0);
			for (int i = 0; i < extrato.getLista().size(); i++) {
				DecimalFormat fmt = new DecimalFormat("#,###.00");
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String data = format.format(extrato.getLista().get(i).getData());
				char c = extrato.getLista().get(i).getTipo();
				int numDoc = extrato.getLista().get(i).getNumDoc();
				String valor = "R$" + fmt.format(extrato.getLista().get(i).getValor());
				String saldo = "R$" + fmt.format(extrato.getSaldo(0, i + 1));
				modelo.addRow(new Object[] { data, c, numDoc, valor, saldo });
			}
		}
	}

	class DiaOutroListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calendar de = (Calendar) getCalenderDe().getSelectedItem();
			Calendar ate = (Calendar) getCalenderAte().getSelectedItem();
			if (de.before(ate)) {
				try {
					extrato = new Extrato(conta, de.getTime(), ate.getTime());
					to = extrato.criar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JTable tabela = getTable();
				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
				modelo.setRowCount(0);
				for (int i = 0; i < extrato.getLista().size(); i++) {
					DecimalFormat fmt = new DecimalFormat("#,###.00");
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String data = format.format(extrato.getLista().get(i).getData());
					char c = extrato.getLista().get(i).getTipo();
					int numDoc = extrato.getLista().get(i).getNumDoc();
					String valor = "R$" + fmt.format(extrato.getLista().get(i).getValor());
					String saldo = "R$" + fmt.format(extrato.getSaldo(0, i + 1));
					modelo.addRow(new Object[] { data, c, numDoc, valor, saldo });
				}
			}
		}
	}
}
