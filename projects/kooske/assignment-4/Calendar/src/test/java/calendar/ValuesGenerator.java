package calendar;

import java.util.Random;

public class ValuesGenerator {
    private final static int MAX_VALUE = 10;
    //private final static final int MIN_VALUE=-10;
    private final static float SET_TO_NULL = 0.05f;

    public static int RandInt(Random random) {
        // get a random number between 0 (inclusive) and  MAX_VALUE=10 (exclusive)
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

    public static char getCharacter(Random random) {
        return BASE_CHARACTERS[random.nextInt(baseCharLength)];
    }

    public static String getString(Random random) {
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
}
