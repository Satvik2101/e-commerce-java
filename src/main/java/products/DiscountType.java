package products;

public enum DiscountType {
    Flat,
    Percentage,
    None,
    ;


    @Override
    public String toString() {
        switch (this){
            case Flat:return "FLAT";
            case Percentage:return "PERC";
            case None:return "NONE";
            default:throw new IllegalArgumentException("Enum DiscountType provided with invalid value");
        }
    }
}

