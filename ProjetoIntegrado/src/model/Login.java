package model;

import java.sql.SQLException;
import java.util.Observable;

import dao.AcessoDAO;
import dao.ContaDAO;


public class Login extends Observable{
	private String agencia, conta, senha, cod;
	private AcessoDAO acesso;
	private ContaDAO contaDAO;
	private Conta contaM;
	public Login(){
		agencia = "12345";
		conta = "345678";
		senha = "123456";
		cod = "111";
		acesso = new AcessoDAO(this);
	}
	public Login(String agencia, String conta, String senha, String cod) throws SQLException {
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.cod = cod;
		acesso = new AcessoDAO(this);
	}
	
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
		setChanged();
		notifyObservers();

	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
		setChanged();
		notifyObservers();

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
		setChanged();
		notifyObservers();

	}

	public String getCod() {
		return ""+acesso.getCod();
	}

	public void setCod(String cod) {
		this.cod = cod;
		setChanged();
		notifyObservers();

	}
	
	public void efetuarLogin(){
		try {
			//acesso.openFile(); Ja esta no construtor
			acesso.criptografarAcesso();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verificarConta(){
		return acesso.verificarConta();
	}
	public boolean verificarAgencia(){
		return acesso.verificarAgencia();
	}
	
	public boolean verificarSenha(){
		return acesso.verificarSenha();
	}
	public boolean verificarCod(){
		return acesso.verificarCod();
	}
	public void fechar(){
		acesso.closeFile();
	}
}
