package dbs_project_v2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

public class TeacherBookings extends JFrame {
	String teacherid;
	private JPanel contentPane;
	private JTable tableBookings;
	private JLabel lblAppid;
	private JLabel lblDay;
	private JLabel lblTslot;
	private JLabel lblStatus;
	private JLabel lblCourseid;
	private JLabel lblNewLabel;
	private JLabel lblStudentid;
	private JLabel lblTeacherid;

	/**
	 * Launch the application.
	 */
	public void teacherbookings() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherBookings frame = new TeacherBookings(teacherid);
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
	public TeacherBookings(String id) {
		teacherid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyAppointments = new JLabel("My Appointments");
		lblMyAppointments.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblMyAppointments.setBounds(137, 11, 242, 37);
		contentPane.add(lblMyAppointments);
		
		tableBookings = new JTable();
		tableBookings.setBounds(24, 106, 467, 70);
		contentPane.add(tableBookings);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			Statement stmt = con.createStatement();
			String sql = "select * from appointment where teacher_id = '" + teacherid + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			tableBookings.setModel(DbUtils.resultSetToTableModel(rs));
			
			lblAppid = new JLabel("AppID");
			lblAppid.setBounds(24, 81, 48, 14);
			contentPane.add(lblAppid);
			
			lblDay = new JLabel("Day");
			lblDay.setBounds(82, 81, 29, 14);
			contentPane.add(lblDay);
			
			lblTslot = new JLabel("TSlot");
			lblTslot.setBounds(137, 81, 35, 14);
			contentPane.add(lblTslot);
			
			lblStatus = new JLabel("Status");
			lblStatus.setBounds(195, 81, 35, 14);
			contentPane.add(lblStatus);
			
			lblCourseid = new JLabel("CourseID");
			lblCourseid.setBounds(443, 81, 48, 14);
			contentPane.add(lblCourseid);
			
			lblNewLabel = new JLabel("ServiceID");
			lblNewLabel.setBounds(246, 81, 48, 14);
			contentPane.add(lblNewLabel);
			
			lblStudentid = new JLabel("StudentID");
			lblStudentid.setBounds(304, 81, 67, 14);
			contentPane.add(lblStudentid);
			
			lblTeacherid = new JLabel("TeacherID");
			lblTeacherid.setBounds(370, 81, 60, 14);
			contentPane.add(lblTeacherid);

			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
