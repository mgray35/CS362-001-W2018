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

	GregorianCalendar gc = new GregorianCalendar(2018, 0, 25);
	CalDay day = new CalDay(gc);
	CalDay day2 = new CalDay();
	Appt appt1 = new Appt(0, 30, 25, 1, 2018, "Class", "CS 362");
	Appt appt2 = new Appt(1, 0, 25, 1, 2018, "Recitation", "MTH 254");
	Appt appt3 = new Appt(23, 30, 25, 1, 2018, "Study", "CS 325");

	@Test
	public void testAddAppt()
	{
		day.addAppt(appt2);
		assertEquals(1, day.getSizeAppts());
		day.addAppt(appt3);
		assertEquals(2, day.getSizeAppts());
		day.addAppt(appt1);
		assertEquals(3, day.getSizeAppts());
		assertEquals("Class", day.getAppts().get(0).getTitle());
		assertEquals("Recitation", day.getAppts().get(1).getTitle());
		assertEquals("Study", day.getAppts().get(2).getTitle());
	}

	@Test
	public void testGetters()
	{
		assertEquals(25, day.getDay());
		assertEquals(0, day.getMonth());
		assertEquals(2018, day.getYear());
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
		day.addAppt(appt1);
		day.addAppt(appt2);
		s = day.toString();
		assertNotNull(s);
		assertNotEquals("", s);
		s = day2.toString();
	}
}
