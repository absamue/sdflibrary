package cu.cs.cpsc2150.project3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog {
	private Account acc;
	
	public LoginDialog(Frame parent){
		super(parent, "Login", true);
		this.initialize();
	}
	
	public void initialize(){
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		
		//grid panel
		final JPanel panel = new JPanel(new GridLayout(3,2,10,10));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		
		//username field
		JLabel userLabel = new JLabel("User", SwingConstants.CENTER);
		panel.add(userLabel);
		final JTextField userText = new JTextField();
		panel.add(userText);
		
		//password field
		JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
		panel.add(passwordLabel);
		final JPasswordField passwordText = new JPasswordField();
		panel.add(passwordText);
		
		
		//login button should process data
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//get associated account from userData and compare password
				if(AccountData.verify(userText.getText(), new String(passwordText.getPassword()))){
					LoginDialog.this.acc = AccountData.getUser(userText.getText());
					LoginDialog.this.setVisible(false);
					userText.setText(null);
					passwordText.setText(null);
				}
				else{
					JOptionPane.showMessageDialog(panel, "Incorrect username or password.");
				}
			}
		});
		//enter to hit login
		this.getRootPane().setDefaultButton(login);
		panel.add(login);
	
		//cancel button to exit program
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		panel.add(cancel);
		
		this.add(panel);

		this.setVisible(true);
	}
	
	public Account getAccount(){
		return acc;
	}
}
