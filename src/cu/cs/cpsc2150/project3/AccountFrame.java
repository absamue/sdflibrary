package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AccountFrame extends JFrame {

	private JPanel myPanel;
	private NewAccountDialog newAccount;
	
	public AccountFrame(){
		super("Account Database");
		//set up panel with database table
		myPanel = new AccountPanel(this);
		//make dialog for adding new accounts
		newAccount = new NewAccountDialog(this);
		this.initialize();
	}
	
	public void initialize(){
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.add(myPanel);
		
		//button to set new account dialog visible
		//keep it nicely contained
		JPanel botPanel = new JPanel(new FlowLayout());		
		JButton addUser = new JButton("Add user");
		addUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				newAccount.setVisible(true);
			}
		});
		botPanel.add(addUser);
		this.add(botPanel, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
	}
}
