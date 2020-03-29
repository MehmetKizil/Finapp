package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import login.LoginModel;

public class AppController implements ActionListener {

	// Deklarieren von Attributen
	AppView appView;
	AppModel appModel = new AppModel();
	
	// Konstruktor
	public AppController(AppView appView) {
		this.appView = appView;
	}
	
	// Übermittelt die eingegebenen Daten der AppModel, um die Daten in der Datenbank zu speichern
	@Override
	public void actionPerformed(ActionEvent e) {
		if (appView.getDataNameTextField().getText().equals("") || appView.getDataValueTextField().getText().equals("")) {
			appView.warningDialog();
		}else {
			if (appView.getIncomeRadioButton().isSelected()) {
				appModel.addData(LoginModel.currentUserID, appView.getDataNameTextField().getText(), appView.getDataValueTextField().getText(), null);
				appView.tableModelIncome.setDataVector(getData("amountIncome"), appView.columnNames);
			}else {
				appModel.addData(LoginModel.currentUserID, appView.getDataNameTextField().getText(), null, appView.getDataValueTextField().getText());	
				appView.tableModelOutgoing.setDataVector(getData("amountOutgoing"), appView.columnNames);
			}	
		}
	}
	// Holt die Daten aus AppModel
	public String[][] getData(String amountType){
		return appModel.getDataFromDB(amountType);
	}
}
