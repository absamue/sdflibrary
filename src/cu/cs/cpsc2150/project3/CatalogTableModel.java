package cu.cs.cpsc2150.project3;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CatalogTableModel extends AbstractTableModel {

	public static CatalogData catalog;
	private final String[] names = {"Title", "Authors", "Genre", "Availability"};
	
	public CatalogTableModel(){
		catalog = new CatalogData();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return catalog.getSize();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Book find = catalog.getBook(arg0);
		Object ret = null;
		switch(arg1){
		case 0:
			ret = find.myTitle;
			break;
		case 1:
			ret = find.myAuthors;
			break;
		case 2:
			ret = find.myGenre;
			break;
		case 3:
			if(find.checkedOut)
				ret = "Checked out.";
			else
				ret = "Available";
			break;
		}
		
		return ret;
	}

	@Override
	public String getColumnName(int column){
		return names[column];
		
	}
	
	
	
	public static CatalogData getCatalog(){
		return catalog;
	}
}
