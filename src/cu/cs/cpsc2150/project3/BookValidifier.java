package cu.cs.cpsc2150.project3;

public class BookValidifier implements Validifier {

	private Book nwBook;
	private Book origBook;
	public String error;

	public BookValidifier(Book nw, Book orig) {
		nwBook = nw;
		origBook = orig;
		if (orig == null) {
			orig = new Book("Gj-'feBJRFA*4\2{", "", "", "");
		}
	}

	@Override
	public boolean validate() {
		// check title field
		if (nwBook.myTitle.equals("")) {
			error = "Title field cannot be left blank.";
			return false;
		}
		if (CatalogTableModel.catalog.checkTitle(nwBook)) {
			if (!nwBook.myTitle.equals(origBook.myTitle)) {
				error = "Book with this title already exisits.";
				return false;
			}
		}

		if (nwBook.myTitle.equals("")) {
			error = "Author field cannot be left blank.";
			return false;
		}

		if (nwBook.myGenre.equals("")) {
			error = "Genre field cannot be left blank.";
			return false;
		}

		return true;
	}

}
