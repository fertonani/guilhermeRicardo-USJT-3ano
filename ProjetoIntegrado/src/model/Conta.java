package model;

import java.sql.SQLException;

import dao.ContaDAO;

import login.Login;
import transferencia.Transferencia;

public class Conta {
	private int agencia, conta, senha, cod, idCliente, Banco;
	private String nome;
	private ContaDAO dao;
	private Login login;
	public Conta(Login login) throws SQLException {
		dao = new ContaDAO(this);
		this.login = login;
		setAgencia(Integer.parseInt(login.getAgencia()));
		setConta(Integer.parseInt(login.getConta()));
		setSenha(Integer.parseInt(login.getSenha()));
		setCod(Integer.parseInt(login.getCod()));
	}
	public Conta(Transferencia transf) throws SQLException {
		dao = new ContaDAO(this);
		setAgencia(Integer.parseInt(transf.getAgencia()));
		setConta(Integer.parseInt(transf.getConta()));
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public int getCod() {
		return Integer.parseInt(login.getCod());
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getIdCliente() throws SQLException {
		return dao.getIdCliente();
	}
	public int getBanco() throws SQLException {
		return dao.getBanco();
	}
	public String getNome() {
		return dao.getNome();
	}
	public Double getSaldo(){
		return dao.getSaldo();
	}
	public void setSaldo(double valor){
		dao.atualizarSaldo(valor);
	}
}
