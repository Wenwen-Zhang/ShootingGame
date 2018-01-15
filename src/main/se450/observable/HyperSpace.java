package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.IObservable;

/**
 * The HyperSpace class defines how the observable hyperSpace event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class HyperSpace extends AbstractObserver implements IObservable
{
	private static HyperSpace hyperSpace = new HyperSpace();
	
	private ArrayList<IHyperSpaceObservable> hyperSpaceObservables = new ArrayList<IHyperSpaceObservable>();
	
	/**
	 * Make HyperSpace events to be observable.
	 */
	private HyperSpace() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iHyperSpaceObservable the IHyperSpaceObservable object that is going to be observable.
	 * @see #addObserver(IHyperSpaceObservable)
	 */
	public static void startObserving(final IHyperSpaceObservable iHyperSpaceObservable){
		
		hyperSpace.addObserver(iHyperSpaceObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iHyperSpaceObservable the IHyperSpaceObservable object that is going to stop being observable.
	 * @see #removeObserver(IHyperSpaceObservable)
	 */
	public static void stopObserving(final IHyperSpaceObservable iHyperSpaceObservable){
		
		hyperSpace.removeObserver(iHyperSpaceObservable);
	}

	/**
	 * The observable hyperSpace event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void hyperSpace() {
		
		hyperSpace.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iHyperSpaceObservable the IHyperSpaceObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		
		if (iHyperSpaceObservable != null) {
			
			if (!hyperSpaceObservables.contains(iHyperSpaceObservable)) {
				
				hyperSpaceObservables.add(iHyperSpaceObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iHyperSpaceObservable the IHyperSpaceObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		
		hyperSpaceObservables.remove(iHyperSpaceObservable);
		
	}

	/**
	 * Override to make the update method concrete, when hyperSpace observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (hyperSpaceObservables != null) {
			
			Iterator<IHyperSpaceObservable> iiHyperSpaceObservables = hyperSpaceObservables.iterator();
			
			while (iiHyperSpaceObservables.hasNext()) {
				
				IHyperSpaceObservable iHyperSpaceObservable = iiHyperSpaceObservables.next();
				
				if(iiHyperSpaceObservables != null) {
					
					iHyperSpaceObservable.hyperSpace();
					
				}
			}
		}
	}


}
