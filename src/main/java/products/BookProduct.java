package products;

public class BookProduct extends Product{
    final String authorName;
    final String yearPublished;
    final int pages;
    final boolean isHardCover;

    public BookProduct(String name, String description, String sellerName, DiscountType discountType,
                          double discountValue, String authorName, String yearPublished, int pages, boolean isHardCover) {
        super(name, description, sellerName, discountType, discountValue);
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
    public void printDetails() {
        super.printDetails();
        String type = isHardCover?"Hard Bound" : "Paperback";
        System.out.println("Author: "+authorName
                           +"\nPages: "+pages
                           +"\nYear published: " +yearPublished
                           +"\n"+type
        );
    }
}
