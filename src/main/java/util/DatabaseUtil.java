package util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
 private static final String URL = "jdbc:postgresql://localhost:5432/todo_db";
 private static final String USER = "postgres";
 private static final  String PASSWORD = "*******";



 public static Connection getConnection() throws SQLException {

     return DriverManager.getConnection(URL,USER,PASSWORD);
 }

}
