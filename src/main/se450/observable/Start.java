package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartObservable;

/**
 * The Start class defines how the observable start event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Start extends AbstractObserver implements IObservable {
	
	private static Start start = new Start();
	
	private ArrayList<IStartObservable> startObservables = new ArrayList<IStartObservable>();
	
	/**
	 * Make Start events to be observable.
	 */
	private Start() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iStartObservable the IStartObservable object that is going to be observable.
	 * @see #addObserver(IStartObservable)
	 */
	public static void startObserving(final IStartObservable iStartObservable){
		
		start.addObserver(iStartObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iStartObservable the IStartObservable object that is going to stop being observable.
	 * @see #removeObserver(IStartObservable)
	 */
	public static void stopObserving(final IStartObservable iStartObservable){
		
		start.removeObserver(iStartObservable);
	}

	/**
	 * The observable start event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void start() {
		
		start.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iStartObservable the IStartObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IStartObservable iStartObservable) {
		
		if (iStartObservable != null) {
			
			if (!startObservables.contains(iStartObservable)) {
				
				startObservables.add(iStartObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iStartObservable the IStartObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IStartObservable iStartObservable) {
		
		startObservables.remove(iStartObservable);
		
	}

	/**
	 * Override to make the update method concrete, when start observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IStartObservable> iiStartObservables = startObservables.iterator();
			
			while (iiStartObservables.hasNext()) {
				
				IStartObservable iStartObservable = iiStartObservables.next();
				
				if(iiStartObservables != null) {
					
					iStartObservable.start();
					
				}
			}
		}
	}

}
