package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Currency;

import org.junit.jupiter.api.Test;

import toolrentalservice.utils.DateHelper;

public class RentalAgreementTest {
    private final static Tool LADW = new Tool("LADW");
    private final RentalAgreement rentalAgreement = new RentalAgreement(
        LADW, 
        DateHelper.createDate("2023-10-03"), 
        DateHelper.createDate("2023-10-05"), 
        2, 
        3, 
        new Money("5.97", Currency.getInstance("USD")), 
        0, 
        new Money("0.00", Currency.getInstance("USD")),
        new Money("5.97", Currency.getInstance("USD")));

    @Test
    public void testToString(){
        String rentalAgreementString = rentalAgreement.toString();

        assertTrue(rentalAgreementString.contains("Tool Code: LADW"));
        assertTrue(rentalAgreementString.contains("Tool Type: Ladder"));
        assertTrue(rentalAgreementString.contains("Tool Brand: Werner"));
        assertTrue(rentalAgreementString.contains("Daily Rental Charge: 1.99"));
        assertTrue(rentalAgreementString.contains("Checkout Date: Tue Oct 03 00:00:00 CDT 2023"));
        assertTrue(rentalAgreementString.contains("Due Date: Thu Oct 05 00:00:00 CDT 2023"));
        assertTrue(rentalAgreementString.contains("Rental Days: 2"));
        assertTrue(rentalAgreementString.contains("Charge Days: 3"));
        assertTrue(rentalAgreementString.contains("Prediscount Charge: 5.97"));
        assertTrue(rentalAgreementString.contains("Discount Percent: 0"));
        assertTrue(rentalAgreementString.contains("Total Discount: 0.00"));
        assertTrue(rentalAgreementString.contains("Final Charge: 5.97"));
    }
}
