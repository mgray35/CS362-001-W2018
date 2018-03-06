package calendar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalendarUtilTest {

    CalendarUtil cu = new CalendarUtil();

    @Test
    public void testIsLeapYear()
    {
        boolean b = cu.IsLeapYear(4);
        assertEquals(true, b);
        b = cu.IsLeapYear(400);
        assertEquals(true, b);
        b = cu.IsLeapYear(0);
        assertEquals(true, b);
        b = cu.IsLeapYear(3);
        assertEquals(false, b);
        b = cu.IsLeapYear(500);
        assertEquals(false, b);
    }
}
