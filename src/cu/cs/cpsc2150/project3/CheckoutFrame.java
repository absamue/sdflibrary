package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	
	private ArrayList<Book> cancelBooks = new ArrayList<Book>();
	
	public CheckoutFrame(){
		super("Checkout");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		myBook = null;
		myCart = new Cart();
		myUser = MainFrame.activeUser;
		selUser = new SelectUserDialog(this);
		selectDialog = new SelectBookDialog(this);
		
	}
	
	public void initialize(){
		myUser = MainFrame.activeUser;
		//check if account is logged in, and ask them to give a member id
		if(myUser.staff){
			selUser.initialize();
			myUser = selUser.getAccount();
			if(myUser == null){
				JOptionPane.showMessageDialog(this, "Checkout can only be applied to member accounts.");
				return;
			}
		}
			
		//panel to show description
		JPanel infoPanel = new JPanel(new GridLayout(2,2,5,30));
		infoPanel.setBorder(new EmptyBorder(15,5,0,5));
		
		JLabel userText = new JLabel("User to checkout: ", SwingConstants.RIGHT);
		infoPanel.add(userText);
		
		JLabel username = new JLabel(myUser.myUsername, SwingConstants.LEFT);
		infoPanel.add(username);
		
		JLabel checked = new JLabel("Currently held books:", SwingConstants.CENTER);
		infoPanel.add(checked);
		
		JLabel actions = new JLabel("Return or checkout books:", SwingConstants.CENTER);
		infoPanel.add(actions);
		
		this.add(infoPanel, BorderLayout.NORTH);
		
		
		//main panel to contain tables
		JPanel panel = new JPanel(new GridLayout(1,2,5,5));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		
		//make a table contained of myUser's checked out books
		user = new JTable(new CheckedTableModel(myUser));
		user.setToolTipText("Double click a row to return selected book.");
		user.addMouseListener(new MouseListener(){

			//add selected book into the action table to be returned
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int row = user.rowAtPoint(e.getPoint());
					if(row != -1){
						//get the book
						Book sel = myUser.checkedOut.remove(row);
						cancelBooks.add(sel);		//list of books to add back to user if cancelled
						myCart.cartBooks.add(sel);
						
						//observer for checkout action
						ReturnObserver reg = new ReturnObserver(sel, myUser);
						myCart.obs.registerObserver(reg);
						
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
		
		//table to hold action items
		select = new JTable(new ActionTableModel());
		JScrollPane right = new JScrollPane(select);
		panel.add(right);
		this.add(panel);
		
		
		//panel to contain buttons
		JPanel buttonPanel = new JPanel(new GridLayout(1,3,5,5));
		buttonPanel.setBorder(new EmptyBorder(0,5,5,5));
		
		
		//checkout button, simply notifys the cart
		JButton checkOut = new JButton("Check Out");
		checkOut.setToolTipText("Apply all checkout actions.");
		checkOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//notify cart of checkout, update data
				myCart.checkout();
				CheckoutFrame.this.updateTables();
				cancelBooks.clear();
				//update current user in the database
				MainFrame.userData.update(myUser, myUser);
			}
		});
		buttonPanel.add(checkOut);
		
		
		//open a frame containing the catalog, that allows selecting a book to check out
		JButton addBook = new JButton("Add Book");
		addBook.setToolTipText("Select a book to be checked out by current user.");
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
		
		//places all uncomplete cart books back where they belong, and hides frame
		JButton cancel = new JButton("Cancel");
		cancel.setToolTipText("Cancel current transaction and remove all actions.");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//add all books from action table back to user
				for(Book bk : cancelBooks){
					myUser.addBook(bk);
				}
				cancelBooks.clear();
				myCart.cartBooks.clear();
			
				//cancel all the observers so none of them fire at later transaction
				myCart.obs.unregisterAll();
				//update tables so that items are in right place
				CheckoutFrame.this.updateTables();
				
				
				CheckoutFrame.this.setVisible(false);
				CheckoutFrame.this.getContentPane().removeAll();
			}
		});
		buttonPanel.add(cancel);
		
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	//update both of the tables, data has changed 
	public void updateTables(){
		((CheckedTableModel) user.getModel()).fireTableDataChanged();
		((ActionTableModel) select.getModel()).fireTableDataChanged();
	}
}
