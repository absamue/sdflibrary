package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

/**
 * The ObserverCollection class provieds a storage of observers.
 * 
 * @author Andrew
 *
 */
public class ObserverCollection {
	/**
	 * ArrayList to contain given observers
	 */
	private ArrayList<Observer> myObservers = new ArrayList<>();

	/**
	 * Add given observer to list.
	 * 
	 * @param obs
	 *            Observer to add
	 */
	public void registerObserver(Observer obs) {
		myObservers.add(obs);
	}

	/**
	 * Remove given observer from list.
	 * 
	 * @param obs
	 *            Observer to remove.
	 */
	public void unregisterObserver(Observer obs) {
		myObservers.remove(obs);
	}

	/**
	 * Clear all observers from myObservers.
	 */
	public void unregisterAll() {
		myObservers.clear();
	}

	/**
	 * Notifies each observer in the list to perform it's action.
	 */
	public void notifyObservers() {
		for (Observer obs : myObservers) {
			obs.doNotify();
		}
	}
}
