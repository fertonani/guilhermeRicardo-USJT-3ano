package teste;
import model.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.Conta;

public class SaqueTest {
	private Login login;
	private Conta conta;
	private Saque saque;

	@Before
	public void setUp() throws SQLException {
		login = new Login("1", "1", "1", "147");
		conta = new Conta(login);
		saque = new Saque(conta, 10.0);
	}

	@Test
	public void testeSaldo() {
		assertEquals("Teste Saldo", saque.verificarSaldo(), true);
	}

	@Test
	public void testeNotas() throws FileNotFoundException {
		assertEquals("Teste Notas", saque.verificarNotas(), true);
	}

	@Test
	public void testeSaque() throws FileNotFoundException {
		assertEquals("Teste Sacar", saque.sacar(), true);
	}
}
