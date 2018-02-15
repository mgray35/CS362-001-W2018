package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {
    private static final long TestTimeout = 60 * 500; /* Timeout at 30 seconds */
    private static final int NUM_TESTS = 100;

    /**
     * Return a randomly selected method to be tests !.
     */
    private static String RandomSelectMethod(Random random) {
        String[] methodArray = new String[]{"isValid", "setRecurrence", };// The list of the of methods to be tested in the Appt class
        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n]; // return the method name
    }

    /**
     * Generate Random Tests that tests Appt Class.
     */
    @Test
    public void randomTest() {

        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
        System.out.println("Start testing...");

        try {
            int iteration  = 0;
            while(elapsed < TestTimeout) {
                //elapsed time should be check at the start of the loop in order to
                //avoid skipped execution from invalid appts
                elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                if ((iteration % 700000) == 0 && iteration != 0) {
                    System.out.println("elapsed time of " + TestTimeout/1000 + " seconds: " + elapsed/1000.00);
                }
                long randomSeed = System.currentTimeMillis(); //10
                //System.out.println(" Seed:"+randomSeed );
                Random random = new Random(randomSeed);
                //Construct a new Appointment object with the initial data
                for (int i = 0; i < NUM_TESTS; i++) {
                    String methodName = ApptRandomTest.RandomSelectMethod(random);
                    if (methodName.equals("isValid")) {
                        Appt appt = ValuesGenerator.generateRandomAppt(random, false);
                        if (!appt.getValid()) {
                            assertFalse(appt.getValid()); //redundant but makes it so every appt instance will be asserted
                            iteration++;
                            continue;
                        }
                        assertTrue(appt.getValid());
                    } else if (methodName.equals("setRecurrence")) {
                        Appt appt = ValuesGenerator.generateRandomAppt(random, false);
                        int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
                        //1% chance to get a null array
                        int[] recurDays;
                        if (ValuesGenerator.getBoolean((float) 0.01, random)) {
                            recurDays = null;
                        } else {
                            recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
                        }
                        int recur = ValuesGenerator.RandomSelectRecur(random);
                        int recurIncrement = ValuesGenerator.RandInt(random);
                        int recurNumber = ValuesGenerator.RandomSelectRecurForeverNever(random);
                        appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
                        //assertions
                        assertEquals(recurIncrement, appt.getRecurIncrement());
                        assertEquals(recurNumber, appt.getRecurNumber());
                        assertEquals(recur, appt.getRecurBy());
                        if (recurDays == null) {
                            assertTrue(Arrays.equals(new int[0], appt.getRecurDays()));
                        } else {
                            assertTrue(Arrays.equals(recurDays, appt.getRecurDays()));
                        }
                    }
                }
                iteration++;
            }
        } catch (NullPointerException e) {
            System.out.println("Caught a null pointer exception");
        }
        System.out.println("Done testing...");
    }
}
