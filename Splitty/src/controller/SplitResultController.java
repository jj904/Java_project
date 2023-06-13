package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

/**
 * This class deals with SplitResult Page
 */
public class SplitResultController extends ControllerAbs{

	@FXML Button returnLbl;
	@FXML Label billSplitTitle;
	@FXML Label summaryLbl;
	
	// configure the table
	@FXML private TableView<Item> resultTableView;
	@FXML private TableColumn<Item, String> itemNameColumn;
	@FXML private TableColumn<Item, Double> costColumn;
	@FXML private TableColumn<Item, String> paidByColumn;
	@FXML private TableColumn<Item, String> paidForColumn;
	
	private String title = NewSplit1Controller.splitTitle;
	private String calculatedResult = NewSplit2Controller.getCalculatedResults();

	/**
	 * Initializes Split Result page.
	 */
	@FXML
	private void initialize() {
		//set title
		billSplitTitle.setText(title);
		
		//configures table resultTableView
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
		costColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("cost"));
		paidByColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("paidBy"));
		paidForColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("paidFor"));
		resultTableView.setItems(NewSplit2Controller.getBillItems());
		
		summaryLbl.setText(calculatedResult);
		
	}

	/**
	 * Returns to Home Page
	 * @param event prompted by user pressing on returnLbl.
	 * @throws IOException
	 */
	public void returnButton(ActionEvent event) throws IOException{
		getHomePage();
	}

}
