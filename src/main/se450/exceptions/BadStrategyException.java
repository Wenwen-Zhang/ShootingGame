package main.se450.exceptions;


/**
 * 
 * This class would handle the exception when bad strategy found in the json file.
 *
 */
public class BadStrategyException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strategy = "Unknown";
	
	public BadStrategyException()
	{
	}
	
	public BadStrategyException(final String sStrategy)
	{
		strategy = sStrategy;
	}
	
	public String getMessage()
	{
		return "Bad Strategy : "  + strategy;
	}
}
    