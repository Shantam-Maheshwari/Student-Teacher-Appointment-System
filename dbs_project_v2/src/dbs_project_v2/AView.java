package dbs_project_v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void aview() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AView frame = new AView();
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
	public AView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminPortal = new JLabel("Admin Portal");
		lblAdminPortal.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAdminPortal.setBounds(186, 22, 174, 30);
		contentPane.add(lblAdminPortal);
		
		JButton btnRegisterNewStudent = new JButton("Register new Student");
		btnRegisterNewStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegisterNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegStudent rs = new RegStudent();
				rs.regstudent();
			}
		});
		btnRegisterNewStudent.setBounds(76, 76, 184, 39);
		contentPane.add(btnRegisterNewStudent);
		
		JButton btnRegisterNewTeacher = new JButton("Register new Teacher");
		btnRegisterNewTeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegisterNewTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegTeacher rt = new RegTeacher();
				rt.regteacher();
			}
		});
		btnRegisterNewTeacher.setBounds(278, 76, 184, 39);
		contentPane.add(btnRegisterNewTeacher);
		
		JButton btnDeregisterStudent = new JButton("Deregister Student");
		btnDeregisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeregStudent ds = new DeregStudent();
				ds.deregstudent();
			}
		});
		btnDeregisterStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeregisterStudent.setBounds(76, 153, 184, 39);
		contentPane.add(btnDeregisterStudent);
		
		JButton btnDeregisterTeacher = new JButton("Deregister Teacher");
		btnDeregisterTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DeregTeacher dt = new DeregTeacher();
				dt.deregteacher();
			}
		});
		btnDeregisterTeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeregisterTeacher.setBounds(278, 153, 184, 39);
		contentPane.add(btnDeregisterTeacher);
	}
}
