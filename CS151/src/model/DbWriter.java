package model;

import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class writes in JSON files of database.
 */
@SuppressWarnings("unchecked")
public class DbWriter extends DbParserAbs
{
	/**
	 * This method writes userData in JSON files by reading existing userdata,
	 * confirming additional userdata, and overwriting it with both old and new content.
	 * 
	 * @param name: name of user
	 * @param username: unique username provided by user. 
	 * @param password: unique password provided by user.
	 * Uniqueness of usernames and passwords are confirmed by system before writing
	 * into database.
	 */
	public static void writeUserData(String name, String username, String password, String fileLocation)
	{
		JSONArray userData = DbReader.readArray(fileLocation);
		JSONObject newUser = wrapUserInfo(name, username, password);
		userData.add(newUser);
		
		//Overwrite JSON file of fileLocation
		writeArrayToFile(userData, fileLocation);
	}
	
	/**
	 * This method writes data in JSONArrays to a JSON file.
	 * @param dataArray: data represented as an array
	 * @param fileLocation: of JSON file to be written
	 */
	private static void writeArrayToFile(JSONArray dataArray, String fileLocation)
	{
		try(FileWriter fw = new FileWriter(fileLocation))
		{
			fw.write(dataArray.toJSONString());
			fw.flush();
			fw.close();
		}
		catch(Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	/**
	 * This method wraps user data into a JSONObject.
	 * @param name: user's name
	 * @param username: user's username
	 * @param password: user's password
	 * @return user: wrapped JSONObject with param contents.
	 */
	public static JSONObject wrapUserInfo(String name, String username, String password)
	{
		JSONObject user = new JSONObject();
		user.put("name", name);
		user.put("username", username);
		user.put("password", password);
		return user;
	}
}