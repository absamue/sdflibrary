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

		JLabel title = new JLabel("Title:", SwingConstants.CENTER);
		panel.add(title);
		JTextField titleText = new JTextField();
		panel.add(titleText);

		JLabel author = new JLabel("Author:", SwingConstants.CENTER);
		panel.add(author);
		JTextField authorText = new JTextField();
		panel.add(authorText);

		JLabel genre = new JLabel("Genre:", SwingConstants.CENTER);
		panel.add(genre);
		JTextField genreText = new JTextField();
		panel.add(genreText);

		JLabel tags = new JLabel("Tags:", SwingConstants.CENTER);
		panel.add(tags);
		JTextField tagsText = new JTextField();
		panel.add(tagsText);

		JButton save = new JButton("Save");
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
