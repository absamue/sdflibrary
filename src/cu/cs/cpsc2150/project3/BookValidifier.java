package cu.cs.cpsc2150.project3;

/**
 * The BookValidifer class provides verification for the information used in
 * creating or updating a book class.
 * 
 * @author Andrew
 *
 */
public class BookValidifier implements Validifier {

	/**
	 * New book created from input fields to be validated.
	 */
	private Book nwBook;
	/**
	 * Book to compare against in case of editing an exisiting book.
	 */
	private Book origBook;
	/**
	 * Error message of failed test, initialized on a test failure.
	 */
	public String error;

	public BookValidifier(Book nw, Book orig) {
		nwBook = nw;
		origBook = orig;
		if (orig == null) {
			orig = new Book("Gj-'feBJRFA*4\2{", "", "", "");
		}
	}

	/**
	 * validate() will perform a series of tests to determine if the given
	 * information satisfies the constraints of the variable.
	 * 
	 * @return returns false if any test fails. returns true if all tests are
	 *         successful.
	 */
	@Override
	public boolean validate() {
		// check title field
		if (nwBook.myTitle.equals("")) {
			error = "Title field cannot be left blank.";
			return false;
		}
		// make sure title doesnt already exist
		if (CatalogTableModel.catalog.checkTitle(nwBook)) {
			if (!nwBook.myTitle.equals(origBook.myTitle)) {
				error = "Book with this title already exisits.";
				return false;
			}
		}

		// author cannot be blank
		if (nwBook.myTitle.equals("")) {
			error = "Author field cannot be left blank.";
			return false;
		}

		// genre cannot be blank
		if (nwBook.myGenre.equals("")) {
			error = "Genre field cannot be left blank.";
			return false;
		}

		return true;
	}

}
