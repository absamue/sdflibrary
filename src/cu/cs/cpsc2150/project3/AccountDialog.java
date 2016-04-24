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
public class AccountDialog extends JDialog {
	private Account myAccount;
	String[] staffOpt = { "Staff", "Member" };

	// initialize dialog with parent frame and account to show info of
	public AccountDialog(Frame parent, Account selection) {
		super(parent, "Account info", true);
		myAccount = selection;
		this.initialize();
	}

	@SuppressWarnings("unchecked")
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
		panel.add(unameText);

		// password
		JLabel pword = new JLabel("Password:", SwingConstants.CENTER);
		panel.add(pword);
		JTextField pwordText = new JTextField(myAccount.myPassword);
		panel.add(pwordText);

		// account type
		JLabel type = new JLabel("Type:", SwingConstants.CENTER);
		panel.add(type);
		@SuppressWarnings("rawtypes")
		JComboBox typeText = new JComboBox(staffOpt);
		if (!myAccount.staff)
			typeText.setSelectedIndex(1);
		panel.add(typeText);

		// name
		JLabel name = new JLabel("Name:", SwingConstants.CENTER);
		panel.add(name);
		JTextField nameText = new JTextField(myAccount.myName);
		panel.add(nameText);

		// email
		JLabel email = new JLabel("Email:", SwingConstants.CENTER);
		panel.add(email);
		JTextField emailText = new JTextField(myAccount.myEmail);
		panel.add(emailText);

		// phone
		JLabel phone = new JLabel("Phone #:", SwingConstants.CENTER);
		panel.add(phone);
		JTextField phoneText = new JTextField(myAccount.myPhone);
		panel.add(phoneText);

		// update selected account, except admin which is absolute
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// build an account from fields, and send it to the validifer
				Account nw = new Account(unameText.getText(), pwordText.getText(), (String) typeText.getSelectedItem(),
						nameText.getText(), emailText.getText(), phoneText.getText(), myAccount.myId);
				AccountValidifier check = new AccountValidifier(nw, myAccount);

				// if validation succeeds, update the account. otherwise show
				// error at failed test
				if (check.validate()) {
					MainFrame.userData.update(myAccount, nw);
					AccountPanel.update();
					AccountDialog.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(AccountDialog.this, check.error);
				}

			}
		});
		// admin doesnt need to change anything, dont want to lock ourselves out
		if (myAccount.myUsername.equals("admin")) {
			update.setEnabled(false);
		}
		panel.add(update);

		// remove current account from database
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.userData.removeAccount(myAccount);
				AccountPanel.update();
				AccountDialog.this.setVisible(false);
			}
		});
		// admin account cannot be removed
		if (myAccount.myUsername.equals("admin"))
			remove.setEnabled(false);
		panel.add(remove);

		this.add(panel);
		this.setVisible(true);
	}

}
