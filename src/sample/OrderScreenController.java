package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class OrderScreenController implements Initializable {

    //Initializing components from scenebuilder

    //buttons
    @FXML private Button enterItemButton;
    @FXML private Button tenderButton;
    @FXML private Button removeItemButton;

    //treeview
    @FXML public TreeView <String> treeView;

    //Choicebox
    @FXML private ChoiceBox<String> loadChoiceBox;

    //textfield
    @FXML private TextField qtyTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField modelNumberTextField;
    @FXML private TextField itemNumberTextField;

    //labels
    @FXML private Label qtyLabel;
    @FXML private Label priceLabel;
    @FXML private Label modelNumberLabel;
    @FXML private Label loadLabel;
    @FXML private Label itemNumberLabel;
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label totalLabel;

    //table view
    @FXML private TableView<Items> orderTable;
    @FXML private TableColumn<Items, Integer> itemNumberColumn;
    @FXML private TableColumn<Items, Integer> qtyColumn;
    @FXML private TableColumn<Items, String> pricePerItemColumn;
    @FXML private TableColumn<Items, String> loadColumn;
    @FXML private TableColumn<Items, String> modelNumberColumn;
    @FXML private TableColumn<Items, String> lineSubtotalColumn;

    //textbox
    @FXML private TextArea infoTextArea;

    //variables
    private double subTotal;
    private double tax;
    private double total;
    private DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setting the enter item button to the default button
        enterItemButton.setDefaultButton(true);

        //Tree View

        //root tree
        TreeItem<String> root = new TreeItem<>("Genesis");
        root.setExpanded(true);

        //main tree items
        TreeItem<String> inventoryTree = new TreeItem<>("Inventory");
        TreeItem<String> sellingScreen = new TreeItem<>("Selling Screen");

        //add items to tree
        root.getChildren().add(inventoryTree);
        root.getChildren().add(sellingScreen);

        treeView.setRoot(root);
        //choice box
        loadChoiceBox.getItems().add("Carry With");
        loadChoiceBox.getItems().add("Lowe's Delivery");
        loadChoiceBox.getItems().add("Pick up later");
        loadChoiceBox.setValue("Carry With");

        //adding columns to table
        itemNumberColumn.setCellValueFactory(cellData -> cellData.getValue().itemNumberProperty().asObject());
        qtyColumn.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
        pricePerItemColumn.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().retailPriceProperty()));
        loadColumn.setCellValueFactory(cellData -> cellData.getValue().loadProperty());
        modelNumberColumn.setCellValueFactory(cellData -> cellData.getValue().modelNumberProperty());
        lineSubtotalColumn.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().lineTotalProperty()));
    }
    public void onEnterItemClick() throws ClassNotFoundException, SQLException{
        if (qtyTextField.getText().equals("")){
            qtyTextField.setText("1");
        }
        try{
            //get item information
            Items item = GenesisDAO.searchItem(Integer.parseInt(itemNumberTextField.getText()), Integer.parseInt(qtyTextField.getText()), loadChoiceBox.getValue());

            //checking to see if item is null (did not find item in database)
            if (item == null){
                infoTextArea.appendText("Item number " + itemNumberTextField.getText() +" was not found\n");
            }

            //populate item on tableview and display
            populateAndShowItem(item);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred while getting ItemInformation from DB.\n" + e);
            throw e;
        }
        catch (NumberFormatException e){
            infoTextArea.appendText("Please enter a valid item number\n");
        }
    }
    public void onRemoveItemClick(){
        ObservableList<Items> itemsSelected = orderTable.getSelectionModel().getSelectedItems();
        ObservableList<Items> allItems;
        allItems = orderTable.getItems();
        //getting the current item
        for (Items item : itemsSelected) {
            subTotal = subTotal - item.getLineTotal(); // net three lines are calculating the subtotal, tax, and total again
            double taxRate = .07;
            tax = subTotal * taxRate;
            total = subTotal * (1 + taxRate);
            //next three lines are setting the appropriate labels to the calculated values above
            subtotalLabel.setText("Total: " + df.format(subTotal));
            taxLabel.setText("Tax: " + df.format(tax));
            totalLabel.setText("Total: " + df.format(total));
        }
        itemsSelected.forEach(allItems::remove);
    }
    @FXML public void tenderButtonClick() {
        ObservableList<Items> tableItems = orderTable.getItems();
        if (tableItems.isEmpty() == false) {
            System.out.println("Table Items is not empty");
            for (int i = 0; i < tableItems.size(); i++) {
                System.out.println(tableItems.toString());
                Items items = tableItems.get(i);
                System.out.println(items.toString());
            }
        }
        else{
            System.out.println("Table Items is empty");
        }
    }
    @FXML private void populateAndShowItem(Items item) {
        if(item != null){
            populateItem(item);
        }
}
    @FXML private void populateItem(Items item) {
        subTotal = subTotal + item.getLineTotal();
        // the .07 is the taxrate of 7% for
        tax = subTotal * .07; // the .07 is the taxrate of 7% for
        // the 1.07 is for finding what the sub total and the tax added together would be
        total = subTotal * 1.07;
        // comment to test githup upload



        //declare an observable list for table view
        ObservableList<Items> itemData = FXCollections.observableArrayList();
        //add item to the observableList
        itemData.addAll(orderTable.getItems());
        itemData.add(item);
        //set items to the items table
        orderTable.setItems(itemData);

        subtotalLabel.setText("Total: " + df.format(subTotal));
        taxLabel.setText("Tax: " + df.format(tax));
        totalLabel.setText("Total: " + df.format(total));

        itemNumberTextField.clear();
    }
    @FXML public void treeItemClicked(){
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        System.out.println(item.getValue());
    }


    //TODO
    // -- do tender button
    //FIXME

}