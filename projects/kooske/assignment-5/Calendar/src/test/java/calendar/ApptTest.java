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

        // test setters and getters for normal information
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

        // test setters and getters for recurrence
        assertFalse(appt.isRecurring());
        appt.setRecurrence(new int[1], Appt.RECUR_BY_YEARLY, 0, Appt.RECUR_NUMBER_FOREVER);
        assertTrue(appt.isRecurring());
        assertEquals(Appt.RECUR_BY_YEARLY, appt.getRecurBy());
        assertEquals(Appt.RECUR_NUMBER_FOREVER, appt.getRecurNumber());
        assertEquals(0, appt.getRecurIncrement());
        assertEquals(1, appt.getRecurDays().length);

        // test null check for recur days, null made into a matrix of length 0 by setRecurrence
        appt.setRecurrence(null, Appt.RECUR_BY_YEARLY, 0, Appt.RECUR_NUMBER_FOREVER);
        assertEquals(0, appt.getRecurDays().length);
    }

    /*
     * Test other possible branches of isValid() that have no already
     * been checked. (the true path)
     */
    @Test
    public void test03() throws Throwable {
        Appt appt = new Appt(0, 0, 1, 1, 2010, "", "");
        assertTrue(appt.getValid());

        appt.setStartHour(24);
        assertFalse(appt.getValid());
        appt.setStartHour(-1);
        assertFalse(appt.getValid());
        appt.setStartHour(0);

        appt.setStartMinute(60);
        assertFalse(appt.getValid());
        appt.setStartMinute(-1);
        assertFalse(appt.getValid());
        appt.setStartMinute(0);

        appt.setStartDay(0);
        assertFalse(appt.getValid());
        appt.setStartDay(50);
        assertFalse(appt.getValid());


        // the false branch for months in isValid() will never be evaluated
        // since the NumDaysInMonth CalendarUtil call will throw an error for
        // an invalid month. isValid() depends on a valid month, this is bad
//        appt.setStartMonth(0);
//        assertFalse(appt.getValid());
    }

    /*
     * Test appt comparison
     */
    @Test
    public void test04() throws Throwable {
        Appt apptLess = new Appt(0, 0, 1, 1, 2010, "", "");
        assertTrue(apptLess.getValid());
        Appt apptGreat = new Appt(23, 59, 29, 11, 2018, "", "");
        assertTrue(apptGreat.getValid());

        // compare works by summing the total difference between the different fields of the appt's.
        // appt1.compareTo(appt2) would find appt1 - appt2 for their fields
        // This way, a (+) value means appt1 > appt2, and a (-) value means appt1 < appt2

        assertTrue(apptGreat.compareTo(apptLess) > 0);
        assertTrue(apptLess.compareTo(apptGreat) < 0);
    }

    /*
     * Test string representation(s)
     */
    @Test
    public void test05() throws Throwable {
        int startHour = 8;
        int startMinute = 30;
        int startDay = 30;
        int startMonth = 1;
        int startYear = 2018;
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

        // hardcoded string in by looking at the representationapp and tostring in appt to test
        assertEquals("\t1/30/2018 at 8:30am ,, \n", appt.toString());

        // check if invalid
        appt.setStartMinute(200);
        assertEquals(null, appt.toString());

        // edge cases for different representations
        appt.setStartMinute(30);
        appt.setStartHour(0);
        assertEquals("\t1/30/2018 at 12:30am ,, \n", appt.toString());

        appt.setStartHour(23);
        assertEquals("\t1/30/2018 at 11:30pm ,, \n", appt.toString());
    }
}
