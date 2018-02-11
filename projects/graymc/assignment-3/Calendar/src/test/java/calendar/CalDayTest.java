package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	GregorianCalendar gc = new GregorianCalendar(2018, 1, 25);
	CalDay day = new CalDay(gc);
	Appt appt1 = new Appt(8, 30, 25, 1, 2018, "Class", "CS 362");
	Appt appt2 = new Appt(10, 0, 25, 1, 2018, "Recitation", "MTH 254");
	
	@Test
	public void testAddAppt()
	{
		day.addAppt(appt2);
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
	public void testToString()
	{
		String s = day.toString();
		assertNotEquals("", s);
	}
}
