package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class GenesisDAO {
    public static Items searchItem(int itemNumber, int qty, String load) throws SQLException, ClassNotFoundException{

        //execute SELECT statement
        try{
            //get resultset from dbexecutequery method
            ResultSet rsitem = DBUtil.dbExecutePreparedStmt(itemNumber);

            //send resultset to the getitemList method and get item object

            //return item object
            return getItemsFromResultSet(rsitem, qty, load);
        }catch (SQLException e){
            System.out.println("While searching an item with item number: " + itemNumber + " an error occurred: "+e);
            throw e;
        }
    }
    private static Items getItemsFromResultSet(@NotNull ResultSet rs, int qty, String load) throws SQLException{ // this method is to get the values from the database and pass them to a item object
        Items item = null;
        if(rs.next()){
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
    public static ObservableList<Items> searchItems() throws SQLException, ClassNotFoundException{
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
    //SELECT * from items operation
    @NotNull
    private static ObservableList<Items> getItemList(ResultSet rs) throws SQLException {
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
    public static Employee employeeLogin(String empId, String pass) throws SQLException, ClassNotFoundException{
        String selectStmt = "SELECT  FROM employees WHERE EmployeeID='" + empId + "' AND Password='" + pass + "';";
        try{
            ResultSet rsEmployee = DBUtil.dbExecuteQuery(selectStmt);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Employee getEmployeeFromResultSet(@NotNull ResultSet rs) throws SQLException{ // this method is to get the values from the database and pass them to a item object
        Employee employee = null;
        if(rs.next()){
            employee = new Employee();
            employee.setEmployeeNumber(rs.getInt("EmployeeID"));
            employee.setDepartmentID(rs.getInt("Department"));
        }
        return employee;
    }



}
