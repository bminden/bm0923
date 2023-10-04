package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToolTest {
    private static Stream<Arguments> tools(){
        return Stream.of(
            Arguments.of("LADW", "Ladder", "Werner", true, true, false),
            Arguments.of("CHNS", "Chainsaw", "Stihl", true, false, true),
            Arguments.of("JAKD", "Jackhammer", "DeWalt", true, false, false),
            Arguments.of("JAKR", "Jackhammer", "Ridgid", true, false, false)
            );
        }

    @ParameterizedTest
    @MethodSource("tools")
    public void verifyToolsAreCreatedCorrectly(String code, String type, String brand, boolean weekdayRate, boolean weekendRate, boolean holidayRate){
        Tool result = new Tool(code);
        assertEquals(code, result.getCode());
        assertEquals(type, result.getType());
        assertEquals(brand, result.getBrand());
        assertEquals(weekdayRate, result.isWeekdayChargeable());
        assertEquals(weekendRate, result.isWeekendChargeable());
        assertEquals(holidayRate, result.isHolidayChargeable());

    }
}
