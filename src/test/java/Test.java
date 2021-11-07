import java.sql.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        System.out.println("Enter RAM (GB)");
        int ram = input.nextInt();
        System.out.println("Enter storage (GB)");
        int storage = input.nextInt();
        System.out.println("Enter no of back cameras");
        int backCamCount = input.nextInt();
        System.out.println("Enter processor name");
        String processor = input.nextLine();
        System.out.println("Enter processor GHz");
        double processorGHz = input.nextDouble();
        System.out.println("Enter year of release");
        String yearOfRelease = input.next();
        System.out.println("RAM : "+ram);
        System.out.println("STORAGE :"+storage);
        System.out.println("Backcam :"+backCamCount);
        System.out.println("processor ghz :"+processorGHz);
        System.out.println("processor: "+processor );
        System.out.println("Year "+yearOfRelease);


    }
}
