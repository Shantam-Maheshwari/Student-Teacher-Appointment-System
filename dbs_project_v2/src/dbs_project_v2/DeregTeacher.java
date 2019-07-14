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

public class DeregTeacher extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void deregteacher() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeregTeacher frame = new DeregTeacher();
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
	public DeregTeacher() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 378, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeregisterTeacher = new JLabel("Deregister Teacher");
		lblDeregisterTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblDeregisterTeacher.setBounds(63, 11, 245, 35);
		contentPane.add(lblDeregisterTeacher);
		
		JLabel lblTeacherid = new JLabel("TeacherID");
		lblTeacherid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeacherid.setBounds(30, 64, 71, 22);
		contentPane.add(lblTeacherid);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(138, 67, 180, 20);
		contentPane.add(textField);
		
		JButton button = new JButton("Deregister");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String teacherid = new String(textField.getText());
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					
					Statement stmt = con.createStatement();
					String sql = "delete from teacher where teacher_id = '" + teacherid + "'" ;
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(132, 115, 108, 22);
		contentPane.add(button);
	}

}
