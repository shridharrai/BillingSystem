package com.shridhar.billing.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.shridhar.billing.dao.UserDAO;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnReset;
	static Logger logger = Logger.getLogger(Login.class);

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1256, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserid = new JLabel("Userid");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUserid.setBounds(389, 91, 104, 56);
		contentPane.add(lblUserid);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.requestFocus(true);
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(567, 76, 320, 71);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(389, 240, 104, 56);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(567, 246, 320, 50);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setForeground(Color.GREEN);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(431, 411, 187, 63);
		contentPane.add(btnLogin);
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(Color.GREEN);
		btnReset.setForeground(Color.BLUE);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBounds(664, 411, 187, 63);
		contentPane.add(btnReset);
	}

	private void checkLogin() {
		// TODO Auto-generated method stub
		logger.debug("Inside check login");
		String userid = textField.getText();
		String password = textField.getText();
		logger.debug("Recieved userid value "+userid+" Password "+password);
		UserDAO common = new UserDAO();
		try {
			boolean isUserexist = common.doLogin(userid, password);
			String message = isUserexist?"Welcome "+userid:" Invalid userid or password";
			JOptionPane.showMessageDialog(this, message);
			if(isUserexist) {
				DashBoard dashboard = new DashBoard(userid);
				dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dashboard.setVisible(true);
				this.setVisible(false);
				this.dispose();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Some database issue occured contact to system admin");
			logger.error(e);
		}
		
	}

}
