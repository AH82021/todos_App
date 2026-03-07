import java.sql.CallableStatement;

public class Main {
    public static void main(String[] args) {
        // JDBC : Java Database C
        //
        // 1- Create database Connection  Connection con = DriverManager.getConnection("jdbc:mysql:..., "username","password")
        // 2- Create statement Object
        // (Statements in JDBC: statement, PreparedStatement, Callable

        //  Statement satement = con.createStatment():
        // // select * from students; stament.executeQuery();
        // 3- Execute SQL query
        // 4- Process the result
        // 5- Close connection

        String username = "user420";
        String password = "'1234";
        String query = "SELECT * from users WHERE username = "+username + " AND password= "+ password;

        // admin
        // paswrod '1234"
        //  "SELECT * from users WHERE username=? AND password= ?";

        // CREATE FUNCtion get-student() RETURNS TABLE(id INT, name TEXT):

        // A  to B

        //  A- 500 Deduct  success  ()

        // B+ 500 Add  fails

        // commit();
        // rollback()
// transaction is a sequence of database operations that must be completed entirely or not al all.

        // ACDI

    }
}
