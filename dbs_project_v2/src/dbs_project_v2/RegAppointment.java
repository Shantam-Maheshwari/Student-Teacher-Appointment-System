package dbs_project_v2;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class RegAppointment extends JFrame {
	private JPanel contentPane;
	private String studentid;
	public String deptid;
	public String day;
	public String tslot;
	public String serviceid;
	public String courseid;
	/**
	 * Launch the application.
	 */
	public void regappointment() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegAppointment frame = new RegAppointment(studentid);
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
	@SuppressWarnings("rawtypes")
	public RegAppointment(String id) {
		studentid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAppointmentBooking = new JLabel("Appointment Booking");
		lblAppointmentBooking.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAppointmentBooking.setBounds(62, 11, 313, 37);
		contentPane.add(lblAppointmentBooking);
		
		JLabel lblAppointmentDay = new JLabel("Appointment Day");
		lblAppointmentDay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAppointmentDay.setBounds(10, 59, 148, 22);
		contentPane.add(lblAppointmentDay);
		
		JLabel lblAppointmentTslot = new JLabel("Appointment TSlot");
		lblAppointmentTslot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAppointmentTslot.setBounds(10, 92, 148, 22);
		contentPane.add(lblAppointmentTslot);
		
		JLabel lblCourseid = new JLabel("CourseID");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(10, 125, 148, 20);
		contentPane.add(lblCourseid);
		
		JLabel lblServicename = new JLabel("ServiceName");
		lblServicename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServicename.setBounds(10, 156, 148, 22);
		contentPane.add(lblServicename);
		
		/* SELECTING COURSEID */
		String courses[] = new String[5];
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			// finding dept_id
			Statement stmt1 = con.createStatement();
			String sql1 = "select dept_id from student where student_id = '" + studentid + "'";
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if(rs1.next()) {
				deptid = new String(rs1.getString("dept_id"));
			}
			
			// finding course_id of dept_id
			Statement stmt2 = con.createStatement();			
			String sql2 = "select course_id from course where dept_id = '" + deptid + "'";
			ResultSet rs2 = stmt2.executeQuery(sql2);
			int i=0;
			while(rs2.next()) {
					courses[i++] = new String(rs2.getString("course_id"));
			}
	
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		/* SELECTING COURSEID */
		JComboBox comboBoxCourseID = new JComboBox(courses);
		comboBoxCourseID.setBounds(168, 126, 136, 22);
		contentPane.add(comboBoxCourseID);

		comboBoxCourseID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				courseid = new String((String) comboBoxCourseID.getSelectedItem());
			}
		});
		
		/* SELECTING DAY */
		String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		@SuppressWarnings("unchecked")
		JComboBox comboBoxAppointmentDay = new JComboBox(days);
		comboBoxAppointmentDay.setBounds(168, 61, 136, 22);
		contentPane.add(comboBoxAppointmentDay);
		
		comboBoxAppointmentDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				day = new String((String) comboBoxAppointmentDay.getSelectedItem());
			}
		});
		
		/* SELECTING TSLOT */
		String tslots[] = {"TSlot1", "TSlot2", "TSlot3"};
		JComboBox comboBoxAppointmentTSlot = new JComboBox(tslots);
		comboBoxAppointmentTSlot.setBounds(168, 94, 136, 22);
		contentPane.add(comboBoxAppointmentTSlot);
		
		comboBoxAppointmentTSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tslot = new String((String) comboBoxAppointmentTSlot.getSelectedItem());
			}
		});
		
		/* SELECTING SERVICE */
		String services[] = {"ser001", "ser002", "ser003"};
		JComboBox comboBoxServiceID = new JComboBox(services);
		comboBoxServiceID.setBounds(168, 158, 136, 22);
		contentPane.add(comboBoxServiceID);
		
		comboBoxServiceID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serviceid = new String((String) comboBoxServiceID.getSelectedItem());
			}
		});
		
		JButton btnBook = new JButton("Book");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBook.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
					
				Statement stmt1 = con.createStatement();
				String sql1 = "select * from studentTT where dept_id = '" + deptid + "' and day = '" + day + "' and " + tslot + " is null";
				System.out.println(day + tslot + serviceid + studentid + courseid);
				ResultSet rs1 = stmt1.executeQuery(sql1);
				System.out.println(2);
				if(rs1.next()) {
					// insert
					Statement stmt2 = con.createStatement();
					Random rand = new Random(); 
					System.out.println(3);
					String sql2 = "insert into appointment values ('" + rand.nextInt(1000) + "', '" + day + "', '" + tslot + "', 'n', '" + serviceid +"', '" + studentid + "', null, '" + courseid + "')" ; //insert into appointment
					ResultSet rs2 = stmt2.executeQuery(sql2);
					JOptionPane.showMessageDialog(null,  "Booking Sucessful!");
				}
				else {
					JOptionPane.showMessageDialog(null,  "TSlot not available!");
				}
				con.close();
			}
			catch(Exception e) {
				
			}
		}});
		btnBook.setBounds(168, 213, 103, 37);
		contentPane.add(btnBook);
		
	}
}
