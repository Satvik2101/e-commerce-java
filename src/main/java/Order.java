import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
    ArrayList<CartItem> cartItems;
    float totalPrice;
    Timestamp timeOrdered;
}
