package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	GregorianCalendar gcFirst = new GregorianCalendar(2018, 1, 29);
	GregorianCalendar gcLast = new GregorianCalendar(2018, 2, 5);

	CalDay day1 = new CalDay(gcFirst);
	Appt appt1 = new Appt(8, 30, 29, 1, 2018, "Class", "CS 362");
	Appt appt2 = new Appt(14, 0, 29, 1, 2018, "Meeting", "");
	TimeTable tt = new TimeTable();

//	@Test
//	public void testGetApptRange()
//	{
//		int recurTR[] = {0, 0, 1, 0, 1, 0, 0};
//		appt1.setRecurrence(recurTR, 1, 1, 10);
//		day1.addAppt(appt1);
//		day1.addAppt(appt2);
//
//		try {
//			tt.getApptRange(day1.getAppts(), gcFirst, gcLast);
//		} catch (DateOutOfRangeException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testDeleteAppt()
//	{
//		day1.addAppt(appt1);
//		day1.addAppt(appt2);
//		assertEquals(day1.getSizeAppts(), 2);
//		LinkedList<Appt> a = tt.deleteAppt(day1.getAppts(), appt2);
//		assertEquals(a.size(), 1);
//	}

	@Test
	public void testPermute()
	{
		day1.addAppt(appt1);
		day1.addAppt(appt2);
		int pvValid[] = {1, 0};
		int pvLong[] = {1, 2, 0};
		int pvShort[] = {0};

		tt.permute(day1.getAppts(), pvValid);

		try {
			tt.permute(day1.getAppts(), pvLong);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			tt.permute(day1.getAppts(), pvShort);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}

}
