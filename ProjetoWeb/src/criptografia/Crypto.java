package criptografia;

import java.io.*;

public class Crypto {
	private byte[] textoCifrado;
	private byte[] textoDecifrado;

	public Crypto() {
		textoCifrado = null;
		textoDecifrado = null;
	}

	public void geraChave(File fDummy) throws IOException {
		// Gera uma chave Dummy simetrica (dk: 0 a 1000):
		int dk = (int) (Math.random() * 1001);
		// Grava a chave Dummy simetrica em formato serializado
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				fDummy));
		oos.writeObject(dk);
		oos.close();
	}

	public void geraCifra(byte[] texto, File fDummy) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				fDummy));
		int iDummy = (Integer) ois.readObject();
		ois.close();
		textoCifrado = texto;
		for (int i = 0; i < texto.length; i+=2) {
			textoCifrado[i] = (byte) (textoCifrado[i] + i*7 + iDummy);
		}
	}

	public byte[] getTextoCifrado() throws Exception {
		return textoCifrado;
	}

	public void geraDecifra(byte[] texto, File fDummy) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				fDummy));
		int iDummy = (Integer) ois.readObject();
		ois.close();
		textoDecifrado = texto;
		for (int i = 0; i < texto.length; i+=2) {
			textoDecifrado[i] = (byte) (textoDecifrado[i] - i*7 - iDummy);
		}
	}

	public byte[] getTextoDecifrado() throws Exception {
		return textoDecifrado;
	}
}