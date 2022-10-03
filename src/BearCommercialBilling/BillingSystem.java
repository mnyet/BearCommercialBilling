package BearCommercialBilling;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//This program is Developed and Written by Janber Christian Calamba//

public class BillingSystem extends JFrame {

	private JPanel contentPane;
	public static BillingSystem frame = new BillingSystem();
	public static JTable tblData;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*enables the jframe of this program*/
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillingSystem() {
		setResizable(false);
		setTitle("Bear Commercial Billing Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 374);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("aone says hi! >///<");
		lblNewLabel.setBounds(449, 320, 90, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 909, 22);
		menuBar.setBackground(SystemColor.window);
		contentPane.add(menuBar);
		
		JMenu mnAdminOptions = new JMenu("Admin Options");
		mnAdminOptions.setFont(new Font("Segoe UI", Font.BOLD, 10));
		menuBar.add(mnAdminOptions);
		
		JMenuItem mntmExit = new JMenuItem("End Session");
		mntmExit.setBackground(SystemColor.window);
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "See you next time!");
				dispose();
				AdminLogin backtologin = new AdminLogin();
				backtologin.setVisible(true);
			} 
		});
		mnAdminOptions.add(mntmExit);
		
		JLabel lblRecentTransactions = new JLabel("Recent Transactions");
		lblRecentTransactions.setBounds(265, 42, 122, 14);
		lblRecentTransactions.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblRecentTransactions);
		
		JButton btnNewRecord = new JButton("New Transaction");
		btnNewRecord.setBounds(10, 177, 235, 43);
		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewTransaction newtrans = new NewTransaction();
				newtrans.setVisible(true);
			} 
		});
		btnNewRecord.setBackground(SystemColor.control);
		contentPane.add(btnNewRecord);
		
		JButton btnManiRecord = new JButton("Find Transactions\r\n");
		btnManiRecord.setBounds(10, 237, 235, 43);
		btnManiRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindTransaction findtrans = new FindTransaction();
				findtrans.setVisible(true);
			} 
		});
		btnManiRecord.setBackground(SystemColor.control);
		contentPane.add(btnManiRecord);
		
		JLabel lblNewLabel_1 = new JLabel("Good Day User! What would you like to do?");
		lblNewLabel_1.setBounds(24, 65, 215, 43);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(265, 67, 634, 245);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		tblData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblData);
		
		JButton btnRetrieve = new JButton("Retrieve Data / Refresh");
		btnRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tblData.setModel(new DefaultTableModel());
					/* pulls out data from database */
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com/sql6496292", "sql6496292", "5DvJ9FtJAE");					Statement stmt=con.createStatement();
					String query="select * from transactions";
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
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} 
		});
		btnRetrieve.setBackground(SystemColor.menu);
		btnRetrieve.setBounds(10, 112, 235, 43);
		contentPane.add(btnRetrieve);
	}
}
