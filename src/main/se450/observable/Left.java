package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.ILeftObservable;

/**
 * The Left class defines how the observable left event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Left extends AbstractObserver implements IObservable
{
	private static Left left = new Left();
	
	private ArrayList<ILeftObservable> leftObservables = new ArrayList<ILeftObservable>();
	
	/**
	 * Make Left events to be observable.
	 */
	private Left() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iLeftObservable the ILeftObservable object that is going to be observable.
	 * @see #addObserver(ILeftObservable)
	 */
	public static void startObserving(final ILeftObservable iLeftObservable){
		
		left.addObserver(iLeftObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iLeftObservable the ILeftObservable object that is going to stop being observable.
	 * @see #removeObserver(ILeftObservable)
	 */
	public static void stopObserving(final ILeftObservable iLeftObservable){
		
		left.removeObserver(iLeftObservable);
	}

	/**
	 * The observable left event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void left() {
		
		left.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iLeftObservable the ILeftObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final ILeftObservable iLeftObservable) {
		
		if (iLeftObservable != null) {
			
			if (!leftObservables.contains(iLeftObservable)) {
				
				leftObservables.add(iLeftObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iLeftObservable the ILeftObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final ILeftObservable iLeftObservable) {
		
		leftObservables.remove(iLeftObservable);
		
	}

	/**
	 * Override to make the update method concrete, when left observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (leftObservables != null) {
			
			Iterator<ILeftObservable> iiLeftObservables = leftObservables.iterator();
			
			while (iiLeftObservables.hasNext()) {
				
				ILeftObservable iLeftObservable = iiLeftObservables.next();
				
				if(iiLeftObservables != null) {
					
					iLeftObservable.left();
					
				}
			}
		}
	}

}
