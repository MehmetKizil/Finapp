package login;

import java.awt.event.*;
import mainApp.Main;

public class LoginController implements ActionListener{
	
	// Attribute
	LoginView loginView;
	private LoginModel loginModel = new LoginModel();
	
	// Konstruktor
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
	}

	// Übergibt die Login Daten dem LoginModel, um den User einzuloggen und die Main Applikation zu öffnen
	@Override
	public void actionPerformed(ActionEvent e) {
		String usernameInput = loginView.getUsernameTextField().getText();
		String passwordInput = new String(loginView.getPasswordField().getPassword());
		
		if (loginModel.verifyUsernamePassword(usernameInput, passwordInput)) {
			Main.startMainProgram();
			loginView.dispose();
		}
		else
			loginView.getLoginWarningLabel().setVisible(true);;
	}
}

class RegisterActionEventHandler implements ActionListener{

	// Attribute
	LoginView loginView;
	
	// Konstruktor
	public RegisterActionEventHandler(LoginView loginView) {
		this.loginView = loginView;
	}
	
	// Registrierungs Button: Öffnet ein Registrierungs-Fenster
	@Override
	public void actionPerformed(ActionEvent e) {
		loginView.startRegisterWindow();
	}	
}

class SaveAndCloseEventHandler implements ActionListener {
	
	// Attribute
	LoginView loginView;
	private LoginModel loginModel = new LoginModel();
	
	// Konstruktor
	public SaveAndCloseEventHandler(LoginView loginView) {
		this.loginView = loginView;
	}

	// Übermittelt die neuen User Daten (Registrierung) dem LoginModel, um die Daten in der Datenbank abzuspeichern
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = new String(loginView.getRegisterUsernameTextField().getText());
		String password = new String(loginView.getRegisterPasswordField().getPassword());
		loginModel.insertUser(username, password);
		loginView.getRegisterFrame().dispose();
	}
}
