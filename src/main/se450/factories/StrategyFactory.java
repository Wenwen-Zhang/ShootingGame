package main.se450.factories;

import main.se450.exceptions.BadStrategyException;
import main.se450.interfaces.IStrategy;
import main.se450.strategies.PassThruStrategy;
import main.se450.strategies.ReboundStrategy;


/**
 * Make border strategy for shapes based from the data read from the json file, is called in the JSONFileShapeListFactory.
 * @author wenwenzhang
 *
 */
public class StrategyFactory
{
	private StrategyFactory()
	{
	}
	
	/**
	 * Make strategy for the shape based on the parameter.
	 * @param sStrategy the strategy string read from the json file.
	 * @return a static IStrategy that indicates how the shape would behave when encounter the borders of window frame.
	 * @throws BadStrategyException when bad strategy found.
	 */
	public static IStrategy makeStrategy(final String sStrategy) throws BadStrategyException
	{
		IStrategy iStrategy = null;

    	if (sStrategy.equals("PassThru"))
    	{
    		iStrategy = new PassThruStrategy();
    	}
        else if (sStrategy.equals("Rebound"))
    	{
        	iStrategy = new ReboundStrategy();
    	}
    	else
    	{
    		throw new BadStrategyException(sStrategy);
    	}
		
		return iStrategy;
	}
}
      