package main.se450.observable;

import main.se450.interfaces.IObservable;

/**
 * This AbstractObserver class defines the basic behaviors of an observable object.
 *
 */
public class AbstractObserver {
	
	private IObservable iObservable = null;
	
	public AbstractObserver() {
		
	}
	
	/**
	 * Make the IObservable parameter start to be observed. 
	 * @param iiObservable the observable object that needs to be observed.
	 */
	public synchronized void startObserver(final IObservable iiObservable) {
		
		iObservable = iiObservable;
	}
	
	/**
	 * If the parameter is currently being observed, stop the observation.
	 * @param iiObservable the observable object that is under observation.
	 */
	public synchronized void stopObserver(final IObservable iiObservable) {
		
		if (isObserving(iiObservable)) {
			
			iObservable = null;
		}
	}
	
	/**
	 * Check if the parameter is currently under observation.
	 * @param iiObservable the observable object that could be observed.
	 * @return a boolean value: true if the parameter is currently being observed.
	 */
	public synchronized boolean isObserving(final IObservable iiObservable) {
		
		return (iObservable == iiObservable);
	}
	
	/**
	 * The observable object calls update() to make some changes, the registered observers would be notified of this action.
	 */
	protected void observed() {
		
		if (iObservable != null) {
			
			iObservable.update();
		}
	}

}
