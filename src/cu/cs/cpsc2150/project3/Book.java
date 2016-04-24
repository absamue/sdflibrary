package cu.cs.cpsc2150.project3;

/**
 * The Book class is used to store instances of books and their attributes in the catalog.
 * @author Andrew
 *
 */
@SuppressWarnings("serial")
public class Book implements java.io.Serializable{
	String myTitle;
	String myAuthors;
	String myGenre;
	String myTags;
	boolean checkedOut;

	/**
	 * Creates a book from the given input fields.
	 * @param title Title of book.
	 * @param author Author of the book.
	 * @param genre Genre of the book.
	 * @param tag Tags of the book.
	 */
	public Book(String title, String author, String genre, String tag){
		checkedOut = false;
		myTitle = title;
		myAuthors = author;	
		myGenre = genre;
		myTags = tag;
	}
	
	/**
	 * Create a new book from an instance of an existing book.
	 * @param clone Book to be cloned.
	 */
	public Book(Book clone){
		myTitle = clone.myTitle;
		myAuthors = clone.myAuthors;
		myGenre = clone.myGenre;
		myTags = clone.myTags;
		checkedOut = clone.checkedOut;
	}

}
