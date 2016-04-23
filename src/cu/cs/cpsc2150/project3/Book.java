package cu.cs.cpsc2150.project3;


@SuppressWarnings("serial")
public class Book implements java.io.Serializable{
	String myTitle;
	String myAuthors;
	String myGenre;
	String myTags;
	boolean checkedOut;

	
	public Book(String title, String author, String genre, String tag){
		checkedOut = false;
		myTitle = title;
		myAuthors = author;	
		myGenre = genre;
		myTags = tag;
	}
	
	public Book(Book clone){
		myTitle = clone.myTitle;
		myAuthors = clone.myAuthors;
		myGenre = clone.myGenre;
		myTags = clone.myTags;
		checkedOut = clone.checkedOut;
	}

}
