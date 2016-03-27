package dao;

import java.io.*;
import java.util.*;

import criptografia.Crypto;
import model.Login;

public class AcessoDAO {
	private Formatter output, outputDecifrado;
	private Scanner input, inputDecifrado;
	private String sAgencia, sConta, sSenha, sCod, entradaAgencia,
			entradaConta, entradaSenha, entradaCod;
	private Object[][] acessoDescriptografado;
	private byte[][] acessoCriptografado;
	private int[] agencia, conta, senha, cod;
	private int[][] contaSenha;
	private Login login;
	private int posicao;

	public AcessoDAO(Login login) {
		openFile();
		this.login = login;
		acessoCriptografado = new byte[tamanho()][4];
		acessoDescriptografado = new Object[tamanho()][4];
		agencia = new int[tamanho()];
		conta = new int[tamanho()];
		senha = new int[tamanho()];
		cod = new int[tamanho()];
		contaSenha = new int[tamanho()][2];
		sAgencia = "";
		sConta = "";
		sSenha = "";
		sCod = "";
	}

	public void openFile() {
		try {
			output = new Formatter("AcessoCifrado.txt");
			input = new Scanner(new File("Acesso.txt"));
			inputDecifrado = new Scanner(new File("AcessoDecifrado.txt"));
			outputDecifrado = new Formatter("AcessoDecifrado.txt");

		}

		catch (SecurityException securityException) {

			System.err
					.println("Voce nao tem permissao para escrever o arquivo.");
			System.exit(1);
		} catch (FileNotFoundException filesNotFoundException) {
			System.err.println("Erro ao criar o arquivo.");
			System.exit(1);
		}
	}

	public boolean criptografarAcesso() throws Exception {
		while (input.hasNextLine()) {
			String cAgencia, cConta, cSenha, cCod;
			byte[] bAgencia, bConta, bSenha, bCod;
			byte[] bcAgencia, bcConta, bcSenha, bcCod;
			int contador = 0;
			while (input.hasNextLine()) {
				sAgencia = input.next();
				sConta = input.next();
				sSenha = input.next();
				sCod = input.next();
				bAgencia = sAgencia.getBytes("ISO-8859-1");
				bConta = sConta.getBytes("ISO-8859-1");
				bSenha = sSenha.getBytes("ISO-8859-1");
				bCod = sCod.getBytes("ISO-8859-1");

				// Inicio da criptografia
				Crypto cdummy = new Crypto();
				cdummy.geraChave(new File("chave.dummy"));

				cdummy.geraCifra(bAgencia, new File("chave.dummy"));
				cAgencia = (new String(cdummy.getTextoCifrado(), "ISO-8859-1"));
				bcAgencia = cdummy.getTextoCifrado();

				cdummy.geraCifra(bConta, new File("chave.dummy"));
				cConta = (new String(cdummy.getTextoCifrado(), "ISO-8859-1"));
				bcConta = cdummy.getTextoCifrado();

				cdummy.geraCifra(bSenha, new File("chave.dummy"));
				cSenha = (new String(cdummy.getTextoCifrado(), "ISO-8859-1"));
				bcSenha = cdummy.getTextoCifrado();

				cdummy.geraCifra(bCod, new File("chave.dummy"));
				cCod = (new String(cdummy.getTextoCifrado(), "ISO-8859-1"));
				bcCod = cdummy.getTextoCifrado();

				acessoCriptografado[contador][0] = bcAgencia[0];
				acessoCriptografado[contador][1] = bcConta[0];
				acessoCriptografado[contador][2] = bcSenha[0];
				acessoCriptografado[contador][3] = bcCod[0];

				acessoDescriptografado[contador][0] = sAgencia;
				acessoDescriptografado[contador][1] = sConta;
				acessoDescriptografado[contador][2] = sSenha;
				acessoDescriptografado[contador][3] = sCod;

				cod[contador] = Integer.parseInt(sCod);
				agencia[contador] = Integer.parseInt(sAgencia);
				conta[contador] = Integer.parseInt(sConta);
				senha[contador] = Integer.parseInt(sSenha);
				contaSenha[contador][0] = Integer.parseInt(sConta);
				contaSenha[contador][1] = Integer.parseInt(sSenha);
				output.format("%s %s %s %s\n", cAgencia, cConta, cSenha, cCod);
				contador++;
				if (input.hasNextLine())
					input.nextLine();
			}
			preencherDecifrado();
		}
		closeFile();
		return true;
	}

