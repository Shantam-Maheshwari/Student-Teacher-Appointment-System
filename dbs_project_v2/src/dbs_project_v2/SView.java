package dbs_project_v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class SView extends JFrame {
	private String studentid;
	private JPanel contentPane;
	private JTable table;
	private JTable tableStudent;
	private JLabel lblStudentDetails;

	/**
	 * Launch the application.
	 */
	public void sview() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SView frame = new SView(studentid);
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
	public SView(String id) {
		studentid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentPortal = new JLabel("Student Portal");
		lblStudentPortal.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblStudentPortal.setBounds(120, 11, 192, 37);
		contentPane.add(lblStudentPortal);
		
		tableStudent = new JTable();
		tableStudent.setBounds(35, 111, 367, 49);
		contentPane.add(tableStudent);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			Statement stmt = con.createStatement();
			String sql = "select student_id, fname, lname, grade, attendance, dept_id from student where student_id = '" + studentid + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			tableStudent.setModel(DbUtils.resultSetToTableModel(rs));
			
			lblStudentDetails = new JLabel("Student Details");
			lblStudentDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblStudentDetails.setBounds(35, 44, 121, 29);
			contentPane.add(lblStudentDetails);
			
			JButton btnMyBookings = new JButton("My Bookings");
			btnMyBookings.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnMyBookings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentBookings sb = new StudentBookings(studentid);
					sb.studentbookings();
				}
			});
			btnMyBookings.setBounds(121, 191, 165, 37);
			contentPane.add(btnMyBookings);
			
			JButton btnBookAppointment = new JButton("Book Appointment");
			btnBookAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnBookAppointment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegAppointment ra = new RegAppointment(studentid);
					ra.regappointment();
				}
			});
			btnBookAppointment.setBounds(121, 249, 165, 37);
			contentPane.add(btnBookAppointment);
			
			JLabel lblStudentid = new JLabel("StudentID");
			lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblStudentid.setBounds(35, 84, 51, 14);
			contentPane.add(lblStudentid);
			
			JLabel lblFirstname = new JLabel("FirstName");
			lblFirstname.setBounds(96, 84, 51, 14);
			contentPane.add(lblFirstname);
			
			JLabel lblLastname = new JLabel("LastName");
			lblLastname.setBounds(162, 84, 51, 14);
			contentPane.add(lblLastname);
			
			JLabel lblGrade = new JLabel("Grade");
			lblGrade.setBounds(223, 84, 44, 14);
			contentPane.add(lblGrade);
			
			JLabel lblAttendance = new JLabel("Attendance");
			lblAttendance.setBounds(277, 84, 67, 14);
			contentPane.add(lblAttendance);
			
			JLabel lblDeptid = new JLabel("DeptID");
			lblDeptid.setBounds(354, 84, 48, 14);
			contentPane.add(lblDeptid);
			
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
