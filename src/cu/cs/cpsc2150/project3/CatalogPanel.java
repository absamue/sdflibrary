package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CatalogPanel extends JPanel {
	
	BookDialog bookDialog;
	CheckoutFrame check;
	static JTable table;
	
	public CatalogPanel(Frame parent){
		
		
		this.setLayout(new BorderLayout());
		
		//make a table from the catalog info, and add to a scrollpanel
		table = new JTable(new CatalogTableModel());
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		//listen for doubleclick to open book window
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					//get selected book
					int row = table.rowAtPoint(arg0.getPoint());
					if(row != -1){
						Book sel = CatalogTableModel.getCatalog().getBook(row);
						bookDialog = new BookDialog(parent, sel);
						CatalogPanel.this.revalidate();
					}
				}
			}

			//dont need these
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {	
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			
		});
		//make the table scrollable
		JScrollPane sPane = new JScrollPane(table);
		this.add(sPane);	
		
		check = new CheckoutFrame();
		JButton checkOut = new JButton("Check out");
		checkOut.setToolTipText("Open the checkout window.");
		checkOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				check.initialize();
		
			}
		});
		this.add(checkOut, BorderLayout.SOUTH);
		
	}
	
	//data somehwere in the table changed, so refresh
	public static void update(){
		((CatalogTableModel) table.getModel()).fireTableDataChanged();
		
	}
	
	
}
