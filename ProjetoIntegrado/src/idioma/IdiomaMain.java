package idioma;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

import login.LoginController;
import login.LoginForm;

public class IdiomaMain extends JFrame implements ActionListener {
	private String names[] = { "eua.png", "br.png", "esp.png" };
	private Icon icons[] = { new ImageIcon(getClass().getResource(names[0])),
			new ImageIcon(getClass().getResource(names[1])),
			new ImageIcon(getClass().getResource(names[2])) };
	private JButton bandeiraEUA, bandeiraBR, bandeiraESP;
	private ResourceBundle bn;

	public IdiomaMain() {
		super("Elige tu idioma ||  Escolha seu idioma || Choose your language");
		setLayout(new BorderLayout());
		bandeiraEUA = new JButton(icons[0]);
		bandeiraBR = new JButton(icons[1]);
		bandeiraESP = new JButton(icons[2]);
		bandeiraEUA.addActionListener(this);
		bandeiraBR.addActionListener(this);
		bandeiraESP.addActionListener(this);
		add(bandeiraEUA, BorderLayout.EAST);
		add(bandeiraBR, BorderLayout.CENTER);
		add(bandeiraESP, BorderLayout.WEST);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bandeiraEUA){
			bn = ResourceBundle.getBundle("proj",
			new Locale("en", "US"));
			new LoginController(bn);
			dispose();
		}
		if(e.getSource() == bandeiraBR){
			bn = ResourceBundle.getBundle("proj",
			new Locale("pt", "BR"));
			new LoginController(bn);
			dispose();
		}
		if(e.getSource() == bandeiraESP){
			bn = ResourceBundle.getBundle("proj",
			new Locale("es", "ES"));
			new LoginController(bn);
			dispose();
		}
	}

	public static void main(String[] args) {
		new IdiomaMain();
	}
}
