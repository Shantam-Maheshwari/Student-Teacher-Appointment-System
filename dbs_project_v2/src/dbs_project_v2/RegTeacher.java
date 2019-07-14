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

public class RegTeacher extends JFrame {

	private JPanel contentPane;
	private JTextField tfTeacherid;
	private JTextField tfPassword;
	private JTextField tfFirstname;
	private JTextField tfLastname;
	private JTextField tfSalary;
	private JTextField tfFC;
	private JTextField tfDeptid;

	/**
	 * Launch the application.
	 */
	public void regteacher() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegTeacher frame = new RegTeacher();
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
	public RegTeacher() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeacherRegistration = new JLabel("Teacher Registration");
		lblTeacherRegistration.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblTeacherRegistration.setBounds(79, 11, 289, 37);
		contentPane.add(lblTeacherRegistration);
		
		JLabel lblTeacherid = new JLabel("TeacherID");
		lblTeacherid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeacherid.setBounds(10, 59, 102, 20);
		contentPane.add(lblTeacherid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 84, 102, 20);
		contentPane.add(lblPassword);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstname.setBounds(10, 109, 102, 20);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastname.setBounds(10, 134, 102, 20);
		contentPane.add(lblLastname);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSalary.setBounds(10, 159, 102, 20);
		contentPane.add(lblSalary);
		
		JLabel lblFacultyChamber = new JLabel("Faculty Chamber");
		lblFacultyChamber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyChamber.setBounds(10, 183, 168, 20);
		contentPane.add(lblFacultyChamber);
		
		JLabel lblDeptid = new JLabel("DeptID");
		lblDeptid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeptid.setBounds(10, 208, 156, 20);
		contentPane.add(lblDeptid);
		
		tfTeacherid = new JTextField();
		tfTeacherid.setBounds(200, 56, 168, 20);
		contentPane.add(tfTeacherid);
		tfTeacherid.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(200, 81, 168, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		tfFirstname = new JTextField();
		tfFirstname.setBounds(200, 106, 168, 20);
		contentPane.add(tfFirstname);
		tfFirstname.setColumns(10);
		
		tfLastname = new JTextField();
		tfLastname.setBounds(200, 131, 168, 20);
		contentPane.add(tfLastname);
		tfLastname.setColumns(10);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(200, 156, 168, 20);
		contentPane.add(tfSalary);
		tfSalary.setColumns(10);
		
		tfFC = new JTextField();
		tfFC.setBounds(200, 180, 168, 20);
		contentPane.add(tfFC);
		tfFC.setColumns(10);
		
		tfDeptid = new JTextField();
		tfDeptid.setBounds(200, 205, 168, 20);
		contentPane.add(tfDeptid);
		tfDeptid.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String Teacherid = new String(tfTeacherid.getText());
					String Password = new String(tfPassword.getText());
					String Firstname = new String(tfFirstname.getText());
					String Lastname = new String(tfLastname.getText());
					String Salary = new String(tfSalary.getText());
					String FC = new String(tfFC.getText());
					String Deptid = new String(tfDeptid.getText());
					
					Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					
					Statement stmt = con.createStatement();
					String sql = "insert into teacher values ('" + Teacherid + "', '" + Password + "', '" + Firstname + "', '" + Lastname + "', " + Salary + ", '" + FC + "', '" + Deptid + "')" ;
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
		btnRegister.setBounds(148, 239, 102, 27);
		contentPane.add(btnRegister);
	}
}
