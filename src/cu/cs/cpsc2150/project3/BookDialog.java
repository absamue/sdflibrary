package cu.cs.cpsc2150.project3;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// title info
		JTextField title = new JTextField();
		title.setText(myBook.myTitle);
		title.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			title.setEditable(false);
		panel.add(title);

		// author info
		JTextField author = new JTextField();
		author.setText(myBook.myAuthors);
		author.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			author.setEditable(false);
		panel.add(author);

		// genre info
		JTextField genre = new JTextField();
		genre.setText(myBook.myGenre);
		genre.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			genre.setEditable(false);
		panel.add(genre);

		// tag info
		JTextField tags = new JTextField();
		tags.setText(myBook.myTags);
		tags.setHorizontalAlignment(JTextField.CENTER);
		if (!MainFrame.getActive().staff)
			tags.setEditable(false);

		panel.add(tags);

		// Show add and remove book buttons if staff is logged in
		if (MainFrame.getActive().staff) {
			JPanel staff = new JPanel(new GridLayout(1, 2, 5, 5));
			staff.setBorder(new EmptyBorder(0, 5, 5, 5));
			

			// delete currently selected book from catalog
			JButton remBook = new JButton("Remove Book");
			remBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CatalogTableModel.getCatalog().removeBook(myBook);
					BookDialog.this.setVisible(false);
					CatalogPanel.update();
				}
			});

			JButton update = new JButton("Update");
			update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Book upd = new Book(title.getText(), author.getText(), genre.getText(), tags.getText());
					CatalogTableModel.getCatalog().updateBook(myBook, upd);
					CatalogPanel.update();
					BookDialog.this.setVisible(false);
				}
			});

		//	staff.add(addBook);
			staff.add(remBook);
			staff.add(update);

			this.add(staff, BorderLayout.SOUTH);
		}

		this.add(panel);

		this.setVisible(true);

	}
}
