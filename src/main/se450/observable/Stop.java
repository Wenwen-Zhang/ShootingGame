package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IObservable;

/**
 * The Stop class defines how the observable stop event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Stop extends AbstractObserver implements IObservable
{
	private static Stop stop = new Stop();
	
	private ArrayList<IStopObservable> stopObservables = new ArrayList<IStopObservable>();
	
	/**
	 * Make Stop events to be observable.
	 */
	private Stop() {
		
		startObserver(this);
	}
	
	/**
	 * Start the observation of the parameter.
	 * @param iStopObservable the IStopObservable object that is going to be observable.
	 * @see #addObserver(IStopObservable)
	 */
	public static void startObserving(final IStopObservable iStopObservable){
		
		stop.addObserver(iStopObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iStopObservable the IStopObservable object that is going to stop being observable.
	 * @see #removeObserver(IStopObservable)
	 */
	public static void stopObserving(final IStopObservable iStopObservable){
		
		stop.removeObserver(iStopObservable);
	}

	/**
	 * The observable stop event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void stop() {
		
		stop.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iStopObservable the IStopObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IStopObservable iStopObservable) {
		
		if (iStopObservable != null) {
			
			if (!stopObservables.contains(iStopObservable)) {
				
				stopObservables.add(iStopObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iStopObservable the IStopObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IStopObservable iStopObservable) {
		
		stopObservables.remove(iStopObservable);
		
	}

	/**
	 * Override to make the update method concrete, when stop observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (stopObservables != null) {
			
			Iterator<IStopObservable> iiStopObservables = stopObservables.iterator();
			
			while (iiStopObservables.hasNext()) {
				
				IStopObservable iStopObservable = iiStopObservables.next();
				
				if(iiStopObservables != null) {
					
					iStopObservable.stop();
					
				}
			}
		}
	}

}
