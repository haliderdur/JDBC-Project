package JDBCtests;

import java.sql.*;

public class TestConnection {

    /*
    host : 54.152.233.34
    port : 1521
    SID  : xe
    user : hr
    pass : hr

    jdbc url aka connection String
    syntax :
           jdbc:vendorName:driverType:@Host:Port:SID
           jdbc:oracle:thin:@54.152.233.34:1521:xe
     */

    public static void main(String[] args) throws SQLException {

        String dbURL = "jdbc:oracle:thin:@52.86.122.75:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");
        /*
        // move pointer to the next row
        resultSet.next();

        // getting info with column name
        System.out.println(resultSet.getString("region_name"));

        // getting info with column index(starts 1)
        System.out.println(resultSet.getString(2));

        System.out.println("----------------------------------------------------------------------------");

        // 1 - Europe
        // 2 - Americas
        // 3 - Asia
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        resultSet.next(); // move to next row
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        resultSet.next(); // move to next row
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

*/
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }


        // close connection
        resultSet.close();
        statement.close();
        connection.close();


    }
}
