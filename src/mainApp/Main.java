package mainApp;

import login.LoginView;

public class Main {

	public static void main(String[] args) {
		start();
	}
	
	// Startet Login Fenster und bringt Programm zum Laufen
	public static void start() {
		LoginView login = new LoginView();
		login.startLogin();
	}
	
	// Startet die Main Applikation
	public static void startMainProgram() {
		AppView app = new AppView();
		app.startApp();
	}
}
