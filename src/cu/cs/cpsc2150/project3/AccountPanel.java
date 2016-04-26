package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class AccountPanel extends JPanel {
	
	static AccountTableModel accModel;
	AccountDialog accDialog;
	Frame myParent;
	
	public AccountPanel(Frame parent){
		myParent = parent;
		this.initialize();
	}
	
	public void initialize(){
		this.setLayout(new BorderLayout());

		//set up table of account information
		accModel = new AccountTableModel();
		JTable table = new JTable(accModel);
		table.setToolTipText("Double click a row to edit or remove account.");
		table.getTableHeader().setReorderingAllowed(false);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					//get selected book
					int row = table.rowAtPoint(arg0.getPoint());
					if(row != -1){
						Account sel = MainFrame.userData.getAccount(row);
						accDialog = new AccountDialog(myParent, sel);
					}
				}				
			}

			//dont need these
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}			
		});
		
		//make the table scrollable
		JScrollPane sPane = new JScrollPane(table);
		this.add(sPane);
		
	}

	
	//data somehwere in the table changed, so refresh
	public static void update(){
		accModel.fireTableDataChanged();
	
	}
}
