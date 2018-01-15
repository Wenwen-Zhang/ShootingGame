package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the reverseThrust event, it must define the concrete behavior when reverseThrust event happens.
 * @author wenwenzhang
 *
 */
public interface IReverseThrustObservable extends IObservable 
{
	void reverseThrust();
}
