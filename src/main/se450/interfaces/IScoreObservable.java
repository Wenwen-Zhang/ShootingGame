package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the score event, it must define the concrete behavior when score event happens.
 * @author wenwenzhang
 *
 */
public interface IScoreObservable extends IObservable
{
	void score();
}
