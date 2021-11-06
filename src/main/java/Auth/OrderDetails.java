package Auth;

import Orders.Order;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class OrderDetails {
    final int orderId;
    final Timestamp timestamp;
    final double price;
    ArrayList<OrderedProductDetails> orderedProductDetailsList;

    OrderDetails(ResultSet set) throws SQLException {
        orderId = set.getInt("orderId");
        timestamp = set.getTimestamp("timestamp");
        price = set.getDouble("price");
        orderedProductDetailsList = new ArrayList<>();
    }

    void fetchOrderedProductsDetails(){
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "select * from orderDetailsTable where (orderId=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderId);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()){
             orderedProductDetailsList.add(new OrderedProductDetails(set));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void printOrderDetails(){
        System.out.println("--------------------------------");
        String timestampString = new SimpleDateFormat("dd.MM.yyyy HH.mm").format(timestamp);
        System.out.println("ORDER NO: "+orderId);
        System.out.println("ORDERED AT: "+timestampString);
        System.out.println("TOTAL PRICE: "+price);
        System.out.println("--------------------------------");

    }

}

