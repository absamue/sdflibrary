package cu.cs.cpsc2150.project3;

/**
 * The CheckoutObserver class provides Observer functionality inorder to mark a book
 * as checked out and place it with the account that checked it out.
 * @author Andrew
 *
 */
public class CheckoutObserver implements Observer {

	/**
	 * Book to check out.
	 */
	private Book myBook;
	/**
	 * account to apply checkout to.
	 */
	private Account myAccount;
	
	public CheckoutObserver(Book check, Account acc){
		myBook = check;
		myAccount = acc;
	}
	
	@Override
	public void doNotify() {
		//update book to be marked as checked out
		Book nw = myBook;
		nw.checkedOut = true;
		CatalogTableModel.catalog.updateBook(myBook, nw);
		
		//add book to user's checked out list
		myAccount.checkedOut.add(myBook);
		
		//update catalog
		CatalogPanel.update();
		CatalogTableModel.catalog.save();
	}

}
