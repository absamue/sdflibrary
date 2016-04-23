package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
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
public class SelectUserDialog extends JDialog {

	private Account find;
	
	public SelectUserDialog(Frame parent){
		super(parent, "Select User", true);
		this.setSize(400,150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		find = null;
	}
	
	public void initialize(){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));
		
		//prompt message
		JLabel message = new JLabel("Enter the ID number of a member to proceed with checkout.", SwingConstants.CENTER);
		panel.add(message, BorderLayout.CENTER);

		
		//text field and label
		JPanel input = new JPanel(new GridLayout(1,3,5,5));
		JLabel id = new JLabel("ID:", SwingConstants.RIGHT);
		input.add(id);
		JTextField idText = new JTextField();
		input.add(idText);
		
		JButton okay = new JButton("Okay");
		okay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					find = MainFrame.userData.getAccount(Integer.parseInt(idText.getText()));
					if(find == null)
						JOptionPane.showMessageDialog(SelectUserDialog.this, "Please enter a valid ID #.");	
					else
						SelectUserDialog.this.setVisible(false);
				} catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(SelectUserDialog.this, "Please enter a valid ID #.");
				}
			}
		});
		input.add(okay, BorderLayout.SOUTH);
		
		panel.add(input, BorderLayout.SOUTH);
		this.add(panel);
		
		this.setVisible(true);

	}
	
	//get the account that the okay button was able to grab from database
	public Account getAccount(){
		return find;
	}
}