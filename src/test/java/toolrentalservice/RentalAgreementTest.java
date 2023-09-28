package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Test;

public class RentalAgreementTest {
    private final static Tool LADW = new Tool("LADW");
    private final RentalAgreement rentalAgreement = new RentalAgreement(LADW, createDate("2020-07-06"), createDate("2020-07-09"), 3, 3, getDailyRate(new BigDecimal(597)), 0, 0, getDailyRate(new BigDecimal(5.97)));

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
    
    public BigDecimal getDailyRate(BigDecimal dailyRate){
        BigDecimal afterDecimal = dailyRate.remainder(BigDecimal.ONE);
        return dailyRate.subtract(afterDecimal).add(afterDecimal.round(new MathContext(3, java.math.RoundingMode.HALF_UP))).stripTrailingZeros();
    }
}
