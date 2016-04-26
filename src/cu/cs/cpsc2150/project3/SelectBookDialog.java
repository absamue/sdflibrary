package cu.cs.cpsc2150.project3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SelectBookDialog extends JDialog {

	private Book sel;

	//create a modal dialog to select a book to check out
	public SelectBookDialog(Frame parent) {
		super(parent, "Select Book", true);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		this.initialize();
	}

	public void initialize() {
		JPanel panel = new JPanel(new BorderLayout());

		// grab the catalog that already exists in MainFrame.
		final JTable table = new JTable(CatalogPanel.table.getModel());
		table.setToolTipText("Select a book to add to cart, then click okay.");
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		//only show available books for selection
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + "Available"));
		JScrollPane sPane = new JScrollPane(table);

		JButton okay = new JButton("Okay");
		okay.setToolTipText("Add currently selected book to cart.");
		okay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					//get book from selected row
					sel = CatalogTableModel.getCatalog().getFromTitle((String) table.getValueAt(row, 0));
					//prevent selecting a checked out book
					if (sel.checkedOut) {
						JOptionPane.showMessageDialog(SelectBookDialog.this,
								"You cannot select a book that is checked out.");
					}
					//prevent selecting a book that is in the cart already
					else if(CheckoutFrame.myCart.cartBooks.contains(sel)){
						JOptionPane.showMessageDialog(SelectBookDialog.this, 
								"Book is already in the checkout cart.");
					}
					//valid checkout book
					else {
						SelectBookDialog.this.setVisible(false);
					}
				} else {
					JOptionPane.showMessageDialog(SelectBookDialog.this, "Please select a row from the list");
				}
			}
		});

		//no selection made, set selected book to null and close window
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sel = null;
				SelectBookDialog.this.setVisible(false);
			}

		});

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		buttonPanel.add(okay);
		buttonPanel.add(cancel);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		panel.add(sPane);

		this.add(panel);

	}

	public Book getBook() {
		return sel;
	}
}
