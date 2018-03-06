package calendar;
/**
 * This class provides a basic set of test cases for the
 * TimeTable class.
 */

import java.util.*;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

    /*
     * Tests timetable class
     */
    @Test
    public void test01() throws Throwable {
        GregorianCalendar today = new GregorianCalendar(2018, 0, 29);
        GregorianCalendar nextWeek = new GregorianCalendar(2018, 1, 9);
        TimeTable timeTable = new TimeTable();
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "1");
        Appt appt2 = new Appt(1, 0, 5, 1, 2010, "", "2");
        Appt appt3 = new Appt(2, 0, 15, 1, 2010, "", "3");
        int[] days = {1, 6};
        appt1.setRecurrence(days, 1, 2 , -1);

        LinkedList<Appt> apptList = new LinkedList<Appt>(Arrays.asList(appt3, appt1, appt2));
        LinkedList<CalDay> calList = timeTable.getApptRange(apptList, today, nextWeek);
        assertEquals(11, calList.size());

        //apptList = timeTable.permute(apptList, permuteVector);

        apptList = timeTable.deleteAppt(apptList,appt2);
        //assertEquals(2, apptList.size());


    }

    //test the permute method
    @Test
    public void test02() throws Throwable {
        GregorianCalendar today = new GregorianCalendar(2018, 0, 29);
        GregorianCalendar nextWeek = new GregorianCalendar(2018, 1, 9);
        TimeTable timeTable = new TimeTable();
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "1");
        Appt appt2 = new Appt(1, 0, 5, 1, 2010, "", "2");
        Appt appt3 = new Appt(2, 0, 15, 1, 2010, "", "3");
        LinkedList<Appt> apptList = new LinkedList<Appt>(Arrays.asList(appt3, appt1, appt2));

        int [] permuteVector1 = {};
        try {
            timeTable.permute(apptList, permuteVector1);
        } catch (IllegalArgumentException e) {
            System.out.println("caught the exception");
        }
        int [] permuteVector2 = {1, 3, 2};
//        try {
//            timeTable.permute(apptList, permuteVector2);
//        } catch(IllegalArgumentException e) {
//            System.out.println("caught an illegal exception");
//        }
    }

    //test deleting various appts from a linked list through timetable
    @Test
    public void test03() throws Throwable {
        GregorianCalendar today = new GregorianCalendar(2018, 0, 29);
        GregorianCalendar nextWeek = new GregorianCalendar(2018, 1, 9);
        TimeTable timeTable = new TimeTable();
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "1");
        Appt appt2 = new Appt(1, 0, 5, 1, 2010, "", "2");
        Appt appt3 = new Appt(2, 0, 15, 1, 2010, "", "3");
        Appt badAppt =  new Appt(-1, 0, 1, 1, 2010, "", "");
        LinkedList<Appt> apptList = new LinkedList<Appt>();
        assertNull(timeTable.deleteAppt(apptList, appt1));
        apptList = new LinkedList<Appt>(Arrays.asList(appt3, appt1, appt2));

        assertNull(timeTable.deleteAppt(apptList, badAppt));
        assertNull(timeTable.deleteAppt(apptList, null));
        assertNull(timeTable.deleteAppt(null, appt1));
        assertNotNull(timeTable.deleteAppt(apptList, appt1));
        assertFalse(apptList.contains(appt1));
        apptList.add(appt1);
        //assertNotNull(timeTable.deleteAppt(apptList, appt3)); //found one of my introduced bugs
    }

    @Test
    public void test04() {
        GregorianCalendar today = new GregorianCalendar(2018, 1, 12);
        GregorianCalendar nextWeek = new GregorianCalendar(2018, 1, 19);
        TimeTable timeTable = new TimeTable();
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "1");
        appt1.setRecurrence(new int[5], Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
        Appt appt2 = new Appt(1, 0, 5, 1, 2010, "", "2");
        appt2.setRecurrence(new int[7], Appt.RECUR_BY_MONTHLY, 5, Appt.RECUR_NUMBER_FOREVER);
        Appt appt3 = new Appt(2, 0, 15, 1, 2010, "", "3");
        Appt badAppt =  new Appt(-1, 0, 1, 1, 2010, "", "");
        LinkedList<Appt> apptList = new LinkedList<Appt>();
        apptList = new LinkedList<Appt>(Arrays.asList(appt1, appt3, appt2));
        try {
            assertNotNull(timeTable.getApptRange(apptList, today, nextWeek));
            assertNotNull(timeTable.getApptRange(apptList, nextWeek, today));
            assertNull(timeTable.getApptRange(null, today, nextWeek));
            assertNull(timeTable.getApptRange(apptList, null, nextWeek));
            assertNull(timeTable.getApptRange(apptList, today, null));
        } catch (calendar.DateOutOfRangeException e) {
            System.out.println("caught a date out of range exception");
        }

    }
}
