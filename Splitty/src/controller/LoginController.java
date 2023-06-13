package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class deals with user login.
 */
public class LoginController extends ControllerAbs
{
	@FXML Button LoginLbl;
	@FXML TextField userID;
	@FXML TextField passID;
	@FXML Label loginMessageLbl;
	
	private static String name = "";
	private String username = "";
	private String password = "";
	
	/**
	 * This method changes scene to Home Page if user enters a validLogin
	 * and clicks on "log in" button. Else scene remains on Main Page.
	 * @param event: event of the action in which user clicks on the button.
	 */
	public void loginButton(ActionEvent event) throws IOException
	{
		getUsername();
		getPassword();
		// if user entered non-empty Strings
		if(username.isEmpty() == false && password.isEmpty() == false) {
			//checks for validLogin
			if(!validLogin())
				return;
			getHomePage();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else {
			loginMessageLbl.setText("Please enter username and password.");
		}
	}
	
	/**
	 * This method returns true or false whether a login is valid or not.
	 * @return valid: validity of username and password matching to database.
	 */
	public boolean validLogin()
	{
		getUsername();
		getPassword();
		boolean valid = (model.DbReader.find(
				model.DbWriter.wrapUserInfo(null, username, password),
				model.DbReader.readArray("src/data/userdata.json")) != null);
		if(!valid)
			loginMessageLbl.setText("Invalid username/password.");
		else name = model.DbReader.getName(username);
		return valid;
	}

	//Getters: 
	public static String getNameFromSystem()
	{
		return name;
	}
	
	public String getUsernameFromSystem()
	{
		return username;
	}
	
	public void getUsername()
	{
		username = userID.getText();
	}
	
	public void getPassword()
	{
		password = passID.getText();
	}
}