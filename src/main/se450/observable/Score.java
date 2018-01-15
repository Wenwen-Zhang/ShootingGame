package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IScoreObservable;
import main.se450.interfaces.IObservable;

/**
 * The Score class defines how the observable score event could be observed by registered observers. 
 * @author wenwenzhang
 */
public class Score extends AbstractObserver implements IObservable
{
	private static Score score = new Score();
	private static int totalScore;
	
	private ArrayList<IScoreObservable> scoreObservables = new ArrayList<IScoreObservable>();

	/**
	 * Make Score events to be observable.
	 */
	private Score() {
		
		startObserver(this);
	}
	
	/**
	 * Change the score by the parameter, no negative scores, so if the changed score is less than zero, then it would be zero.
	 * @param nScore the points that would be added to the total score, could be negative or position.
	 */
	public static void changeScoreBy(int nScore)
	{
		totalScore += nScore;
		if (totalScore < 0)
			totalScore = 0;
	}
	
	/**
	 * Get the current total score points of the game
	 * @return the current totalScore
	 */
	public static int getTotalScores()
	{
		return totalScore;
	}
	
	/**
	 * Clear the score to zero.
	 */
	public static void clearScore()
	{
		totalScore = 0;
	}

	/**
	 * Start the observation of the parameter.
	 * @param iScoreObservable the IScoreObservable object that is going to be observable.
	 * @see #addObserver(IScoreObservable)
	 */
	public static void startObserving(final IScoreObservable iScoreObservable){
		
		score.addObserver(iScoreObservable);
	}

	/**
	 * Stop the observation of the parameter.
	 * @param iScoreObservable the IScoreObservable object that is going to stop being observable.
	 * @see #removeObserver(IScoreObservable)
	 */
	public static void stopObserving(final IScoreObservable iScoreObservable){
		
		score.removeObserver(iScoreObservable);
	}

	/**
	 * The observable score event has occured, the observer would be notified for this updates.
	 * @see #update()
	 */
	public static void score() {
		
		score.observed();
	}

	/**
	 * Add the parameter to the observable arraylist, make it observable.
	 * @param iScoreObservable the IScoreObservable object that is going to be observable.
	 */
	private synchronized void addObserver(final IScoreObservable iScoreObservable) {
		
		if (iScoreObservable != null) {
			
			if (!scoreObservables.contains(iScoreObservable)) {
				
				scoreObservables.add(iScoreObservable);
			}
		}
	}

	/**
	 * Stop the observation by remove the parameter from the observable arraylist.
	 * @param iScoreObservable the IScoreObservable object that is going to stop being observable.
	 */
	private synchronized void removeObserver(final IScoreObservable iScoreObservable)
	{		
		scoreObservables.remove(iScoreObservable);		
	}

	/**
	 * Override to make the update method concrete, when score observable events happen, the registered observers would be notified.
	 */
	@Override
	public synchronized void update() {
		
		if (scoreObservables != null) {
			
			Iterator<IScoreObservable> iiScoreObservables = scoreObservables.iterator();
			
			while (iiScoreObservables.hasNext()) {
				
				IScoreObservable iScoreObservable = iiScoreObservables.next();
				
				if(iiScoreObservables != null) {
					
					iScoreObservable.score();
					
				}
			}
		}
	}

}
