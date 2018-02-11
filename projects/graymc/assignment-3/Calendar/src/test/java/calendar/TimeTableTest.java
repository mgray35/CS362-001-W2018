package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.*;


import org.junit.Test;
import sun.awt.image.ImageWatched;

import static org.junit.Assert.*;

public class TimeTableTest {

	GregorianCalendar gc0 = new GregorianCalendar(2018, 1, 28);
	GregorianCalendar gcFirst = new GregorianCalendar(2018, 1, 30);
	GregorianCalendar gcSecond = new GregorianCalendar(2018, 2, 6);
	GregorianCalendar gcThird = new GregorianCalendar(2018, 3, 13);
	GregorianCalendar gcLast = new GregorianCalendar(2019, 1, 30);

	CalDay day1 = new CalDay(gcFirst);
	Appt appt1 = new Appt(8, 30, 30, 1, 2018, "Class", "CS 362");
	Appt appt2 = new Appt(14, 0, 30, 1, 2018, "Meeting", "");
	Appt appt3 = new Appt(12, 30, 30, 1, 2018, "Dentist Appt", "");
	Appt appt4 = new Appt(16, 0, 25, 12, 2018, "Christmas", "");
	Appt badAppt = new Appt(12, 60, 29, 1, 2018, "", "");
	TimeTable tt = new TimeTable();

	@Test
	public void testGetApptRange()
	{
		int recurTR[] = {0, 0, 1, 0, 1, 0, 0};
		int recurNone[] = {0, 0, 0, 0, 0, 0, 0};
		appt1.setRecurrence(recurTR, Appt.RECUR_BY_WEEKLY, 1, 10);
		appt2.setRecurrence(recurNone, Appt.RECUR_BY_MONTHLY, 1, 12);;
		day1.addAppt(appt1);
		day1.addAppt(appt2);
		day1.addAppt(appt3);
		day1.addAppt(appt4);

		try {
			LinkedList<CalDay> range = tt.getApptRange(day1.getAppts(), gcFirst, gcSecond);
			assertEquals(4, range.size());
			assertEquals(day1.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			LinkedList<CalDay> range = tt.getApptRange(day1.getAppts(), gcFirst, gcThird);
			assertEquals(day1.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			LinkedList<CalDay> range = tt.getApptRange(day1.getAppts(), gcFirst, gcLast);
			assertEquals(day1.getDay(), range.get(0).getDay());
		} catch (DateOutOfRangeException e) {
			assertTrue(false);
		}

		try {
			tt.getApptRange(day1.getAppts(), gcSecond, gcFirst);
		} catch (DateOutOfRangeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testDeleteAppt()
	{
		day1.addAppt(appt1);
		day1.addAppt(appt2);
		day1.addAppt(appt3);
		day1.addAppt(badAppt);
		assertEquals(3, day1.getSizeAppts());
		LinkedList<Appt> a = tt.deleteAppt(day1.getAppts(), appt1);
//		assertEquals(2, a.size());

		int initialSize = day1.getSizeAppts();
		a = tt.deleteAppt(day1.getAppts(), null);
		assertNull(a);
		assertEquals(initialSize, day1.getSizeAppts());
		a = tt.deleteAppt(null, appt2);
		assertNull(a);
		assertEquals(initialSize, day1.getSizeAppts());
		a = tt.deleteAppt(day1.getAppts(), badAppt);
		assertNull(a);
		assertEquals(initialSize, day1.getSizeAppts());
	}

	@Test
	public void testPermute()
	{
		day1.addAppt(appt1);
		day1.addAppt(appt2);
		int pvValid[] = {1, 0};
		int pvLong[] = {1, 2, 0};
		int pvShort[] = {0};

		Appt a = day1.getAppts().get(1);
		LinkedList<Appt> l = tt.permute(day1.getAppts(), pvValid);
		assertEquals(day1.getSizeAppts(), l.size());
//		assertEquals(a, l.get(0));

		try {
			tt.permute(day1.getAppts(), pvLong);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		try {
			tt.permute(day1.getAppts(), pvShort);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

}
