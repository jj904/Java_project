package controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

/**
 * This class controls the second page of New Split.
 */
public class NewSplit2Controller extends ControllerAbs{
	@FXML Button submitLbl;
	@FXML Label submitMessageLbl;
	@FXML Button addItemLbl;
	@FXML Label newSplitTitle;	
	@FXML Button backLbl1;
	@FXML TextField itemTextField;
	@FXML TextField costTextField;
	@FXML Label errorMsgLbl;
	@FXML Label nextErrorMegLbl;
	@FXML Button deleteLbl1;
	
	@FXML CheckBox checkBox1;
	@FXML CheckBox checkBox2;
	@FXML CheckBox checkBox3;
	@FXML CheckBox checkBox4;
	@FXML CheckBox checkBox5;
	@FXML CheckBox checkBox6;
	@FXML ChoiceBox<String> choiceBox;

	// configure the table
	@FXML private TableView<Item> tableView;
	@FXML private TableColumn<Item, String> itemNameColumn;
	@FXML private TableColumn<Item, Double> costColumn;
	@FXML private TableColumn<Item, String> paidByColumn;
	@FXML private TableColumn<Item, String> paidForColumn;
	private static ObservableList<Item> items = FXCollections.observableArrayList();
	
	private String title = NewSplit1Controller.splitTitle;
	private String user = LoginController.getNameFromSystem();
	private String splitter2 = NewSplit1Controller.splitterUser2;
	private String splitter3 = NewSplit1Controller.splitterUser3;
	private String splitter4 = NewSplit1Controller.splitterUser4;
	private String splitter5 = NewSplit1Controller.splitterUser5;
	private String splitter6 = NewSplit1Controller.splitterUser6;
	private double[][] matrixData;
	private static String calculatedResult = "";
	
