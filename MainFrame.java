import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{
	private JTabbedPane mainPanel;
	
	private JoinPanel joinPanel;
	private LoginPanel loginPanel;
	private SelectPanel selectPanel;
	private DeletePanel deletePanel;
	private UpdatePanel updatePanel;
	
	public MainFrame() {
		joinPanel = new JoinPanel();
		loginPanel = new LoginPanel();
		selectPanel = new SelectPanel();
		deletePanel = new DeletePanel();
		updatePanel = new UpdatePanel();
		
		mainPanel = new JTabbedPane();
		mainPanel.addTab("JOIN", joinPanel);
		mainPanel.addTab("LOGIN", loginPanel);
		mainPanel.addTab("SELECT", selectPanel);
		mainPanel.addTab("DELETE", deletePanel);
		mainPanel.addTab("UPDATE", updatePanel);		
		add(mainPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
	}

}
