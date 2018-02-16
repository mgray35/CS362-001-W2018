package calendar;

import java.util.Random;

public class ValuesGenerator {
    private final static int MAX_VALUE = 100;
    //private final static int MIN_VALUE = -10;
    private final static float SET_TO_NULL = 0.05f;

    public static int RandInt(Random random, boolean neg) {
        if (neg) {
            return (random.nextInt(MAX_VALUE) * (random.nextBoolean() ? -1 : 1));
        }
        return random.nextInt(MAX_VALUE);
    }

    /**
     * This generates a coin flip with a probability <tt>probability</tt> of
     * returning true, else returning false.
     */
    public static boolean getBoolean(float probability, Random random) {
        return random.nextFloat() < probability;
    }

    private static final char[] BASE_CHARACTERS = {
            ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static final int baseCharLength = BASE_CHARACTERS.length;

    private static char getCharacter(Random random) {
        return BASE_CHARACTERS[random.nextInt(baseCharLength)];
    }

    private static String getString(Random random) {
        if (ValuesGenerator.getBoolean(SET_TO_NULL, random)) {
            return null;
        }
        final int randNum;
        if (ValuesGenerator.getBoolean(.75f, random)) randNum = random.nextInt(10);
        else if (ValuesGenerator.getBoolean(.75f, random)) randNum = random.nextInt(50);
        else randNum = random.nextInt(100);
        char str[] = new char[randNum];
        for (int i = 0; i < randNum; i++ ) {
            str[i] = getCharacter(random);
        }
        return new java.lang.String(str);
    }

    /**
     * @param random, min , max
     * @return get a random number between MIN_VALUE (inclusive) and  MAX_VALUE (inclusive)
     */
    public static int getRandomIntBetween(Random random, int min, int max) {
        //adding 1 to the bound makes this have an inclusive sup
        return random.nextInt((max - min) + 1) + min;
    }

    public static int[] generateRandomArray(Random random, int n) {
        int[] tempArray = new int[n];
        for (int i = 0; i < n; i++) {
            tempArray[i] = getRandomIntBetween(random, 0, MAX_VALUE);
        }
        return tempArray;
    }

    /**
     * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
     */
    public static int RandomSelectRecur(Random random) {
        int[] RecurArray = new int[]{Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return the value of the  appointments to recur
    }

    /**
     * Return a randomly selected appointments to recur forever or Never recur  !.
     */
    public static int RandomSelectRecurForeverNever(Random random) {
        // The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
        int[] RecurArray = new int[]{Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER};
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n]; // return appointments to recur forever or Never recur
    }


    /**
     * Returns a randomly generated appt; not guaranteed to be valid
     * @param random seeded random object
     * @param recur boolean - specifies if recur should also be randomly generated
     * @param neg boolean - specifies if negative values should be used, if false greatly reduce chance of invalid appt
     * @return randomized appt object
     */
    public static Appt generateRandomAppt(Random random, boolean recur, boolean neg) {
        int startHour = ValuesGenerator.RandInt(random, neg);
        int startMinute = ValuesGenerator.RandInt(random, neg);
        int startDay = ValuesGenerator.RandInt(random, neg);
        int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
        int startYear = ValuesGenerator.RandInt(random, neg);
        String title = ValuesGenerator.getString(random);
        String description = ValuesGenerator.getString(random);
        Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
        //set recur if the boolean passed it true
        if (recur) {
            int recurBy = ValuesGenerator.RandomSelectRecur(random);
            //recurIncrement is used no where in the program... LOL
            int recurIncrement = ValuesGenerator.RandInt(random, neg);
            int recurNumber = ValuesGenerator.RandomSelectRecurForeverNever(random);
            int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
            int[] recurDays;
            if (ValuesGenerator.getBoolean((float) 0.01, random)) {
                recurDays = null;
            } else {
                recurDays = ValuesGenerator.generateRandomArray(random, sizeArray);
            }
            appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);
        }
        return appt;
    }
}
