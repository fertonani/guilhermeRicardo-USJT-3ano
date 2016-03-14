package saldo;

import java.sql.SQLException;
import java.util.Observable;

import model.Conta;

public class Saldo extends Observable{
	private double saldo;
	private Conta conta;
	public Saldo(Conta conta) throws SQLException{
		this.conta = conta;
	}
	public double getSaldo() throws SQLException {
		return conta.getSaldo();
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
		setChanged();
		notifyObservers();
	}
	public String getNome(){
		return conta.getNome();
	}
}