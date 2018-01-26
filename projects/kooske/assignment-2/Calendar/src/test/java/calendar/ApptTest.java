package calendar;
/**
 * This class provides a basic set of test cases for the
 * Appt class.
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
    @Test
    public void test01() throws Throwable {
        int startHour = 21;
        int startMinute = 30;
        int startDay = 15;
        int startMonth = 01;
        int startYear = 2018;
        String title = "Birthday Party";
        String description = "This is my birthday party.";
        //Construct a new Appointment object with the initial data
        Appt appt = new Appt(startHour,
                startMinute,
                startDay,
                startMonth,
                startYear,
                title,
                description);
        // assertions
        assertTrue(appt.getValid());
        assertEquals(21, appt.getStartHour());
        assertEquals(30, appt.getStartMinute());
        assertEquals(15, appt.getStartDay());
        assertEquals(01, appt.getStartMonth());
        assertEquals(2018, appt.getStartYear());
        assertEquals("Birthday Party", appt.getTitle());
        assertEquals("This is my birthday party.", appt.getDescription());
    }

    /*
     * Test our setting methods
     */
    @Test
    public void test02() throws Throwable {
        int startHour = 0;
        int startMinute = 0;
        int startDay = 1;
        int startMonth = 1;
        int startYear = 0;
        String title = null;
        String description = null;
        //Construct a new Appointment object with the initial data
        Appt appt = new Appt(startHour,
                startMinute,
                startDay,
                startMonth,
                startYear,
                title,
                description);
        assertTrue(appt.getValid());

        appt.setTitle("A title");
        assertEquals("A title", appt.getTitle());

        appt.setDescription("A description");
        assertEquals("A description", appt.getDescription());

        appt.setStartDay(20);
        assertEquals(20, appt.getStartDay());

        appt.setStartHour(23);
        assertEquals(23, appt.getStartHour());

        appt.setStartMinute(59);
        assertEquals(59, appt.getStartMinute());

        appt.setStartMonth(2);
        assertEquals(2, appt.getStartMonth());

        appt.setStartYear(2018);
        assertEquals(2018, appt.getStartYear());

    }
//add more unit tests as you needed

    /*
     * Test the possible branches of isValid()
     */
    @Test
    public void test03() throws Throwable {

    }

    /*
     * Test comparison
     */
    @Test
    public void test04() throws Throwable {

    }

    /*
     * Test string representation(s)
     */
    @Test
    public void test05() throws Throwable {

    }
}
