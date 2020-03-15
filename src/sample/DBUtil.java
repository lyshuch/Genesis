package sample;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {
    //declare jdbc driver
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    //connection
    private static Connection conn = null;

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MYSQL driver");
            e.printStackTrace();
            throw e;
        }
        System.out.println("MySQL JDBC Driver Registered");

        // establish the mysql connection using connection string
        try {
            conn = DriverManager.getConnection("jdbc:mysql://51.83.66.220:3306/malauxco_Genesis", "malauxco_admin", "austin2531");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }



    static ResultSet dbSearhForEmployee(String user, String pass) throws SQLException, ClassNotFoundException {
        dbConnect();
        String insertString = "SELECT * FROM employees WHERE GenesisPassword=? AND GenesisUsername= ?";
        PreparedStatement stmt = conn.prepareStatement(insertString);

        ResultSet resultSet = null;
        CachedRowSet crs;
        try {
            //dbConnect(); // do i need this because i am already connected above?
            System.out.println();
            stmt.setString(1, pass);
            stmt.setString(2, user);
            resultSet = stmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation: " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //close statment
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    static ResultSet dbSearchForItem(int itemNumber) throws SQLException, ClassNotFoundException {
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
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }//this is to search for an item using a prepared statement to prevent against sql injection

    /* none of these have been used but i might need them latter so keeping them here for now
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statment as null
        Statement stmt = null;
        try {
            //connect to DB
            dbConnect();
            //create statement
            stmt = conn.createStatement();
            //runn execute update operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation: " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
    }

    public static ResultSet preparedStatementSelect(String table, String columnToSelect, String thing) throws SQLException, ClassNotFoundException {
        dbConnect();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + table + " WHERE columnToSelect = ?");

        ResultSet resultSet = null;
        CachedRowSet crs;
        try {
            dbConnect();
            System.out.println("Select Statement: SELECT * FROM " + table + " WHERE " + columnToSelect + "= ");
            stmt.setString(1, String.valueOf(thing));
            resultSet = stmt.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare Statement, resultSet and CachedRusultSet as null
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
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //close statement
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

     */
}
