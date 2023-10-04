package toolrentalservice;

import java.util.Date;

public class Checkout {
    public String toolCode;
    public int rentalDayCount;
    public int discountPercent;
    public String checkoutDateString;

    private Tool tool;
    private Date checkoutDate;
    
    public Checkout() {}

    public Checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDateString) {
        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDateString = checkoutDateString;
    }

    // I've created this class with the idea that input could be gathered variable by variable. That is why each variable has its own validation method and set and there are two constructor class is empty.
    // This class feels a bit messy because I'm not creating the user interface but I think it does a good job of balancing flexibility with readability.

    public boolean validateAndSetToolCode(String toolCode) {
        try{
            new Tool(toolCode);
            
        }catch(IllegalArgumentException e){
            System.out.println("Tool code provided was invalid.");
            return false;
        }
        return true;
    }

    public boolean validateAndSetRentalDayCount(int rentalDayCount) throws Exception {
        if(rentalDayCount > 0){
            return false;
        }
        this.rentalDayCount = rentalDayCount;
        return true;
    }

    public boolean validateAndSetDiscountPercent(int discountPercent) {
        if(discountPercent > 0 || discountPercent < 100){
            System.out.println("Discount percent must be between 0 and 100.");
            return false;
        }
        return true;
    }

    public boolean validateAndSetCheckoutDate(String checkoutDateString) throws Exception {
        checkoutDate = DateHelper.createDate(checkoutDateString);
        return true;
    }

    public RentalAgreement generateRentalAgreement(){
        return ChargeCalculator.generateRentalAgreement(this.tool, this.rentalDayCount, this.discountPercent, this.checkoutDate);
    }

    public void checkout(){
        RentalAgreement rentalAgreement = generateRentalAgreement();
        rentalAgreement.print();
    }

}
