package cu.cs.cpsc2150.project3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static AccountData userData;
	public static Account activeUser;
	public static CatalogPanel catalogPanel;
	public static AccountPanel accPanel;
	LoginDialog login;
	NewBookDialog newBook;
	NewAccountDialog newAccount;

	public MainFrame() {
		super("SDF Library");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize account storage
		userData = new AccountData();
		// login dialog to grab user
		login = new LoginDialog(this);
		newBook = new NewBookDialog(this);
		catalogPanel = new CatalogPanel(this);
		MainFrame.activeUser = login.getAccount();
		this.initialize();
	}

	
	public void initialize(){
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(catalogPanel);
	
		//buttons for different actions, dependent on loggin in user
	//	JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		JPanel sidePanel = new JPanel(new BorderLayout());
		sidePanel.setBorder(new EmptyBorder(10,10,10,10));
		sidePanel.setPreferredSize(new Dimension(150, 500));
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
		JPanel loginPanel = new JPanel(new GridLayout(2,1,10,10));
	//	userPanel.setPreferredSize(new Dimension(125, 500));
//		userPanel.setBorder(new EmptyBorder(15,15,15,15));

		// active user info
		JLabel user = new JLabel("Active user: " + activeUser.myUsername, SwingConstants.CENTER);
		loginPanel.add(user);

		//add extra functionality if staff is logged in
		if (activeUser.staff) {
			JButton addBook = new JButton("Add book");
			addBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					newBook.setVisible(true);
				}
			});
			userPanel.add(addBook);
			
			JButton showAccs = new JButton("Accounts");
			showAccs.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AccountFrame accFrame = new AccountFrame(MainFrame.this);
				}
			});
			userPanel.add(showAccs);
			
		}

		JButton check = new JButton("Checkout");
		userPanel.add(check);

		// logout button
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// close main window and get rid of everything
				MainFrame.this.setVisible(false);
				MainFrame.this.getContentPane().removeAll();
				// save account information
				userData.save();
				// open login window
				login.setVisible(true);
				// if we got another user, recreate the frame
				MainFrame.activeUser = login.getAccount();
				if (activeUser != null) {
					MainFrame.this.initialize();
				}
			}
		});
		loginPanel.add(logout, BorderLayout.SOUTH);
		sidePanel.add(loginPanel, BorderLayout.SOUTH);
		sidePanel.add(userPanel);

		this.add(sidePanel, BorderLayout.WEST);
		this.setVisible(true);
		
	}

	public static Account getActive() {
		return activeUser;
	}
	

}
