import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdatePanel extends JPanel{
	private JLabel lbId;
	private JLabel lbPassword;
	private JLabel lbName;
	private JLabel lbEmail;
	
	private JTextField tfId;
	private JPasswordField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	
	private JTextField CtfId;
	private JPasswordField CtfPassword;
	private JTextField CtfName;
	private JTextField CtfEmail;
	
	private JButton btUpdate;
	private JButton btReset;
	
	private UpdatePanel panel;
	
	public UpdatePanel() {
		panel = this;
		
		setLayout(new GridLayout(6,3));
		lbId = new JLabel("ID : ", JLabel.CENTER);
		lbPassword = new JLabel("Password : ", JLabel.CENTER);
		lbName = new JLabel("Name : ", JLabel.CENTER);
		lbEmail = new JLabel("E-mail : ", JLabel.CENTER);
		
		tfId = new JTextField();
		tfPassword = new JPasswordField();
		tfName = new JTextField();
		tfEmail = new JTextField();
		
		CtfId = new JTextField();
		CtfPassword = new JPasswordField();
		CtfName = new JTextField();
		CtfEmail = new JTextField();
		
		btUpdate = new JButton("Update");
		btReset = new JButton("Reset");
		btUpdate.addActionListener(actionListener);
		btReset.addActionListener(actionListener);
		
		add(new JLabel("종류", JLabel.CENTER), JLabel.CENTER);
		add(new JLabel("조건", JLabel.CENTER), JLabel.CENTER_ALIGNMENT);
		add(new JLabel("바꿀 값", JLabel.CENTER), JLabel.CENTER_ALIGNMENT);
		add(lbId, JLabel.CENTER_ALIGNMENT);
		add(tfId);add(CtfId);
		add(lbPassword, JLabel.CENTER_ALIGNMENT);
		add(tfPassword);add(CtfPassword);
		add(lbName, JLabel.CENTER_ALIGNMENT);
		add(tfName);add(CtfName);
		add(lbEmail, JLabel.CENTER_ALIGNMENT);
		add(tfEmail);add(CtfEmail);
		add(new JLabel("", JLabel.CENTER), JLabel.CENTER_ALIGNMENT);
		add(btUpdate, JLabel.CENTER_ALIGNMENT);
		add(btReset);
		
		setSize(400, 200);
	}
	private ActionListener actionListener = new ActionListener() {
		@Override
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if(source == btUpdate) {
				Connection connection = null;
				Statement stmt = null;
				
				try {
					connection = DatabaseManager.getConnection();
					if(connection != null) {
						stmt = connection.createStatement();
						String sql = "select name, email from member where id='" + tfId.getText() + "'" + "AND password='" + tfPassword.getText() + "'";
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()) {
							tfName.setText(rs.getString("name"));
							tfEmail.setText(rs.getString("email"));						
						}
						int count = stmt.executeUpdate("update member set name='" + CtfName.getText() + "', email='" + CtfEmail.getText() + "'"
								+ "where id='" + tfId.getText() + "'" + "AND password='" + tfPassword.getText() + "'");
						
						if(count == 1)
							JOptionPane.showMessageDialog(panel, "update success");
						else
							JOptionPane.showMessageDialog(panel, "update fail");
								
								
						rs.close();
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						if(stmt != null)
							stmt.close();
						if(connection != null)
							connection.close();
					}catch(Exception ee) {
						ee.printStackTrace();
					}
				}
			} else if(source == btReset) {
				reset();
			}
		}
	};
	private void reset() {
		tfId.setText("");
		tfPassword.setText("");
		tfName.setText("");
		tfEmail.setText("");
	}
}
