package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToolTest {
    private static Stream<Arguments> tools(){
        return Stream.of(
            Arguments.of("LADW", "Ladder", "Werner", new BigDecimal("1.99"), new BigDecimal("1.99"), new BigDecimal("0.00")),
            Arguments.of("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49"), new BigDecimal("0.00"), new BigDecimal("1.49")),
            Arguments.of("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99"), new BigDecimal("0.00"), new BigDecimal("0.00")),
            Arguments.of("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99"), new BigDecimal("0.00"), new BigDecimal("0.00"))
            );
        }

    @ParameterizedTest
    @MethodSource("tools")
    public void verifyToolsAreCreatedCorrectly(String code, String type, String brand, BigDecimal weekdayRate, BigDecimal weekendRate, BigDecimal holidayRate){
        Tool result = new Tool(code);
        assertEquals(code, result.getCode());
        assertEquals(type, result.getType());
        assertEquals(brand, result.getBrand());
        assertEquals(weekdayRate, result.getWeekdayCharge().getAmount());
        assertEquals(weekendRate, result.getWeekendCharge().getAmount());
        assertEquals(holidayRate, result.getHolidayCharge().getAmount());

    }
}
