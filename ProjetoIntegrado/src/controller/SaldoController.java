package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import model.Conta;
import model.Saldo;
import view.SaldoForm;

public class SaldoController extends SaldoForm{
	private ResourceBundle bn;
	private Conta conta;
	private Saldo saldo;
	public SaldoController(ResourceBundle bn, Conta conta) throws SQLException{
		super(bn, conta);
		this.bn = bn;
		this.conta = conta;
		saldo = new Saldo(conta);
		super.setValor(verSaldo());
		super.setNome(conta.getNome());
		
	}
	public String verSaldo() throws SQLException{
		DecimalFormat fmt = new DecimalFormat("#,###.00");
		return "R$" + fmt.format(saldo.getSaldo());
	}
}
