
import javax.swing.plaf.nimbus.State;
import java.sql.*;


public class runner {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String query = "select * from test";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while  (result.next()){
                System.out.println(result.getString("id"));
                System.out.println(result.getString("name"));
//                System.out.println(result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
