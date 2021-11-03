package products;

public class LaptopProduct extends Product {
    final int ram; //GB
    final int storage;//GB
    final double weight;
    final double processorGHz;
    final String processor;
    final String yearOfRelease;

    public LaptopProduct(String name,
                         String description,
                         String sellerName,
                         DiscountType discountType,
                         double discountValue,
                         int ram,
                         int storage,
                         double weight, double processorGHz, String processor, String yearOfRelease) {
        super(name, description, sellerName, discountType, discountValue);
        this.ram = ram;
        this.storage = storage;
        this.weight = weight;
        this.processorGHz = processorGHz;
        this.processor = processor;
        this.yearOfRelease = yearOfRelease;
        super.initPrices();
    }

    @Override
    protected double getBasePrice() {
        double resultingPrice = 0;
        if (ram >= 64) {
            resultingPrice += 12000;
        } else if (ram >= 32) {
            resultingPrice += 8000;
        } else if (ram >= 16) {
            resultingPrice += 4000;
        } else {
            resultingPrice += 2000;
        }

        if (storage >= 1024) {
            resultingPrice += (double)(storage/1000) * 8000;
        } else if (storage >= 512) {
            resultingPrice += 4000;
        } else if (storage >= 256) {
            resultingPrice += 2000;
        } else {
            resultingPrice += 1000;
        }

        resultingPrice += processorGHz / 5.1 * 50000;
        if (weight<3)
        resultingPrice += (3-weight)*12000;
        int year = Integer.parseInt(yearOfRelease);
        if (year >= 2020) {
            resultingPrice += 10000;
        } else if (year >= 2019) {
            resultingPrice += 8000;
        } else if (year >= 2018) {
            resultingPrice += 6000;
        }

        return resultingPrice;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("RAM: " + ram + " GB"
                                   + "\nStorage: " + storage + " GB"
                                   + "\nYear released: " + yearOfRelease
                                   + "\nWeight: " +weight
                                   + "\nProcessor : " + processor + " " + processorGHz +" Ghz"
        );
    }
}
