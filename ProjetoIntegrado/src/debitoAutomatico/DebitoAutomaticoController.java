package debitoAutomatico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.Conta;

public class DebitoAutomaticoController extends DebitoAutomaticoForm{
	private DebitoAutomatico debito;
	private ResourceBundle bn;
	private Conta conta;
	public DebitoAutomaticoController(ResourceBundle bn, Conta conta){
		super(bn, conta);
		this.bn = bn;
		this.conta = conta;
		super.setNome(conta.getNome());
		addCadastrarListener(new CadastrarListener());
	}
	class CadastrarListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(getCodOp().equals("") || getCodCo().equals("") || getData().equals("")){
				JOptionPane.showMessageDialog(null, bn.getString("debitoAutomaticoForm.campoNaoPreenchido"),
						bn.getString("geral.mensagem.informacao"), JOptionPane.ERROR_MESSAGE);
			}else if(getData().before(GregorianCalendar.getInstance().getTime())){
				JOptionPane.showMessageDialog(null, bn.getString("debitoAutomaticoForm.dataPosterior"),
						bn.getString("geral.mensagem.informacao"), JOptionPane.ERROR_MESSAGE);
			}else{
				try {
					debito = new DebitoAutomatico(conta, getCodOp(), getData(), getCodCo());
					debito.cadastrarDebito();
					JOptionPane.showMessageDialog(null, bn.getString("debitoAutomaticoForm.cadastrado"),
							bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
