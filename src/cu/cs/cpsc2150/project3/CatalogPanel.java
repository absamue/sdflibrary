package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CatalogPanel extends JPanel {
	
	BookDialog bookDialog;
	JTable table;
	
	public CatalogPanel(Frame parent){
		this.setLayout(new BorderLayout());
		
		//make a table from the catalog info, and add to a scrollpanel
		table = new JTable(new CatalogTableModel());
		//listen for doubleclick to open book window
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					//get selected book
					Book sel = CatalogTableModel.getCatalog().getBook(table.getSelectedRow());
					bookDialog = new BookDialog(parent, sel);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JScrollPane sPane = new JScrollPane(table);
		this.add(sPane);	
		
	}
	
	//data somehwere in the table changed, so refresh
	public void update(){
		((CatalogTableModel) table.getModel()).fireTableChanged(null);

	}
	
	
}
