package Auth;

import products.*;

import java.sql.*;

class OrderedProductDetails {
    final int uniqueId;
    final int productId;
    final String productName;
    final int quantity;
    final String productType;
    Product product;

    OrderedProductDetails(int uniqueId, ResultSet resultSet) throws SQLException {
        this.uniqueId = uniqueId;
        productId = resultSet.getInt("productId");
        productName = resultSet.getString("productName");
        quantity = resultSet.getInt("quantity");
        productType = resultSet.getString("productType");
    }

    void printOrderedProductDetails(){
        System.out.println("ITEM NO: "+uniqueId);
        System.out.println("NAME : "+productName);
        System.out.println("Quantity : "+ quantity);
    }

    @SuppressWarnings("DuplicatedCode")
    public void fetchCompleteProductDetails(){
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "select * from "+productType+"Table where (id="+productId+");";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while (set.next()){
                switch (productType) {
                    case "book":
                        product = new BookProduct(set);
                        break;
                    case "fixedPrice":
                        product = new FixedPriceProduct(set);
                        break;
                    case "laptop":
                        product = new LaptopProduct(set);
                        break;
                    case "smartphone":
                        product = new SmartphoneProduct(set);
                        break;
                    default:
                        throw new IllegalArgumentException("Table name provided not valid");
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCompleteProductDetails(){
        product.printDetails();
    }
}
