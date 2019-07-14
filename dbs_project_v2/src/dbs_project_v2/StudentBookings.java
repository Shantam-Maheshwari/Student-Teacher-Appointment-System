package dbs_project_v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

public class StudentBookings extends JFrame {
	private JPanel contentPane;
	private String studentid;
	private JTable tableBookings;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	/**
	 * Launch the application.
	 */
	public void studentbookings() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentBookings frame = new StudentBookings(studentid);
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
	public StudentBookings(String id) {
		studentid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 496, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyBookings = new JLabel("My Bookings");
		lblMyBookings.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblMyBookings.setBounds(154, 11, 178, 37);
		contentPane.add(lblMyBookings);
		
		tableBookings = new JTable();
		tableBookings.setBounds(49, 86, 401, 90);
		contentPane.add(tableBookings);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			Statement stmt = con.createStatement();
			String sql = "select * from appointment where student_id = '" + studentid + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			tableBookings.setModel(DbUtils.resultSetToTableModel(rs));
			
			label = new JLabel("AppID");
			label.setBounds(49, 62, 48, 14);
			contentPane.add(label);
			
			label_1 = new JLabel("Day");
			label_1.setBounds(98, 62, 29, 14);
			contentPane.add(label_1);
			
			label_2 = new JLabel("TSlot");
			label_2.setBounds(138, 61, 35, 14);
			contentPane.add(label_2);
			
			label_3 = new JLabel("Status");
			label_3.setBounds(183, 61, 35, 14);
			contentPane.add(label_3);
			
			label_4 = new JLabel("ServiceID");
			label_4.setBounds(228, 61, 48, 14);
			contentPane.add(label_4);
			
			label_5 = new JLabel("StudentID");
			label_5.setBounds(286, 61, 67, 14);
			contentPane.add(label_5);
			
			label_6 = new JLabel("TeacherID");
			label_6.setBounds(343, 62, 60, 14);
			contentPane.add(label_6);
			
			label_7 = new JLabel("CourseID");
			label_7.setBounds(402, 61, 48, 14);
			contentPane.add(label_7);

			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
