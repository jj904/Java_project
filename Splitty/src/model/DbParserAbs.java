package model;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class DbParserAbs
{
	/**
	 * This method will parse a JSON file located in a
	 * specified folder defined and returns an Object to 
	 * read the JSON file.
	 * @param fileLocation: fileLocation of the JSON file
	 * @return parsedData: parsed object pointing to the JSON file.
	 */
	static Object getParsedData(String fileLocation)
	{
		JSONParser parser = new JSONParser();
		Object parsedData = null;
		
		try
		{
			parsedData = (Object)parser.parse(new FileReader(fileLocation));
		}
		catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
		return parsedData;
	}
}