package products;

import java.sql.*;

public class BookProduct extends Product{
    final String authorName;
    final String yearPublished;
    final int pages;
    final boolean isHardCover;

    public BookProduct(String name, String description, String sellerName, DiscountType discountType,
                          double discountValue, String authorName, String yearPublished, int pages, boolean isHardCover) {
        super( name, description, sellerName, discountType, discountValue);
        this.authorName = authorName;
        this.yearPublished = yearPublished;
        this.pages = pages;
        this.isHardCover = isHardCover;
        super.initPrices();
    }
    public BookProduct(int id,String name, String description, String sellerName, DiscountType discountType,
                       double discountValue, String authorName, String yearPublished, int pages, boolean isHardCover) {
        super(id, name, description, sellerName, discountType, discountValue);
        this.authorName = authorName;
        this.yearPublished = yearPublished;
        this.pages = pages;
        this.isHardCover = isHardCover;
        super.initPrices();
    }



    @Override
    protected double getBasePrice() {
        double resultingPrice= pages*0.5;
        if (isHardCover) resultingPrice+=1000;
        int year = Integer.parseInt(yearPublished);
        if (year>=2020){
            resultingPrice+=200;
        }else if(year>=2018){
            resultingPrice+=100;
        }else if(year>=2008){
            resultingPrice+=50;
        }
        return resultingPrice;
    }


    @Override
    public String getProductType() {
        return "book";
    }

    @Override
    public void printDetails() {
        super.printDetails();
        String type = isHardCover?"Hard Bound" : "Paperback";
        System.out.println("Author: "+authorName
                           +"\nPages: "+pages
                           +"\nYear published: " +yearPublished
                           +"\n"+type
        );
    }

    @Override
    public void writeToDatabase() {
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String productType = getProductType();
            String createTableQuery = "create table if not exists "+productType+"Table " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, description TEXT, " +
                    "sellerName TEXT, discountType TEXT, " +
                    "discountValue REAL,authorName TEXT, yearPublished TEXT, " +
                    "pages INTEGER, isHardCover INTEGER" +
                    ")";
            Statement createQuery = connection.createStatement();
            createQuery.executeUpdate(createTableQuery);
            PreparedStatement statement = connection.prepareStatement("insert into " + productType+"Table (name," +
                                                                              "description,sellerName,discountType," +
                                                                              "discountValue,authorName,yearPublished," +
                                                                              "pages,isHardCover) "
                                                                      +"values(?,?,?,?,?,?,?,?, ?);");
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setString(3,sellerName);
            statement.setString(4,discountType.toString());
            statement.setDouble(5,discountValue);
            statement.setString(6,authorName);
            statement.setString(7,yearPublished);
            statement.setInt(8,pages);
            statement.setInt(9,isHardCover?1:0);

            statement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
