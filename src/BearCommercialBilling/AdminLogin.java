package BearCommercialBilling;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPasswordField;

//This program is Developed and Written by Janber Christian Calamba//

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField textFieldPassword;
	public static AdminLogin frame = new AdminLogin();

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
	public AdminLogin() {
		setResizable(false);
		setTitle("Bear Commercial Billing Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 338);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbAdminLogin = new JLabel("Admin Login");
		lbAdminLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lbAdminLogin.setBounds(104, 29, 125, 25);
		contentPane.add(lbAdminLogin);
		
		JLabel lbUsername = new JLabel("Username:");
		lbUsername.setBounds(27, 78, 68, 14);
		contentPane.add(lbUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(27, 101, 280, 31);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 143, 68, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com/sql6496292", "sql6496292", "5DvJ9FtJAE");
					Statement stmt=con.createStatement();
					String query="select * from users where username='"+textFieldUsername.getText()+"' and pass='"+textFieldPassword.getText().toString()+"'";
					ResultSet rs=stmt.executeQuery(query);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successfully!");
						dispose();
						BillingSystem billsys = new BillingSystem();
						billsys.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect Password!");
					}
				} catch(Exception e) {System.out.print(e);}
			}
		});
		
		btnLogin.setBackground(SystemColor.control);
		btnLogin.setBounds(27, 220, 125, 31);
		contentPane.add(btnLogin);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(24, 168, 283, 31);
		contentPane.add(textFieldPassword);
	}
}
