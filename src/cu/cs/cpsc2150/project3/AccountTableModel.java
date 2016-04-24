package cu.cs.cpsc2150.project3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class AccountTableModel extends AbstractTableModel {

	private final String[] names = {"ID", "Username", "Name", "Type"};
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		//row count is number of accounts in database
		return MainFrame.userData.getSize();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account acc = MainFrame.userData.getAccount(rowIndex);
		Object ret = null;
		switch(columnIndex){
		case 0:
			ret = acc.myId;
			break;
		case 1:
			ret = acc.myUsername;
			break;
		case 2:
			ret = acc.myName;
			break;
		case 3:
			if(acc.staff)
				ret = "Staff";
			else
				ret = "Member";
			break;
		}
		
		return ret;
	}
	
	@Override
	public String getColumnName(int column){
		return names[column];
		
	}

}
