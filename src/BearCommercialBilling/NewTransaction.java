package BearCommercialBilling;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

//This program is Developed and Written by Janber Christian Calamba//

public class NewTransaction extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldName;
	private JTextField textFieldAmount;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*enables the jframe of this program*/
				NewTransaction frame = new NewTransaction();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewTransaction() {
		setResizable(false);
		setTitle("New Transaction");
		setBounds(100, 100, 402, 310);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBackground(SystemColor.control);
		table.setBounds(496, 306, -484, -280);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Add a new transaction");
		lblNewLabel.setBounds(22, 30, 140, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(22, 66, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Amount:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1_1.setBounds(22, 110, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Purpose:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1_1.setBounds(22, 150, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(71, 58, 284, 31);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(71, 102, 284, 31);
		contentPane.add(textFieldAmount);
		
		JRadioButton rdbtnRent = new JRadioButton("Payment for Rent");
		buttonGroup.add(rdbtnRent);
		rdbtnRent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnRent.setBackground(SystemColor.window);
		rdbtnRent.setBounds(74, 146, 123, 23);
		contentPane.add(rdbtnRent);
		
		JRadioButton rdbtnElectricity = new JRadioButton("Payment for Electricity");
		buttonGroup.add(rdbtnElectricity);
		rdbtnElectricity.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnElectricity.setBackground(SystemColor.window);
		rdbtnElectricity.setBounds(215, 146, 140, 23);
		contentPane.add(rdbtnElectricity);
		
		JRadioButton rdbtnWater = new JRadioButton("Payment for Water Bills");
		buttonGroup.add(rdbtnWater);
		rdbtnWater.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnWater.setBackground(Color.WHITE);
		rdbtnWater.setBounds(74, 180, 140, 23);
		contentPane.add(rdbtnWater);
		
		JRadioButton rdbtnTax = new JRadioButton("Payment for Taxes");
		buttonGroup.add(rdbtnTax);
		rdbtnTax.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnTax.setBackground(Color.WHITE);
		rdbtnTax.setBounds(215, 180, 140, 23);
		contentPane.add(rdbtnTax);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String amountNumCheck = textFieldAmount.getText();
					boolean numCheckResult = amountNumCheck.matches("[0-9]+");
					if(textFieldName.getText().trim().isEmpty() && textFieldAmount.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "We cannot submit any blank entries!");
					}
					else {
						if(numCheckResult == false) {
							JOptionPane.showMessageDialog(null, "No numbers allowed in the amounts field!");
						}
						
						else {
							String purpose="";
							
							if(rdbtnRent.isSelected() == true) {
								purpose = "Rental";
							}
							else if(rdbtnElectricity.isSelected() == true) {
								purpose = "Electric Bill";
							}
							else if(rdbtnWater.isSelected() == true) {
								purpose = "Water Bill";
							}
							else if(rdbtnTax.isSelected() == true) {
								purpose = "Tax Payment";
							}
							/* pulls out data from database */
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com/sql6496292", "sql6496292", "5DvJ9FtJAE");							Statement stmt=con.createStatement();
							String query="insert into transactions values(0, '"+textFieldName.getText()+"', '"+textFieldAmount.getText()+"', '"+purpose+"', CURRENT_TIMESTAMP)";
							stmt.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Entry Submitted Successfully!");
						}
					}
				} catch(Exception e1) {System.out.print(e1);}
			}
		});
		btnSubmit.setBackground(SystemColor.control);
		btnSubmit.setBounds(73, 210, 124, 31);
		contentPane.add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldName.setText("");
				textFieldAmount.setText("");
				buttonGroup.clearSelection();
			}
		});
		btnClear.setBackground(SystemColor.control);
		btnClear.setBounds(207, 210, 67, 31);
		contentPane.add(btnClear);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			} 
		});
		btnBack.setBackground(SystemColor.menu);
		btnBack.setBounds(284, 210, 71, 31);
		contentPane.add(btnBack);
		
	}
}
