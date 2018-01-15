package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the hyperspace event, it must define the concrete behavior when hyperspace event happens.
 * @author wenwenzhang
 *
 */
public interface IHyperSpaceObservable extends IObservable
{
	void hyperSpace();
}
