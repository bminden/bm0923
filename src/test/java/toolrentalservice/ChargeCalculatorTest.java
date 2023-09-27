package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChargeCalculatorTest{
    private final static Tool LADW = new Tool("LADW"); // Using the ladder because its easiest to manually check because it only isn't charged on holidays
    private final static long LADDERCHARGE = 199L;
    private final static long NOCHARGE = 0L;

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
            Arguments.of(LADW, "2020-07-06", 3, 597L),
            Arguments.of(LADW, "2023-09-23", 1, 199L),
            Arguments.of(LADW, "2023-09-04", 365, 72038L), //This range includes one July 4th and two Labor days. So 365 - 3 = 362 and 362 * 199 = 72038
            Arguments.of(LADW, "2024-09-02", 5, 796L)
            );
        }

    @ParameterizedTest
    @MethodSource("dates")
    public void determineChargeTest(Tool tool, String dateString, long expectedCharge){
        assertEquals(expectedCharge, ChargeCalculator.determineCharge(tool, createDate(dateString)));
    }

    @ParameterizedTest
    @MethodSource("rentals")
    public void calculateChargeTest(Tool tool, String dateString, int rentalDays, long expectedCharge){
        assertEquals(expectedCharge, ChargeCalculator.calculatePrediscountCharges(tool, createDate(dateString), rentalDays));
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