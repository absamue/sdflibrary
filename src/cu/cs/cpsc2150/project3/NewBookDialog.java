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
public class NewBookDialog extends JDialog {

	public NewBookDialog(Frame parent) {
		super(parent, "Add new book", true);
		this.initialize();
	}

	public void initialize() {
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		//title field
		JLabel title = new JLabel("Title:", SwingConstants.CENTER);
		panel.add(title);
		final JTextField titleText = new JTextField();
		panel.add(titleText);

		//author field
		JLabel author = new JLabel("Author:", SwingConstants.CENTER);
		panel.add(author);
		final JTextField authorText = new JTextField();
		panel.add(authorText);

		//genre field
		JLabel genre = new JLabel("Genre:", SwingConstants.CENTER);
		panel.add(genre);
		final JTextField genreText = new JTextField();
		panel.add(genreText);

		//tags field
		JLabel tags = new JLabel("Tags:", SwingConstants.CENTER);
		panel.add(tags);
		final JTextField tagsText = new JTextField();
		panel.add(tagsText);

		//add book to catalog on successful validation
		JButton save = new JButton("Save");
		save.setToolTipText("Create new book from text fields and add new book to catalog.");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// make a new book with fields, and validate it's information
				Book newBook = new Book(titleText.getText(), authorText.getText(), genreText.getText(),
						tagsText.getText());
				BookValidifier check = new BookValidifier(newBook, null);

				//upon successful validation, add this book to the dialog and update table
				if (check.validate()) {
					CatalogTableModel.getCatalog().putBook(newBook);

					// redraw catalog panel
					CatalogPanel.update();
					NewBookDialog.this.setVisible(false);
					// clear fields
					titleText.setText("");
					authorText.setText("");
					genreText.setText("");
					tagsText.setText("");
				} else {
					JOptionPane.showMessageDialog(NewBookDialog.this, check.error);
				}
			}
		});
		panel.add(save);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewBookDialog.this.setVisible(false);
				// clear fields
				titleText.setText("");
				authorText.setText("");
				genreText.setText("");
				tagsText.setText("");
			}

		});
		panel.add(cancel);
		this.add(panel);

	}
}
