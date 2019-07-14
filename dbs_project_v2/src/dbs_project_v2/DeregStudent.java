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

public class DeregStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void deregstudent() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeregStudent frame = new DeregStudent();
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
	public DeregStudent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeregisterStudent = new JLabel("Deregister Student");
		lblDeregisterStudent.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblDeregisterStudent.setBounds(68, 11, 245, 35);
		contentPane.add(lblDeregisterStudent);
		
		JLabel lblStudentid = new JLabel("StudentID");
		lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentid.setBounds(52, 64, 71, 22);
		contentPane.add(lblStudentid);
		
		textField = new JTextField();
		textField.setBounds(133, 67, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDeregister = new JButton("Deregister");
		btnDeregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String studentid = new String(textField.getText());
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					
					Statement stmt = con.createStatement();
					String sql = "delete from student where student_id = '" + studentid + "'" ;
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Success!");
					}
					else
						JOptionPane.showMessageDialog(null,  "Not found!");
					con.close();
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnDeregister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeregister.setBounds(130, 114, 108, 22);
		contentPane.add(btnDeregister);
	}
}
