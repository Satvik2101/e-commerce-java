import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    ArrayList<CartItem> cartItemList;
    float totalPrice;
    Timestamp timeOrdered;
}
