package toolrentalservice;

import toolrentalservice.utils.ChargeCalculator;
import toolrentalservice.utils.DateHelper;

public class Checkout {
    public String toolCode;
    public int rentalDayCount;
    public int discountPercent;
    public String checkoutDate;

    public Checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) {
        if(rentalDayCount < 1){
            throw new IllegalArgumentException("Rental day count must be greater than 0.");
        }

        if(discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    public RentalAgreement generateRentalAgreement(){
        return ChargeCalculator.generateRentalAgreement(new Tool(this.toolCode), this.rentalDayCount, this.discountPercent, DateHelper.createDate(this.checkoutDate));
    }

    public void checkout(){
        RentalAgreement rentalAgreement = generateRentalAgreement();
        rentalAgreement.print();
    }

}
