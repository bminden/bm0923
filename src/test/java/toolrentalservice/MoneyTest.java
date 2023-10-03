package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyTest {
       private static Stream<Arguments> numbers(){
        return Stream.of(
            Arguments.of("1.999", "2.00"),
            Arguments.of("1.989", "1.99"),
            Arguments.of("100.999", "101.00"),
            Arguments.of("0.001", "0.00")
            );
        }

    @Test
    public void createDollarTest(){
        Money dollar = new Money("1", Currency.getInstance("USD"));

        assertTrue(new BigDecimal("1.00").equals(dollar.getAmount()));
        assertEquals(Currency.getInstance("USD"), dollar.getCurrency());
    }

    @Test
    public void addToAmountTest(){
        Money dollar = new Money("1", Currency.getInstance("USD"));

        dollar.add(new Money("2", Currency.getInstance("USD")));

        assertTrue(new BigDecimal("3.00").equals(dollar.getAmount()));
    }

    @Test
    public void multipleAmountTest(){
        Money dollar = new Money("5", Currency.getInstance("USD"));

        dollar.multiple(new Money("2", Currency.getInstance("USD")));

        assertTrue(new BigDecimal("10.00").equals(dollar.getAmount()));
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void verifyMathContext(String originalAmount, String expectedAmount){
        Money dollar = new Money(originalAmount, Currency.getInstance("USD"));

        assertTrue(new BigDecimal(expectedAmount).equals(dollar.getAmount()));
    }
}
