package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the left event, it must define the concrete behavior when left event happens.
 * @author wenwenzhang
 *
 */
public interface ILeftObservable extends IObservable
{
	void left();
}
