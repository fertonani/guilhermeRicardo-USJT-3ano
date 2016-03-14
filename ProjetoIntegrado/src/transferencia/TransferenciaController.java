package transferencia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Conta;
import painel.PainelController;
import painel.PainelForm;

public class TransferenciaController extends TransferenciaForm {
	private ResourceBundle bn;
	private Conta contas;

	public TransferenciaController(ResourceBundle bnn, Conta conta) {
		super(bnn, conta);
		this.contas = conta;
		this.bn = bnn;
		setNome(conta.getNome());
		addTransferirListener(new TransferirListener());
	}

	class TransferirListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double valorReal = -1;
			Transferencia transferencia = null;
			int opcao = 1;
			if (getAgencia().equals("") || getConta().equals("") || getValor().equals(""))
				JOptionPane.showMessageDialog(null, bn.getString("transferenciaForm.preenchaTudo"),
						bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
			else {
				try {
					valorReal = Double.parseDouble(getValor());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, bn.getString("geral.erro.numeroInteiro"),
							bn.getString("geral.erro.titulo"), JOptionPane.ERROR_MESSAGE);
					new TransferenciaController(bn, contas);
					dispose();
				}
				try {
					transferencia = new Transferencia(contas, getAgencia(), getConta(), valorReal);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (transferencia.verificarSaldo()) {
					JPanel ppBaixo, ppCentro, confirmacao;
					JLabel ffAgencia, ffConta, ffValor, ffNome, ffNomeDestino, ffCod, agencia, conta, valor;
					JTextField codAcesso;
					confirmacao = new JPanel();

					ppCentro = new JPanel();
					ppCentro.setLayout(new GridLayout(5, 2, 3, 4));
					agencia = new JLabel(bn.getString("transferenciaForm.agencia"));
					conta = new JLabel(bn.getString("transferenciaForm.conta"));
					valor = new JLabel(bn.getString("transferenciaForm.valor"));
					ffAgencia = new JLabel(getAgencia());
					ffConta = new JLabel(getConta());
					DecimalFormat fmt = new DecimalFormat("#,###.00");
					ffValor = new JLabel("R$" + fmt.format(valorReal));
					ffNome = new JLabel(bn.getString("transferenciaForm.nome"));
					ffNomeDestino = new JLabel(transferencia.getNome());
					ffCod = new JLabel(bn.getString("transferenciaForm.codAcesso"));
					codAcesso = new JPasswordField(3);
					ppCentro.add(agencia);
					ppCentro.add(ffAgencia);
					ppCentro.add(conta);
					ppCentro.add(ffConta);
					ppCentro.add(valor);
					ppCentro.add(ffValor);
					ppCentro.add(ffNome);
					ppCentro.add(ffNomeDestino);
					ppCentro.add(ffCod);
					ppCentro.add(codAcesso);

					ppBaixo = new JPanel();
					ppBaixo.setLayout(new FlowLayout(FlowLayout.RIGHT));

					confirmacao.add(ppCentro, BorderLayout.CENTER);
					confirmacao.add(ppBaixo, BorderLayout.SOUTH);

					String[] opcoes = { bn.getString("transferenciaForm.bTransferir") };

					opcao = JOptionPane.showOptionDialog(null, confirmacao, contas.getNome(),
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
					if (opcao == 0) {
						if (contas.getCod() == Integer.parseInt(codAcesso.getText())) {
							transferencia.efetuarTransferencia();
							JOptionPane.showMessageDialog(null, bn.getString("transferenciaForm.mensagemSucesso"),
									bn.getString("geral.mensagem.informacao"), JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, bn.getString("codigoAcessoForm.codErrado"),
									bn.getString("geral.mensagem.informacao"), JOptionPane.ERROR_MESSAGE);
						}
					}
					new PainelController(bn, contas);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, bn.getString("transferenciaForm.saldoInsuficiente"),
							bn.getString("geral.mensagem.informacao"), JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}
}
