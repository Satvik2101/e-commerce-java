package Orders;

import products.Product;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class Order {
    final ArrayList<CartItem> cartItems;
    final double totalPrice;
    final Timestamp timeOrdered;
    final String username;
    int id;

    public Order(ArrayList<CartItem> cartItems,String username){
        this.cartItems= cartItems;
        this.username= username;
        totalPrice = calculateTotalPrice();
        timeOrdered= Timestamp.from(Instant.now());
    }
    private double calculateTotalPrice(){
        double price = 0;
        for (CartItem item:cartItems){
            price+=item.product.getFinalProductPrice()* item.quantity;
        }
        return price;
    }

    public void writeToDatabase(){
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String createTableQuery = "create table if not exists ordersTable " +
                    "(orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "timestamp DATETIME ,username TEXT,price REAL);";
            Statement createQuery = connection.createStatement();

            createQuery.executeUpdate(createTableQuery);
            PreparedStatement statement = connection.prepareStatement("insert into ordersTable (timestamp,username," +
                                                                              "price) " +
                                                                              "values (?,?,?);");
            statement.setTimestamp(1,timeOrdered);
            statement.setString(2,username);
            statement.setDouble(3,totalPrice);
            statement.executeUpdate();

            String query = "select seq from sqlite_sequence where name=\"ordersTable\"";
            Statement orderIdStatement = connection.createStatement();
            ResultSet result = orderIdStatement.executeQuery(query);
            while   (result.next()){
                id = result.getInt(1);
            }
            createTableQuery = "create table if not exists orderDetailsTable (orderId INTEGER, productName TEXT, " +
                    "quantity INTEGER, " +
                    "productType TEXT, productId INTEGER,FOREIGN KEY(orderId) REFERENCES ordersTable(orderId)) ";
            createQuery.executeUpdate(createTableQuery);
            statement = connection.prepareStatement("insert into orderDetailsTable values(?,?,?,?,?)");
            statement.setInt(1,id);
            for (CartItem item:cartItems){
                Product product = item.product;
                statement.setString(2, product.name);
                statement.setInt(3,item.quantity);
                statement.setString(4,product.getProductType());
                statement.setInt(5,product.id);
                statement.executeUpdate();
            }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
