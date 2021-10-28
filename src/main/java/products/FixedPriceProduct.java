package products;

public class FixedPriceProduct extends Product {
    final double price;

    public FixedPriceProduct(String name, String description, String sellerName, DiscountType discountType,
                                double discountValue, double price) {
        super(name, description, sellerName, discountType, discountValue);
        this.price = price;
        super.initPrices();
    }

    @Override
    protected double getBasePrice() {
        return price;
    }
}
