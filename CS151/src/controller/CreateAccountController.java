package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class handles creations of a new account.
 */
public class CreateAccountController extends ControllerAbs
{
	@FXML Button CreateAccountLbl;
	@FXML Label createMessageLbl;
	@FXML private TextField nameID;
	@FXML private TextField userID;
	@FXML private TextField passID;
	Stage stage;
	private String name = "";
	private String username = "";
	private String password = "";
	
	/**
	 * Creates an account with a name, username and password
	 * @param event in which user clicks on createAccountLbl
	 * @throws IOException
	 */
	public void createAccountButton(ActionEvent event) throws IOException
	{
		getName();
		getUsername();
		getPassword();
		if(name.isEmpty() == false && username.isEmpty() == false && password.isEmpty() == false) {
			if(!isUnique())
				return;
			model.DbWriter.writeUserData(name, username, password, "src/data/userdata.json");
			getMainPage();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else {
			createMessageLbl.setText("Please enter your information.");
		}
		
	}
	
	/**
	 * Checks if account isUnique
	 * @return true if account does not exist in the system, false if it does.
	 */
	public boolean isUnique()
	{
		boolean unique = false;
		getName();
		getUsername();
		if(name.length() > 9)
		{
			createMessageLbl.setText("Name must contain less than 10 characters.");
			return unique;
		}
		unique = (model.DbReader.userExists(
				model.DbWriter.wrapUserInfo(null, username, null),
				model.DbReader.readArray("src/data/userdata.json")) == null);
		if(unique == false)
		{
			createMessageLbl.setText("Your username already exists in our system :(");
		}
		return unique;
	}
	
	/**
	 * getter for user input for name of account
	 */
	public void getName()
	{
		name = nameID.getText();
	}
	
	/**
	 * getter for user input for username of account
	 */
	public void getUsername()
	{
		username = userID.getText();
	}
	
	/**
	 * getter for user input for password of account.
	 */
	public void getPassword()
	{
		password = passID.getText();
	}
}