package model;

import java.io.FileNotFoundException;

import dao.DispenserDAO;

public class Dispenser {
	private DispenserDAO dispenser;

	public Dispenser() {
		dispenser = new DispenserDAO();
	}

	public boolean sacar(double valor) throws FileNotFoundException {
		if (valor % 10 % 10 != 0)
			return false;
		int qtd50, qtd20, qtd10;
		qtd50 = qtd20 = qtd10 = 0;
		if (valor >= 50) {
			qtd50 = (int) (valor / 50);
			valor = valor - 50 * qtd50;
		}
		if (valor >= 20) {
			qtd20 = (int) (valor / 20);
			valor = valor - 20 * qtd20;
		}
		if (valor >= 10) {
			qtd10 = (int) (valor / 10);
			valor = valor - 10 * qtd10;
		}
		if (dispenser.get10() < qtd10 || dispenser.get20() < qtd20 || dispenser.get50() < qtd50) {
			return false;
		} else {
			dispenser.sacar(qtd50, 50);
			dispenser.sacar(qtd20, 20);
			dispenser.sacar(qtd10, 10);
		}
		return true;
	}

	public boolean verificarNotas(double valor) throws FileNotFoundException {
		if (valor % 10 % 10 != 0)
			return false;
		int qtd50, qtd20, qtd10;
		qtd50 = qtd20 = qtd10 = 0;
		if (valor >= 50) {
			qtd50 = (int) (valor / 50);
			valor = valor - 50 * qtd50;
		}
		if (valor >= 20) {
			qtd20 = (int) (valor / 20);
			valor = valor - 20 * qtd20;
		}
		if (valor >= 10) {
			qtd10 = (int) (valor / 10);
			valor = valor - 10 * qtd10;
		}
		if (dispenser.get10() > qtd10 && dispenser.get20() > qtd20 && dispenser.get50() > qtd50) {
			return true;
		}
		return false;
	}
}
