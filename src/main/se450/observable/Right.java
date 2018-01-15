package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IObservable;

/**
 * The Right class defines how the observable right event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Right extends AbstractObserver implements IObservable
{
	private static Right right = new Right();
	
	private ArrayList<IRightObservable> rightObservables = new ArrayList<IRightObservable>();
	
	/**
	 * Make Right events to be observable.
	 */
	private Right() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iRightObservable the IRightObservable object that is going to be observable.
	 * @see #addObserver(IRightObservable)
	 */
	public static void startObserving(final IRightObservable iRightObservable){
		
		right.addObserver(iRightObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iRightObservable the IRightObservable object that is going to stop being observable.
	 * @see #removeObserver(IRightObservable)
	 */
	public static void stopObserving(final IRightObservable iRightObservable){
		
		right.removeObserver(iRightObservable);
	}

	/**
	 * The observable right event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void right() {
		
		right.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iRightObservable the IRightObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IRightObservable iRightObservable) {
		
		if (iRightObservable != null) {
			
			if (!rightObservables.contains(iRightObservable)) {
				
				rightObservables.add(iRightObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iRightObservable the IRightObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IRightObservable iRightObservable) {
		
		rightObservables.remove(iRightObservable);
		
	}

	/**
	 * Override to make the update method concrete, when right observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (rightObservables != null) {
			
			Iterator<IRightObservable> iiRightObservables = rightObservables.iterator();
			
			while (iiRightObservables.hasNext()) {
				
				IRightObservable iRightObservable = iiRightObservables.next();
				
				if(iiRightObservables != null) {
					
					iRightObservable.right();
					
				}
			}
		}
	}

}
