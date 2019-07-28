package com.shridhar.billing.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.shridhar.billing.dao.ProductDAO;
import com.shridhar.billing.dto.ProductDTO;
import com.shridhar.billing.utils.BundleReader;
import com.shridhar.billing.utils.CommonConstants;
import com.shridhar.billing.utils.Email;
import com.shridhar.billing.utils.ExcelReader;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DashBoard extends JFrame implements CommonConstants {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DashBoard frame = new DashBoard("shridhar");
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
	
	private void uploadXLS() {
		JFileChooser openDialog = new JFileChooser("C:\\Users\\Administrator\\Desktop\\products.xlsx");
		openDialog.showOpenDialog(this);
		File currentFile = openDialog.getSelectedFile();
		System.out.println("File "+currentFile.getAbsolutePath());
		try {
			ArrayList<ProductDTO> products = ExcelReader.readExcel(currentFile);
			ProductDAO productDAO = new ProductDAO();
			String message = productDAO.bulkAdd(products)?"Bulk Uploaded Successfully ":"Not Uploaded some error";
			if(message.contains("Successfully")) {
				Email.sendMail();
				JOptionPane.showMessageDialog(this, message);
			}
			else {
				JOptionPane.showMessageDialog(this, message);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void downloadXLS() {
		File file = new File(BundleReader.getValue(XLS_FORMAT_KEY));
		if(file.exists()) {
			try {
				FileInputStream fs = new FileInputStream(file);
				FileOutputStream fo = 
						new FileOutputStream(BundleReader.getValue(DOWNLOAD_KEY));
				int singleByte = fs.read();
				while(singleByte!=EOF) {
					fo.write(singleByte);
					System.out.println((char)singleByte);
					singleByte = fs.read();
				}
				JOptionPane.showMessageDialog(this, "Download Completed "+BundleReader.getValue(DOWNLOAD_KEY));
				fs.close();
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public DashBoard(String userid) {
		setTitle("Welcome "+userid);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		
		JMenu mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);
		
		JMenuItem mntmAddProduct = new JMenuItem("Download XLS");
		mntmAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downloadXLS();
			}
		});
		mnProducts.add(mntmAddProduct);
		
		JMenuItem mntmDeleteProduct = new JMenuItem("Upload XLS");
		mntmDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadXLS();
			}
		});
		mnProducts.add(mntmDeleteProduct);
		
		JMenuItem mntmUpdateProduct = new JMenuItem("List Product");
		mnProducts.add(mntmUpdateProduct);
		
		JMenuItem mntmCustomerReport = new JMenuItem("Customer Report");
		mnProducts.add(mntmCustomerReport);
		
		JMenu mnOrder = new JMenu("Order");
		menuBar.add(mnOrder);
		
		JMenuItem mntmOrderPlace = new JMenuItem("Order Place");
		mnOrder.add(mntmOrderPlace);
		
		JMenuItem mntmTrack = new JMenuItem("Track");
		mnOrder.add(mntmTrack);
//		menuBar.setBounds(10, 11, 99, 22);
//		contentPane.add(menuBar);
	}
}
