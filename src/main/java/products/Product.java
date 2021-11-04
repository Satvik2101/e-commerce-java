package products;

import java.sql.*;

public abstract class Product {

    public int id=0;
    public final String name;
    final String description;
    final String sellerName;
    final DiscountType discountType;
    final double discountValue; //This should be percentage if percentage discount,
                                // flat value if flat discount, 0 if none
    double basePrice;
    double finalPrice;

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (obj.getClass()!=this.getClass()) return false;
        Product other  = (Product)obj;
        return other.name.equals(this.name);

    }

    protected Product( String name, String description, String sellerName,
                      DiscountType discountType, double discountValue) {

        this.name = name;
        this.description = description;
        this.sellerName = sellerName;
        this.discountType = discountType;
        this.discountValue = discountValue;
    }
    protected Product( int id,String name, String description, String sellerName,
                       DiscountType discountType, double discountValue) {
        this.id= id;
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
    protected abstract String getProductType();

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
        System.out.println("id:"+id);
        System.out.println("Name: "+name
                           +"\nDescription: " +description
                           +"\nSeller: "+sellerName
                           +"\nPrice: "+basePrice
                           +"\nDISCOUNTED PRICE: "+finalPrice
        );
    }
    abstract public void writeToDatabase();
    protected void setIdFromDatabase(){
        //MUST BE CALLED IMMEDIATELY AFTER INSERTING;
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String query = "select seq from sqlite_sequence where name=\""+getProductType()+"Table\"";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
//            System.out.println(query);
            while   (result.next()){

               id = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
