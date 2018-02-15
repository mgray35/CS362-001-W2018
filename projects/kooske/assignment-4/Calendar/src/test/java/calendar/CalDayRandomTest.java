package calendar;

import org.junit.Test;
import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Random Test Generator  for CalDay class.
 */
public class CalDayRandomTest {
    private static final long TestTimeout = 60 * 500; /* Timeout at 30 seconds */
    private static final int NUM_TESTS = 100;

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
                if ((iteration % 700000) == 0 && iteration != 0) {
                    System.out.println("elapsed time of " + TestTimeout/1000 + " seconds: " + elapsed/1000.00);
                }
                long randomSeed = System.currentTimeMillis(); //10
                //System.out.println(" Seed:"+randomSeed );
                Random random = new Random(randomSeed);
                Appt appt = ValuesGenerator.generateRandomAppt(random, true);







                iteration++;
            }
        } catch (NullPointerException e) {
            System.out.println("");
        }
    }
}
