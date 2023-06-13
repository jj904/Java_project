package model;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class reads JSON files in database.
 */
@SuppressWarnings("unchecked")
public class DbReader extends DbParserAbs
{
	/**
	 * This method reads and returns a JSONArray. JSONArrays contain existing
	 * user data including all usernames, passwords, and Splitty receipts.
	 * @param fileLocation: fileLocation of the JSON file to be read
	 * @return dataArray: a JSONArray containing data.
	 */
	public static JSONArray readArray(String fileLocation)
	{
		JSONArray targetData = (JSONArray)getParsedData(fileLocation);
		JSONArray dataArray = new JSONArray();
		
		Iterator<JSONObject> reading = targetData.iterator();
		while(reading.hasNext())
		{
			JSONObject temp = reading.next();
			dataArray.add(temp);
		}
		return dataArray;
	}
	
	/**
	 * This method finds a specific JSONObject target from a JSONArray array.
	 * Returns null if target isn't present in array and the target content matched
	 * in array if it is found. Used to check if user input of username and password
	 * exist in database.
	 * @param target: the target JSONObject to be found in a JSONArray.
	 * @param array: the JSONarray that target is to be found in.
	 * @return a JSONObject with the content of found target or null.
	 */
	public static JSONObject find(JSONObject target, JSONArray array)
	{
		Iterator<JSONObject> reading = array.iterator();
		while(reading.hasNext())
		{
			JSONObject temp = reading.next();
			if(accountMatches(target, temp))
				return temp;
		}
		return null;
	}
	
	/**
	 * Similar to previous method except solely to find if username already exists in database.
	 * @param target: the target JSONObject to be found in a JSONArray.
	 * @param array: the JSONarray that target is to be found in.
	 * @return a JSONObject with the content of found target or null.
	 */
	public static JSONObject userExists(JSONObject target, JSONArray array)
	{
		Iterator<JSONObject> reading = array.iterator();
		while(reading.hasNext())
		{
			JSONObject temp = reading.next();
			if(userMatches(target, temp))
				return temp;
		}
		return null;
	}
	
	/**
	 * This method compares two accounts by their username and passwords.
	 * @param firstAccount: JSONObject that contains a username and password.
	 * @param secondAccount: JSONObject that contains a username and password.
	 * @return true if accountMatches, false if not.
	 */
	public static boolean accountMatches(JSONObject firstAccount, JSONObject secondAccount)
	{
		String firstUser = (String) firstAccount.get("username");
		String secondUser = (String) secondAccount.get("username");
		String firstPass = (String) firstAccount.get("password");
		String secondPass = (String) secondAccount.get("password");
		return((firstUser.equals(secondUser)) && (firstPass.equals(secondPass)));
	}
	
	/**
	 * This method compares two accounts by their username.
	 * @param firstAccount: JSONObject that contains a username
	 * @param secondAccount: JSONObject that contains a username
	 * @return true if username matches, false if not.
	 */
	public static boolean userMatches(JSONObject firstAccount, JSONObject secondAccount)
	{
		String firstUser = (String) firstAccount.get("username");
		String secondUser = (String) secondAccount.get("username");
		return((firstUser.equals(secondUser)));
	}
	
	public static String getName(String username)
	{
		String name = "";
		JSONObject user = userExists(model.DbWriter.wrapUserInfo(null,username, null), readArray("src/data/userdata.json"));
		name = (String) user.get("name");
		return name;
	}
}