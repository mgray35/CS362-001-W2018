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
    //private static final long TestTimeout = 60 * 500; /* Timeout at 30 seconds */
    private static final long TestTimeout = 1;
    private static final int NUM_TESTS = 100; /* possibility of invalid appts is high so */

    //redundant but still here for consistency with random testing
    private static String RandomSelectMethod(Random random) {
        String[] methodArray = new String[]{"addAppt"};// The list of the of methods to be tested in the Appt class
        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n]; // return the method name
    }

    /**
     * Generate Random Tests that tests CalDay Class.
     */
    @Test
    public void randomTest() {
        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Start testing...");

        try {
            int iteration = 0;
            while (elapsed < TestTimeout) {
                elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                if ((iteration % 10000) == 0 && iteration != 0) {
                    System.out.println("elapsed time of " + TestTimeout / 1000 + " seconds: " + elapsed / 1000.00);
                }
                long randomSeed = System.currentTimeMillis(); //10
                //System.out.println(" Seed:"+randomSeed );
                Random random = new Random(randomSeed);
                int month = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int day = ValuesGenerator.RandInt(random, false);
                int year = ValuesGenerator.RandInt(random, false);
                CalDay calDay = new CalDay(new GregorianCalendar(year, month, day));
                int numAddedAppts = 0;
                for (int i = 0; i < NUM_TESTS; i++) {
                    String method = RandomSelectMethod(random);
                    if (method.equals("addAppt")) {
                        //add a randomized number of appts to calday
                        Appt appt = ValuesGenerator.generateRandomAppt(random, true, false);
                        //leave a small chance to add an invalid appt
                        calDay.addAppt(appt);
                        if (appt.getValid()) {
                            numAddedAppts++;
                        }
                        //check number of appts added every valid loop
                        assertEquals(numAddedAppts, calDay.getSizeAppts());
                    }
                }
                //check total number of appts added before next iteration
                assertEquals(numAddedAppts, calDay.getSizeAppts());
                iteration++;
            }
        } catch (NullPointerException e) {
            System.out.println("Caught a null pointer exception");
        }
    }
}
