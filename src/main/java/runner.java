import products.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class runner {
    static ArrayList<Product> products;
    static Cart cart;
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
    static void addToCart(){
        int productNo;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the product no you want to add");
        productNo = input.nextInt();

        while (productNo>products.size() || productNo<0){
            System.out.println("Please enter valid product no");
            productNo = input.nextInt();
        }
        cart.addToCart(products.get(productNo-1));
    }
    static void changeCartItem(){
        cart.displayCartItems();
        Scanner input = new Scanner(System.in);

        System.out.println("Enter item no to change");
        int itemNo = input.nextInt();
        while (itemNo<=0 || itemNo>cart.cartItems.size()){
            System.out.println("Invalid no, try again");
            itemNo= input.nextInt();
        }
        System.out.println("Enter new quantity (Setting to 0 will remove the product)");
        int quantity = input.nextInt();
        while (quantity<0){
            System.out.println("Must be positive");
            quantity= input.nextInt();
        }
        cart.setQuantity(itemNo,quantity);
    }
    static void placeOrder(){
        //TODO:Change to actual username after login
        Order order = new Order(cart.cartItems,"satvik");
        order.writeToDatabase();
        System.out.println("ORDER PLACED!");
    }

    public static void main(String[] args) {
        products = new ArrayList<>();
        String[] tables = {"book", "fixedPrice", "laptop", "smartphone"};
        for (String table : tables) {
            products.addAll(getFromDB(table));
        }


        cart = new Cart();

        //Menu based program

        int chosenOption ;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("CHOOSE YOUR ACTION:");
            System.out.println("1. View available products");
            System.out.println("2. View cart items");
            System.out.println("3. Add items to cart");
            System.out.println("4. Change cart item quantity");
            System.out.println("5. Place Order");
//            TODO: IMPLEMENT:
//            System.out.println("Place order");
//            System.out.println("View previous orders");
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
                default:
                    System.out.println("INVALID CONDITION.");

            }
        }
    }
}
