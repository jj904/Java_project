package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class handles Item.
 */
public class Item {
	private SimpleStringProperty itemName, paidBy, paidFor;
	private SimpleDoubleProperty cost;
	
	/**
	 * Creates a new Item
	 * @param itemName: an item's name
	 * @param cost: the cost of an item
	 * @param paidBy: who the item is paid by
	 * @param paidFor: who the item is paid for
	 */
	public Item(String itemName, double cost, String paidBy, String paidFor)
	{
		this.itemName = new SimpleStringProperty(itemName);
		this.paidBy = new SimpleStringProperty(paidBy);
		this.paidFor = new SimpleStringProperty(paidFor);
		this.cost = new SimpleDoubleProperty(cost);
	}

	//Getters & setters:
	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(SimpleStringProperty itemName) {
		this.itemName = itemName;
	}

	public String getPaidBy() {
		return paidBy.get();
	}

	public void setPaidBy(SimpleStringProperty paidBy) {
		this.paidBy = paidBy;
	}

	public String getPaidFor() {
		return paidFor.get();
	}

	public void setPaidFor(SimpleStringProperty paidFor) {
		this.paidFor = paidFor;
	}

	public double getCost() {
		return cost.get();
	}

	public void setCost(SimpleDoubleProperty cost) {
		this.cost = cost;
	}
	
	
}