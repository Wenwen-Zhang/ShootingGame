package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the fire event, it must define the concrete behavior in fire() when fire event happens.
 * @author wenwenzhang
 *
 */
public interface IFireObservable extends IObservable {
	
	void fire();

}
