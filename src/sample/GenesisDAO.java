package sample;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenesisDAO {
    //functions dealing with items
    public static Items searchItem(int itemNumber, int qty, String load) throws SQLException, ClassNotFoundException {

        //execute SELECT statement
        try {
            //get resultset from dbexecutequery method
            ResultSet rsitem = DBUtil.dbSearchForItem(itemNumber);
            //return item object
            return getItemsFromResultSet(rsitem, qty, load);
        } catch (SQLException e) {
            System.out.println("While searching an item with item number: " + itemNumber + " an error occurred: " + e);
            throw e;
        }
    }

    private static Items getItemsFromResultSet(@NotNull ResultSet rs, int qty, String load) throws SQLException { // this method is to get the values from the database and pass them to a item object
        Items item = null;
        if (rs.next()) {
            item = new Items();
            item.setItemNumber(rs.getInt("itemNumber"));
            item.setInventory(rs.getInt("Inventory"));
            item.setRetailPrice(rs.getDouble("Retail_Price"));
            item.setCost(rs.getDouble("Cost"));
            item.setModelNumber(rs.getString("ModelNum"));
            item.setItemName(rs.getString("ItemName"));
            item.setQty(qty);
            item.setLineTotal(item.getQty() * item.getRetailPrice());
            item.setLoad(load);
        }
        return item;
    }

    //functions dealing with employees
    public static Employee searchEmployee(String user, String pass) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rsemp = DBUtil.dbSearhForEmployee(user, pass);
            return getEmployeeFromResultSet(rsemp);
        } catch (SQLException e) {
            System.out.println("Something happened while searching for the employee!");
            throw e;
        }

    }

    private static Employee getEmployeeFromResultSet(@NotNull ResultSet rs) throws SQLException { // this method is to get the values from the database and pass them to a item object
        Employee employee = null;

        if (rs.next()) {
            System.out.println("Got into getEmployeeFromResultSet funchtion!");
            employee = new Employee();
            employee.setEmployeeNumber(rs.getInt("EmployeeID"));
            employee.setDepartmentID(rs.getInt("Department"));
            employee.setF_name(rs.getString("FirstName"));
            employee.setL_name(rs.getString("LastName"));
            employee.setSales(rs.getDouble("Sales"));
            employee.setPassword(rs.getString("Password"));
            employee.setGenesisUsername(rs.getString("GenesisUsername"));
            employee.setGenesisPassword(rs.getString("GenesisPassword"));
            employee.setOverride_number(rs.getInt("Overide_number"));
        }
        else {
            System.out.println("rs.next appears to be null");
        }
        return employee;
    }


    // things that are not used but might need later
    //----------------------------------------------------------------------------------------// separating for clarity
    /*public static ObservableList<Items> searchItems() throws SQLException, ClassNotFoundException{
        //declare a select statement
        String selectStmt = "SELECT * FROM items";

        //execute SELECT statement
        try{
            //get resultSet from dbExecuteQuery method
            ResultSet rsItems = DBUtil.dbExecuteQuery(selectStmt);
            //send result set to getItemList method and get items object

            //return item object
            return getItemList(rsItems);

        }catch (SQLException e){
            System.out.println("SQL select operation had failed: " + e);
            throw e;
        }
    }
    private static ObservableList<Items> getItemList(@NotNull ResultSet rs) throws SQLException {
        //Declare a observable list wich comprises of item objects
        ObservableList<Items> itemList = FXCollections.observableArrayList();

        while (rs.next()){
            Items item = new Items();
            item.setItemNumber(rs.getInt("itemNumber"));
            item.setInventory(rs.getInt("Inventory"));
            item.setRetailPrice(rs.getDouble("Retail_Price"));
            item.setCost(rs.getDouble("Cost"));
            item.setModelNumber(rs.getString("ModelNum"));
            item.setItemName(rs.getString("ItemName"));
            //add item to the observable list
            itemList.add(item);
        }
        return itemList;
    }
     */
}
