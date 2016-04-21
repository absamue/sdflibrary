package cu.cs.cpsc2150.project3;

public class CheckoutObserver implements Observer {

	private Book myBook;
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
		MainFrame.userData.save();
		
		//update catalog
		CatalogPanel.update();
	}

}
