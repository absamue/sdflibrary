package cu.cs.cpsc2150.project3;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NewAccountDialog extends JDialog {
	String[] staffOpt = {"Staff", "Member"};
	Frame myParent;

	public NewAccountDialog(Frame parent) {
		super(parent, "Add account", true);
		myParent = parent;
		this.initialize();
	}

	@SuppressWarnings("unchecked")
	public void initialize() {
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// info fields
		// username
		JLabel uname = new JLabel("Username:", SwingConstants.CENTER);
		panel.add(uname);
		JTextField unameText = new JTextField();
		panel.add(unameText);

		// password
		JLabel pword = new JLabel("Password:", SwingConstants.CENTER);
		panel.add(pword);
		JTextField pwordText = new JTextField();
		panel.add(pwordText);

		// CHANGE THIS TO DROP DOWN LIST
		JLabel type = new JLabel("Type:", SwingConstants.CENTER);
		panel.add(type);
//		JTextField typeText = new JTextField();
		@SuppressWarnings("rawtypes")
		JComboBox typeText = new JComboBox(staffOpt);
		panel.add(typeText);
		// CHANGE ME

		// name
		JLabel name = new JLabel("Name:", SwingConstants.CENTER);
		panel.add(name);
		JTextField nameText = new JTextField();
		panel.add(nameText);

		// email
		JLabel email = new JLabel("Email:", SwingConstants.CENTER);
		panel.add(email);
		JTextField emailText = new JTextField();
		panel.add(emailText);

		// phone
		JLabel phone = new JLabel("Phone #:", SwingConstants.CENTER);
		panel.add(phone);
		JTextField phoneText = new JTextField();
		panel.add(phoneText);

		// update selected account, except admin which is absolute
		JButton add = new JButton("Add User");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//make a new account and validifier from fields
				Account nw = new Account(unameText.getText(), pwordText.getText(), (String) typeText.getSelectedItem(),
						nameText.getText(), emailText.getText(), phoneText.getText(), 999);
				AccountValidifier check = new AccountValidifier(nw, null);
				
				//validate info. update if successful otherwise show relevant error
				if(check.validate()){
					MainFrame.userData.addUser(nw);
					AccountPanel.update();
					NewAccountDialog.this.setVisible(false);
					//remove all text fields for next open
					unameText.setText("");
					pwordText.setText("");
					nameText.setText("");
					emailText.setText("");
					phoneText.setText("");
				}
				else{
					JOptionPane.showMessageDialog(NewAccountDialog.this, check.error);
				}
			}
		});
		panel.add(add);

		// close window
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewAccountDialog.this.setVisible(false);
				//remove all text fields for next open
				unameText.setText("");
				pwordText.setText("");
				nameText.setText("");
				emailText.setText("");
				phoneText.setText("");
				
			}
		});

		panel.add(cancel);
		this.add(panel);
	}
}
