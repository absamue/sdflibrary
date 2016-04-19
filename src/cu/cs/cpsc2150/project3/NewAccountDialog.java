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
public class NewAccountDialog extends JDialog {

	Frame myParent;

	public NewAccountDialog(Frame parent) {
		super(parent, "Add account", true);
		myParent = parent;
		this.initialize();
	}

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
		JTextField typeText = new JTextField();
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
				Account nw = new Account(unameText.getText(), pwordText.getText(), typeText.getText(),
						nameText.getText(), emailText.getText(), phoneText.getText(), 999);
				//check password field
				if (nw.myPassword.isEmpty()) {
					JOptionPane.showMessageDialog(NewAccountDialog.this, "Password field cannot be blank.");
				} else {
					// make sure not overlapping usernames
					if (MainFrame.userData.checkUser(nw.myUsername)) {
						MainFrame.userData.addUser(nw);
						AccountPanel.update();
						NewAccountDialog.this.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(NewAccountDialog.this, "Account with username already exists.");
					}
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
			}
		});

		panel.add(cancel);
		this.add(panel);
	}
}
