package dbs_project_v2;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppointmentRequests extends JFrame {
	private String teacherid;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void appointmentrequests() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentRequests frame = new AppointmentRequests(teacherid);
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
	public AppointmentRequests(String id) {
		teacherid = new String(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAppointmentRequests = new JLabel("Appointment Requests");
		lblAppointmentRequests.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAppointmentRequests.setBounds(70, 11, 294, 46);
		contentPane.add(lblAppointmentRequests);
		
		JList listRequests = new JList();
		listRequests.setBounds(44, 90, 330, 69);
		contentPane.add(listRequests);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
			
			Statement stmt = con.createStatement();
			String sql = " select * from appointment where status = 'n' and course_id in (select course_id from teacher natural join course where teacher_id = '" + teacherid + "')";
			ResultSet rs = stmt.executeQuery(sql);
			
			DefaultListModel model = new DefaultListModel(); //create a new list model

		    while (rs.next()) //go through each row that your query returns
		    {
		        model.addElement(rs.getString("app_id") + rs.getString("app_day") + "    " + rs.getString("app_tslot") + "    " + rs.getString("service_id") + "    " + rs.getString("student_id") + "    " + rs.getString("course_id"));
		    }
		    listRequests.setModel(model);
		    
		    JButton btnAccept = new JButton("Accept");
		    btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    btnAccept.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent ae) {
		    		String request = (String)listRequests.getSelectedValue();
		    		int i;
		    		for(i=0; (int)request.charAt(i) < 'A'; i++);
		    		String appid = request.substring(0,  i);
		    		try {
		    			Class.forName("oracle.jdbc.driver.OracleDriver"); // register the driver
		    			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "Ededdacad01"); // establish connection
		    			con.setAutoCommit(false); 
		    			
		    			Statement stmt = con.createStatement();
		    			String sql = "update appointment set teacher_id = '" + teacherid + "', status = 'y' where app_id = '" + appid + "'";
		    			@SuppressWarnings("unused")
						ResultSet rs = stmt.executeQuery(sql);
		    			JOptionPane.showMessageDialog(null,  "Appointment Confirmed!");
		    	
		    			con.close();
		    		}
		    		catch(Exception e) {
		    			System.out.println(e);
		    		}
		    	}
		    });
		    btnAccept.setBounds(164, 182, 97, 33);
		    contentPane.add(btnAccept);
		    
		    JLabel lblAppid = new JLabel("AppID");
		    lblAppid.setBounds(44, 68, 48, 14);
		    contentPane.add(lblAppid);
		    
		    JLabel lblStudentid = new JLabel("StudentID");
		    lblStudentid.setBounds(248, 68, 68, 14);
		    contentPane.add(lblStudentid);
		    
		    JLabel lblDay = new JLabel("Day");
		    lblDay.setBounds(98, 68, 30, 14);
		    contentPane.add(lblDay);
		    
		    JLabel lblTslot = new JLabel("TSlot");
		    lblTslot.setBounds(138, 68, 48, 14);
		    contentPane.add(lblTslot);
		    
		    JLabel lblServiceid = new JLabel("ServiceID");
		    lblServiceid.setBounds(179, 68, 48, 14);
		    contentPane.add(lblServiceid);
		    
		    JLabel lblCourseid = new JLabel("CourseID");
		    lblCourseid.setBounds(326, 68, 48, 14);
		    contentPane.add(lblCourseid);
		    con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
