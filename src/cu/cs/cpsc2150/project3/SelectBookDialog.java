package cu.cs.cpsc2150.project3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class SelectBookDialog extends JDialog {
	
	private Book sel;
	
	public SelectBookDialog(Frame parent){
		super(parent, "Select Book", true);
		this.setSize(300,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.initialize();
	}
	
	public void initialize(){
		JPanel panel = new JPanel(new BorderLayout());
		
		JTable table = new JTable(CatalogPanel.table.getModel());
		JScrollPane sPane = new JScrollPane(table);
		
		JButton okay = new JButton("Okay");
		okay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					 sel = CatalogTableModel.getCatalog().getBook(row);
					 if(sel.checkedOut){
						 JOptionPane.showMessageDialog(SelectBookDialog.this, "You cannot select a book that is checked out.");
					 }
					 else{
					 SelectBookDialog.this.setVisible(false);
					 }
				}	
				else{
					JOptionPane.showMessageDialog(SelectBookDialog.this, "Please select a row from the list");
				}
				
			}
			
		});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				sel = null;
				SelectBookDialog.this.setVisible(false);
			}
			
		});
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,5,5));
		buttonPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		buttonPanel.add(okay);
		buttonPanel.add(cancel);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		panel.add(sPane);
		
		this.add(panel);
		
	}
	
	public Book getBook(){
		return sel;
	}
}
