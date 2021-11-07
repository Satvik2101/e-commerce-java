package products;

import java.sql.*;
import java.util.Scanner;

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

    protected Product(ResultSet resultSet) throws SQLException {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.description = resultSet.getString("description");
            this.sellerName = resultSet.getString("sellerName");
            this.discountType = DiscountType.fromString(resultSet.getString("discountType"));
            this.discountValue = resultSet.getDouble("discountValue");
    }
    protected Product(String sellerName){
        this.sellerName= sellerName;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter product name");
        this.name = input.nextLine();
        System.out.println("Enter description");
        this.description = input.nextLine();
        System.out.println("Enter discount type: FLAT for flat discount, PERC for Percentage and NONE for no discount");
        boolean discountTypeStringValid = false;
        DiscountType temp = DiscountType.None;
        while (!discountTypeStringValid) {
            try {
                String discountTypeString = input.next();
                temp= DiscountType.fromString(discountTypeString);
                discountTypeStringValid= true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid value. Enter again");
            }
        }
        discountType= temp;
        System.out.println("Enter discount value (0 for No discount)");
        this.discountValue= input.nextDouble();
    }
    //THIS METHOD MUST BE CALLED IN EACH SUB CLASS CONSTRUCTOR AT THE END
    protected void initPrices(){
        basePrice = getBasePrice();
        finalPrice = getFinalProductPrice();
    }

    protected abstract double getBasePrice();
    public abstract String getProductType();

    public double getFinalProductPrice(){
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
        System.out.println("---------------------------------------------------------");
//        System.out.println("id:"+id);
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
            //This gets last auto incremented id from sqlite for the table name we pass
            String query = "select seq from sqlite_sequence where name=\""+getProductType()+"Table\"";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while   (result.next()){

               id = result.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected void confirmAndAddToDatabase(){
        System.out.println("--------------------------------");
        System.out.println("Product Details: ");
        printDetails();
        System.out.println("--------------------------------");
        System.out.println("Add product? (N for no, any other value for Yes)");
        Scanner input = new Scanner(System.in);
        String ans = input.next();
        if (ans.equals("N")){
            System.out.println("Not added!");
                    return;
        }
        writeToDatabase();
        System.out.println("Added!");
    }

}
