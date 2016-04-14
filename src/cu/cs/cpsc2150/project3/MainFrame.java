package cu.cs.cpsc2150.project3;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	AccountData userData;
	Account activeUser;
	LoginDialog login;
	
	public MainFrame(){
		super("SDF Library");
		this.setResizable(false);
	}
	
	public void initialize(){
		userData = new AccountData();
		login = new LoginDialog(this);
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.activeUser = login.getAccount();
	}

	

}
