package cu.cs.cpsc2150.project3;

/**
 * The ReturnObserver class provides an observer to return a checked out book to
 * the catalog.
 * 
 * @author Andrew
 *
 */
public class ReturnObserver implements Observer {

	/**
	 * Book to return.
	 */
	Book myBook;
	/**
	 * Account to pull book from.
	 */
	Account myAccount;

	public ReturnObserver(Book ret, Account acc) {
		myBook = ret;
		myAccount = acc;
	}

	/**
	 * doNotify() will perform the return action by changing the status of the
	 * book and then updating the database and tables.
	 */
	@Override
	public void doNotify() {
		// update book to be marked as available
		Book nw = new Book(myBook);
		nw.checkedOut = false;
		CatalogTableModel.catalog.updateBook(myBook, nw);

		// update catalog
		CatalogPanel.update();
		CatalogTableModel.catalog.save();
	}

}
