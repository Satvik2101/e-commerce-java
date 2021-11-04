import products.BookProduct;
import products.DiscountType;
import products.FixedPriceProduct;
import products.LaptopProduct;

public class ProductsTest {
    public static void main(String[] args) {
        BookProduct book = new BookProduct(

                "HP1",
                "Harry potter first",
                "Bloomsbury",
                DiscountType.Percentage,
                10,
                "JKR",
                "1990",
                230,
                true
        );
        book.printDetails();
        book.writeToDatabase();
        /*
        FixedPriceProduct fixedPriceProduct = new FixedPriceProduct(
                "fixed",
                "desc fixed",
                "seller fixed",
                DiscountType.Flat,
                100,
                500
        );
        fixedPriceProduct.printDetails();
*/
        LaptopProduct laptopProduct = new LaptopProduct(
                "Old laptop",
                "Second hand laptop",
                "laptop seller no2 ",
                DiscountType.Flat,
                10000,
                32,
                1000
                ,
                2.9,
                4.0
                ,
                "i7",
                "2020"
        );
        laptopProduct.printDetails();
        laptopProduct.writeToDatabase();
    }
}
