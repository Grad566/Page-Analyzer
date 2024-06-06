package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    public void tempTest() {
        var mun = 1;
        var actual = App.tempMet();
        assertEquals(actual, mun);
    }
}