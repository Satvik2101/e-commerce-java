package Orders;

import products.Product;


import java.util.ArrayList;
import java.util.Scanner;

public class Cart{
    public ArrayList<CartItem> cartItems;
    double totalPrice;
    public Cart(){
        cartItems = new ArrayList<>();
        totalPrice= 0;
    }
    public void addToCart(Product product){
        product.printDetails();
        int add;
        System.out.println("Add to cart? (1 for yes,0 for no)");
        Scanner input = new Scanner(System.in);
        add = input.nextInt();
        if (add==0)return;
        int quantity;
        System.out.println("Enter quantity");
        quantity = input.nextInt();
        if (quantity==0){
            System.out.println("Item not added!");
            return;
        }
        System.out.println("Adding.....");
        int id = cartItems.size() +1;
         cartItems.add(new CartItem(id, product, quantity));
        System.out.println(quantity + " "+product.name+ " added to cart!");
        totalPrice+=quantity*product.getFinalProductPrice();
    }
    public void displayCartItems(){
        if (cartItems.isEmpty()){
            System.out.println("EMPTY CART!");
            return;
        }
        for (CartItem item:cartItems){
            item.printCartItemData();
        }
        System.out.println("TOTAL ---------------- "+totalPrice);
        System.out.println();
    }
    public void displayProductData(int id){
        cartItems.get(id).printCartItemProductDetails();
    }
    public void setQuantity(int id, int newQuantity){
        if (newQuantity==0){
            Product product = cartItems.get(id-1).product;
            cartItems.remove(id-1);
            System.out.println(product.name+ " removed from Orders.Cart");
            for (int i = id-1;i<cartItems.size();i++){
                cartItems.get(i).decrementId();
            }
            return;
        }
        cartItems.get(id-1).setQuantity(newQuantity);

    }
    public void emptyCart(){
        cartItems.clear();
    }
}
