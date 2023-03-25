package JDBCtests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class dynamic_list {

    String dbURL = "jdbc:oracle:thin:@52.86.122.75:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        // in order to get column names, we need resultsetMetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        // list of maps to keep all information
        List<Map<String, Object>> queryData = new ArrayList<>();

        // number of columns
        int colCount = rsmd.getColumnCount();

        // loop through each row
        while (resultSet.next()) {

            Map<String, Object> row = new LinkedHashMap<>();

            for (int i = 1; i <= colCount; i++) {
                row.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }

            queryData.add(row);
        }

        // print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }


        // close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
}
