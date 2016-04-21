package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CheckoutFrame extends JFrame {
	private Book myBook;
	public static Cart myCart;
	private Account myUser;
	private SelectUserDialog selUser;
	public static JTable user;
	public static JTable select;
	private SelectBookDialog selectDialog;
	
	public CheckoutFrame(Book sel){
		super("Checkout");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		myBook = sel;
		myCart = new Cart();
		myUser = MainFrame.activeUser;
		selUser = new SelectUserDialog(this);
		selectDialog = new SelectBookDialog(this);
		
		this.initialize();
	}
	
	public void initialize(){
		
		//check if account is logged in, and ask them to give a member id
		if(myUser.staff){
			selUser.initialize();
			myUser = selUser.getAccount();
			if(myUser == null){
				JOptionPane.showMessageDialog(this, "Checkout can only be applied to member accounts.");
				return;
			}
		}
		
		//add an observer of selected book
/*		else{
			myCart.obs.registerObserver(new CheckoutObserver(myBook, myUser));
			myCart.cartBooks.add(myBook);
		}
*/		
		//panel to show description
		JPanel infoPanel = new JPanel(new GridLayout(2,2,5,30));
		infoPanel.setBorder(new EmptyBorder(15,5,0,5));
		
		JLabel userText = new JLabel("User to checkout: ", SwingConstants.RIGHT);
		infoPanel.add(userText);
		
		JLabel username = new JLabel(myUser.myUsername, SwingConstants.LEFT);
		infoPanel.add(username);
		
		JLabel checked = new JLabel("Currently held books (Double click to return):", SwingConstants.CENTER);
		infoPanel.add(checked);
		
		JLabel actions = new JLabel("Return or checkout books:", SwingConstants.CENTER);
		infoPanel.add(actions);
		
		this.add(infoPanel, BorderLayout.NORTH);
		
		
		//main panel to contain tables
		JPanel panel = new JPanel(new GridLayout(1,2,5,5));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		
		user = new JTable(new CheckedTableModel(myUser));
		user.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int row = user.rowAtPoint(e.getPoint());
					if(row != -1){
						Book sel = myUser.checkedOut.get(row);
						myUser.checkedOut.remove(sel);
						myCart.cartBooks.add(sel);
						myCart.obs.registerObserver(new ReturnObserver(sel, myUser));
						CheckoutFrame.this.updateTables();
					}
				}
			}
			//dont need these, again
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});
		JScrollPane left = new JScrollPane(user);
		panel.add(left);			
		
		select = new JTable(new ActionTableModel());
		JScrollPane right = new JScrollPane(select);
		panel.add(right);
		this.add(panel);
		
		
		//panel to contain buttons
		JPanel buttonPanel = new JPanel(new GridLayout(1,3,5,5));
		buttonPanel.setBorder(new EmptyBorder(0,5,5,5));
		
		JButton checkOut = new JButton("Check Out");
		checkOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//notify cart of checkout, update data
				myCart.checkout();
				CheckoutFrame.this.updateTables();
			}
		});
		buttonPanel.add(checkOut);
		
		JButton addBook = new JButton("Add Book");
		addBook.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDialog.setVisible(true);
				myBook = selectDialog.getBook();
				if(myBook != null){
					myCart.obs.registerObserver(new CheckoutObserver(myBook, myUser));
					myCart.cartBooks.add(myBook);
					CheckoutFrame.this.updateTables();
				}
			}
		});
		buttonPanel.add(addBook);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckoutFrame.this.setVisible(false);
			}
		});
		buttonPanel.add(cancel);
		
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		this.setVisible(true);
	}
	
	public void updateTables(){
		((CheckedTableModel) user.getModel()).fireTableDataChanged();
		((ActionTableModel) select.getModel()).fireTableDataChanged();
	}
}
