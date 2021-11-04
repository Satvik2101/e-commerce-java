package products;

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
    }

    @Override
    protected void writeToDatabase() {

    }
}
