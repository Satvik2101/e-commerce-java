import java.util.ArrayList;


public class User {
    final String name;
    final String sellerName;

    final String password;
    ArrayList<Integer> orderIds;

    public User(String name, String sellerName, String password) {
        this.name = name;
        this.sellerName = sellerName;

        this.password = password;
        orderIds= new ArrayList<Integer>();
    }

    public User(String name, String sellerName, String password,ArrayList<Integer> orderIds) {
        this.name = name;
        this.sellerName = sellerName;
        this.password = password;
        this.orderIds= new ArrayList<Integer>(orderIds);
    }

}
