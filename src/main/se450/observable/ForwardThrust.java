package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IObservable;

/**
 * The ForwardThrust class defines how the observable forwardThrust event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class ForwardThrust extends AbstractObserver implements IObservable 
{
	private static ForwardThrust forwardThrust = new ForwardThrust();
	
	private ArrayList<IForwardThrustObservable> forwardThrustObservables = new ArrayList<IForwardThrustObservable>();
	
	/**
	 * Make ForwardThrust events to be observable.
	 */
	private ForwardThrust() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iForwardThrustObservable the IForwardThrustObservable object that is going to be observable.
	 * @see #addObserver(IForwardThrustObservable)
	 */
	public static void startObserving(final IForwardThrustObservable iForwardThrustObservable){
		
		forwardThrust.addObserver(iForwardThrustObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iForwardThrustObservable the IForwardThrustObservable object that is going to stop being observable.
	 * @see #removeObserver(IForwardThrustObservable)
	 */
	public static void stopObserving(final IForwardThrustObservable iForwardThrustObservable){
		
		forwardThrust.removeObserver(iForwardThrustObservable);
	}

	/**
	 * The observable forwardThrust event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void forwardThrust() {
		
		forwardThrust.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iForwardThrustObservable the IForwardThrustObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IForwardThrustObservable iForwardThrustObservable) {
		
		if (iForwardThrustObservable != null) {
			
			if (!forwardThrustObservables.contains(iForwardThrustObservable)) {
				
				forwardThrustObservables.add(iForwardThrustObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iForwardThrustObservable the IForwardThrustObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IForwardThrustObservable iForwardThrustObservable) {
		
		forwardThrustObservables.remove(iForwardThrustObservable);
	}

	/**
	 * Override to make the update method concrete, when forwardThrust observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (forwardThrustObservables != null) {
			
			Iterator<IForwardThrustObservable> iiForwardThrustObservables = forwardThrustObservables.iterator();
			
			while (iiForwardThrustObservables.hasNext()) {
				
				IForwardThrustObservable iForwardThrustObservable = iiForwardThrustObservables.next();
				
				if(iiForwardThrustObservables != null) {
					
					iForwardThrustObservable.forwardThrust();
					
				}
			}
		}
	}

}
