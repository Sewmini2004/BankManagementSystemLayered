package lk.ijse.gdse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

private static DBConnection dbConnection;
private Connection connection;

 private DBConnection() {
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem?useSSL=false", "root", "1234");
     } catch (SQLException | ClassNotFoundException throwables) {
         throwables.printStackTrace();
     }
 }
        public static DBConnection getInstance() {
            return (null == dbConnection) ? dbConnection = new DBConnection() : dbConnection;
        }
        public Connection getConnection() {
            return connection;
        }

    }
