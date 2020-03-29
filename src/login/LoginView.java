package login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView extends JFrame{

	// Deklarieren von verschiedenen Attributen
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel titelLabel;
	private JLabel registerTitelLabel;
	private JLabel loginWarningLabel;
	private JLabel registerWarningLabel;
	private JTextField usernameTextField;
	private JTextField registerUsernameTextField;
	private JPasswordField passwordField;
	private JPasswordField registerPasswordField;
	private JButton loginButton;
	private JButton registerButton;
	private JButton saveAndCloseButton;
	private ActionListener actionEventHandler;
	private ActionListener registerActionEventHandler;
	private ActionListener saveAndCloseEventHandler;
	private JFrame registerFrame;
	
	// Konstruktor
	public LoginView() {
		super("Login");
	}
	
	// Erstellt und startet den ersten Screen des Programms (Login Fenster)
	public void startLogin() {
		
		// Erstelle TitelPanel
		JPanel titelPanel = new JPanel();
		titelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titelLabel = new JLabel("Anmelden oder Registrieren");
		titelPanel.add(titelLabel);
		
		// Erstelle LoginPanel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		usernameLabel = new JLabel ("Benutzername: ");
		passwordLabel = new JLabel ("Passwort: ");
		usernameTextField = new JTextField(10);
		passwordField = new JPasswordField(10);
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameTextField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);
		
		// Erstelle ButtonPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		loginWarningLabel = new JLabel("Benutername oder Passwort falsch");
		loginWarningLabel.setVisible(false);
		loginWarningLabel.setForeground(Color.RED);
		loginButton = new JButton("Login");
		registerButton = new JButton("Registrieren");
		buttonPanel.add(loginWarningLabel);
		buttonPanel.add(loginButton);
		buttonPanel.add(registerButton);
		
		// EventHandling
		actionEventHandler = new LoginController(this);
		registerActionEventHandler = new RegisterActionEventHandler(this);
		loginButton.addActionListener(actionEventHandler);
		registerButton.addActionListener(registerActionEventHandler);
		
		// Bringe Panels zusammen und zeige Fenster
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,150);
		add(titelPanel, BorderLayout.NORTH);
		add(loginPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	// Erstellt ein Registrierungs Fenster
	public void startRegisterWindow() {
		
		registerFrame = new JFrame("Registrieren");
		// Erstelle TitelPanel
		JPanel registerTitelPanel = new JPanel();
		registerTitelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		registerTitelLabel = new JLabel("Neuen User registrieren");
		registerTitelPanel.add(registerTitelLabel);
		
		// Erstelle LoginPanel
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		usernameLabel = new JLabel ("Benutzername: ");
		passwordLabel = new JLabel ("Passwort: ");
		registerWarningLabel = new JLabel("Bitte füllen Sie die Felder aus!");
		registerWarningLabel.setVisible(false);
		registerWarningLabel.setForeground(Color.red);
		registerUsernameTextField = new JTextField(10);
		registerPasswordField = new JPasswordField(10);
		registerPanel.add(usernameLabel);
		registerPanel.add(registerUsernameTextField);
		registerPanel.add(passwordLabel);
		registerPanel.add(registerPasswordField);
		registerPanel.add(registerWarningLabel);
		
		// Erstelle ButtonPanel
		JPanel registerButtonPanel = new JPanel();
		registerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		saveAndCloseButton = new JButton("Speichern und schließen");
		registerButtonPanel.add(saveAndCloseButton);
			
		// EventHandling
		saveAndCloseEventHandler = new SaveAndCloseEventHandler(this);
		saveAndCloseButton.addActionListener(saveAndCloseEventHandler);
		
		// Bringe Panels zusammen und zeige Fenster
		registerFrame.getContentPane().setLayout(new BorderLayout());
		registerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		registerFrame.setSize(400,150);
		registerFrame.add(registerTitelPanel, BorderLayout.NORTH);
		registerFrame.add(registerPanel, BorderLayout.CENTER);
		registerFrame.add(registerButtonPanel, BorderLayout.SOUTH);
		registerFrame.setVisible(true);
	}
	
	// Benötigte Getter und Setter
	public JLabel getLoginWarningLabel() {
		return loginWarningLabel;
	}

	public JFrame getRegisterFrame() {
		return registerFrame;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JTextField getRegisterUsernameTextField() {
		return registerUsernameTextField;
	}

	public JPasswordField getRegisterPasswordField() {
		return registerPasswordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JLabel getRegisterWarningLabel() {
		return registerWarningLabel;
	}
}
