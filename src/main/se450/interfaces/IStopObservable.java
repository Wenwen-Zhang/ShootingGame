package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the stop event, it must define the concrete behavior when stop event happens.
 * @author wenwenzhang
 */
public interface IStopObservable extends IObservable
{
	void stop();
}
