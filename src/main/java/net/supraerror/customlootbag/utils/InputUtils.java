package net.supraerror.customlootbag.utils;

import java.util.Collection;

/**
 * The type Input utils.
 */
public final class InputUtils {

    /**
     * Is positive boolean.
     *
     * @param number the number
     * @return the boolean
     */
    public static boolean isPositive(int number) {
        return number >= 0;
    }

    /**
     * Is in range boolean.
     *
     * @param min   the min
     * @param max   the max
     * @param value the value
     * @return the boolean
     */
    public static boolean isInRange(int min, int max, int value) {
        return value >= min && value <= max;
    }

    /**
     * Is in range boolean.
     *
     * @param min   the min
     * @param max   the max
     * @param value the value
     * @return the boolean
     */
    public static boolean isInRange(double min, double max, double value) {
        return value >= min && value <= max;
    }


    /**
     * In range int.
     *
     * @param min   the min
     * @param max   the max
     * @param value the value
     * @return the int
     * @throws IllegalArgumentException if not in range
     */
    public static int inRange(int min, int max, int value) {
        if (isInRange(min, max, value)) {
            return value;
        } else {
            throw new IllegalArgumentException("Value out of range min: " + min + " max: " + max + " value: " + value);
        }
    }

    /**
     * In range double.
     *
     * @param min   the min
     * @param max   the max
     * @param value the value
     * @return the double
     * @throws IllegalArgumentException if not in range
     */
    public static double inRange(double min, double max, double value) {
        if (isInRange(min, max, value)) {
            return value;
        } else {
            throw new IllegalArgumentException("Value out of range min: " + min + " max: " + max + " value: " + value);
        }
    }

    /**
     * Non null element in collection.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @return the collection
     * @throws NullPointerException if a elem is null
     */
    public static <T> Collection<T> nonNullCollection(Collection<T> collection) {
        for (var o : collection) {
            if (o == null) {
                throw new NullPointerException("Collection contains null element");
            }
        }
        return collection;
    }

}
