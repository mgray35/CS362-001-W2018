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
     * only two methods of timetable are public
     */
    @Test
    public void test01() throws Throwable {
        GregorianCalendar today = new GregorianCalendar(2018, 0, 29);
        GregorianCalendar nextWeek = new GregorianCalendar(2018, 1, 9);
        TimeTable timeTable = new TimeTable();
        Appt appt1 = new Appt(0, 0, 1, 1, 2010, "", "");
        Appt appt2 = new Appt(1, 0, 1, 1, 2010, "", "");
        Appt appt3 = new Appt(2, 0, 1, 1, 2010, "", "");
        appt1.setRecurrence(null, 1, 0 , -1);

        LinkedList<Appt> apptList = new LinkedList<Appt>(Arrays.asList(appt3, appt1, appt2));
        LinkedList<CalDay> calList = timeTable.getApptRange(apptList, today, nextWeek);
        System.out.println(calList.toString());

    }

//add more unit tests as you needed
}
