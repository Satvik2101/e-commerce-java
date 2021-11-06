package Orders;

import products.Product;

class CartItem {
    int id;
    final Product product;
    int quantity;

    public CartItem(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public void printCartItemData() {
        System.out.println("--------------------------------");
        System.out.println(id + ". " + product.name + " x" + quantity+" ==> " + product.getFinalProductPrice()*quantity);
        System.out.print("--------------------------------");

    }

    public void printCartItemProductDetails() {
        product.printDetails();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        System.out.println("Quantity of " + product.name + "set to " + quantity);
    }

    public void decrementId() {
        this.id = this.id - 1;
    }
}
