package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	
	Appt apptValidLow = new Appt(0, 0, 1, 1, 0, "Title", "Description");
	Appt apptValidHigh = new Appt(23, 59, 31, 12, 10000, "This is a Rather Long Title", "This is a somewhat long description");
	Appt apptValidFeb = new Appt(12, 30, 29, 2, 2000, "Leap day", "");
	Appt apptBadHourHigh = new Appt(24, 0, 1, 1, 0, "Title", "Description");
	Appt apptBadHourLow = new Appt(-1, 0, 1, 1, 0, "Title", "Description");
	Appt apptBadMinHigh = new Appt(0, 60, 1, 1, 0, "Title", "Description");
	Appt apptBadMinLow = new Appt(0, -1, 1, 1, 0, "Title", "Description");
	Appt apptBadDayHigh = new Appt(0, 0, 30, 2, 0, "Title", "Description");
	Appt apptBadDayLow = new Appt(0, 0, 0, 1, 0, "Title", "Description");
	
	@Test 
	public void testGetValid()
	{
		boolean v = apptValidLow.getValid();
		assertEquals(v, true);
		v = apptValidHigh.getValid();
		assertEquals(v, true);
//		v = apptValidFeb.getValid();
//		assertEquals(v, true);
//		v = apptBadHourHigh.getValid();
//		assertEquals(v, false);
		v = apptBadHourLow.getValid();
		assertEquals(v, false);
		v = apptBadMinHigh.getValid();
		assertEquals(v, false);
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
		
		k = apptBadMinHigh.getStartMinute();
		assertEquals(k, 60);
		apptBadHourHigh.setStartMinute(30);
		k = apptBadHourHigh.getStartMinute();
		assertEquals(k, 30);
		
		k = apptBadDayHigh.getStartDay();
		assertEquals(k, 30);
		apptBadDayHigh.setStartDay(10);
		k = apptBadDayHigh.getStartDay();
		assertEquals(k, 10);

		k = apptValidLow.getStartYear();
		assertEquals(k, 0);
		apptBadHourHigh.setStartYear(2018);
		k = apptBadHourHigh.getStartYear();
		assertEquals(k, 2018);
		
		String s = apptValidLow.getTitle();
		assertEquals(s, "Title");
		apptValidLow.setTitle("New Title");
		s = apptValidLow.getTitle();
		assertEquals(s, "New Title");
		
		s = apptValidLow.getDescription();
		assertEquals(s, "Description");
		apptValidLow.setDescription("New Description");
		s = apptValidLow.getDescription();
		assertEquals(s, "New Description");
	}
	
	@Test
	public void testToString()
	{
		String s = apptValidHigh.toString();
		assertNotEquals("", s);
	}

	@Test
	public void testCompare()
	{
		int k = apptValidLow.compareTo(apptValidHigh);
		assertNotEquals(k, 0);
		k = apptValidHigh.compareTo(apptValidHigh);
		assertEquals(k, 0);
	}
	
}
