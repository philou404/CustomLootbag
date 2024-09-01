package net.supraerror.customlootbag.config;


import net.minecraft.world.item.Item;
import net.supraerror.customlootbag.items.LootObject;
import net.supraerror.customlootbag.utils.NameUtility;

/**
 * The type Loot item definition.
 */
public final class LootItemDefinition {
    private final String id;
    private final int quantity;
    private final int weight;


    /**
     * Instantiates a new Loot item definition.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param weight   the weight
     */
    public LootItemDefinition(String id, int quantity, int weight) {
        this.id = id;
        this.quantity = quantity;
        this.weight = weight;
    }


    /**
     * Create loot object loot object.
     *
     * @return the loot object
     */
    public LootObject createLootObject() {
        Item item = NameUtility.getItemByID(id);
        if (item == null) {
            throw new IllegalStateException("Item not found: " + id);
        }
        return LootObject.createLootObject(item, quantity, weight);
    }

    @Override
    public String toString() {
        return "LootItemDefinition{" +
                "id='" + id + '\'' +
                ", maxQuantity=" + quantity +
                ", weight=" + weight +
                '}';
    }
}