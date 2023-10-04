/*
 * I know a user interface wasn't required but I built it anyway to make it easier to test the program. I haven't written a command line interface since college but always enjoyed it.
 */
package toolrentalservice;

import java.util.Scanner;

public class ApplicationRunner {
    static Scanner reader = new Scanner(System.in);
    private static Checkout checkout = new Checkout();

    public static void main(String[] args) throws Exception {
        printIntro();
        getToolCode();
        getRentalDayCount();
        getDiscountPercent();
        getCheckoutDate();

        checkout.generateRentalAgreement();
        checkout.checkout();
    }

    private static void getToolCode() {
        while(true){
            System.out.println("Please enter the tool code: ");
            String input = reader.nextLine();
            if(!checkout.validateAndSetToolCode(input)){
                
            }else{
                break;
            }
        }
    }

    private static void getRentalDayCount() throws Exception {
        while(true){
            System.out.println("Please enter the number of rental days: ");
            int input = reader.nextInt();
            if(!checkout.validateAndSetRentalDayCount(input)){
                System.out.println("Rental day count must be greater than 0.");
            }else{
                break;
            }
        }
    }

    private static void getDiscountPercent() throws Exception {
        while(true){
            System.out.println("Please enter the discount percent: ");
            int input = reader.nextInt();
            if(!checkout.validateAndSetDiscountPercent(input)){
                System.out.println("Discount percent must be between 0 and 100.");
            }else{
                break;
            }
        }
    }

    private static void getCheckoutDate() throws Exception {
        while(true){
            System.out.println("Please enter the checkout date using the yyyy-MM-dd format: ");
            String input = reader.nextLine();
            if(!checkout.validateAndSetCheckoutDate(input)){
                System.out.println("That date is not parseable, please try again.");
            }else{
                break;
            }
        }
    }

    public static void printIntro() {
        System.out.println("Welcome to the Tool Rental Service!");
    }
}
