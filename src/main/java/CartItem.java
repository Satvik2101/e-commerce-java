import products.Product;

public class CartItem {
    final Product product;
    int quantity;

    public CartItem(Product product,int quantity) {
        this.product = product;
        this.quantity= quantity;
    }

    public void printCartItemData(){

        System.out.println(product.name+" x" +quantity);
    }
    public void printCartItemProductDetails(){
        product.printDetails();
    }
}

