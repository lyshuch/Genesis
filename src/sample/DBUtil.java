package sample;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {
    //declare jdbc driver
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    //connection
    private static Connection conn = null

    public static void dbConnect() throws SQLException, ClassNotFoundException{
        try {
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("Where is your MYSQL driver");
            e.printStackTrace();
            throw e;
        }
        System.out.println("MySQL JDBC Driver Registered");

        // establish the mysql connection using connection string
        try{
            conn = DriverManager.getConnection(//removed data for security purposes);
        }catch(SQLException e){
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException{
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException{
        //Declare Statment, resultSet and CachedRusultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {
            //connect to DB (Establish a connection)
            dbConnect();
            System.out.println("Select Statement: " + queryStmt + "\n");

            //create statement
            stmt = conn.createStatement();

            //execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //cachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //we are useing CachedRowSet
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        }catch(SQLException e){
            System.out.println("Problem occurred at executeQuery operation : " + e );
            throw e;
        }finally {
            if (resultSet != null){
                //close resultSet
                resultSet.close();
            }
            if (stmt != null){
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    public static ResultSet dbExecutePreparedStmt(int itemNumber) throws SQLException, ClassNotFoundException{
        dbConnect();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items WHERE ItemNumber=(?)");

        ResultSet resultSet = null;
        CachedRowSet crs;
        try {
            dbConnect();
            System.out.println("Select Statement: SELECT * FROM items WHERE ItemNumber= " + itemNumber);
            stmt.setString(1, String.valueOf(itemNumber));
            resultSet = stmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        }catch (SQLException e){
            System.out.println("Problem occurred at executeQuery operation : " + e );
            throw e;
        }finally {
            if (resultSet != null){
                //close resultSet
                resultSet.close();
            }
            if (stmt != null){
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }//this is to search for an item using a prepaired statement to prevent agesnt sql injection

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException{
        //Declare statment as null
        Statement stmt = null;
        try {
            //connect to DB
            dbConnect();
            //create statement
            stmt =conn.createStatement();
            //runn execute update operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        }catch (SQLException e){
            System.out.println("Problem occurred at executeUpdate operation: " + e);
            throw e;
        }finally {
            if(stmt != null){
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
    }
}
