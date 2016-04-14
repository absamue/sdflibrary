package cu.cs.cpsc2150.project3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	public LoginFrame(String title){
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void initialize(){
		this.setSize(300, 150);
		
		//grid panel
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		//username field
		JLabel userLabel = new JLabel("User", SwingConstants.CENTER);
		panel.add(userLabel);
		JTextField userText = new JTextField();
		panel.add(userText);
		
		//password field
		JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
		panel.add(passwordLabel);
		JPasswordField passwordText = new JPasswordField();
		panel.add(passwordText);
		
		
		//login button should process data
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//get associated account from userData and compare password
				if(AccountData.verify(userText.getText(), new String(passwordText.getPassword()))){
					JOptionPane.showMessageDialog(panel, "success");
				}
				else{
					JOptionPane.showMessageDialog(panel, "Login error");
				}
			}
		});
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
		
		//open in center of screen
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation(dimension.width/2-this.getSize().width/2, dimension.height/2-this.getSize().height/2);

		this.setVisible(true);
	}
}
