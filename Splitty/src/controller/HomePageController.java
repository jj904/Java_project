package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * This class controls the Home Page of Splitty app.
 */
public class HomePageController extends ControllerAbs
{
	@FXML Button NewSplitLbl;
	@FXML Button ViewReceiptsLbl;
	@FXML Button LogoutLbl;
	@FXML Text nameLbl;
	private String name = LoginController.getNameFromSystem();
	
	/**
	 * Initializes home page, greeting user with their name.
	 */
	@FXML
	private void initialize() {
		nameLbl.setText(name + ",");
	}
			
	/**
	 *  This method changes the scene to New Split Page when the
	 *  "new split" button is clicked on by user.
	 *  @param event: event of the action in which user clicks on the button.
	 */
	public void newSplitButton(ActionEvent event) throws IOException
	{
		getNewSplit1Page();
	}
	
	
	/**
	 * This method changes the scene to Main Page when the
	 * "log out" button is clicked on by user.
	 * @param event: event of the action in which user clicks on the button.
	 */
	public void logoutButton(ActionEvent event) throws IOException
	{
		getMainPage();
	}
}