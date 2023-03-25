package JDBCtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbURL = "jdbc:oracle:thin:@52.86.122.75:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

/*        // move to first row
        resultSet.next();
        System.out.println(resultSet.getString(2));
*/

        // display departments table in 10-Administration-200-1700
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) +
                    " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));
        }


        // close connection
        resultSet.close();
        statement.close();
        connection.close();

    }


    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        // how to find how many rows we have for the query
        // move to last row
        resultSet.last();
        // get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        // print all column information
        resultSet.beforeFirst(); // we need to move to first row
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        // close connection
        resultSet.close();
        statement.close();
        connection.close();

    }

    @DisplayName("Metadata")
    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        // get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        // get the resultsetMetadata - rsmd
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        // how many columns we have
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        // getting column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        // print all the column names dynamically
        for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }

        


        // close connection
        resultSet.close();
        statement.close();
        connection.close();

    }


}




























