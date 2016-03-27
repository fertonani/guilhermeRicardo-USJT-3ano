package model;

import java.sql.SQLException;
import java.util.Observable;

import dao.MovimentoDAO;

public class Transferencia extends Observable {
	private String agencia, conta, nome;
	private double valor;
	private Conta dao, remetente;
	private MovimentoDAO movR, movD;

	public Transferencia(Conta remetente, String agencia, String conta, double valor) throws SQLException {
		setAgencia(agencia);
		setConta(conta);
		setValor(valor);
		dao = new Conta(this);
		movR = new MovimentoDAO(remetente);
		movD = new MovimentoDAO(dao);
		this.remetente = remetente;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getNome() {
		return dao.getNome();
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean verificarSaldo() {
		if (remetente.getSaldo() >= getValor())
			return true;
		return false;
	}

	public void efetuarTransferencia() {
		double saldoRemetente = remetente.getSaldo() - valor;
		double saldoDestinatario = dao.getSaldo() + valor;
		movR.incluir('D', valor);
		movD.incluir('C', valor);
		dao.setSaldo(saldoDestinatario);
		remetente.setSaldo(saldoRemetente);
	}
}
