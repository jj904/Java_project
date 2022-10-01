package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class controls the Main Page (first page) of Splitty app.
 */
public class MainPageController
{
	Stage createNewAccStage = null;
	Stage LoginStage = null;
	@FXML Button CreateLbl;
	@FXML Button LoginLbl;

	
	/**
	 * When "sign up" button is pressed, this method pops up createNewAccStage.
	 * @param event: event of the action in which user clicks on the button.
	 */
	public void createAccountButton(ActionEvent event) throws IOException
	{
		AnchorPane createNewAccPane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/CreateAccount.fxml"));
		Scene createNewAccScene = new Scene(createNewAccPane,400, 300);
		createNewAccScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		if(createNewAccStage == null || !createNewAccStage.isShowing())
		{
			createNewAccStage = new Stage();
			createNewAccStage.setScene(createNewAccScene);
			createNewAccStage.show();
		}
		else
			createNewAccStage.toFront();
	}

	/**
	 * When "login" button is pressed, this method pops up LoginStage.
	 * @param event: event of the action in which user clicks on the button.
	 */
	public void loginButton(ActionEvent event) throws IOException
	{
		AnchorPane LoginPane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Scene LoginScene = new Scene(LoginPane,400, 300);
		LoginScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		
		if(LoginStage == null || !LoginStage.isShowing())
		{
			LoginStage = new Stage();
			LoginStage.setScene(LoginScene);
			LoginStage.show();
		}
		else
			LoginStage.toFront();
	}	
}