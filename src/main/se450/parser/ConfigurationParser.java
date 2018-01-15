package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.ConfigurationManager;
import main.se450.utilities.Extractor;

/**
 * The ConfigurationParser class loads the configuration settings from the configuration json file.
 * @author wenwenzhang
 *
 */

public class ConfigurationParser {
	
	
	private ConfigurationParser() {}
	
	
	/**
	 * Load game configuration settings and playship's properties from the configuration json file, parses these data into the configuration object 
	 * for further usage. 
	 * @param fileName the configuration file name.
	 */
	public static void loadConfiguration(String fileName)
	{
        
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("game");
	        
	        Iterator<?> jsonIterator = jsonArray.iterator();
	        while (jsonIterator.hasNext())
	        {
	        	JSONObject configuration = (JSONObject)jsonIterator.next();
	        	if (configuration.containsKey("framespersecond"))
	        	{
	        		try
	        		{
	        		IStrategy iStrategy = StrategyFactory.makeStrategy(configuration.get("borders").toString());	
	        		ConfigurationManager.getConfigurationManager().setConfiguration(Extractor.extractInteger(configuration.get("framespersecond")),
	        																		Extractor.extractInteger(configuration.get("repeatkeyspeed")),
	        																		Extractor.extractInteger(configuration.get("width")),
	        																		Extractor.extractInteger(configuration.get("height")),
															        				Extractor.extractInteger(configuration.get("shapes")),
															        				Extractor.extractInteger(configuration.get("shipwidth")),
															        				Extractor.extractInteger(configuration.get("shipheight")), 
															        				Extractor.extractFloat(configuration.get("shotspeed")),
															        				Extractor.extractFloat(configuration.get("shotdiameter")),
															        				Extractor.extractInteger(configuration.get("shotlifetime")),
															        				Extractor.extractFloat(configuration.get("forwardthrust")), 
															        				Extractor.extractFloat(configuration.get("reversethrust")), 
															        				Extractor.extractFloat(configuration.get("friction")),
															        				Extractor.extractFloat(configuration.get("leftright")), 
															        				Extractor.extractColor(configuration.get("color")), iStrategy);     	
        			}
        			catch (BadStrategyException e)
        			{
	        			System.out.println(e);
        			}

	        	}
	        }
		}
        catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
        catch(ParseException eParseException)
        {
        }
		
		
	}

}
