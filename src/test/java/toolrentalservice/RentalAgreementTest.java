package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class RentalAgreementTest {
    private final static Tool LADW = new Tool("LADW");
    private final RentalAgreement rentalAgreement = new RentalAgreement(
        LADW, 
        createDate("2023-10-03"), 
        createDate("2023-10-05"), 
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

    private Date createDate(String dateString){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
