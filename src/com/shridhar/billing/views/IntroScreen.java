package com.shridhar.billing.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import java.awt.Color;

public class IntroScreen extends JWindow {
	static Logger logger = Logger.getLogger(IntroScreen.class);

	private JPanel contentPane;
	Timer timer;
	int counter = 1;
	
	private void animation() {
		logger.debug("Animation starts with counter value "+counter);
		timer = new Timer(50, (e)->{
			progressBar.setValue(counter);
			counter++;
			if(counter>=100) {
				this.setVisible(false);
				this.dispose();
				timer.stop();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		timer.start();
		logger.debug("Animation ends with counter value "+counter);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.debug("Inside main");
					IntroScreen frame = new IntroScreen();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.animation();
					logger.debug("Main ends");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JProgressBar progressBar = new JProgressBar();
	public IntroScreen() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 511);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel billingIntro = new JLabel("");
		billingIntro.setIcon(new ImageIcon("D:\\eclipse-workspace\\Billing\\src\\com\\shridhar\\billing\\views\\introImage"));
		billingIntro.setBounds(70, 103, 551, 266);
		contentPane.add(billingIntro);
		
		progressBar.setBackground(Color.RED);
		progressBar.setStringPainted(true);
		progressBar.setBounds(100, 311, 488, 46);
		contentPane.add(progressBar);
		
		
	}
}
