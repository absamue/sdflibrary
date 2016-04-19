package cu.cs.cpsc2150.project3;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AccountDialog extends JDialog {
	private Account myAccount;

	public AccountDialog(Frame parent, Account selection) {
		super(parent, "Account info", true);
		myAccount = selection;
		this.initialize();
	}

	public void initialize() {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// info fields
		// username
		JLabel uname = new JLabel("Username:", SwingConstants.CENTER);
		panel.add(uname);
		JTextField unameText = new JTextField(myAccount.myUsername);
		if (!MainFrame.activeUser.staff)
			unameText.setEditable(false);
		panel.add(unameText);

		// password
		JLabel pword = new JLabel("Password:", SwingConstants.CENTER);
		panel.add(pword);
		JTextField pwordText = new JTextField(myAccount.myPassword);
		if (!MainFrame.activeUser.staff)
			pwordText.setEditable(false);
		panel.add(pwordText);

		// CHANGE THIS TO DROP DOWN LIST
		JLabel type = new JLabel("Type:", SwingConstants.CENTER);
		panel.add(type);
		JTextField typeText = new JTextField(myAccount.myType);
		if (!MainFrame.activeUser.staff)
			typeText.setEditable(false);
		panel.add(typeText);
		// CHANGE ME

		// name
		JLabel name = new JLabel("Name:", SwingConstants.CENTER);
		panel.add(name);
		JTextField nameText = new JTextField(myAccount.myName);
		if (!MainFrame.activeUser.staff)
			nameText.setEditable(false);
		panel.add(nameText);

		// email
		JLabel email = new JLabel("Email:", SwingConstants.CENTER);
		panel.add(email);
		JTextField emailText = new JTextField(myAccount.myEmail);
		if (!MainFrame.activeUser.staff)
			emailText.setEditable(false);
		panel.add(emailText);

		// phone
		JLabel phone = new JLabel("Phone #:", SwingConstants.CENTER);
		panel.add(phone);
		JTextField phoneText = new JTextField(myAccount.myPhone);
		if (!MainFrame.activeUser.staff)
			phoneText.setEditable(false);
		panel.add(phoneText);

		// update selected account, except admin which is absolute
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Account nw = new Account(unameText.getText(), pwordText.getText(), typeText.getText(),
						nameText.getText(), emailText.getText(), phoneText.getText(), myAccount.myId);
				//no blank passwords allowed, for obvious security reasons
				if (nw.myPassword.isEmpty()) {
					JOptionPane.showMessageDialog(AccountDialog.this, "Password field cannot be blank.");
				} else {
					if (nw.myUsername.equals(myAccount.myUsername) || MainFrame.userData.checkUser(nw.myUsername)) {
						MainFrame.userData.update(myAccount, nw);
						AccountPanel.update();
						AccountDialog.this.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(AccountDialog.this, "Account with username already exists.");
					}
				}
			}
		});
		// admin doesnt need to change anything, dont want to lock ourselves out
		if (myAccount.myUsername.equals("admin")) {
			update.setEnabled(false);
		}
		panel.add(update);

		// close window
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.userData.removeAccount(myAccount);
				AccountPanel.update();
				AccountDialog.this.setVisible(false);
			}
		});
		if(myAccount.myUsername.equals("admin"))
			remove.setEnabled(false);
		panel.add(remove);
		
		this.add(panel);
		this.setVisible(true);
	}

}
