import products.BookProduct;
import products.DiscountType;
import products.FixedPriceProduct;

import java.awt.print.Book;

public class runner {
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
    }
}
