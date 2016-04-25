package cu.cs.cpsc2150.project3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The CatalogData class provides storage for a series of books that are contained in the "Library".
 * @author Andrew
 *
 */
public class CatalogData {
	
	/**
	 * Array list to contain the books.
	 */
	public ArrayList<Book> catalogData;
	
	/**
	 * CatalogData() will build the array list by reading from disk the stored list.
	 * Does nothing if file is not found.
	 */
	@SuppressWarnings("unchecked")
	public CatalogData(){
		try {
			//read from catalogData.ser to build catalog
			FileInputStream fis = new FileInputStream("catalogData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			catalogData = (ArrayList<Book>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			//file not found, set up empty list
			catalogData = new ArrayList<Book>();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	/**
	 * save() will write the userData arraylist to disk in the file catalogData.ser.
	 */
	public void save() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("catalogData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(catalogData);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * putBook(Book in) will add the given book to the end of the list.
	 * The new list will then be saved to disk.
	 * @param in Book to add to list.
	 */
	public void putBook(Book in){
		catalogData.add(in);
		this.save();
	}
	
	/**
	 * getBook(int out) will return a book from its given index in the list.
	 * Used to populate the catalog table.
	 * @param out Index in the list to get from
	 * @return Book at specified index.
	 */
	public Book getBook(int out){
		return catalogData.get(out);
	}
	
	/**
	 * getFromTitle(String title) returns the book with the specified title.
	 * @param title Title of book to find.
	 * @return Returns book with matching title if found, otherwise returns null.
	 */
	public Book getFromTitle(String title){
		for(Book bk : catalogData){
			if(bk.myTitle.equals(title))
				return bk;
		}
		return null;
	}
	
	/**
	 * removeBook(Book) rem will remove the specified book from the catalog.
	 * @param rem Book to be removed.
	 */
	public void removeBook(Book rem){
		catalogData.remove(rem);
		this.save();
	}
	
	/**
	 * updateBook(Book old, Book nw) will replace the book at index of old with the 
	 * updated version contained in nw.
	 * @param old Book to be replaced with updated data.
	 * @param nw Book to replace old item.
	 */
	public void updateBook(Book old, Book nw){
		catalogData.set(catalogData.indexOf(old), nw);
		this.save();
		
	}
	
	/**
	 * getSize() will get the size of the catalog. Used to fill catalog table.
	 * @return Size of the catalog.
	 */
	public int getSize(){
		return catalogData.size();
	}
	
	/**
	 * checkTitle(Book in) will check if a book with the same title as in exists in the catalog.
	 * @param in Book to check if title is present
	 * @return Returns false if title does not exist, otherwise returns true.
	 */
	public boolean checkTitle(Book in){
		boolean check = false;
		for(Book bk : catalogData){
			if(bk.myTitle.equals(in.myTitle))
					check = true;
		}
		
		return check;
	}
}
