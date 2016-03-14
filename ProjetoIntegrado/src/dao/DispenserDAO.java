package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class DispenserDAO {
	private Formatter output;
	private Scanner input;

	public DispenserDAO() {
		
	}

	public void openFile() {
		try {
			input = new Scanner(new File("Dispenser.txt"));
		}

		catch (SecurityException securityException) {

			System.err.println("Voce nao tem permissao para escrever o arquivo.");
			System.exit(1);
		} catch (FileNotFoundException filesNotFoundException) {
			System.err.println("Erro ao criar o arquivo.");
			System.exit(1);
		}
	}

	public void closeFile() {
		if (input != null) {
			input.close();
		}
	}

	public int get10() throws FileNotFoundException {
		Scanner aux = new Scanner(new File("Dispenser.txt"));
		int quantidade = aux.nextInt();
		return quantidade;
	}

	public int get20() throws FileNotFoundException {
		Scanner aux = new Scanner(new File("Dispenser.txt"));
		int quantidade = 0;
		aux.nextInt();
		quantidade = aux.nextInt();
		return quantidade;
	}

	public int get50() throws FileNotFoundException {
		Scanner aux = new Scanner(new File("Dispenser.txt"));
		int quantidade = 0;
		aux.nextInt();
		aux.nextInt();
		quantidade = aux.nextInt();
		return quantidade;
	}

	public void sacar(int quantidade, int nota) throws FileNotFoundException {
		Formatter output = new Formatter("DispenserAuxiliar.txt");
		int linha = 0;
		int qtdTotal = 0;
		int contador = 0;
		switch (nota) {
		case 10:
			qtdTotal = get10();
			linha = 1;
			break;
		case 20:
			qtdTotal = get20();
			linha = 2;
			break;
		default:
			qtdTotal = get50();
			linha = 3;
			break;
		}
		if (1 != linha) {
			output.format(get10() + "\n");
		} else {
			int aux = get10() - quantidade;
			output.format(aux + "\n");
		}
		if (2 != linha) {
			output.format(get20() + "\n");
		} else {
			int aux = get20() - quantidade;
			output.format(aux + "\n");
		}
		if (3 != linha) {
			output.format(get50() + "\n");
		} else {
			int aux = get50() - quantidade;
			output.format(aux + "\n");
		}
		output.close();
		Scanner leitor = new Scanner(new File("DispenserAuxiliar.txt"));
		String v1, v2, v3;
		v1 = leitor.next();
		v2 = leitor.next();
		v3 = leitor.next();
		leitor.close();
		Formatter oi = new Formatter("Dispenser.txt");
		oi.format(v1 + "\n");
		oi.format(v2 + "\n" + v3);
		oi.close();

	}
	public String dados() throws FileNotFoundException{
		String aux = "";
		Scanner x = new Scanner(new File("Dispenser.txt"));
		while(x.hasNextLine()){
			aux += x.next() + "\n";
			if(x.hasNextLine()) x.nextLine();
		}
		x.close();
		return aux;
	}
}
