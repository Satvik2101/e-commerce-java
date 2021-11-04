import products.*;

import java.util.ArrayList;

public class ProductsTest {
    public static void main(String[] args) {

    Product [] products={
            new FixedPriceProduct(
                    "Notebook",
                    "Both side ruled,200 page blank notebook",
                    "Classmate",
                    DiscountType.None,
                    0,
                    60
            ),
            new FixedPriceProduct(
                    "iPhone XR Phone Cover",
                    "Back cover for iPhone XR. Matte Black ,shock proof and durable",
                    "E-shop",
                    DiscountType.Percentage,
                    10,
                    600
            ),
            new FixedPriceProduct(
                    "Parker Frontier ",
                    "Parker Roller Ball Frontier pen with silver body and blue ink. Iridium tipped with glossy finish",
                    "Parker",
                    DiscountType.Flat,
                    10,
                    320
            ),
            new FixedPriceProduct(
                    "OnePlus Power Bank",
                    "1000 mAh power bank with 18W Power Delivery, dual USB Ports and premium build",
                    "OnePlus",
                    DiscountType.Percentage,
                    15,
                    1200
            ),
            new FixedPriceProduct(
                    "Boult Wireless Earphones",
                    "Boult Wireless Earphones with 10 hours battery life and complete surround sound.",
                    "Boult",
                    DiscountType.Percentage,
                    20,
                    3500
            ),
            new FixedPriceProduct(
                    "Fossil Watch",
                    "Fossil Dean Chronograph Black Dial Men's Watch-FS4545",
                    "Fossil",
                    DiscountType.None,
                    0,
                    9000
            ),
            new FixedPriceProduct(
                    "LED Lights for Diwali",
                    "50 LEDs, 5 Meter color changing lights for home decoration",
                    "Xergy Store",
                    DiscountType.Flat,
                    320,
                    800
            ),
            new BookProduct(
                    "Harry Potter and the Philosopher's Stone",
                    "The first book in the world famous Harry Potter series",
                    "Bloomsbury",
                    DiscountType.Flat,
                    150,
                    "JK Rowling",
                    "1997",
                    230,
                    false
            ),
            new BookProduct(
                    "Immortals Of Meluha",
                    "A unique take on the Mahadev, Lord Shiva",
                    "Westland",
                    DiscountType.Flat,
                    100,
                    "Amish Tripathi",
                    "2012",
                    440,
                    false
            ),
            new BookProduct(
                    "Stormbreaker - Alex Rider Book 1",
                    "The story of a reluctant teenage spy and his first mission",
                    "Puffin",
                    DiscountType.None,
                    0,
                    "Anthony Horowitz",
                    "2006",
                    250,
                    false
            ),
//            new BookProduct(
//                    "Secret of the Nagas",
//                    "Part 2 of the Meluha series",
//                    "Westland",
//                    DiscountType.None,
//                    0,
//                    "Amish Tripathi",
//                    "2014",
//                    500,
//                    false
//            ),
//            new BookProduct(
//                    "Oath of the Vayuputras",
//                    "The third and final part of Meluha series",
//                    "Westland",
//                    DiscountType.None,
//                    0,
//                    "Amish Tripathi",
//                    "2016",
//                    700,
//                    false
//            ),
//            new BookProduct(
//                    "Raven's Gate",
//                    "The first episode in The Power of Five series by the author of the bestselling Alex Rider series, Anthony Horowitz",
//                    "Puffin",
//                    DiscountType.Percentage,
//                    10,
//                    "Anthony Horowitz",
//                    "2013",
//                    304,
//                    false
//            ),
            new SmartphoneProduct(
                    "OnePlus Nord",
                    "All new flagship phone by One Plus. Never Settle",
                    "OnePlus",
                    DiscountType.Flat,
                    1000,
                    8,
                    128,
                    3,
                    3.0,
                    "Arm-Cortex A78",
                    "2021"
            ),
            new SmartphoneProduct(
                    "Mi11X",
                    "The killer new smartphone by Xiaomi",
                    "Xiaomi",
                    DiscountType.Percentage,
                    10,
                    6,
                    128,
                    2,
                    3.2,
                    "Qualcomm Snapdragon 870 5G",
                    "2021"
            ),
            new SmartphoneProduct(
                    "iPhone11",
                    "Apple iPhone 11",
                    "Apple",
                    DiscountType.None,
                    0,
                    16,
                    256,
                    3,
                    3.7,
                    "A15 Bionic",
                    "2020"
            ),

            new LaptopProduct(
                    "DELL XPS",
                    "Dell's new XPS Laptop with unbeatable specs",
                    "Dell",
                    DiscountType.Flat,
                    10000,
                    16,
                    512,
                    2.4,
                    4.5,
                    "i7",
                    "2020"
            ),
            new LaptopProduct(
                    "Acer Aspire",
                    "Acer's Flagship Notebook, ultra thin and ultra smooth",
                    "Acer",
                    DiscountType.Flat,
                    12000,
                    8,
                    1024,
                    1.5,
                    4.0,
                    "i7",
                    "2021"
            ),
            new LaptopProduct(
                    "Lenovo ThinkPad",
                    "A powerful yet affordable thinkpad for professionals",
                    "Lenovo",
                    DiscountType.None,
                    0,
                    32,
                    512,
                    3.0,
                    4.5,
                    "i9",
                    "2021"
            ),
            new LaptopProduct(
                    "Apple MacBook Air",
                    "Thin. Fast. Supreme.",
                    "Apple",
                    DiscountType.None,
                    0,
                    32,
                    1024,
                    1.3,
                    4.6,
                    "i9",
                    "2021"
            ),
    };
//        Product prod =   new LaptopProduct(
//                "Acer Aspire",
//                "Acer's Flagship Notebook, ultra thin and ultra smooth",
//                "Acer",
//                DiscountType.Flat,
//                12000,
//                8,
//                1024,
//                1.5,
//                4.0,
//                "i7",
//                "2021"
//        );
        for (Product product:products){
            product.writeToDatabase();
        }

    }
}
