package net.supraerror.customlootbag.random;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * The type Random generator.
 */
public class RandomGenerator {

    private static final Random RANDOM;
    private static final Queue<Integer> NUMBERS_INT;
    private static final Queue<Double> NUMBERS_DOUBLE;


    static {
        RANDOM = new Random();
        NUMBERS_INT = new LinkedList<>();
        NUMBERS_DOUBLE = new LinkedList<>();
    }

    /**
     * Clear numbers.
     */
    public static void clearNumbers() {
        clearNumbersDouble();
        clearNumbersInt();
    }

    /**
     * Clear numbers int.
     */
    public static void clearNumbersInt() {
        NUMBERS_INT.clear();
    }

    /**
     * Clear numbers double.
     */
    public static void clearNumbersDouble() {
        NUMBERS_DOUBLE.clear();
    }

    /**
     * Add futur numbers.
     *
     * @param futurInt the futur int
     */
    public static void addFuturNumbers(int... futurInt) {
        for (int i : futurInt) {
            NUMBERS_INT.add(i);
        }
    }

    /**
     * Add futur numbers.
     *
     * @param futurDouble the futur double
     */
    public static void addFuturNumbers(double... futurDouble) {
        for (double i : futurDouble) {
            NUMBERS_DOUBLE.add(i);
        }
    }

    /**
     * Gets random int.
     *
     * @return the random int between 0 and {@code Integer.MAX_VALUE}
     */
    public static int getRandomInt() {
        return getRandomInt(Integer.MAX_VALUE);
    }

    /**
     * Gets random int.
     *
     * @param max the max
     * @return the random int
     */
    public static int getRandomInt(int max) {
        return getRandomInt(0, max);
    }

    /**
     * Gets random int.
     *
     * @param min the min
     * @param max the max
     * @return the random int
     */
    public static int getRandomInt(int min, int max) {

        Integer randomNumber = NUMBERS_INT.poll();
        if (randomNumber == null) {
            randomNumber = RANDOM.nextInt((max - min) + 1) + min;
        }
        return randomNumber;
    }

    /**
     * Gets random double.
     *
     * @return the random double
     */
    public static double getRandomDouble() {
        return getRandomDouble(0.0, 1.0);
    }

    /**
     * Gets random double.
     *
     * @param max the max
     * @return the random double
     */
    public static double getRandomDouble(double max) {
        return getRandomDouble(0.0, max);
    }

    /**
     * Gets random double.
     *
     * @param min the min
     * @param max the max
     * @return the random double
     */
    public static double getRandomDouble(double min, double max) {
        Double randomNumber = NUMBERS_DOUBLE.poll();
        if (randomNumber == null) {
            randomNumber = RANDOM.nextDouble() * (max - min) + min;
        }
        return randomNumber;
    }


}
