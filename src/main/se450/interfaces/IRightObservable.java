package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the right event, it must define the concrete behavior when right event happens.
 * @author wenwenzhang
 *
 */
public interface IRightObservable extends IObservable
{
	void right();
}