	public boolean buscaBinaria(int[] v, int valor) {
		int ini = 0, meio, fim = v.length - 1, ret = -1;
		do {
			meio = (ini + fim) / 2;
			if (v[meio] < valor)
				ini = meio + 1;
			else if (v[meio] > valor)
				fim = meio - 1;
			else {
				ret = meio;
				fim = -1;
				return true;
			}
		} while (ini <= fim);

		return false;
	}

	public boolean buscaBinariaDif(int[] v, int valor) {
		int ini = 0, meio, fim = v.length - 1, ret = -1;
		do {
			meio = (ini + fim) / 2;
			if (v[meio] < valor)
				ini = meio + 1;
			else if (v[meio] > valor)
				fim = meio - 1;
			else {
				ret = meio;
				fim = -1;
				if (v[meio] == valor)
					return true;
			}
		} while (ini <= fim);

		return false;
	}

	public boolean buscaBinaria(int[] v, int valor, int pos) {
		int ini = 0, meio, fim = v.length - 1, ret = -1;
		do {
			meio = pos;
			if (v[meio] < valor)
				ini = meio + 1;
			else if (v[meio] > valor)
				fim = meio - 1;
			else {
				return true;
			}
		} while (ini <= fim);

		return false;
	}

	public boolean buscaBinaria(int[][] v, int conta, int senha) {
		int ini = 0, meio, fim = v.length - 1, ret = -1;
		do {
			meio = (ini + fim) / 2;
			if (v[meio][0] < conta)
				ini = meio + 1;
			else if (v[meio][0] > conta)
				fim = meio - 1;
			else {
				ret = meio;
				fim = -1;
				if (v[meio][1] == senha) {
					posicao = meio;
					return true;
				} else {
					return false;
				}
			}
		} while (ini <= fim);

		return false;
	}

	public void closeFile() {
		if (output != null)
			output.close();
		if (input != null) {
			input.close();
		}
		if (outputDecifrado != null)
			outputDecifrado.close();
		if (inputDecifrado != null) {
			inputDecifrado.close();
		}
	}

	public int tamanho() {
		Scanner aux = null;
		try {
			aux = new Scanner(new File("Acesso.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int contador = 0;
		while (aux.hasNextLine()) {
			contador++;
			if (aux.hasNextLine())
				aux.nextLine();
		}
		aux.close();
		return contador;
	}

	public void preencherDecifrado() {
		Scanner aux = null;
		try {
			aux = new Scanner(new File("Acesso.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int contador = 0;
		String agencia, conta, senha, cod;
		while (aux.hasNextLine()) {
			agencia = (String) acessoDescriptografado[contador][0];
			conta = (String) acessoDescriptografado[contador][1];
			senha = (String) acessoDescriptografado[contador][2];
			cod = (String) acessoDescriptografado[contador][3];
			outputDecifrado.format("%s %s %s %s\n", agencia, conta, senha, cod);
			contador++;
			if (aux.hasNextLine())
				aux.nextLine();
		}
		aux.close();
	}

	public boolean verificarConta() {
		return buscaBinaria(conta, Integer.parseInt(login.getConta()));
	}

	public boolean verificarAgencia() {
		return buscaBinariaDif(agencia, Integer.parseInt(login.getAgencia()));
	}

	public boolean verificarSenha() {
		return buscaBinaria(contaSenha, Integer.parseInt(login.getConta()),
				Integer.parseInt(login.getSenha()));
	}

	public boolean verificarCod() {
		return buscaBinaria(cod, Integer.parseInt(login.getCod()), posicao);
	}

	public int getCod() {
		return cod[posicao];
	}
}