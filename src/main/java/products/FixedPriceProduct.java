package products;

import java.sql.*;

public class FixedPriceProduct extends Product {

    final double price;

    public FixedPriceProduct(String name, String description, String sellerName, DiscountType discountType,
                                double discountValue, double price) {
        super( name, description, sellerName, discountType, discountValue);
        this.price = price;
        super.initPrices();
    }
    public FixedPriceProduct(int id,String name, String description, String sellerName, DiscountType discountType,
                             double discountValue, double price) {
        super( id,name, description, sellerName, discountType, discountValue);
        this.price = price;
        super.initPrices();
    }

    @Override
    protected double getBasePrice() {
        return price;
    }

    @Override
    public String getProductType() {
        return "fixedPrice";
    }

    @Override
    public void writeToDatabase() {
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String productType = getProductType();
            String createTableQuery = "create table if not exists "+productType+"Table " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT ,description TEXT, " +
                    "sellerName TEXT, discountType TEXT, " +
                    "discountValue REAL, price REAL"+
                    ")";
//
            Statement createQuery = connection.createStatement();
            createQuery.executeUpdate(createTableQuery);
            PreparedStatement statement = connection.prepareStatement("insert into " + productType+"Table (name," +
                                                                              "description,sellerName,discountType," +
                                                                              "discountValue,price) "
                                                                              +"values(?,?,?,?,?,?);");
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setString(3,sellerName);
            statement.setString(4,discountType.toString());
            statement.setDouble(5,discountValue);
            statement.setDouble(6,price);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
