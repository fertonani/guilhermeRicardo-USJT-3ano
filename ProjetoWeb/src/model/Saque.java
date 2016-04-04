package model;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Observable;

import dao.MovimentoDAO;
import to.SaqueTO;

public class Saque extends Observable {
	private Conta conta;
	private Dispenser dispenser;
	private double valor;
	private MovimentoDAO movimento;
	private SaqueTO to;
	public Saque(Conta conta, Double valor) throws SQLException{
		this.conta = conta;
		this.valor = valor;
		dispenser = new Dispenser();
		movimento = new MovimentoDAO(conta);
	}
	public boolean verificarSaldo(){
		if(conta.getSaldo() >= valor) return true;
		return false;
	}
	public boolean sacar() throws FileNotFoundException{
		if(dispenser.sacar(valor)){
			funcaoSacar();
			return true;
		}
		return false;
	}
	public void funcaoSacar(){
		conta.setSaldo(conta.getSaldo() - valor);
		movimento.incluir('D', valor);
	}
	
	public boolean verificarNotas() throws FileNotFoundException{
		return dispenser.verificarNotas(valor);
	}
	
	public double getValor(){
		return valor;
	}
	public void criarTO(){
		to = new SaqueTO();
		to.setValor(valor);
	}
}
