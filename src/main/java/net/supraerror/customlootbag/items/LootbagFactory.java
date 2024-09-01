package net.supraerror.customlootbag.items;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * The type Lootbag factory.
 */
public final class LootbagFactory {

    /**
     * Creates a Lootbag with default properties and color.
     *
     * @param lootObjects Collection of LootObject to be used.
     * @param name        The name of the Lootbag.
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Collection<LootObject> lootObjects, @NotNull String name) {
        return new Lootbag(new Item.Properties(), lootObjects, name, 0);
    }

    /**
     * Creates a Lootbag with default properties and color.
     *
     * @param lootObjects Collection of LootObject to be used.
     * @param name        The name of the Lootbag.
     * @param weight      the weight
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Collection<LootObject> lootObjects, @NotNull String name, int weight) {
        return new Lootbag(new Item.Properties(), lootObjects, name, weight);
    }

    /**
     * Creates a Lootbag with default properties and color, with specified max drop amount.
     *
     * @param lootObjects Collection of LootObject to be used.
     * @param maxDrop     The maximum number of item types to drop.
     * @param name        The name of the Lootbag.
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Collection<LootObject> lootObjects, int maxDrop, @NotNull String name) {
        return new Lootbag(new Item.Properties(), lootObjects, maxDrop, name, 0);
    }

    /**
     * Creates a Lootbag with default properties and color, with specified max drop amount.
     *
     * @param lootObjects Collection of LootObject to be used.
     * @param maxDrop     The maximum number of item types to drop.
     * @param name        The name of the Lootbag.
     * @param weight      the weight
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Collection<LootObject> lootObjects, int maxDrop, @NotNull String name, int weight) {
        return new Lootbag(new Item.Properties(), lootObjects, maxDrop, name, weight);
    }

    /**
     * Creates a Lootbag with specified properties, default max drop amount and color.
     *
     * @param properties  The properties of the Lootbag item.
     * @param lootObjects Collection of LootObject to be used.
     * @param name        The name of the Lootbag.
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Item.Properties properties, @NotNull Collection<LootObject> lootObjects, @NotNull String name) {
        return new Lootbag(properties, lootObjects, name, 0);
    }

    /**
     * Creates a Lootbag with specified properties, max drop amount, and color.
     *
     * @param properties  The properties of the Lootbag item.
     * @param lootObjects Collection of LootObject to be used.
     * @param maxDrop     The maximum number of item types to drop.
     * @param name        The name of the Lootbag.
     * @return a new Lootbag instance.
     */
    public static @NotNull Lootbag createLootbag(@NotNull Item.Properties properties, @NotNull Collection<LootObject> lootObjects, int maxDrop, @NotNull String name) {
        return new Lootbag(properties, lootObjects, maxDrop, name, 0);
    }
}
