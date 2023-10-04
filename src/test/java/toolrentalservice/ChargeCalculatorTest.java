package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChargeCalculatorTest{
    private static Stream<Arguments> finalRentals(){
    return Stream.of(
        Arguments.of("LADW", "2020-07-02", 3, 10, "3.58") // Test 2
        );
    }

    @ParameterizedTest
    @MethodSource("finalRentals")
    public void calculateFinalChargeTest(String toolString, String dateString, int rentalDays, int discount, String expectedCharge){
        BigDecimal expectedAmount = new Money(expectedCharge, Currency.getInstance("USD")).getAmount();
        BigDecimal amount = ChargeCalculator.generateRentalAgreement(new Tool(toolString), rentalDays, discount, createDate(dateString)).getFinalCharge().getAmount();
        assertTrue(expectedAmount.equals(amount));
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