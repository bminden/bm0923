package toolrentalservice;

import java.util.Date;

public class RentalAgreement{
    private Tool tool;
    private Date checkoutDate;
    private Date dueDate;
    private int rentalDays;
    private int chargeDays;
    private Money prediscountCharge;
    private int discountPercent;
    private Money totalDiscount;
    private Money finalCharge;

    public RentalAgreement(Tool tool, Date checkoutDate, Date dueDate, int rentalDays, int chargeDays, Money prediscountCharge, int discountPercent, Money totalDiscount, Money finalCharge){
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.rentalDays = rentalDays;
        this.chargeDays = chargeDays;
        this.prediscountCharge = prediscountCharge;
        this.discountPercent = discountPercent;
        this.totalDiscount = totalDiscount;
        this.finalCharge = finalCharge;
    }

    public String toString(){
        String rentalAgreementString = tool.toString();
        rentalAgreementString += "Checkout Date: " + checkoutDate.toString() + "\n";
        rentalAgreementString += "Due Date: " + dueDate.toString() + "\n";
        rentalAgreementString += "Rental Days: " + rentalDays + "\n";
        rentalAgreementString += "Charge Days: " + chargeDays + "\n";
        rentalAgreementString += "Prediscount Charge: " + prediscountCharge.getAmount() + "\n";
        rentalAgreementString += "Discount Percent: " + discountPercent + "\n";
        rentalAgreementString += "Total Discount: " + totalDiscount.getAmount() + "\n";
        rentalAgreementString += "Final Charge: " + finalCharge.getAmount() + "\n";

        return rentalAgreementString;
    }

    public void print(){
        System.out.println(this.toString());
    }

    public Money getFinalCharge(){
        return finalCharge;
    }
    
}