package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IObservable;

/**
 * The Fire class defines how the observable fire event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Fire extends AbstractObserver implements IObservable{

	private static Fire fire = new Fire();
	
	private ArrayList<IFireObservable> fireObservables = new ArrayList<IFireObservable>();
	
	/**
	 * Make Fire events to be observable.
	 */
	private Fire() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iFireObservable the IFireObservable object that is going to be observable.
	 * @see #addObserver(IFireObservable)
	 */
	public static void startObserving(final IFireObservable iFireObservable){
		
		fire.addObserver(iFireObservable);
	}
	
	/**
	 * Stop the observation of the parameter.
	 * @param iFireObservable the IFireObservable object that is going to stop being observable.
	 * @see #removeObserver(IFireObservable)
	 */
	public static void stopObserving(final IFireObservable iFireObservable){
		
		fire.removeObserver(iFireObservable);
	}
	
	/**
	 * The observable fire event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void fire() {
		
		fire.observed();
	}
	
	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iFireObservable the IFireObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IFireObservable iFireObservable) {
		
		if (iFireObservable != null) {
			
			if (!fireObservables.contains(iFireObservable)) {
				
				fireObservables.add(iFireObservable);
			}
		}
	}
	
	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iFireObservable the IFireObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IFireObservable iFireObservable) {
		
		fireObservables.remove(iFireObservable);
	}
	
	
	/**
	 * Override to make the update method concrete, when fire observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (fireObservables != null) {
			
			Iterator<IFireObservable> iiFireObservables = fireObservables.iterator();
			
			while (iiFireObservables.hasNext()) {
				
				IFireObservable iFireObservable = iiFireObservables.next();
				
				if(iiFireObservables != null) {
					
					iFireObservable.fire();
					
				}
			}
		}
	}

}
