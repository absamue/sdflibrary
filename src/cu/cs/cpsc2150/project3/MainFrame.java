package cu.cs.cpsc2150.project3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public AccountData userData;
	public static Account activeUser;
	public static CatalogPanel catalogPanel;
	LoginDialog login;

	public MainFrame() {
		super("SDF Library");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize account storage
		userData = new AccountData();
		// login dialog to grab user
		login = new LoginDialog(this);
		MainFrame.activeUser = login.getAccount();
		this.initialize();
	}

	public void initialize() {
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		// Catalog tab
		JTabbedPane tabbedPane = new JTabbedPane();
		catalogPanel = new CatalogPanel(this);
		tabbedPane.addTab("Catalog", catalogPanel);

		// Accounts tab
		if (activeUser.staff == true) {
			JPanel accPanel = new JPanel();
			tabbedPane.addTab("Accounts", accPanel);
		}
		this.add(tabbedPane);

		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 5));
		userPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// active user info
		JLabel user = new JLabel("Active user: " + activeUser.username);
		userPanel.add(user);

		if (activeUser.staff) {
			JButton addBook = new JButton("Add book");
			addBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					NewBookDialog newBook = new NewBookDialog(MainFrame.this);
				}
			});
			userPanel.add(addBook);
		}

		JButton check = new JButton("Check out");
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
		userPanel.add(logout);

		this.add(userPanel, BorderLayout.SOUTH);
		this.setVisible(true);

	}

	public static Account getActive() {
		return activeUser;
	}

}
