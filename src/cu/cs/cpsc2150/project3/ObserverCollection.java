package cu.cs.cpsc2150.project3;

import java.util.ArrayList;

public class ObserverCollection {
	private ArrayList<Observer> myObservers = new ArrayList<>();

	public void registerObserver(Observer obs) {
		myObservers.add(obs);
	}

	public void unregisterObserver(Observer obs) {
		myObservers.remove(obs);
	}

	public void unregisterAll(){
		myObservers.clear();
	}
	
	public void notifyObservers() {
		for (Observer obs : myObservers) {
			obs.doNotify();
		}
	}
}
