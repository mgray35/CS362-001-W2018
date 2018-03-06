package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	GregorianCalendar gc = new GregorianCalendar(2018, 1, 25);
	CalDay day = new CalDay(gc);
	CalDay day2 = new CalDay();
	Appt appt1 = new Appt(8, 30, 25, 1, 2018, "Class", "CS 362");
	Appt appt2 = new Appt(10, 0, 25, 1, 2018, "Recitation", "MTH 254");

	@Test
	public void testAddAppt()
	{
		day.addAppt(appt2);
		assertEquals(day.getSizeAppts(), 1);
		day.addAppt(appt1);
		assertEquals(day.getSizeAppts(), 2);
		assertEquals(day.getAppts().get(0).getTitle(), "Class");
		assertEquals(day.getAppts().get(1).getTitle(), "Recitation");
	}

	@Test
	public void testGetters()
	{
		assertEquals(day.getDay(), 25);
		assertEquals(day.getMonth(), 1);
		assertEquals(day.getYear(), 2018);
	}

	@Test
	public void testIsValid()
	{
		boolean v = day.isValid();
		assertEquals(true, v);

		v = day2.isValid();
		assertEquals(false, v);
	}

	@Test
	public void testIterator()
	{
		Iterator<Appt> it = (Iterator<Appt>)day.iterator();
		assertNotNull(it);

		it = (Iterator<Appt>)day2.iterator();
		assertNull(it);
	}

	@Test
	public void testToString()
	{
		String s = day.toString();
		assertNotNull(s);
		assertNotEquals("", s);
	}
}
