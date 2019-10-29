package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Items {
    private SimpleIntegerProperty itemNumber;
    private SimpleIntegerProperty inventory;
    private SimpleDoubleProperty retailPrice;
    private SimpleDoubleProperty cost;
    private StringProperty description;
    private StringProperty modelNumber;
    private StringProperty itemName;
    private SimpleIntegerProperty qty;
    private SimpleDoubleProperty lineTotal;
    private SimpleStringProperty load;
    private SimpleDoubleProperty total;
    private SimpleDoubleProperty tax;

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public double getTax() {
        return tax.get();
    }

    public SimpleDoubleProperty taxProperty() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax.set(tax);
    }

    public Items() {
        this.itemNumber = new SimpleIntegerProperty();
        this.inventory = new SimpleIntegerProperty();
        this.retailPrice = new SimpleDoubleProperty();
        this.cost = new SimpleDoubleProperty();
        this.description = new SimpleStringProperty();
        this.modelNumber = new SimpleStringProperty();
        this.itemName = new SimpleStringProperty();
        this.qty = new SimpleIntegerProperty();
        this.lineTotal = new SimpleDoubleProperty();
        this.load = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        this.tax = new SimpleDoubleProperty();
    }
    /*public Items(int qty) {
        this.itemNumber = new SimpleIntegerProperty();
        this.inventory = new SimpleIntegerProperty();
        this.retailPrice = new SimpleDoubleProperty();
        this.cost = new SimpleDoubleProperty();
        this.description = new SimpleStringProperty();
        this.modelNumber = new SimpleStringProperty();
        this.itemName = new SimpleStringProperty();
    }*/

    public String getLoad() {
        return load.get();
    }

    public SimpleStringProperty loadProperty() {
        return load;
    }

    public void setLoad(String load) {
        this.load.set(load);
    }

    public int getQty() {
        return qty.get();
    }

    public SimpleIntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public double getLineTotal() {
        return lineTotal.get();
    }

    public SimpleDoubleProperty lineTotalProperty() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal.set(lineTotal);
    }

    public void setLineTotal(int lineTotal) {
        this.lineTotal.set(lineTotal);
    }

    public int getItemNumber() {
        return itemNumber.get();
    }

    public SimpleIntegerProperty itemNumberProperty() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber.set(itemNumber);
    }

    public int getInventory() {
        return inventory.get();
    }

    public SimpleIntegerProperty inventoryProperty() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory.set(inventory);
    }

    public double getRetailPrice() {
        return retailPrice.get();
    }

    public SimpleDoubleProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice);
    }

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getModelNumber() {
        return modelNumber.get();
    }

    public StringProperty modelNumberProperty() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber.set(modelNumber);
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }
    public String toString(){
        return itemNumber.toString() + " "+ qty.toString() +" "+ retailPrice.toString() +" "+ load.toString() +" "+ modelNumber.toString() +" "+ lineTotal.toString();
    }
}
