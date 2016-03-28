package teste;
import model.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import dao.MovimentoDAO;
import model.Conta;

public class ExtratoTest {
	private Login login, login1;
	private Extrato extrato, extrato1;
	private ArrayList<Extrato> listaExtrato;
	private Conta conta, conta1;
	private MovimentoDAO movimento;
	Calendar agora = Calendar.getInstance();
	Calendar aux = Calendar.getInstance();
	
	@Before
	public void setUp() throws SQLException {
		login = new Login("1", "1", "1", "147");
		conta = new Conta(login);
		login1 = new Login("2", "2", "2", "169");
		conta1 = new Conta(login);
		aux.add(Calendar.DATE, -7);
		try {
			extrato = new Extrato(conta, aux.getTime(), agora.getTime());
			extrato1 = new Extrato(conta, aux.getTime(), agora.getTime());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void testeSaldo() throws SQLException{
		movimento = new MovimentoDAO(conta);
		listaExtrato = new ArrayList<Extrato>();
		extrato.setLista(movimento.carregarExtrato(aux.getTime(), agora.getTime()));
		assertEquals("Teste Extrato", extrato.getLista().get(1).getValor() == extrato1.getLista().get(1).getValor(), true);
	}
	
	
}
