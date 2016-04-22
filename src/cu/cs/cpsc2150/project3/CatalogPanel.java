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
		
		JButton checkOut = new JButton("Check out");
		checkOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
		//		int book = table.getSelectedRow();
			//	if(book != -1){
		//		Book sel = CatalogTableModel.catalog.getBook(book);
				CheckoutFrame check = new CheckoutFrame(null);
		/*		}
				else{
					JOptionPane.showMessageDialog(CatalogPanel.this, "Please select a book from the catalog table to checkout.");
				}*/
			}
		});
		this.add(checkOut, BorderLayout.SOUTH);
		
	}
	
	//data somehwere in the table changed, so refresh
	public static void update(){
		((CatalogTableModel) table.getModel()).fireTableDataChanged();
		
	}
	
	
}
