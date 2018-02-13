package calendar;
/**
 * This class provides a basic set of test cases for the
 * CalDay class.
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

    /*
     * Test constructor and getters
     */
    @Test
    public void test01() throws Throwable {
    CalDay calDay = new CalDay(new GregorianCalendar());

    // make a new calendar and get current date from it
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    int month = gregorianCalendar.get(GregorianCalendar.MONTH);
    int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
    int year = gregorianCalendar.get(GregorianCalendar.YEAR);

    assertEquals(day, calDay.getDay());
    assertEquals(month, calDay.getMonth());
    assertEquals(year, calDay.getYear());
    assertEquals(0, calDay.getSizeAppts());
    assertEquals(new LinkedList<Appt>(), calDay.getAppts());

    CalDay badCalDay = new CalDay();
    assertFalse(badCalDay.isValid());

    GregorianCalendar cal1 = new GregorianCalendar(1996, 6, 21);
    CalDay calDay1 = new CalDay(cal1);
    assertEquals(21, calDay1.getDay());
    assertEquals(1996, calDay1.getYear());
    assertEquals(6, calDay1.getMonth());
    }

    /*
     * Test string representation
     */
    @Test
    public void test02() throws Throwable {
        CalDay calDay = new CalDay(new GregorianCalendar());

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int month = gregorianCalendar.get(GregorianCalendar.MONTH);
        int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int year = gregorianCalendar.get(GregorianCalendar.YEAR);

        String today = month + "/" + day + "/" + year;
        assertEquals("\t --- " + today + " --- \n --- -------- Appointments ------------ --- \n\n",calDay.toString());


    }

    /*
     * Test addings appts to calday
     */
    @Test
    public void test03() throws Throwable {
        CalDay calDay = new CalDay(new GregorianCalendar());
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "");
        Appt appt2 = new Appt(2, 22, 2, 2, 2222, "", "");
        Appt appt3 = new Appt(2, 22, 2, 2, 2222, "", "");


        calDay.addAppt(appt2);
        assertEquals(1, calDay.getSizeAppts());

        calDay.addAppt(appt1);
        assertEquals(2, calDay.getSizeAppts());

        calDay.addAppt(appt3);
        assertEquals(3, calDay.getSizeAppts());

        Iterator iter1 = calDay.iterator();
        assertTrue(iter1.hasNext());

        calDay.toString();

        // test more branches of calday class
        CalDay calDay2 = new CalDay();
        Iterator iter2 = calDay2.iterator();
        assertNull(iter2);
        calDay2.toString();
        Appt apptBad = new Appt(25, 0, 1, 1, 2010, "", "");
        calDay2.addAppt(apptBad);




    }
}