	/**
	 * Initializes 2nd new split page, with title on top.
	 */
	@FXML
	private void initialize() {
		//set title
		newSplitTitle.setText(title);
		
		//resets data
		calculatedResult = "";
		
		//configures new data for calculation
		matrixData = new double[6][6];
		
		//set checkboxes and choiceboxes
		checkBox1.setText(user);
		choiceBox.getItems().add(user);
		if(splitter2.isEmpty() == false)
		{
			checkBox2.setText(splitter2);
			checkBox2.setOpacity(1);
			choiceBox.getItems().add(splitter2);
		}
		if(splitter3.isEmpty() == false)
		{
			checkBox3.setText(splitter3);
			checkBox3.setOpacity(1);
			choiceBox.getItems().add(splitter3);
		}		
		if(splitter4.isEmpty() == false)
		{
			checkBox4.setText(splitter4);
			checkBox4.setOpacity(1);
			choiceBox.getItems().add(splitter4);
		}
		if(splitter5.isEmpty() == false)
		{
			checkBox5.setText(splitter5);
			checkBox5.setOpacity(1);
			choiceBox.getItems().add(splitter5);
		}
		if(splitter6.isEmpty() == false)
		{
			checkBox6.setText(splitter6);
			checkBox6.setOpacity(1);
			choiceBox.getItems().add(splitter6);
		}
		
		//configures table
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
		costColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("cost"));
		paidByColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("paidBy"));
		paidForColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("paidFor"));
	}
	
	/**
	 * Scene moves to NewSplit1Page prompted by user clicking on the back button.
	 * @param event: on the event of user pressing the back button.
	 * @throws IOException
	 */
	public void backButton(ActionEvent event) throws IOException {
		getNewSplit1Page();
	}
	
	/**
	 * Scene moves to ResultPage prompted by user clicking on the next(submit) button.
	 * @param event: on the event of user pressing the submit button.
	 * @throws IOException
	 */
	public void submitButton(ActionEvent event) throws IOException{
		if (tableView.getItems().isEmpty() == false) {
			items = tableView.getItems();
			calculate();
			convertDataToString();
			getResultPage();
		}
		else {
			nextErrorMegLbl.setText("Please add an item you'd like to split.");
		}
	} 
	
	/**
	 * Creates a new Item and adds it to the table.
	 */
	@FXML public void addItemButton() {
		String paidFor = "";
		
		//Check if user input valid info
		if(itemTextField.getText().isEmpty() == false && costTextField.getText().isEmpty() == false && choiceBox.getValue().isEmpty() == false) {
			errorMsgLbl.setText(" ");			
			
			//identifying paidFor
			if(checkBox1.isSelected())
			{
				paidFor = user;
			}
			if(checkBox2.isSelected())
			{
				if(checkBox1.isSelected() == false)
					paidFor = splitter2;
				else
					paidFor += ", " + splitter2;
			}
			if(checkBox3.isSelected())
			{
				if((checkBox1.isSelected() == false) && (checkBox2.isSelected() == false))
					paidFor = splitter3;
				else
					paidFor += ", " + splitter3;
			}
			if(checkBox4.isSelected())
			{
				if((checkBox1.isSelected() == false) && (checkBox2.isSelected() == false) && (checkBox3.isSelected() == false))
					paidFor = splitter4;
				else
					paidFor+= ", " + splitter4;
			}
			if(checkBox5.isSelected())
			{
				if((checkBox1.isSelected() == false) && (checkBox2.isSelected() == false) && (checkBox3.isSelected() == false) && (checkBox4.isSelected() == false))
					paidFor = splitter5;
				else
					paidFor+= ", " + splitter5;
			}
			if(checkBox6.isSelected())
			{
				if((checkBox1.isSelected() == false) && (checkBox2.isSelected() == false) && (checkBox3.isSelected() == false) && (checkBox4.isSelected() == false) && (checkBox5.isSelected() == false))
					paidFor = splitter6;
				else
					paidFor+= ", " + splitter6;
			}	
			
			try {
                Item newItem = new Item(itemTextField.getText(), Double.parseDouble(costTextField.getText()), choiceBox.getValue(), paidFor);
                tableView.getItems().add(newItem);
                nextErrorMegLbl.setText(" ");

            }catch(NumberFormatException e) {
                errorMsgLbl.setText("Invalid value, enter the cost of item.");
            }
		}
		
		else {
			errorMsgLbl.setText("Please enter the values above.");
			
		} 	
	}
	
	/**
	 * Delete button to delete any item from the table.
	 */
	
	public void deleteButton() {
		ObservableList<Item> selectLine, allItem;
		allItem = tableView.getItems();
		
		 selectLine = tableView.getSelectionModel().getSelectedItems();
		
		for(Item item: selectLine) {
			allItem.remove(item);
		}
	}
	
	/**
	 * Calculates the final results of the newSplit bill,
	 */
	public void calculate() {
		createData(items);
		matrixData = subtract(matrixData, transpose(matrixData));
	}
	
	/**
	 * Converts data to string format for readable UI.
	 */
	public void convertDataToString() {
		int width = 6;
		int height = 6;
		String paidBySplitter = "";
		String paidForSplitter = "";
		for (int x = 0; x < width; x++) {
			//keeping track of who paid
			if(x == 0) paidBySplitter = user;
			if(x == 1) paidBySplitter = splitter2;
			if(x == 2) paidBySplitter = splitter3;
			if(x == 3) paidBySplitter = splitter4;
			if(x == 4) paidBySplitter = splitter5;
			if(x == 5) paidBySplitter = splitter6;
			
            for (int y = 0; y < height; y++) {
            	//keeping track of who the bill was paid for
            	//y will owe x cost amount of money
            	if(y == 0) paidForSplitter = user;
    			if(y == 1) paidForSplitter = splitter2;
    			if(y == 2) paidForSplitter = splitter3;
    			if(y == 3) paidForSplitter = splitter4;
    			if(y == 4) paidForSplitter = splitter5;
    			if(y == 5) paidForSplitter = splitter6;
            	
    			double cost = matrixData[x][y];
                if(cost > 0)
                {
                	calculatedResult = calculatedResult + paidForSplitter + "-->" + paidBySplitter + " $" + String.format("%.2f",cost) + "\n";
                }
            }
        }
	}
	
	/**
	 *Creates an array of double[] data based off of user input and addsArray to the dataMatrix.
	 */
	public void createData(ObservableList<Item> aListofItems)
	{	
		int i = 0;
		for(Item itemObserved : aListofItems)
		{
			//Data to keep track of items and calculate split bills:
			//Data is represented by a double array
			//index 0 = cost of item, 1 = paidBy, 2 = 1 (HIGH) if user has been paidFor, 3 = 1 (HIGH) if splitter2 has been paidFor, etc....
			double[] data;
			data = new double[8];
			itemObserved = aListofItems.get(i);
			data[0] = itemObserved.getCost();
			String paidBy = itemObserved.getPaidBy();
			String paidFor = itemObserved.getPaidFor();
			i++;
			
			//identifying data for paidBy
			for(int j = 0; j < 6; j++)
			{
				if(paidBy.equals(user))
				{
					data[1] = 0;
					break;
				}
				if(paidBy.equals(splitter2))
				{
					data[1] = 1;
					break;
				}
				if(paidBy.equals(splitter3))
				{
					data[1] = 2;
					break;
				}
				if(paidBy.equals(splitter4))
				{
					data[1] = 3;
					break;
				}
				if(paidBy.equals(splitter5))
				{
					data[1] = 4;
					break;
				}
				if(paidBy.equals(splitter6))
				{
					data[1] = 5;
					break;
				}
			}
			
			//identifying data for paidFor
			if((paidFor.contains(user)) && (user.isEmpty() == false))
			{
				data[2] = 1;
			}
			if((paidFor.contains(splitter2)) && (splitter2.isEmpty() == false))
			{
				data[3] = 1;
			}
			if((paidFor.contains(splitter3)) && (splitter3.isEmpty() == false))
			{
				data[4] = 1;
			}
			if((paidFor.contains(splitter4)) && (splitter4.isEmpty() == false))
			{
				data[5] = 1;
			}
			if((paidFor.contains(splitter5)) && (splitter5.isEmpty() == false))
			{
				data[6] = 1;
			}
			if((paidFor.contains(splitter6)) && (splitter6.isEmpty() == false))
			{
				data[7] = 1;
			}
			else {}
			matrixData = addArray(split(data), matrixData);
		}
	}
	
	/**
	 * Transposes an original matrix to be used for calculation() and indicate what each splitter owes.
	 * @param originalArray: an array created by data to show how much paidFor owes paidBy
	 * @return transposedArray: the transposed 2D array matrix
	 */
	public double [][] transpose(double[][] originalArray) {
		if (originalArray == null || originalArray.length == 0) {
            return originalArray;
        }
        
        int width = originalArray.length;
        int  height = originalArray[0].length;


        double [][] transposedArray = new double [height][width];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                transposedArray[y][x] = originalArray[x][y];
            }
        }
        return transposedArray;
    }
    
	/**
	 * Takes the transposed 2D array matrix and subtracts it from the original matrix
	 * @param originalArray
	 * @param transposedArray
	 * @return subtractedArray: the subtracted result matrix
	 */
    public double [][] subtract(double[][] originalArray, double[][] transposedArray) {
    	if (originalArray == null || originalArray.length == 0) {
    		return originalArray;
        }
    	int width = 6;
        int  height = 6;
        double [][] subtractedArray = new double [width][height];
        for (int x = 0; x < width; x++) {
        	for (int y = 0; y < height; y++) {
        		subtractedArray[x][y] = originalArray[x][y] - transposedArray[x][y];
        	}
        }
        return subtractedArray;
    }
    
    /**
	 * Takes information from the user-entered data array and splits the money equally amongst the splitters
	 * @param dataArray
	 * @return chargedAmounts: an array composed of how much each splitter must pay
	 */
    public double[] split(double[] dataArray)
    {            
    	int count = 0;
    	double totalAmountPaid = dataArray[0];
    	double[] chargedAmounts = new double [7];
    	chargedAmounts[6] = dataArray[1];
    	for (int i = 2; i < dataArray.length; i++)
    	{
    		if (dataArray[i] == 1)
    		{
    			count++;
    		}
    	}
        for (int i = 5; i >= 0; i--)
        {
        	double amountPerPerson = Math.round((totalAmountPaid/count) * 100.0)/100.0;
        	if(dataArray[i+2] == 1)
        	{
        		chargedAmounts[i] = amountPerPerson;
            	totalAmountPaid -= amountPerPerson;
            	count--;
        	}        
        }
        return chargedAmounts;
    }
    
    /**
	 * Takes information from the user-entered data array and adds it to the original matrix
	 * @param dataArray
	 * @param originalArray
	 * @return originalArray: which has been altered to hold the values in the user-entered data in dataArray
	 */
    double[][] addArray (double[] dataArray, double[][] originalArray)
    {
        int width = 6;
        int paidByData = (int)(dataArray[6]);
        
        for (int i = 0; i < width; i++) {
        	originalArray[paidByData][i] += dataArray[i];
        }
        return originalArray;
    }
	
    /**
     * Getter for items
     * @return items: an ObservableList of Items from tableView.
     */
	public static ObservableList<Item> getBillItems()
	{
		return items;
	}
	
	/**
	 * Getter for calculatedResults
	 * @return calculatedResult: a String representing what splitters owe
	 */
	public static String getCalculatedResults()
	{
		return calculatedResult;
	}
}