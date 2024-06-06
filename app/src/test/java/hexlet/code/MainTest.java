package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void tempTest() {
        var mun = 1;
        var actual = Main.tempMet();
        assertEquals(actual, mun);
    }
}