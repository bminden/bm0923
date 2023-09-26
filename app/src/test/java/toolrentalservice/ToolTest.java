package toolrentalservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ToolTest {
    private static Stream<Arguments> tools(){
        return Stream.of(
            Arguments.of("CHNS", "Chainsaw", "Stihl"),
            Arguments.of("LADW", "Ladder", "Werner"),
            Arguments.of("JAKD", "Jackhammer", "DeWalt"),
            Arguments.of("JAKR", "Jackhammer", "Ridgid")
            );
        }

    @ParameterizedTest
    @MethodSource("tools")
    public void verifyToolsAreCreatedCorrectly(String code, String type, String brand){
        Tool result = new Tool(code);
        assertEquals(code, result.getCode());
        assertEquals(type, result.getType());
        assertEquals(brand, result.getBrand());
    }
}
