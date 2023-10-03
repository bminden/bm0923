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
    private final static Tool LADW = new Tool("LADW"); // Using the ladder because its easiest to manually check because it only isn't charged on holidays
    private final static String LADDERCHARGE = "1.99";
    private final static String NOCHARGE = "0";

    private static Stream<Arguments> dates(){
        return Stream.of(
            Arguments.of(LADW, "2023-09-22", LADDERCHARGE),
            Arguments.of(LADW, "2023-07-04", NOCHARGE),
            Arguments.of(LADW, "2020-07-03", NOCHARGE),
            Arguments.of(LADW, "2020-07-04", LADDERCHARGE),
            Arguments.of(LADW, "2021-07-04", LADDERCHARGE),
            Arguments.of(LADW, "2021-07-05", NOCHARGE),
            Arguments.of(LADW, "2023-09-04", NOCHARGE),
            Arguments.of(LADW, "2024-09-02", NOCHARGE),
            Arguments.of(LADW, "1895-09-02", NOCHARGE), // First labor day celebrated on a Monday
            Arguments.of(LADW, "2023-09-23", LADDERCHARGE)
            );
        }

    private static Stream<Arguments> rentals(){
        return Stream.of(
            Arguments.of(LADW, "2020-07-06", 3, "5.97"),
            Arguments.of(LADW, "2023-09-23", 1, "1.99"),
            Arguments.of(LADW, "2023-09-04", 365, "720.38"), //This range includes one July 4th and two Labor days. So 365 - 3 = 362 and 362 * 199 = 72038
            Arguments.of(LADW, "2024-09-02", 5, "7.96")
            );
        }

    @ParameterizedTest
    @MethodSource("dates")
    public void determineChargeTest(Tool tool, String dateString, String expectedCharge){
        BigDecimal expectedAmount = new Money(expectedCharge, Currency.getInstance("USD")).getAmount();
        BigDecimal amount = ChargeCalculator.determineCharge(tool, createDate(dateString)).getAmount();
        assertTrue(expectedAmount.equals(amount));
    }

    @ParameterizedTest
    @MethodSource("rentals")
    public void calculateChargeTest(Tool tool, String dateString, int rentalDays, String expectedCharge){
        BigDecimal expectedAmount = new Money(expectedCharge, Currency.getInstance("USD")).getAmount();
        BigDecimal amount = ChargeCalculator.calculatePrediscountCharges(tool, createDate(dateString), rentalDays).getAmount();
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