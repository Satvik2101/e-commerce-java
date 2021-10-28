package products;

public abstract class Discount {
    final protected double originalPrice;

    protected Discount(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public abstract double getFinalPrice();
}

class FlatDiscount extends Discount {
    final double flatDiscountValue;


    FlatDiscount(double originalPrice,double flatDiscountValue) {
        super(originalPrice);
        this.flatDiscountValue = flatDiscountValue;
    }

    @Override
    public double getFinalPrice() {
        if (flatDiscountValue>=originalPrice)return 0;
        return originalPrice-flatDiscountValue;
    }
}

class PercentageDiscount extends Discount{
    final double percentageDiscount;

    PercentageDiscount(double originalPrice,double percentageDiscount) {
        super(originalPrice);
        if (percentageDiscount>=100){
            throw new ArithmeticException("Discount percentage cannot be greater than or equal to 100");
        }
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double getFinalPrice() {
        return originalPrice * (100-percentageDiscount);
    }
}

class NoDiscount extends Discount{
    NoDiscount(double originalPrice){
        super(originalPrice);
    }

    @Override
    public double getFinalPrice() {
        return originalPrice;
    }
}