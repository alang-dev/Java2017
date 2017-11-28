package com.kenny;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UserInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserInterface() {
		setTitle("Calculator");
		this.setBounds(0, 0, 600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*---------------Menu Bar------------------*/

		JMenuBar menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		file.add(new JMenuItem("Open"));
		file.add(new JMenuItem("Close"));
		file.add(new JMenuItem("Exit"));

		menuBar.add(file);

		JMenu option = new JMenu("Option");

		JMenu subMenu = new JMenu("Calculator");
		subMenu.add(new JCheckBoxMenuItem("Standard"));
		subMenu.add(new JCheckBoxMenuItem("Scientific"));
		subMenu.add(new JCheckBoxMenuItem("Programmer"));
		subMenu.add(new JCheckBoxMenuItem("Date Calculation"));

		option.add(subMenu);
		option.add(new JMenuItem("Money converter"));

		menuBar.add(option);

		this.setJMenuBar(menuBar);

		/*---------------Layout------------------*/		
		this.add(BorderLayout.WEST, new JButton("West"));
		this.add(BorderLayout.SOUTH, new JButton("South"));
		this.add(BorderLayout.NORTH, new JLabel("Expression"));

		/*---------------Table------------------*/
		Object[] columnNames = { "ID", "FULLNAME", "ADDRESS", "SALARY" };
		Object[][] data = { { "1", "Smith", "Snowboarding", new Integer(5) }, { "2", "Doe", "Rowing", new Integer(3) },
				{ "3", "Black", "Knitting", new Integer(2) }, { "4", "White", "Speed reading", new Integer(20) },
				{ "5", "Brown", "Pool", new Integer(10) } };
		
		JTable customerTable = new JTable(data, columnNames);
		customerTable.setBorder(null);
		// ScrollPane
	    JScrollPane scrollPane = new JScrollPane(customerTable);
	    scrollPane.setBounds(36, 37, 407, 79);
	    getContentPane().add(scrollPane);
	    
		this.add(BorderLayout.CENTER, scrollPane);

		this.setVisible(true);
	}

	public ResultSet databaseConnection() {
		ResultSet customers = null;
		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/mydatabase";
			String username = "root";
			String password = "1234";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			customers = statement.executeQuery("Select * from customer");					

		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return customers;
	}
	
	public Customer resultSetRowToCustomer(ResultSet rs)
	{
		Customer customer = new Customer();
		try {
			customer.setId(rs.getInt(1));
			customer.setFullname(rs.getString(2));
			customer.setAddress(rs.getString(3));
			customer.setSalary(rs.getDouble(4));
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return customer;
	}
}
