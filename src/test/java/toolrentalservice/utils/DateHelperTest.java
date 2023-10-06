package toolrentalservice.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DateHelperTest {
    @Test 
    public void createDateTestInvalidString(){
        assertThrows(IllegalArgumentException.class, () -> DateHelper.createDate("INVALID"));
    }
}
