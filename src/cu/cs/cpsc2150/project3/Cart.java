package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

public class Cart {
	public ObserverCollection obs;
	public ArrayList<Book> cartBooks = new ArrayList<Book>();
	
	public Cart(){
		obs = new ObserverCollection();
	}
	
	public void checkout(){
		obs.notifyObservers();
		cartBooks.clear();
		obs.unregisterAll();
	}

}
