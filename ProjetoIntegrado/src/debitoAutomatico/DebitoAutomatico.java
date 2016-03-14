package debitoAutomatico;

import java.sql.SQLException;
import java.util.Date;
import java.util.Observable;

import dao.DebitoAutomaticoDAO;

import model.Conta;

public class DebitoAutomatico extends Observable {
	private String codOp, codCo;
	private Date data;
	private DebitoAutomaticoDAO dao;

	public DebitoAutomatico(Conta conta, String codOp, Date data, String codCo) throws SQLException {
		this.codOp = codOp;
		this.data = data;
		this.codCo = codCo;
		dao = new DebitoAutomaticoDAO(conta, this);
	}

	public String getCodOp() {
		return codOp;
	}

	public void setCodOp(String codOp) {
		this.codOp = codOp;
		setChanged();
		notifyObservers();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
		setChanged();
		notifyObservers();
	}

	public String getCodCo() {
		return codCo;
	}

	public void setCodCo(String codCo) {
		this.codCo = codCo;
		setChanged();
		notifyObservers();
	}

	public void cadastrarDebito() {
		dao.incluir();
	}
}
