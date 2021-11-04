import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:database.db";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl);
//            String query = " insert into fixedPriceTable (name,description,sellerName,discountType,discountValue," +
//                    "price) values('blah','blah2','blah3','NONE',0.0,50);";
            String query = "select seq from sqlite_sequence where name=\"fixedPriceTable\"";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while   (result.next()){
                System.out.println(result.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
