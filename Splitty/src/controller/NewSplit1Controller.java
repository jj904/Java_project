package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class deals with UI for the first page of a newSplit and data entry by user.
 */
public class NewSplit1Controller extends ControllerAbs{
	
	@FXML Button nextLbl;
	@FXML Label errorMsgLbl;
	@FXML TextField splitTitleLbl;
	@FXML TextField splitter1;
	@FXML TextField splitter2;
	@FXML TextField splitter3;
	@FXML TextField splitter4;
	@FXML TextField splitter5;
	@FXML TextField splitter6;
	
	public static String splitTitle = "";
	public static String splitterUser2 = "";
	public static String splitterUser3 = "";
	public static String splitterUser4 = "";
	public static String splitterUser5 = "";
	public static String splitterUser6 = "";
	
	/**
	 * Initializes 1st new split page, with user's name entered in.
	 */
	@FXML
	private void initialize() {
		splitter1.setText(LoginController.getNameFromSystem());
	}
	
	/**
	 * Scene changes to the second page of newSplit
	 * @param event prompted by user pressing nextLbl
	 * @throws IOException
	 */
	public void  nextButton(ActionEvent event) throws IOException{
		splitTitle = splitTitleLbl.getText();
		splitterUser2 = splitter2.getText();
		splitterUser3 = splitter3.getText();
		splitterUser4 = splitter4.getText();
		splitterUser5 = splitter5.getText();
		splitterUser6 = splitter6.getText();
		if(splitterUser2.isEmpty() == false || splitterUser3.isEmpty() == false || splitterUser4.isEmpty() == false || splitterUser5.isEmpty() == false || splitterUser6.isEmpty() == false) 
		{
			getNewSplit2Page();
		}
		else {
			errorMsgLbl.setText("Please enter the names of the people splitting this bill.");;
		
		} 
	}
}