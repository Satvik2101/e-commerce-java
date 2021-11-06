import Auth.Auth;
import Orders.Cart;
import Orders.Order;
import products.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class runner {
    static ArrayList<Product> products;
    static Cart cart;
    static Auth auth;
    static Scanner input;

    static ArrayList<Product> getFromDB(String tableName) {
        ArrayList<Product> products = new ArrayList<>();
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "select * from " + tableName + "Table";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product;
                switch (tableName) {
                    case "book":
                        product = new BookProduct(resultSet);
                        break;
                    case "fixedPrice":
                        product = new FixedPriceProduct(resultSet);
                        break;
                    case "laptop":
                        product = new LaptopProduct(resultSet);
                        break;
                    case "smartphone":
                        product = new SmartphoneProduct(resultSet);
                        break;
                    default:
                        throw new IllegalArgumentException("Table name provided not valid");
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return products;
    }

    static void printProducts() {
        for (int i = 0; i < products.size(); i++) {

            System.out.println(i + 1);
            products.get(i).printDetails();
        }
    }

    static void addToCart() {
        int productNo;
        System.out.println("Enter the product no you want to add");
        productNo = input.nextInt();

        while (productNo > products.size() || productNo < 0) {
            System.out.println("Please enter valid product no");
            productNo = input.nextInt();
        }
        cart.addToCart(products.get(productNo - 1));
    }

    static void changeCartItem() {
        cart.displayCartItems();

        System.out.println("Enter item no to change");
        int itemNo = input.nextInt();
        while (itemNo <= 0 || itemNo > cart.cartItems.size()) {
            System.out.println("Invalid no, try again");
            itemNo = input.nextInt();
        }
        System.out.println("Enter new quantity (Setting to 0 will remove the product)");
        int quantity = input.nextInt();
        while (quantity < 0) {
            System.out.println("Must be positive");
            quantity = input.nextInt();
        }
        cart.setQuantity(itemNo, quantity);
    }

    static void placeOrder() {
        Order order = new Order(cart.cartItems, auth.user.username);
        order.writeToDatabase();
        System.out.println("ORDER PLACED!");
        cart.emptyCart();
    }

    static void register() {
        System.out.println("Enter desired username");
        String username = input.next();
        while (!auth.tryRegister(username)) {
            System.out.println("USERNAME ALREADY EXISTS. PLEASE ENTER ANOTHER.");
            username = input.next();
        }
        
        System.out.println("ENTER PASSWORD");
        String password = input.next();
        System.out.println("ENTER DESIRED SELLER NAME");
        String sellerName = input.next();
        auth.register(username,password,sellerName);
    }

    static void login(){
        System.out.println("ENTER USERNAME");
        String username = input.next();
        System.out.println("ENTER PASSWORD");
        String password = input.next();
        while (!auth.login(username,password)){
            System.out.println("ENTER USERNAME");
            username = input.next();
            System.out.println("ENTER PASSWORD");
            password = input.next();
        }
    }

    static void viewCompleteProductDetails(int orderId){
        int productId;
        System.out.println("ENTER PRODUCT ID");
        productId = input.nextInt();
        while (!auth.user.viewOrderCompleteProductDetails(orderId,productId)){
            System.out.println("INVALID PRODUCT ID.ENTER AGAIN");
            productId= input.nextInt();
        }

    }
    static void viewOrderDetails(){
        int orderId;
        System.out.println("ENTER ORDER ID");
        orderId= input.nextInt();
        while (!auth.user.viewSingleOrderDetails(orderId)){
            orderId= input.nextInt();
        }
        while (true) {
            System.out.println("Would you like to view complete product details?");
            System.out.println("1. Yes");
            System.out.println("0. No");
            int chosenOption;
            chosenOption = input.nextInt();
            switch (chosenOption) {
                case 0:
                    return;
                case 1:
                    viewCompleteProductDetails(orderId);
                    break;
                default:
                    System.out.println("INVALID CONDITION.");

            }
        }

    }
    static void viewPreviousOrders(){
        auth.user.fetchUserOrdersDetails();
        auth.user.printUserOrdersDetails();

        while (true) {
            System.out.println("Would you like to view order details?");
            System.out.println("1. Yes");
            System.out.println("0. No");
            int chosenOption;
            chosenOption = input.nextInt();
            switch (chosenOption) {
                case 0:
                    return;
                case 1:
                    viewOrderDetails();
                    break;
                default:
                    System.out.println("INVALID CONDITION.");

            }
        }
    }

    public static void main(String[] args) {
        products = new ArrayList<>();
        String[] tables = {"book", "fixedPrice", "laptop", "smartphone"};
        for (String table : tables) {
            products.addAll(getFromDB(table));
        }


        cart = new Cart();
        auth = new Auth();
        int chosenOption;
        input = new Scanner(System.in);

        //Authenticate
        while (!auth.isAuthenticated) {
            System.out.println("CHOOSE YOUR ACTION:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.println("Type the number for the option you want.");
            chosenOption = input.nextInt();
            switch (chosenOption) {
                case 0:
                    System.out.println("Exiting! Thanks for shopping with us!");
                    return;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("INVALID CONDITION.");

            }
        }

        while (true) {
            System.out.println("CHOOSE YOUR ACTION:");
            System.out.println("1. View available products");
            System.out.println("2. View cart items");
            System.out.println("3. Add items to cart");
            System.out.println("4. Change cart item quantity");
            System.out.println("5. Place Order");
            System.out.println("6. View previous orders");
            //TODO: ADD SELLING PRODUCT
            System.out.println("0. Exit");
            System.out.println("Type the number for the option you want.");
            chosenOption = input.nextInt();
            switch (chosenOption) {
                case 0:
                    System.out.println("Exiting! Thanks for shopping with us!");
                    return;
                case 1:
                    printProducts();
                    break;
                case 2:
                    cart.displayCartItems();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    changeCartItem();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    viewPreviousOrders();
                    break;
                default:
                    System.out.println("INVALID CONDITION.");

            }
        }
    }
}
