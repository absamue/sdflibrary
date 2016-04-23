package cu.cs.cpsc2150.project3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CatalogData {
	
	public ArrayList<Book> catalogData;
	
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
			//catalog data not found
			catalogData = new ArrayList<Book>();
			Book test = new Book("test", "memelord", "dank memes", "dank");
			catalogData.add(test);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
	}

	//save catalogData to catalogData.ser
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
	
	public void putBook(Book in){
		catalogData.add(in);
		this.save();
	}
	
	public Book getBook(int out){
		return catalogData.get(out);
	}
	
	public void removeBook(Book rem){
		catalogData.remove(rem);
		this.save();
	}
	
	public void updateBook(Book old, Book nw){
		catalogData.set(catalogData.indexOf(old), nw);
		this.save();
		
	}
	
	public int getSize(){
		return catalogData.size();
	}
	
	//return true if the given book's title is in the catalog
	public boolean checkTitle(Book in){
		boolean check = false;
		for(Book bk : catalogData){
			if(bk.myTitle.equals(in.myTitle))
					check = true;
		}
		
		return check;
	}
}
