package dbs_project_v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegStudent extends JFrame {

	private JPanel contentPane;
	private JTextField tfStudentid;
	private JTextField tfPassword;
	private JTextField tfLastname;
	private JTextField tfFirstname;
	private JTextField tfDeptid;

	/**
	 * Launch the application.
	 */
	public void regstudent() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegStudent frame = new RegStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegStudent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentRegistration = new JLabel("Student Registration");
		lblStudentRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblStudentRegistration.setBounds(63, 11, 278, 37);
		contentPane.add(lblStudentRegistration);
		
		JLabel lblStudentid = new JLabel("StudentID");
		lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentid.setBounds(10, 59, 74, 14);
		contentPane.add(lblStudentid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 84, 74, 14);
		contentPane.add(lblPassword);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstname.setBounds(10, 109, 82, 14);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastname.setBounds(10, 134, 74, 14);
		contentPane.add(lblLastname);
		
		JLabel lblDeptid = new JLabel("DeptID");
		lblDeptid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeptid.setBounds(10, 159, 74, 14);
		contentPane.add(lblDeptid);
		
		tfStudentid = new JTextField();
		tfStudentid.setBounds(164, 58, 186, 20);
		contentPane.add(tfStudentid);
		tfStudentid.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(164, 83, 186, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		tfLastname = new JTextField();
		tfLastname.setBounds(164, 133, 186, 20);
		contentPane.add(tfLastname);
		tfLastname.setColumns(10);
		
		tfFirstname = new JTextField();
		tfFirstname.setBounds(164, 108, 186, 20);
		contentPane.add(tfFirstname);
		tfFirstname.setColumns(10);
		
		tfDeptid = new JTextField();
		tfDeptid.setBounds(164, 158, 186, 20);
		contentPane.add(tfDeptid);
		tfDeptid.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String Studentid = new String(tfStudentid.getText());
					String Password = new String(tfPassword.getText());
					String Firstname = new String(tfFirstname.getText());
					String Lastname = new String(tfLastname.getText());
					String Deptid = new String(tfDeptid.getText());
					
					Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					
					Statement stmt = con.createStatement();
					String sql = "insert into student values ('" + Studentid + "', '" + Password + "', '" + Firstname + "', '" + Lastname + "', 0, 0, '" + Deptid + "')" ;
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Registration Successful!");
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null,  "Error!");
					con.close();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,  "Error!");
				}
				
			}
		});
		btnRegister.setBounds(144, 207, 96, 27);
		contentPane.add(btnRegister);
	}
}
