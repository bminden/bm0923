package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChargeCalculatorTest{
    private static Stream<Arguments> finalRentals(){
    return Stream.of(
        Arguments.of("LADW", "2020-07-02", 3, 10, "3.58"), // Test 2
        Arguments.of("CHNS", "2015-07-02", 5, 25, "5.59"), // Test 3
        Arguments.of("JAKD", "2015-09-03", 6, 0, "14.95"), // Test 4
        Arguments.of("JAKR", "2015-07-02", 9, 0, "23.92"), // Test 5
        Arguments.of("JAKR", "2020-07-02", 4, 50, "4.49") // Test 6
        );
    }

    @ParameterizedTest
    @MethodSource("finalRentals")
    public void calculateFinalChargeTest(String toolString, String dateString, int rentalDays, int discount, String expectedCharge){
        BigDecimal expectedAmount = new Money(expectedCharge, Currency.getInstance("USD")).getAmount();
        BigDecimal amount = ChargeCalculator.generateRentalAgreement(new Tool(toolString), rentalDays, discount, DateHelper.createDate(dateString)).getFinalCharge().getAmount();
        assertTrue(expectedAmount.equals(amount));
    }
}