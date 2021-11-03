import products.BookProduct;
import products.DiscountType;
import products.FixedPriceProduct;
import products.LaptopProduct;

public class ProductsTest {
    public static void main(String[] args) {
        BookProduct book = new BookProduct(
                "Book",
                "Desc",
                "Seller",
                DiscountType.Percentage,
                20,
                "author",
                "2019",
                200,
                false
        );
        book.printDetails();
        FixedPriceProduct fixedPriceProduct = new FixedPriceProduct(
                "fixed",
                "desc fixed",
                "seller fixed",
                DiscountType.Flat,
                100,
                500
        );
        fixedPriceProduct.printDetails();

        LaptopProduct laptopProduct = new LaptopProduct(
                "Laptop",
                "New laptop",
                "seller",
                DiscountType.Flat,
                5000,
                32,
                2000
                ,
                2.5,
                4.7
                ,
                "i9",
                "2021"
        );
        laptopProduct.printDetails();
    }
}
