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
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

//This program is Developed and Written by Janber Christian Calamba//

public class FindTransaction extends JFrame {

	private JPanel contentPane;
	private JLabel lblNamel;
	private JTextField textFieldName;
	private JLabel lblNewLabel_1;
	private JRadioButton rdbtnTax;
	private JRadioButton rdbtnWater;
	private JRadioButton rdbtnRent;
	private JRadioButton rdbtnElectricity;
	private JButton btnFind;
	private JButton btnBack;
	private JTable tblData;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*enables the jframe of this program*/
				FindTransaction frame = new FindTransaction();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FindTransaction() {
		setResizable(false);
		setTitle("Find Transaction");
		setBounds(100, 100, 923, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Find a transaction by:");
		lblNewLabel.setBounds(21, 25, 163, 14);
		contentPane.add(lblNewLabel);
		
		lblNamel = new JLabel("Name:");
		lblNamel.setBounds(21, 57, 46, 14);
		contentPane.add(lblNamel);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(21, 82, 232, 30);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Purpose:");
		lblNewLabel_1.setBounds(21, 123, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		rdbtnTax = new JRadioButton("Payment for Taxes");
		buttonGroup.add(rdbtnTax);
		rdbtnTax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnTax.setBackground(Color.WHITE);
		rdbtnTax.setBounds(21, 247, 142, 23);
		contentPane.add(rdbtnTax);
		
		rdbtnWater = new JRadioButton("Payment for Water Bills");
		buttonGroup.add(rdbtnWater);
		rdbtnWater.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnWater.setBackground(Color.WHITE);
		rdbtnWater.setBounds(21, 195, 142, 23);
		contentPane.add(rdbtnWater);
		
		rdbtnRent = new JRadioButton("Payment for Rent");
		buttonGroup.add(rdbtnRent);
		rdbtnRent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnRent.setBackground(Color.WHITE);
		rdbtnRent.setBounds(21, 169, 142, 23);
		contentPane.add(rdbtnRent);
		
		rdbtnElectricity = new JRadioButton("Payment for Electricity");
		buttonGroup.add(rdbtnElectricity);
		rdbtnElectricity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnElectricity.setBackground(Color.WHITE);
		rdbtnElectricity.setBounds(21, 221, 142, 23);
		contentPane.add(rdbtnElectricity);
		
		JRadioButton rdbtnAllPurposeCream = new JRadioButton("All");
		buttonGroup.add(rdbtnAllPurposeCream);
		rdbtnAllPurposeCream.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnAllPurposeCream.setBackground(Color.WHITE);
		rdbtnAllPurposeCream.setBounds(21, 144, 142, 23);
		contentPane.add(rdbtnAllPurposeCream);
		rdbtnAllPurposeCream.setSelected(true);
		
		btnFind = new JButton("Go");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String searchpurpose="";
					
					if(rdbtnRent.isSelected() == true) {
						searchpurpose = "Rental";
					}
					else if(rdbtnElectricity.isSelected() == true) {
						searchpurpose = "Electric Bill";
					}
					else if(rdbtnWater.isSelected() == true) {
						searchpurpose = "Water Bill";
					}
					else if(rdbtnTax.isSelected() == true) {
						searchpurpose = "Tax Payment";
					}
					else if(rdbtnAllPurposeCream.isSelected() == true) {
						searchpurpose = "";
					}
					tblData.setModel(new DefaultTableModel());
					/* pulls out data from database */
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com/sql6496292", "sql6496292", "5DvJ9FtJAE");					Statement stmt=con.createStatement();
					String query="select * from transactions where Name like '%"+textFieldName.getText()+"%' and Purpose like '%"+searchpurpose+"%'";
					ResultSet rs=stmt.executeQuery(query);
					ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
					DefaultTableModel model= (DefaultTableModel) tblData.getModel();
					
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					
					for(int i=0; i<cols; i++) {
						colName[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
					}
					
					String transno, name, amount, purpose, date;
					
					while(rs.next()) {
						transno=rs.getString(1);
						name=rs.getString(2);
						amount=rs.getString(3);
						purpose=rs.getString(4);
						date=rs.getString(5);
						
						String[] row= {transno, name, amount, purpose, date};
						model.addRow(row);
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnFind.setBackground(SystemColor.control);
		btnFind.setBounds(21, 277, 111, 30);
		contentPane.add(btnFind);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			} 
		});
		btnBack.setBackground(SystemColor.menu);
		btnBack.setBounds(142, 277, 111, 30);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 11, 626, 296);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		scrollPane.setViewportView(tblData);

	}
}
