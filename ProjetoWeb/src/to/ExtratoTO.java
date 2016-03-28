package to;

import java.util.ArrayList;
import java.util.Date;

import dao.MovimentoDAO;
import model.Conta;

public class ExtratoTO {
	private Date data;
	private char tipo;
	private int numDoc, idHistorico;
	private double valor;
	private ArrayList<ExtratoTO> listaExtrato;
	private Conta conta;
	private MovimentoDAO movimento;
	private double saldo;

	public ExtratoTO(int idHistorico, Date data, char tipo, int numDoc, double valor) {
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

	public int getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ArrayList<ExtratoTO> getListaExtrato() {
		return listaExtrato;
	}

	public void setListaExtrato(ArrayList<ExtratoTO> listaExtrato) {
		this.listaExtrato = listaExtrato;
	}
	

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public MovimentoDAO getMovimento() {
		return movimento;
	}

	public void setMovimento(MovimentoDAO movimento) {
		this.movimento = movimento;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}