import products.Product;


import java.util.ArrayList;
import java.util.Scanner;

class CartItem {
    final int id;
    final Product product;
    int quantity;

    public CartItem(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity= quantity;
    }

    public void printCartItemData(){
        System.out.println("--------------------------------");
        System.out.println(id+". "+product.name+" x" +quantity);
        System.out.print("--------------------------------");

    }
    public void printCartItemProductDetails(){
        product.printDetails();
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}

public class Cart{
    ArrayList<CartItem> cartItems;
    Cart(){
        cartItems = new ArrayList<>();
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
    }
    public void displayCartItems(){
        if (cartItems.isEmpty()){
            System.out.println("EMPTY CART!");
            return;
        }
        for (CartItem item:cartItems){
            item.printCartItemData();
        }
        System.out.println();
    }
    public void displayProductData(int id){
        cartItems.get(id).printCartItemProductDetails();
    }
    public void setQuantity(int id, int newQuantity){
        cartItems.get(id).setQuantity(newQuantity);
    }
}
