import  java.sql.Connection;
import  java.sql.DriverManager;
    import java.sql.SQLException;
class Main{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital";

        //database creadential
        String username = "arshadkn";
        String password = "Khan@123";


        //establish the connection
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Connection successully");

        }catch (SQLException e){
            System.out.println("Connection failed:"+e.getMessage());
        }
    }
}