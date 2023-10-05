package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CheckoutTest {

    private static Stream<Arguments> percents(){
        return Stream.of(
            Arguments.of(-1),
            Arguments.of(101)
            );
        }
    
    private static Stream<Arguments> finalRentals(){
        return Stream.of(
        Arguments.of("LADW", "2020-07-02", 3, 10, "3.58"), // Test 2
        Arguments.of("CHNS", "2015-07-02", 5, 25, "5.59"), // Test 3
        Arguments.of("JAKD", "2015-09-03", 6, 0, "14.95"), // Test 4
        Arguments.of("JAKR", "2015-07-02", 9, 0, "23.92"), // Test 5
        Arguments.of("JAKR", "2020-07-02", 4, 50, "4.49") // Test 6
        );
    }

    @Test
    public void checkoutTest(){
        Checkout checkout = new Checkout("LADW", 3, 10, "2020-07-02");
        checkout.checkout();
    }

    @Test
    public void checkoutShouldThrowExceptionForRentalDays(){
        assertThrows(IllegalArgumentException.class, () -> new Checkout("LADW", 0, 10, "2020-07-02"));
    }

    @ParameterizedTest
    @MethodSource("percents")
    public void checkoutShouldThrowExceptionForDiscountPercent(int discountPercent){
        assertThrows(IllegalArgumentException.class, () -> new Checkout("LADW", 1, discountPercent, "2020-07-02"));
    }

    @ParameterizedTest
    @MethodSource("finalRentals")
    public void calculateFinalChargeTest(String toolString, String checkoutDate, int rentalDays, int discount, String expectedCharge){
        Checkout checkout = new Checkout(toolString, rentalDays, discount, checkoutDate);
        BigDecimal expectedAmount = new Money(expectedCharge, Currency.getInstance("USD")).getAmount();
        BigDecimal amount = checkout.generateRentalAgreement().getFinalCharge().getAmount();
        assertTrue(expectedAmount.equals(amount));
    }

    @Test
    public void calculateFinalChargeWithInvalidPercentTest(){
        assertThrows(IllegalArgumentException.class, () -> new Checkout("JAKR", 5, 101, "2015-09-03")); // Test
    }

}