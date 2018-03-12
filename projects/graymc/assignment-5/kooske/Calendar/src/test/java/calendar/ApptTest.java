package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static calendar.Appt.*;
import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */

	Appt apptValidLow = new Appt(0, 0, 1, 1, 0, "", "");
	Appt apptValidHigh = new Appt(23, 59, 31, 12, 9999, "This is a Rather Long Title", "This is a somewhat long description");
	Appt apptValidFeb = new Appt(12, 30, 29, 2, 2000, "Leap day", "");
	Appt apptBadHourHigh = new Appt(24, 0, 1, 1, 2018, "Title", "Description");
	Appt apptBadHourLow = new Appt(-1, 0, 1, 1, 2018, "Title", "Description");
	Appt apptBadMinHigh = new Appt(0, 60, 1, 1, 2018, "Title", "Description");
	Appt apptBadMinLow = new Appt(0, -1, 1, 1, 2018, "Title", "Description");
	Appt apptBadDayHigh = new Appt(0, 0, 30, 2, 2018, "Title", "Description");
	Appt apptBadDayLow = new Appt(0, 0, 0, 1, 2018, "Title", "Description");

	@Test
	public void testGetValid()
	{
		boolean v = apptValidLow.getValid();
		assertEquals(v, true);
		v = apptValidHigh.getValid();
		assertEquals(v, true);
		v = apptValidFeb.getValid();
		assertEquals(v, true);
		v = apptBadHourHigh.getValid();
		assertEquals(v, false);
		v = apptBadHourLow.getValid();
		assertEquals(v, false);
//		v = apptBadMinHigh.getValid();
//		assertEquals(v, false);
		v = apptBadMinLow.getValid();
		assertEquals(v, false);
		v = apptBadDayHigh.getValid();
		assertEquals(v, false);
		v = apptBadDayLow.getValid();
		assertEquals(v, false);
	}

	@Test
	public void testGettersSetters()
	{
		int k = apptBadHourHigh.getStartHour();
		assertEquals(k, 24);
		apptBadHourHigh.setStartHour(12);
		k = apptBadHourHigh.getStartHour();
		assertEquals(k, 12);
		boolean v = apptBadHourHigh.getValid();
		assertEquals(true, v);

		k = apptBadMinHigh.getStartMinute();
		assertEquals(k, 60);
		apptBadMinHigh.setStartMinute(30);
		k = apptBadMinHigh.getStartMinute();
		assertEquals(k, 30);
		v = apptBadMinHigh.getValid();
		assertEquals(true, v);

		k = apptBadDayHigh.getStartDay();
		assertEquals(k, 30);
		apptBadDayHigh.setStartDay(10);
		k = apptBadDayHigh.getStartDay();
		assertEquals(k, 10);
		v = apptBadDayHigh.getValid();
		assertEquals(true, v);

		k = apptValidLow.getStartYear();
		assertEquals(k, 0);
		apptValidLow.setStartYear(2018);
		k = apptValidLow.getStartYear();
		assertEquals(k, 2018);

		String s = apptValidLow.getTitle();
		assertEquals(s, "");
		apptValidLow.setTitle("New Title");
		s = apptValidLow.getTitle();
		assertEquals(s, "New Title");
		apptValidLow.setTitle(null);
		s = apptValidLow.getTitle();
		assertEquals(s, "");

		s = apptValidLow.getDescription();
		assertEquals(s, "");
		apptValidLow.setDescription("New Description");
		s = apptValidLow.getDescription();
		assertEquals(s, "New Description");
		apptValidLow.setDescription(null);
		s = apptValidLow.getDescription();
		assertEquals(s, "");
	}

	@Test
	public void testToString()
	{
		String s = apptValidHigh.toString();
		assertEquals("\t12/31/9999 at 11:59pm ,This is a Rather Long Title, This is a somewhat long description\n", s);
		s = apptValidLow.toString();
		assertEquals("\t1/1/0 at 12:0am ,, \n", s);
		s = apptValidFeb.toString();
		assertEquals("\t2/29/2000 at 12:30pm ,Leap day, \n", s);
	}

	@Test
	public void testCompare()
	{
		int k = apptValidLow.compareTo(apptValidHigh);
		assertNotEquals(k, 0);
		k = apptValidHigh.compareTo(apptValidHigh);
		assertEquals(k, 0);
	}

	@Test
	public void testRecurrence()
	{
		boolean b = apptValidLow.isRecurring();
		assertEquals(false, b);
		int r = apptValidLow.getRecurNumber();
		assertEquals(RECUR_NUMBER_NEVER, r);
		r = apptValidLow.getRecurIncrement();
		assertEquals(0, r);
		int rda[] = apptValidLow.getRecurDays();
		int rde[] = new int[0];
		assertArrayEquals(rde, rda);

		int temp[] = {1, 1, 1, 1, 1, 1, 1};
		rde = temp;
		apptValidLow.setRecurrence(rde, RECUR_BY_WEEKLY, 1, RECUR_NUMBER_FOREVER);
		b = apptValidLow.isRecurring();
		assertEquals(true, b);
		r = apptValidLow.getRecurBy();
		assertEquals(RECUR_BY_WEEKLY, r);
		r = apptValidLow.getRecurNumber();
		assertEquals(RECUR_NUMBER_FOREVER, r);
		r = apptValidLow.getRecurIncrement();
		assertEquals(1, r);
		rda = apptValidLow.getRecurDays();
		assertArrayEquals(rde, rda);
	}
}
