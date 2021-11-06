package Auth;

import Orders.Order;

import java.sql.*;
import java.util.ArrayList;

public class User {
    public final String username;
    final String sellerName;
    final String password;
    ArrayList<OrderDetails> userOrderDetails;
    public User(String username,  String password,String sellerName) throws SQLException {
        this.username = username;
        this.password = password;
        this.sellerName = sellerName;
        userOrderDetails= new ArrayList<>();
    }

    public void writeToDatabase() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        String createTableQuery = "create table if not exists users " +
                "(username TEXT PRIMARY KEY,password TEXT, sellerName TEXT);";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTableQuery);

        String insertCommand = "insert into users values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCommand);
        preparedStatement.setString(1, username);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,sellerName);
        preparedStatement.executeUpdate();
        connection.close();
    }
    public void fetchUserOrdersDetails(){
        System.out.println("Fetching order details.....");
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "select * from ordersTable where (username=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()){
                userOrderDetails.add(new OrderDetails(set));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void printUserOrdersDetails(){
        for (OrderDetails orderDetails:userOrderDetails){
            orderDetails.printOrderDetails();
        }
    }
    public boolean viewSingleOrderDetails(int orderId){
        for (OrderDetails userOrderDetail : userOrderDetails) {
            if (userOrderDetail.orderId==orderId){
                userOrderDetail.fetchOrderedProductsDetails();
                userOrderDetail.viewProductsDetails();
            return true;
            }
        }
        System.out.println("INVALID ORDER ID");
        return false;

    }
}