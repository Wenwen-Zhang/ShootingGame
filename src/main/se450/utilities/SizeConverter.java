package main.se450.utilities;

/**
 * The SizeConverter for the shapes. <br>
 * "large" = 3; <br>
 * "medium" = 2; <br>
 * "small" = 1; 
 * @author wenwenzhang
 *
 */
public class SizeConverter {
	
	/**
	 * Convert the shape size from String to Integer.
	 * @param size the size in String
	 * @return a correspondent Integer size
	 */
	public static Integer toIntConverter(String size)
	{
		int intSize = 0;
		
		if (size.equals("large"))
			intSize = 3;
		else if (size.equals("medium"))
			intSize = 2;
		else if (size.equals("small"))
			intSize = 1;
		
		return intSize;
	}

	/**
	 * Convert the shape size from Integer to String.
	 * @param size the Integer size
	 * @return a correspondent size in String
	 */
	public static String toStringConverter(int size)
	{
		String stringSize = "";
		
		if (size == 3)
			stringSize = "large";
		else if (size == 2)
			stringSize = "medium";
		else if (size == 1)
			stringSize = "small";
		
		return stringSize;
	}

}
