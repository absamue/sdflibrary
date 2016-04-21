package cu.cs.cpsc2150.project3;

public class ReturnObserver implements Observer {

	Book myBook;
	Account myAccount;
	
	public ReturnObserver(Book ret, Account acc){
		myBook = ret;
		myAccount = acc;
	}
	
	
	@Override
	public void doNotify() {
		//update book to be marked as available
		Book nw = myBook;
		nw.checkedOut = false;
		CatalogTableModel.catalog.updateBook(myBook, nw);
		
		MainFrame.userData.save();
		
		//update catalog
		CatalogPanel.update();
	}

}
