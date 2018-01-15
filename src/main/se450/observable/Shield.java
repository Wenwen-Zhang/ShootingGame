package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IObservable;

/**
 * The Shield class defines how the observable shield event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Shield extends AbstractObserver implements IObservable{
	
	private static Shield shield = new Shield();
	
	private ArrayList<IShieldObservable> shieldObservables = new ArrayList<IShieldObservable>();
	
	/**
	 * Make Shield events to be observable.
	 */
	private Shield() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iShieldObservable the IShieldObservable object that is going to be observable.
	 * @see #addObserver(IShieldObservable)
	 */
	public static void startObserving(final IShieldObservable iShieldObservable){
		
		shield.addObserver(iShieldObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iShieldObservable the IShieldObservable object that is going to stop being observable.
	 * @see #removeObserver(IShieldObservable)
	 */
	public static void stopObserving(final IShieldObservable iShieldObservable){
		
		shield.removeObserver(iShieldObservable);
	}

	/**
	 * The observable shield event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void shield() {
		
		shield.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iShieldObservable the IShieldObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IShieldObservable iShieldObservable) {
		
		if (iShieldObservable != null) {
			
			if (!shieldObservables.contains(iShieldObservable)) {
				
				shieldObservables.add(iShieldObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iShieldObservable the IShieldObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IShieldObservable iShieldObservable) {
		
		shieldObservables.remove(iShieldObservable);
		
	}

	/**
	 * Override to make the update method concrete, when shield observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (shieldObservables != null) {
			
			Iterator<IShieldObservable> iiShieldObservables = shieldObservables.iterator();
			
			while (iiShieldObservables.hasNext()) {
				
				IShieldObservable iShieldObservable = iiShieldObservables.next();
				
				if(iiShieldObservables != null) {
					
					iShieldObservable.shield();
					
				}
			}
		}
	}

}
