package main.se450.interfaces;

/**
 * When implement, an object registers to be an observer of the shield event, it must define the concrete behavior when shield event happens.
 * @author wenwenzhang
 */
public interface IShieldObservable extends IObservable
{
	void shield();
}
