package dbs_project_v2;

// start "C:\Users\MAHE\Desktop\dbs_drop_v1.sql"
// start "C:\Users\MAHE\Desktop\dbs_schema_v2.sql"

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserid;
	private JPasswordField tfPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblLogin.setBounds(126, 11, 78, 37);
		contentPane.add(lblLogin);
		
		JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonGroup.add(rdbtnStudent);
		rdbtnStudent.setBounds(6, 55, 89, 23);
		contentPane.add(rdbtnStudent);
		
		JRadioButton rdbtnTeacher = new JRadioButton("Teacher");
		rdbtnTeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonGroup.add(rdbtnTeacher);
		rdbtnTeacher.setBounds(126, 51, 89, 31);
		contentPane.add(rdbtnTeacher);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(245, 55, 78, 23);
		contentPane.add(rdbtnAdmin);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserid.setBounds(24, 128, 78, 23);
		contentPane.add(lblUserid);
		
		tfUserid = new JTextField();
		tfUserid.setBounds(126, 131, 197, 20);
		contentPane.add(tfUserid);
		tfUserid.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(24, 184, 71, 23);
		contentPane.add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(126, 187, 197, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					Statement stmt = con.createStatement();
			
					/* STUDENT */
					if(rdbtnStudent.isSelected()) {
						String sql = "select * from student where student_id = '" + tfUserid.getText() + "' and student_pw = '" + tfPassword.getText() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Login Successful!");
							SView s = new SView(tfUserid.getText());
							s.sview();
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null,  "Incorrect UserId or Password!");	
					}
					
					/* TEACHER */
					else if(rdbtnTeacher.isSelected()) {
						String sql = "select * from teacher where teacher_id = '" + tfUserid.getText() + "' and teacher_pw = '" + tfPassword.getText() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null,  "Login Successful!");
							TView t = new TView(tfUserid.getText());
							t.tview();
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null,  "Incorrect UserId or Password!");
					}
					
					/* ADMIN */
					else if(rdbtnAdmin.isSelected()) {
						String sql = "select * from alogin where id = '" + tfUserid.getText() + "' and pw = '" + tfPassword.getText() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null,  "Login Successful!");
							AView a = new AView();
							a.aview();
							dispose();
						}
						else
							JOptionPane.showMessageDialog(null,  "Incorrect UserId or Password!");
					}
					con.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		btnLogin.setBounds(126, 244, 89, 23);
		contentPane.add(btnLogin);
	}
}
