package net.supraerror.customlootbag.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.supraerror.customlootbag.config.LootbagContentAndTypeConfig;

import java.util.*;

import static net.supraerror.customlootbag.CustomLootbag.MODID;

/**
 * The type Mod items.
 */
public final class ModItems {
    /**
     * The constant ITEMS.
     */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    /**
     * The constant LOOTBAG_ITEMS.
     */
    public static final Map<String, DeferredItem<Item>> LOOTBAG_ITEMS = new HashMap<>();
    /**
     * The constant lootbagsNameOrder.
     */
    public static final List<String> lootbagsNameOrder = new ArrayList<>();

    /**
     * Register lootbags.
     */
    public static void registerLootbags() {
        LootbagContentAndTypeConfig config = LootbagContentAndTypeConfig.loadConfig();
        if (config != null) {
            for (LootbagContentAndTypeConfig.LootbagDefinition lootbagDef : config.getLootbags()) {
                String id = lootbagDef.getName();
                Collection<LootObject> lootObjects = lootbagDef.createLootObjects();
                int maxItems = lootbagDef.getMaximumTypeItemsDrop();
                int weight = lootbagDef.getWeight();
                LOOTBAG_ITEMS.put(id, addLootbag(id, lootObjects, maxItems, weight));
                lootbagsNameOrder.add(id);
            }
        }
    }

    /**
     * Add lootbag deferred item.
     *
     * @param id      the id
     * @param loot    the loot
     * @param maximum the maximum
     * @param weight  the weight
     * @return the deferred item
     */
    public static DeferredItem<Item> addLootbag(String id, Collection<LootObject> loot, int maximum, int weight) {
        return ITEMS.register(id, () ->
                LootbagFactory.createLootbag(loot, maximum, id, weight)
        );
    }

    /**
     * Add lootbag deferred item.
     *
     * @param id      the id
     * @param loot    the loot
     * @param maximum the maximum
     * @return the deferred item
     */
    public static DeferredItem<Item> addLootbag(String id, Collection<LootObject> loot, int maximum) {
        return ITEMS.register(id, () ->
                LootbagFactory.createLootbag(loot, maximum, id)
        );
    }

    /**
     * Add lootbag deferred item.
     *
     * @param id   the id
     * @param loot the loot
     * @return the deferred item
     */
    public static DeferredItem<Item> addLootbag(String id, Collection<LootObject> loot) {
        return ITEMS.register(id, () ->
                LootbagFactory.createLootbag(loot, id)
        );
    }

    /**
     * Init.
     */
    public static void init() {
        registerLootbags();
    }

    /**
     * Gets loot bag items.
     *
     * @return the loot bag items
     */
    public static Collection<Lootbag> getLootBagItems() {
        Collection<Lootbag> lootbags = new ArrayList<>();
        for (var item : LOOTBAG_ITEMS.values()) {
            lootbags.add((Lootbag) item.get());
        }
        return lootbags;
    }
}