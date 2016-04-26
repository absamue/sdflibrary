package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class CatalogPanel extends JPanel {
	
	BookDialog bookDialog;
	CheckoutFrame check;
	static JTable table;
	
	public CatalogPanel(Frame parent){
		
		
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridLayout(2,1,5,5));
		panel.setBorder(new EmptyBorder(5,5,5,5));
		JPanel searchPanel = new JPanel(new FlowLayout());
		
		//make a table from the catalog info, and add to a scrollpanel
		table = new JTable(new CatalogTableModel());
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setToolTipText("Double click a row to see book information.");

		//listen for doubleclick to open book window
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){					
					//get selected book
					int row = table.rowAtPoint(arg0.getPoint());
					if(row != -1){
						Book sel = CatalogTableModel.getCatalog().getFromTitle((String) table.getValueAt(row, 0));
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
		
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		//Search panel
		JLabel searchLabel = new JLabel("Search:");
		searchPanel.add(searchLabel);
		JTextField searchText = new JTextField();
		searchText.setPreferredSize(new Dimension(431,27));
		searchPanel.add(searchText);
		
		JButton searchButton = new JButton("Search");
		searchButton.setToolTipText("Search the catalog.");
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowSorter.setRowFilter(null);
				String text = searchText.getText();
				if(text.trim().length() == 0){
					rowSorter.setRowFilter(null);
				}
				else{
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
		searchPanel.add(searchButton);
		
		JButton clear = new JButton("Clear");
		clear.setToolTipText("Clear search.");
		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchText.setText("");
				rowSorter.setRowFilter(null);
			}
		});
		searchPanel.add(clear);
		panel.add(searchPanel);
		
		
		//setup checkout window
		check = new CheckoutFrame();
		JButton checkOut = new JButton("Check out");
		checkOut.setToolTipText("Open the checkout window.");
		//show the checkout frame on button click
		checkOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				check.initialize();
		
			}
		});
		panel.add(checkOut);
		this.add(panel, BorderLayout.SOUTH);
		
	}
	
	//data somehwere in the table changed, so refresh
	public static void update(){
		((CatalogTableModel) table.getModel()).fireTableDataChanged();
		
	}
	
	
}
