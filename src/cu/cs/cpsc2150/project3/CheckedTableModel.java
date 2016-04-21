package cu.cs.cpsc2150.project3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CheckedTableModel extends AbstractTableModel {

	private Account myAccount;
	private final String[] names = {"Title", "Author", "Genre"};
	
	public CheckedTableModel(Account acc){
		myAccount = acc;
	}
	
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return myAccount.checkedOut.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book find = myAccount.checkedOut.get(rowIndex);
		Object ret = null;
		switch(columnIndex){
		case 0:
			ret = find.myTitle;
			break;
		case 1:
			ret = find.myAuthors;
			break;
		case 2:
			ret = find.myGenre;
			break;
		}
		
		return ret;
	}
	
	@Override
	public String getColumnName(int column){
		return names[column];
	}

}
