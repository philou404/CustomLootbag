package net.supraerror.customlootbag.items;

import net.minecraft.world.item.Item;
import net.supraerror.customlootbag.CustomLootbag;
import net.supraerror.customlootbag.random.WeightedObject;
import net.supraerror.customlootbag.utils.InputUtils;

import java.util.Objects;

/**
 * The type Loot object.
 */
public final class LootObject implements WeightedObject {

    /**
     * The constant MAX_SIZE_STACK.
     */
    public static final int MAX_SIZE_STACK = 64;
    /**
     * The constant MAX_WEIGHT.
     */
    public static final int MAX_WEIGHT = 1000;
    private final Item item;
    private final int quantity;
    private final int weight;

    private LootObject(Item item, int quantity, int weight) {
        this.item = Objects.requireNonNull(item, "Item cannot be null");
        try {
            this.quantity = InputUtils.inRange(1, MAX_SIZE_STACK, quantity);
            this.weight = InputUtils.inRange(1, MAX_WEIGHT, weight);
        } catch (IllegalArgumentException e) {
            String error = String.format("""
                    Look at the configuration file,
                    the weight must be between 1 and %s\s
                    quantity must be between 1 and %s\s""", MAX_WEIGHT, MAX_SIZE_STACK);
            CustomLootbag.LOGGER.error(error, e);

            throw new IllegalArgumentException(error);
        }
    }

    /**
     * Create loot object loot object.
     *
     * @param item     the item
     * @param quantity the quantity
     * @param weight   the weight
     * @return the loot object
     */
    public static LootObject createLootObject(Item item, int quantity, int weight) {
        return new LootObject(item, quantity, weight);
    }

    @Override
    public String toString() {
        String sb = "LootObject{" + "item=" + item +
                ", quantity=" + quantity +
                ", weight=" + weight +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LootObject that)) return false;
        return this.quantity == that.quantity && this.weight == that.weight && Objects.equals(this.item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem(), getQuantity(), getWeight());
    }

    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public Item getItem() {
        return item;
    }
}
