package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the forwardThrust event, it must define the concrete behavior when forwardThrust event happens.
 * @author wenwenzhang
 *
 */
public interface IForwardThrustObservable extends IObservable {
	
	void forwardThrust();

}
