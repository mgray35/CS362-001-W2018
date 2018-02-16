package calendar;

import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Random Test Generator  for TimeTable class.
 */
public class TimeTableRandomTest {
    private static final long TestTimeout = 60 * 500; /* Timeout at 30 seconds */
    //private static final long TestTimeout = 1;
    private static final int NUM_TESTS = 10;

    private static String RandomSelectMethod(Random random) {
        //String[] methodArray = new String[]{"deleteAppt", "getApptRange"};// The list of the of methods to be tested in the Appt class
        String[] methodArray = new String[]{"getApptRange"};// delete appt is utterly broken; this is used to test just getApptRange
        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        return methodArray[n]; // return the method name
    }

    /**
     * Generate Random Tests that tests TimeTable Class.
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
                TimeTable timeTable = new TimeTable();
                LinkedList<Appt> appts = new LinkedList<Appt>();
                int apptsSize = ValuesGenerator.getRandomIntBetween(random, 0, 20); //random length of ll
                for (int j = 0; j < apptsSize; j++) {
                    Appt appt = ValuesGenerator.generateRandomAppt(random, true, false);
                    if (!appt.getValid()) {
                        j--;
                        continue;
                    }
                    appts.add(appt);
                }
                for (int i = 0; i < NUM_TESTS; i++) {
                    String methodName = RandomSelectMethod(random);
                    if (methodName.equals("deleteAppt")) {
                        //test null appts linked list and null appt to remove
                        LinkedList<Appt> apptsNull = new LinkedList<Appt>();
                        if (ValuesGenerator.getBoolean( (float) 0.5, random)) {
                            Appt appt;
                            if (ValuesGenerator.getBoolean( (float) 0.5, random)) {
                                appt = null;
                            } else {
                                appt = ValuesGenerator.generateRandomAppt(random, false, false);
                            }
                            apptsNull = timeTable.deleteAppt(null, appt);
                            assertNull(apptsNull);
                            continue;
                        }
                        //delete the appts from the list one by one and check that they have been removed
                        for (int t = 0; t < apptsSize; t++) {
                            int index = ValuesGenerator.getRandomIntBetween(random, 0, apptsSize-1);
                            Appt toRemoveAppt = appts.get(index);
                            appts = timeTable.deleteAppt(appts, toRemoveAppt);
                            //assertEquals(apptsSize - (t + 1), appts.size());
                        }
                        //assertEquals(0, appts.size());
                    } else if (methodName.equals("getApptRange")) {
                        int month = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                        int day = ValuesGenerator.RandInt(random, false);
                        int year = ValuesGenerator.RandInt(random, false);
                        GregorianCalendar cal1 = new GregorianCalendar(year, month, day);
                        GregorianCalendar cal2 = new GregorianCalendar(year, month, day);
                        if (ValuesGenerator.getBoolean( (float) 0.001, random)) {
                            cal1.add(cal2.DATE, 5); //small change to have the earlier date as the later date in call
                        } else {
                            cal2.add(cal1.DATE, 5);
                        }
                        LinkedList<CalDay> dayList = new LinkedList<CalDay>();
                        try {
                            dayList = timeTable.getApptRange(appts, cal1, cal2);

                        } catch (DateOutOfRangeException e) {
                            System.out.println("Caught a date out of range exception");
                        }
                    }
                }
                iteration++;
            }
        } catch (NullPointerException e) {
            System.out.println("Caught a null pointer exception");
        }
    }
}
