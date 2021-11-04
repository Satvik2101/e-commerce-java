import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:database.db";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String query = " insert into fixedPriceTable (name,description,sellerName,discountType,discountValue," +
                    "price) values('blah','blah2','blah3','NONE',0.0,50);";
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            System.out.println(result);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
