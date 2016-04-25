package cu.cs.cpsc2150.project3;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BookDialog extends JDialog {

	private Book myBook;

	public BookDialog(Frame parent, Book selection) {
		super(parent, "Book info", true);
		myBook = selection;
		this.initialize();
	}

	public void initialize() {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// title info
		JLabel title = new JLabel("Title:", SwingConstants.CENTER);
		panel.add(title);
		JTextField titleText = new JTextField();
		titleText.setText(myBook.myTitle);
		titleText.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			titleText.setEditable(false);
		panel.add(titleText);

		// author info
		JLabel author = new JLabel("Author:", SwingConstants.CENTER);
		panel.add(author);
		JTextField authorText = new JTextField();
		authorText.setText(myBook.myAuthors);
		authorText.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			authorText.setEditable(false);
		panel.add(authorText);

		// genre info
		JLabel genre = new JLabel("Genre:", SwingConstants.CENTER);
		panel.add(genre);
		JTextField genreText = new JTextField();
		genreText.setText(myBook.myGenre);
		genreText.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			genreText.setEditable(false);
		panel.add(genreText);

		// tag info
		JLabel tags = new JLabel("Tags:", SwingConstants.CENTER);
		panel.add(tags);
		JTextField tagsText = new JTextField();
		tagsText.setText(myBook.myTags);
		tagsText.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			tagsText.setEditable(false);

		panel.add(tagsText);

		// Show add and remove book buttons if staff is logged in
		if (MainFrame.getActive().staff) {

			// delete currently selected book from catalog
			JButton remBook = new JButton("Remove Book");
			remBook.setToolTipText("Remove currently selected book from catalog.");
			remBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CatalogTableModel.getCatalog().removeBook(myBook);
					BookDialog.this.setVisible(false);
					CatalogPanel.update();
				}
			});
			if(myBook.checkedOut){
				remBook.setEnabled(false);
				remBook.setToolTipText("Cannot remove a book that is checked out.");
			}

			JButton update = new JButton("Update");
			update.setToolTipText("Update currently selected book from text fields.");
			update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//make new book from fields and verify information
					Book upd = new Book(titleText.getText(), authorText.getText(), genreText.getText(),
							tagsText.getText());
					BookValidifier check = new BookValidifier(upd, myBook);
				
					//update on validation success
					if (check.validate()) {
						CatalogTableModel.getCatalog().updateBook(myBook, upd);
						CatalogPanel.update();
						BookDialog.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(BookDialog.this, check.error);
					}
				}
			});
			if(myBook.checkedOut){
				update.setEnabled(false);
				update.setToolTipText("Cannot update a book that is checked out.");
			}

			panel.add(remBook);
			panel.add(update);

			this.add(panel);
		}

		this.add(panel);

		this.setVisible(true);

	}
}
