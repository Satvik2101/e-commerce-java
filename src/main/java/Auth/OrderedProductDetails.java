package Auth;

import java.sql.ResultSet;
import java.sql.SQLException;

class OrderedProductDetails {
    final int productId;
    final String productName;
    final int quantity;
    final String productType;

    OrderedProductDetails(ResultSet resultSet) throws SQLException {
        productId = resultSet.getInt("productId");
        productName = resultSet.getString("productName");
        quantity = resultSet.getInt("quantity");
        productType = resultSet.getString("productType");
    }


}
