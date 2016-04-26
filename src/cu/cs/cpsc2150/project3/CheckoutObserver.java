package cu.cs.cpsc2150.project3;

/**
 * The CheckoutObserver class provides Observer functionality inorder to mark a
 * book as checked out and place it with the account that checked it out.
 * 
 * @author Andrew
 *
 */
public class CheckoutObserver implements Observer {

	/**
	 * Book to check out.
	 */
	private Book myBook;
	/**
	 * Account to apply checkout to.
	 */
	private Account myAccount;

	/**
	 * Creates a CheckoutObserver instance with the given book and account.
	 * 
	 * @param check
	 *            Book to checkout.
	 * @param acc
	 *            Account to apply checkout to.
	 */
	public CheckoutObserver(Book check, Account acc) {
		myBook = check;
		myAccount = acc;
	}

	/**
	 * doNotify will perform the checkout action by updating the status of the
	 * book in the dialog, adding the book to the account's checkedOut list, and
	 * updating the catalog table.
	 */
	@Override
	public void doNotify() {
		// update book to be marked as checked out
		Book nw = myBook;
		nw.checkedOut = true;
		CatalogTableModel.catalog.updateBook(myBook, nw);

		// add book to user's checked out list
		myAccount.checkedOut.add(myBook);

		// update catalog
		CatalogPanel.update();
		CatalogTableModel.catalog.save();
	}

}
