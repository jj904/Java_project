package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class ControllerAbs
{
	public Stage primaryStage = application.Main.window;
	
	// This method changes the scene to Main Page.
	public void getMainPage() throws IOException
	{
		AnchorPane mainPagePane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Mainpage.fxml"));
		Scene mainPageScene = new Scene(mainPagePane, 1080, 630);
		
		primaryStage.setScene(mainPageScene);
		primaryStage.show();
	}
	
	// This method changes the scene to the Home Page.
	public void getHomePage() throws IOException
	{
		AnchorPane homePagePane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"));
		Scene homePageScene = new Scene(homePagePane, 1080, 630);
		
		primaryStage.setScene(homePageScene);
		primaryStage.show();
	}
	
	//This method changes the scene to the first New Split Page.
	public void getNewSplit1Page() throws IOException
	{
		AnchorPane newSplitPagePane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/NewSplit1.fxml"));
		Scene newSplitPageScene = new Scene(newSplitPagePane, 1080, 630);
		
		primaryStage.setScene(newSplitPageScene);
		primaryStage.show();	
	}
	
	//This method changes the scene to the second New Split Page.
	public void getNewSplit2Page() throws IOException
	{
		AnchorPane newSplitPagePane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/NewSplit2.fxml"));
		Scene newSplitPageScene = new Scene(newSplitPagePane, 1080, 630);
		
		primaryStage.setScene(newSplitPageScene);
		primaryStage.show();	
	}
	
	//This method changes the scene to the Result Page.
	public void getResultPage() throws IOException
	{
		AnchorPane ResultPagePane = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/SplitResult.fxml"));
		Scene ResultPageScene = new Scene(ResultPagePane, 1080, 630);
		
		primaryStage.setScene(ResultPageScene);
		primaryStage.show();	
	}
}