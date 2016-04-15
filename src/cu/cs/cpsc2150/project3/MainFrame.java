package cu.cs.cpsc2150.project3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	AccountData userData;
	Account activeUser;
	LoginDialog login;
	
	public MainFrame(){
		super("SDF Library");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//initialize account storage
		userData = new AccountData();
		//login dialog to grab user
		login = new LoginDialog(this);
		this.activeUser = login.getAccount();
		this.initialize();
	}
	
	public void initialize(){
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		JPanel userPanel = new JPanel(new BorderLayout(30, 30));
		userPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		//active user info
		JLabel user = new JLabel("Active user: " + activeUser.username);
		userPanel.add(user, BorderLayout.EAST);
		this.add(userPanel, BorderLayout.SOUTH);

		
		//Catalog tab
		JTabbedPane tabbedPane = new JTabbedPane();
		CatalogPanel catalogPanel = new CatalogPanel();
		tabbedPane.addTab("Catalog", catalogPanel);
		
		
		//Accounts tab
		if(activeUser.staff == true){
			JPanel accPanel = new JPanel();
			tabbedPane.addTab("Accounts", accPanel);
		}
		this.add(tabbedPane);
		
		//logout button
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener(){
			@Override	
			public void actionPerformed(ActionEvent e){
				//close main window and get rid of everything
				MainFrame.this.setVisible(false);
				MainFrame.this.getContentPane().removeAll();
				//save account information
				userData.save();
				//open login window
				login.setVisible(true);
				//if we got another user, recreate the frame
				MainFrame.this.activeUser = login.getAccount();
				if(activeUser != null){
					MainFrame.this.initialize();
				}
			}
		});
		userPanel.add(logout, BorderLayout.WEST);
		
		this.setVisible(true);

	}

	

}
