package main.se450.exceptions;

/**
 * 
 * This class would handle the exception when bad shape type found in the json file.
 *
 */
public class BadShapeException extends Exception
{

	private static final long serialVersionUID = 1L;
	
	private String type = "Unknown";
	
	public BadShapeException()
	{
	}
	
	public BadShapeException(final String sType)
	{
		type = sType;
	}
	
	public String getMessage()
	{
		return "Bad Shape : "  + type;
	}
}
    