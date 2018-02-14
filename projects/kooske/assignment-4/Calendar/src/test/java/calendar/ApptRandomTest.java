package calendar;

import java.util.Calendar;
import java.util.Random;
import org.junit.Test;


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
     * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
     */
    private static int RandomSelectRecur(Random random) {
        int[] RecurArray = new int[]{Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return the value of the  appointments to recur
    }

    /**
     * Return a randomly selected appointments to recur forever or Never recur  !.
     */
    private static int RandomSelectRecurForeverNever(Random random) {
        // The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
        int[] RecurArray = new int[]{Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER};
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return appointments to recur forever or Never recur
    }

    /**
     * Generate Random Tests that tests Appt Class.
     */
    @Test
    public void randomTest() throws Throwable {

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
                int startHour = ValuesGenerator.RandInt(random);
                int startMinute = ValuesGenerator.RandInt(random);
                int startDay = ValuesGenerator.RandInt(random);
                int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                //int startMonth = ValuesGenerator.RandInt(random);
                int startYear = ValuesGenerator.RandInt(random);
                String title = "Birthday Party";
                String description = "This is my birthday party.";
                //Construct a new Appointment object with the initial data
                Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
                if (!appt.getValid()) {
                    iteration++;
                    continue;
                }
                for (int i = 0; i < NUM_TESTS; i++) {
                    String methodName = ApptRandomTest.RandomSelectMethod(random);
                    if (methodName.equals("isValid")) {
                        //by calling a setter we're called isValid()
                        //effectively calling isValid() 5 times per execution
                        appt.setStartHour(ValuesGenerator.RandInt(random));
                        appt.setStartMinute(ValuesGenerator.RandInt(random));
                        appt.setStartDay(ValuesGenerator.RandInt(random));
                        appt.setStartYear(ValuesGenerator.RandInt(random));
                        appt.setStartMonth(ValuesGenerator.getRandomIntBetween(random, 1, 11));
                    } else if (methodName.equals("setRecurrence")) {
                        int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
                        //1% change to get a null array
                        int[] recurDays;
                        if (ValuesGenerator.getBoolean((float) 0.01, random)) {
                            recurDays = null;
                        } else {
                            recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
                        }
                        int recur = ApptRandomTest.RandomSelectRecur(random);
                        int recurIncrement = ValuesGenerator.RandInt(random);
                        int recurNumber = ApptRandomTest.RandomSelectRecurForeverNever(random);
                        appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
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
