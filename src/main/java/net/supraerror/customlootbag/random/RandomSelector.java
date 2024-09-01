package net.supraerror.customlootbag.random;

import java.util.Collection;


/**
 * The type Random selector.
 */
public final class RandomSelector {

    /**
     * Draw item t.
     *
     * @param <T>   the type parameter
     * @param items the items
     * @return the t
     */
    public static <T extends WeightedObject> T drawItem(Collection<T> items) {
        int totalWeight = items.stream().mapToInt(WeightedObject::getWeight).sum();
        int randomWeight = RandomGenerator.getRandomInt(totalWeight);
        int weightSum = 0;

        for (T item : items) {
            weightSum += item.getWeight();
            if (randomWeight < weightSum) {
                return item;
            }
        }
        return null;
    }


}
