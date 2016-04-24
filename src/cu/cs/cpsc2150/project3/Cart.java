package cu.cs.cpsc2150.project3;

import java.util.ArrayList;
/**
 * The cart class stores all the books that are processed during a checkout transaction.
 * Utilizes an observer collection to perform the approprate action for each of the books in the cart.
 * @author Andrew
 *
 */
public class Cart {
	/**
	 * Collection of observers
	 */
	public ObserverCollection obs;
	/**
	 * container of books in the cart, used to populate action table.
	 */
	public ArrayList<Book> cartBooks = new ArrayList<Book>();
	
	public Cart(){
		obs = new ObserverCollection();
	}
	
	/**
	 * checkout() notifies all registered observers to perform their actions.
	 * Subsequently removes all observers and clears cart book list.
	 */
	public void checkout(){
		obs.notifyObservers();
		cartBooks.clear();
		obs.unregisterAll();
	}

}
