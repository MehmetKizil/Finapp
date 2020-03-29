package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AppView extends JFrame{
	
	// Deklarieren/Initialisieren von Attributen
	private JLabel appTitelLabel;
	private JTable incomeTable;
	private JTable outgoingTable;
	private JLabel dataNameLabel;
	private JLabel dataValueLabel;
	private JTextField dataNameTextField;
	private JTextField dataValueTextField;
	private JButton addDataButton;
	private JRadioButton incomeRadioButton;
	private JRadioButton outgoingRadioButton;
	private ButtonGroup entryTypeGroup;
	AppController appController = new AppController(this);
	DefaultTableModel tableModelIncome;
	DefaultTableModel tableModelOutgoing;
	String [] columnNames = {"Beschreibung" , "Wert"};
	private ActionListener actionEventHandler;
	
	// Konstruktor
	public AppView() {
		super("Finapp");
	}
	
	// Erstellt das Main-Fenster
	public void startApp() {
		JPanel appTitelPanel = new JPanel();
		appTitelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		appTitelLabel = new JLabel("Finapp");
		appTitelPanel.add(appTitelLabel);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout());
		columnNames[0] = "Einkommen";
		tableModelIncome = new DefaultTableModel(appController.getData("amountIncome"), columnNames);
		incomeTable = new JTable(tableModelIncome);
		JScrollPane incomeTablePane = new JScrollPane(incomeTable);
		columnNames[0] = "Ausgaben";
		tableModelOutgoing = new DefaultTableModel(appController.getData("amountOutgoing"), columnNames);
		outgoingTable = new JTable(tableModelOutgoing);
		JScrollPane outgoingTablePane = new JScrollPane(outgoingTable);
		tablePanel.add(incomeTablePane);
		tablePanel.add(outgoingTablePane);

		JPanel addDataPanel = new JPanel();
		addDataPanel.setLayout(new BoxLayout(addDataPanel, BoxLayout.Y_AXIS));
		dataNameLabel = new JLabel("Name des Eintrags:");
		dataValueLabel = new JLabel("Wert des Eintrags:");
		dataNameTextField = new JTextField();
		dataNameTextField.setMaximumSize(new Dimension(300,20));
		dataValueTextField = new JTextField();
		dataValueTextField.setMaximumSize(new Dimension(300, 20));
		addDataButton = new JButton("Hinzufügen");
		incomeRadioButton = new JRadioButton("Income");
		incomeRadioButton.setSelected(true);
		outgoingRadioButton = new JRadioButton("Outgoing");
		addDataPanel.add(dataNameLabel);
		addDataPanel.add(dataNameTextField);
		addDataPanel.add(dataValueLabel);
		addDataPanel.add(dataValueTextField);
		addDataPanel.add(incomeRadioButton);
		addDataPanel.add(outgoingRadioButton);
		addDataPanel.add(addDataButton);
		entryTypeGroup = new ButtonGroup();
		entryTypeGroup.add(incomeRadioButton);
		entryTypeGroup.add(outgoingRadioButton);
		
		actionEventHandler = new AppController(this);
		addDataButton.addActionListener(actionEventHandler);
		
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100,400);
		add(appTitelPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(addDataPanel, BorderLayout.WEST);
		setVisible(true);
	}

	public void warningDialog() {
		JOptionPane.showMessageDialog(this, "Sie haben keine Daten eingegeben. Bitte füllen Sie die Felder aus");
	}
	// Benötige Getter und Setter
	public JRadioButton getIncomeRadioButton() {
		return incomeRadioButton;
	}

	public JRadioButton getOutgoingRadioButton() {
		return outgoingRadioButton;
	}

	public JTextField getDataNameTextField() {
		return dataNameTextField;
	}

	public JTextField getDataValueTextField() {
		return dataValueTextField;
	}
} 
