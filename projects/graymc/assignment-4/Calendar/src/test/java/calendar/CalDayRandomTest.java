package calendar;


import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TEST_TIMEOUT = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	
	public static Appt RandomAppt(Random random)
	{
		int startHour = ValuesGenerator.RandInt(random);
		int startMinute = ValuesGenerator.RandInt(random);
		int startDay = ValuesGenerator.RandInt(random);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
		int startYear = ValuesGenerator.RandInt(random);
		String title = ValuesGenerator.getString(random);
		String description = ValuesGenerator.getString(random);

		return new Appt(startHour, startMinute , startDay , startMonth , startYear , title, description);
	}

	public static GregorianCalendar RandomGC(Random random)
	{
		GregorianCalendar gc = new GregorianCalendar();

		int month = ValuesGenerator.getRandomIntBetween(random, 1, 12);
		int year = ValuesGenerator.getRandomIntBetween(random, 0, 3000);
		int maxDay;
		if (month == 2 && !CalendarUtil.IsLeapYear(year))
			maxDay = 28;
		else if (month == 2)
			maxDay = 29;
		else if (month == 4 || month == 6 || month == 9 || month == 11)
			maxDay = 30;
		else
			maxDay = 31;
		int day = ValuesGenerator.getRandomIntBetween(random, 1, maxDay);

		gc.set(year, month, day);
		return gc;
	}

	@Test
	public void randomTest() throws Throwable
	{
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Testing CalDay...");

		try {
			for (int iteration = 0; elapsed < TEST_TIMEOUT; iteration++)
			{
				Random random = new Random(System.currentTimeMillis());

				GregorianCalendar gc = RandomGC(random);
				CalDay calDay = new CalDay(gc);

				int maxAppts = ValuesGenerator.RandInt(random);
				for (int i = 0; i < maxAppts; i++)
				{
					Appt appt = RandomAppt(random);
					calDay.addAppt(appt);
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+ TEST_TIMEOUT);
			}
		} catch(NullPointerException e){
			System.out.println(e);
		}
	}
}
