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

        assertTrue(rentalAgreementString.contains("Final Charge: 5.97"));
    }
}
