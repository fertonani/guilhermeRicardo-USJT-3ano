package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import dao.MovimentoDAO;
import to.ExtratoTO;

public class Extrato extends Observable {
	private Date data;
	private char tipo;
	private int numDoc, idHistorico;
	private double valor;
	private ArrayList<Extrato> listaExtrato;
	private Conta conta;
	private MovimentoDAO movimento;
	private ExtratoTO to;
	private double saldo;

	public Extrato(Conta conta, Date de, Date ate) throws SQLException {
		this.conta = conta;
		movimento = new MovimentoDAO(conta);
		listaExtrato = new ArrayList<Extrato>();
		setLista(movimento.carregarExtrato(de, ate));
	}

	public Extrato(int idHistorico, Date data, char tipo, int numDoc, double valor) {
		this.idHistorico = idHistorico;
		this.data = data;
		this.tipo = tipo;
		this.numDoc = numDoc;
		this.valor = valor;

	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(int numDoc) {
		this.numDoc = numDoc;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}

	public ArrayList<Extrato> getLista() {
		return listaExtrato;
	}

	public void setLista(ArrayList<Extrato> e) {
		this.listaExtrato = e;
	}

	public double getSaldo(int de, int ate) {
		double totalCredito = 0;
		double totalDebito = 0;
		for (int i = de; i < ate; i++) {
			if (listaExtrato.get(i).getTipo() == 'C') {
				totalCredito += listaExtrato.get(i).getValor();
			} else {
				totalDebito += listaExtrato.get(i).getValor();
			}
		}
		setSaldo(totalCredito - totalDebito);
		return totalCredito - totalDebito;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ExtratoTO criar() {
		to = new ExtratoTO(idHistorico, data, tipo, numDoc, valor);
		return to;
	}

	public ExtratoTO atualizar() {
		to.setIdHistorico(idHistorico);
		to.setData(data);
		to.setConta(conta);
		//to.setListaExtrato(listaExtrato);
		to.setMovimento(movimento);
		to.setNumDoc(numDoc);
		to.setTipo(tipo);
		to.setValor(valor);
		to.setSaldo(saldo);
		return to;
	}
}