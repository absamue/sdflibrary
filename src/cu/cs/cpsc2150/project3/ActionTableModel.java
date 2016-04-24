package cu.cs.cpsc2150.project3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ActionTableModel extends AbstractTableModel {

	private final String[] names = {"Action", "Book"};
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		//number of books in the cart
		return CheckoutFrame.myCart.cartBooks.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book find = CheckoutFrame.myCart.cartBooks.get(rowIndex);
		Object ret = null;
		switch(columnIndex){
		case 0:
			if(find.checkedOut)
				ret = "Return";
			else
				ret = "Check out";
			break;
		case 1:
			ret = find.myTitle;
			break;
		}
		
		return ret;
	}

	@Override
	public String getColumnName(int columnIndex){
		return names[columnIndex];
	}
}
