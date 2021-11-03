package products;

abstract class Product {

    final String name;
    final String description;
    final String sellerName;
    final DiscountType discountType;
    final double discountValue; //This should be percentage if percentage discount,
                                // flat value if flat discount, 0 if none
    double basePrice;
    double finalPrice;


    protected Product(String name, String description, String sellerName,
                      DiscountType discountType, double discountValue) {
        this.name = name;
        this.description = description;
        this.sellerName = sellerName;
        this.discountType = discountType;
        this.discountValue = discountValue;
    }
    //THIS METHOD MUST BE CALLED IN EACH SUB CLASS CONSTRUCTOR AT THE END
    protected void initPrices(){
        basePrice = getBasePrice();
        finalPrice = getFinalProductPrice();
    }

    protected abstract double getBasePrice();

    protected double getFinalProductPrice(){
        Discount discount;
        if (discountType==DiscountType.Flat){
            discount = new FlatDiscount(basePrice,discountValue);
        }else if(discountType == DiscountType.Percentage){
            discount = new PercentageDiscount(basePrice,discountValue);
        }else{
            discount = new NoDiscount(basePrice);
        }
        return discount.getFinalPrice();
    }
    public void printDetails(){
        System.out.println("Name: "+name
                           +"\nDescription: " +description
                           +"\nSeller: "+sellerName
                           +"\nPrice: "+basePrice
                           +"\nDISCOUNTED PRICE: "+finalPrice
        );
    }
}
