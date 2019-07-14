package dbs_project_v2;

import java.awt.BorderLayout;
import net.proteanit.sql.DbUtils;
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

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TView extends JFrame {
	private String teacherid;
	private JPanel contentPane;
	private JTable tableTeacher;
	private JButton btnMyAppointments;
	private JButton btnAppointmentRequests;
	private JLabel lblTeacherid;
	private JLabel lblFirstname;
	private JLabel lblLastname;
	private JLabel lblSalary;
	private JLabel lblFc;
	private JLabel lblDeptid;
	private JLabel lblTeacherDetails;

	/**
	 * Launch the application.
	 */
	public void tview() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TView frame = new TView(teacherid);
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
	public TView(String id) {
		teacherid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeacherPortal = new JLabel("Teacher Portal");
		lblTeacherPortal.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblTeacherPortal.setBounds(130, 11, 188, 42);
		contentPane.add(lblTeacherPortal);
		
		tableTeacher = new JTable();
		tableTeacher.setBounds(45, 116, 353, 59);
		contentPane.add(tableTeacher);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			Statement stmt = con.createStatement();
			String sql = "select teacher_id, fname, lname, salary, fc, dept_id from teacher where teacher_id = '" + teacherid + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			tableTeacher.setModel(DbUtils.resultSetToTableModel(rs));
			
			btnMyAppointments = new JButton("My Appointments");
			btnMyAppointments.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnMyAppointments.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TeacherBookings tb = new TeacherBookings(teacherid);
					tb.teacherbookings();
				}
			});
			btnMyAppointments.setBounds(130, 199, 187, 36);
			contentPane.add(btnMyAppointments);
			
			btnAppointmentRequests = new JButton("Appointment Requests");
			btnAppointmentRequests.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAppointmentRequests.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AppointmentRequests ar = new AppointmentRequests(teacherid);
					ar.appointmentrequests();
				}
			});
			btnAppointmentRequests.setBounds(130, 246, 188, 36);
			contentPane.add(btnAppointmentRequests);
			
			lblTeacherid = new JLabel("TeacherID");
			lblTeacherid.setBounds(45, 91, 60, 14);
			contentPane.add(lblTeacherid);
			
			lblFirstname = new JLabel("FirstName");
			lblFirstname.setBounds(109, 91, 60, 14);
			contentPane.add(lblFirstname);
			
			lblLastname = new JLabel("LastName");
			lblLastname.setBounds(179, 91, 60, 14);
			contentPane.add(lblLastname);
			
			lblSalary = new JLabel("Salary");
			lblSalary.setBounds(249, 91, 40, 14);
			contentPane.add(lblSalary);
			
			lblFc = new JLabel("FC");
			lblFc.setBounds(299, 91, 24, 14);
			contentPane.add(lblFc);
			
			lblDeptid = new JLabel("DeptID");
			lblDeptid.setBounds(343, 91, 40, 14);
			contentPane.add(lblDeptid);
			
			lblTeacherDetails = new JLabel("Teacher Details");
			lblTeacherDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTeacherDetails.setBounds(48, 51, 121, 29);
			contentPane.add(lblTeacherDetails);
			
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
