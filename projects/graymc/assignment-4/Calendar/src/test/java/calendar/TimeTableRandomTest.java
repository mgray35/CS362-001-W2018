package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;
import sun.awt.image.ImageWatched;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TEST_TIMEOUT = 60 * 500 * 1; /* Timeout at 30 seconds */

	public static String RandomMethod(Random random){
		String[] methodArray = new String[] {"getApptRange", "deleteAppt"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

		return methodArray[n] ; // return the method name
	}

	public static LinkedList<Appt> RandomApptList(Random random)
	{
		LinkedList<Appt> appts = new LinkedList<Appt>();
		int numAppts = ValuesGenerator.RandInt(random);
		for (int i = 0; i < numAppts; i++)
		{
			appts.add(CalDayRandomTest.RandomAppt(random));
		}

		return appts;
	}
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	 @Test
	 public void randomTest()  throws Throwable
	 {
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 System.out.println("Testing TimeTable...");

		 try {
		 	Random random = new Random(System.currentTimeMillis());
		 	TimeTable tt = new TimeTable();

		 	for (int iteration = 0; elapsed < TEST_TIMEOUT; iteration++)
		 	{
				String methodName = RandomMethod(random);
				if (methodName.equals("getApptRange")){
					LinkedList<Appt> appts = RandomApptList(random);
					GregorianCalendar gcFirst = CalDayRandomTest.RandomGC(random);
					GregorianCalendar gcLast = CalDayRandomTest.RandomGC(random);

					try {
						tt.getApptRange(appts, gcFirst, gcLast);
					} catch (DateOutOfRangeException e) {
						continue;
					}
				}
				else if (methodName.equals("deleteAppt")) {
					LinkedList<Appt> appts = null;
					Appt apptToRemove = null;

					if (!ValuesGenerator.getBoolean(ValuesGenerator.SET_TO_NULL, random))
						appts = RandomApptList(random);

					int indexToRemove;
					if (appts != null) {
						indexToRemove = ValuesGenerator.getRandomIntBetween(random, 0, appts.size() - 1);
						if (appts.size() > 0 && !ValuesGenerator.getBoolean(ValuesGenerator.SET_TO_NULL, random))
							apptToRemove = appts.get(indexToRemove);
					}

					try {
						tt.deleteAppt(appts, apptToRemove);
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e);
					}
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				System.out.println("elapsed time: "+ elapsed + " of " + TEST_TIMEOUT);
		 	}
		 } catch(NullPointerException e){
			 System.out.println(e);
		 }
	 }
}
