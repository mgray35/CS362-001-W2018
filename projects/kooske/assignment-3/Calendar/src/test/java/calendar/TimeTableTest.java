package calendar;
/**
 * This class provides a basic set of test cases for the
 * TimeTable class.
 */

import java.util.*;

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

        int [] permuteVector = {1, 3, 2};
        //apptList = timeTable.permute(apptList, permuteVector);

        apptList = timeTable.deleteAppt(apptList,appt2);
        //assertEquals(2, apptList.size());


    }

    @Test
    public void test02() throws Throwable {

    }
}
