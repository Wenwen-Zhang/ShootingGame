package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IReverseThrustObservable;
import main.se450.interfaces.IObservable;

/**
 * The ReverseThrust class defines how the observable reverseThrust event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class ReverseThrust extends AbstractObserver implements IObservable 
{
	private static ReverseThrust reverseThrust = new ReverseThrust();
	
	private ArrayList<IReverseThrustObservable> reverseThrustObservables = new ArrayList<IReverseThrustObservable>();
	
	/**
	 * Make ReverseThrust events to be observable.
	 */
	private ReverseThrust() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iReverseThrustObservable the IReverseThrustObservable object that is going to be observable.
	 * @see #addObserver(IReverseThrustObservable)
	 */
	public static void startObserving(final IReverseThrustObservable iReverseThrustObservable){
		
		reverseThrust.addObserver(iReverseThrustObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iReverseThrustObservable the IReverseThrustObservable object that is going to stop being observable.
	 * @see #removeObserver(IReverseThrustObservable)
	 */
	public static void stopObserving(final IReverseThrustObservable iReverseThrustObservable){
		
		reverseThrust.removeObserver(iReverseThrustObservable);
	}

	/**
	 * The observable reverseThrust event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void reverseThrust() {
		
		reverseThrust.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iReverseThrustObservable the IReverseThrustObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IReverseThrustObservable iReverseThrustObservable) {
		
		if (iReverseThrustObservable != null) {
			
			if (!reverseThrustObservables.contains(iReverseThrustObservable)) {
				
				reverseThrustObservables.add(iReverseThrustObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iReverseThrustObservable the IReverseThrustObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IReverseThrustObservable iReverseThrustObservable) {
		
		reverseThrustObservables.remove(iReverseThrustObservable);
	}

	/**
	 * Override to make the update method concrete, when reverseThrust observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (reverseThrustObservables != null) {
			
			Iterator<IReverseThrustObservable> iiReverseThrustObservables = reverseThrustObservables.iterator();
			
			while (iiReverseThrustObservables.hasNext()) {
				
				IReverseThrustObservable iReverseThrustObservable = iiReverseThrustObservables.next();
				
				if(iiReverseThrustObservables != null) {
					
					iReverseThrustObservable.reverseThrust();
					
				}
			}
		}
	}

}
