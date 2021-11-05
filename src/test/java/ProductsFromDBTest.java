import products.*;

import java.sql.*;
import java.util.ArrayList;

public class ProductsFromDBTest {

    static ArrayList<Product> getFromDB(String tableName){
        ArrayList<Product> products = new ArrayList<Product>();
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "select * from "+tableName+"Table";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Product product;
                if (tableName.equals("book")){
                    product = new BookProduct(resultSet);
                }else if (tableName.equals("fixedPrice")){
                    product = new FixedPriceProduct(resultSet);
                }else if (tableName.equals("laptop")){
                    product = new LaptopProduct(resultSet);
                }else if (tableName.equals("smartphone")){
                    product = new SmartphoneProduct(resultSet);
                }else{
                    throw new IllegalArgumentException("Table name provided not valid");
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return products;
    }
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<Product>();
        String[] tables = {"book","fixedPrice","laptop","smartphone"};
        for (String table:tables){
            products.addAll(getFromDB(table));
        }

        for (Product product: products){
            product.printDetails();
        }
    }
}
