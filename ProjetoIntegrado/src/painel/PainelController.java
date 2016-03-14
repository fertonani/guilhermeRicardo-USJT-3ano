package painel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ResourceBundle;

import debitoAutomatico.DebitoAutomaticoController;
import debitoAutomatico.DebitoAutomaticoForm;
import extrato.ExtratoController;
import extrato.ExtratoForm;
import model.Conta;
import saldo.SaldoController;
import saque.SaqueController;
import saque.SaqueForm;
import transferencia.TransferenciaController;
import transferencia.TransferenciaForm;

public class PainelController extends PainelForm{
	private ResourceBundle bn;
	private Conta conta;
	
	public PainelController(ResourceBundle bn, Conta conta) {
		super(bn);
		this.bn = bn;
		this.conta = conta;
		setNome(conta.getNome());
		addSaqueListener(new SaqueListener());
		addExtratoListener(new ExtratoListener());
		addSaldoListener(new SaldoListener());
		addTransferenciaListener(new TransferenciaListener());
		addDebitoAutomaticoListener(new DebitoAutomaticoListener());
	}
	class SaqueListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new SaqueController(bn, conta);
			dispose();
		}
	}
	class ExtratoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new ExtratoController(bn, conta);
			dispose();
		}
	}
	class SaldoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
				new SaldoController(bn, conta);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}
	class TransferenciaListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new TransferenciaController(bn, conta);
			dispose();
		}
	}
	class DebitoAutomaticoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new DebitoAutomaticoController(bn, conta);
			dispose();
		}
	}
}
