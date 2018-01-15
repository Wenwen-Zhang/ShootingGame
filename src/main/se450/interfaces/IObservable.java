package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the update event, it must define the concrete behavior when update event happens.
 * @author wenwenzhang
 *
 */
public interface IObservable 
{
	void update(); 
}
      