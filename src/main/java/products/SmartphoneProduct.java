package products;

import java.sql.*;

public class SmartphoneProduct extends Product {
    final int ram; //GB
    final int storage;//GB
    final int backCamCount;
    final double processorGHz;
    final String processor;
    final String yearOfRelease;

    public SmartphoneProduct(
                             String name,
                             String description,
                             String sellerName,
                             DiscountType discountType,
                             double discountValue,
                             int ram,
                             int storage,
                             int backCamCount, double processorGHz, String processor, String yearOfRelease
    ) {
        super( name, description, sellerName, discountType, discountValue);
        this.ram = ram;
        this.storage = storage;
        this.backCamCount = backCamCount;
        this.processorGHz = processorGHz;
        this.processor = processor;
        this.yearOfRelease = yearOfRelease;
        super.initPrices();
    }
    public SmartphoneProduct(
            int id,
            String name,
            String description,
            String sellerName,
            String discountType,
            double discountValue,
            int ram,
            int storage,
            int backCamCount, double processorGHz, String processor, String yearOfRelease
    ) {
        super(id, name, description, sellerName, DiscountType.fromString(discountType), discountValue);
        this.ram = ram;
        this.storage = storage;
        this.backCamCount = backCamCount;
        this.processorGHz = processorGHz;
        this.processor = processor;
        this.yearOfRelease = yearOfRelease;
        super.initPrices();
    }
    public SmartphoneProduct(ResultSet resultSet) throws  SQLException{
        super(resultSet);
        this.ram = resultSet.getInt("ram");
        this.storage = resultSet.getInt("storage");
        this.backCamCount = resultSet.getInt("backCamCount");
        this.processorGHz = resultSet.getDouble("processorGHz");
        this.processor = resultSet.getString("processor");
        this.yearOfRelease = resultSet.getString("yearOfRelease");
        super.initPrices();
    }

    @Override
    protected double getBasePrice() {
        double resultingPrice = 0;
        if (ram >= 32) {
            resultingPrice += 8000;
        } else if (ram >= 16) {
            resultingPrice += 4000;
        } else if (ram >= 8) {
            resultingPrice += 2000;
        } else {
            resultingPrice += 1000;
        }

        if (storage >= 128) {
            resultingPrice += 8000;
        } else if (storage >= 64) {
            resultingPrice += 4000;
        } else if (storage >= 32) {
            resultingPrice += 2000;
        } else {
            resultingPrice += 1000;
        }
        resultingPrice += backCamCount * 6000;
        resultingPrice += processorGHz / 3.3 * 12000;
        int year = Integer.parseInt(yearOfRelease);
        if (sellerName.equals("Apple")){
            resultingPrice+=20000;
        }
        if (year >= 2020) {
            resultingPrice += 2000;
        } else if (year >= 2018) {
            resultingPrice += 1000;
        } else if (year >= 2015) {
            resultingPrice += 500;
        }

        return resultingPrice;
    }

    @Override
    public String getProductType() {
        return "smartphone";
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("RAM: " + ram + " GB"
                                   + "\nStorage: " + storage + " GB"
                                   + "\nYear released: " + yearOfRelease
                                   + "\nBack cameras: " + backCamCount
                                   + "\nProcessor : " + processor + " " + processorGHz
        );
        System.out.println("---------------------------------------------------------");
    }

    @Override
    public void writeToDatabase() {
        String jdbcUrl = "jdbc:sqlite:database.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String productType = getProductType();
            String createTableQuery = "create table if not exists "+productType+"Table " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT ,description TEXT, " +
                    "sellerName TEXT, discountType TEXT, " +
                    "discountValue REAL,ram INTEGER, storage INTEGER, backCamCount" +
                    " INTEGER, processorGHz REAL, processor TEXT, yearOfRelease TEXT" +
                    ")";
            Statement createQuery = connection.createStatement();
            createQuery.executeUpdate(createTableQuery);
            PreparedStatement statement = connection.prepareStatement("insert into " + productType+"Table (name," +
                                                                              "description,sellerName,discountType," +
                                                                              "discountValue,ram,storage," +
                                                                              "backCamCount,processorGHz,processor," +
                                                                              "yearOfRelease) "
                                                                              +"values(?,?,?,?,?,?,?,?,?,?,?);");

            //noinspection DuplicatedCode
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setString(3,sellerName);
            statement.setString(4,discountType.toString());
            statement.setDouble(5,discountValue);
            statement.setInt(6,ram);
            statement.setInt(7,storage);
            statement.setInt(8,backCamCount);
            statement.setDouble(9,processorGHz);
            statement.setString(10,processor);
            statement.setString(11,yearOfRelease);
            statement.executeUpdate();
            setIdFromDatabase();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
