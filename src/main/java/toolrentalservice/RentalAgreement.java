package toolrentalservice;

import java.util.Date;

public class RentalAgreement{
    private Tool tool;
    private Date checkoutDate;
    private Date dueDate;
    private int rentalDays;
    private int chargeDays;
    private int prediscountCharge;
    private int discountPercent;
    private int totalDiscount;
    private int finalCharge;

    public String toString(){
        String rentalAgreementString = tool.toString();
        rentalAgreementString = "Checkout Date: " + checkoutDate.toString() + "\n";
        rentalAgreementString = "Due Date: " + dueDate.toString() + "\n";
        rentalAgreementString = "Rental Days: " + rentalDays + "\n";
        rentalAgreementString = "Charge Days: " + chargeDays + "\n";
        rentalAgreementString = "Prediscount Charge: " + prediscountCharge + "\n";
        rentalAgreementString = "Discount Percent: " + discountPercent + "\n";
        rentalAgreementString = "Total Discount: " + totalDiscount + "\n";
        rentalAgreementString = "Final Charge: " + String.format("%02d", finalCharge/100) + "\n";

        return rentalAgreementString;
    }

    public void print(){
        System.out.println(this.toString());
    }
    
}