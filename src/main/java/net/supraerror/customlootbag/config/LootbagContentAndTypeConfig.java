package net.supraerror.customlootbag.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.supraerror.customlootbag.CustomLootbag;
import net.supraerror.customlootbag.items.LootObject;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The type Lootbag config.
 */
public final class LootbagContentAndTypeConfig {

    /**
     * The constant CONFIG_FILE_NAME.
     */
    public static final String CONFIG_FILE_NAME = "config/custom_lootbag_config.json";


    private List<LootbagDefinition> lootbags;

    /**
     * Load config lootbag config.
     *
     * @return the lootbag config
     */
    public static LootbagContentAndTypeConfig loadConfig() {
        Path path = Paths.get(CONFIG_FILE_NAME);
        if (!Files.exists(path)) {
            createDefaultConfig(path);
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(reader, LootbagContentAndTypeConfig.class);
        } catch (Exception e) {
            CustomLootbag.LOGGER.error("Error while loading config", e);
            return null;
        }
    }

    private static void createDefaultConfig(Path path) {
        LootbagContentAndTypeConfig defaultConfig = new LootbagContentAndTypeConfig();
        defaultConfig.lootbags = List.of(
                DefaultLootbag.createCommonLootbag("common_lootbag", 3, 50),
                DefaultLootbag.createRareLootbag("rare_lootbag", 3, 30),
                DefaultLootbag.createEpicLootbag("epic_lootbag", 3, 10),
                DefaultLootbag.createLegendaryLootbag("legendary_lootbag", 2, 5)
        );

        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(defaultConfig, writer);
            }
        } catch (IOException e) {
            CustomLootbag.LOGGER.error("Error while creating default config", e);
        }
    }


    /**
     * Gets lootbags.
     *
     * @return the lootbags
     */
    public List<LootbagDefinition> getLootbags() {
        return Objects.requireNonNull(lootbags, "The configuration file has an issue, delete it if you don't find the issues in the config file\nWhen the file is deleted, the default config is created");
    }


    /**
     * The type Lootbag definition.
     */
    public static class LootbagDefinition {
        private String name;
        private int maximumTypeItemsDrop;
        private int weight;
        private Set<LootItemDefinition> items;


        /**
         * Gets weight.
         *
         * @return the weight
         */
        public int getWeight() {
            return weight;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets maximum type items drop.
         *
         * @return the maximum type items drop
         */
        public int getMaximumTypeItemsDrop() {
            return maximumTypeItemsDrop;
        }

        /**
         * Create loot objects collection.
         *
         * @return the collection
         */
        public Collection<LootObject> createLootObjects() {
            Set<LootObject> lootObjects = new HashSet<>();
            for (LootItemDefinition item : items) {
                lootObjects.add(item.createLootObject());
            }
            return lootObjects;
        }
    }

    /**
     * The type Default lootbag.
     */
    static class DefaultLootbag {
        private static LootItemDefinition createDefaultLootItem(String id, int quantity, int weight) {
            return new LootItemDefinition(id, quantity, weight);
        }

        /**
         * Create common lootbag lootbag definition.
         *
         * @param name     the name
         * @param maxItems the max items
         * @param weight   the weight
         * @return the lootbag definition
         */
        public static LootbagDefinition createCommonLootbag(String name, int maxItems, int weight) {
            LootbagDefinition lootbag = new LootbagDefinition();
            lootbag.name = name;
            lootbag.maximumTypeItemsDrop = maxItems;
            lootbag.weight = weight;
            lootbag.items = Set.of(
                    createDefaultLootItem("minecraft:iron_ingot", 1, 15),
                    createDefaultLootItem("minecraft:iron_block", 1, 2),
                    createDefaultLootItem("minecraft:gold_ingot", 1, 5)
            );
            return lootbag;
        }

        /**
         * Create rare lootbag lootbag definition.
         *
         * @param name     the name
         * @param maxItems the max items
         * @param weight   the weight
         * @return the lootbag definition
         */
        public static LootbagDefinition createRareLootbag(String name, int maxItems, int weight) {
            LootbagDefinition lootbag = new LootbagDefinition();
            lootbag.name = name;
            lootbag.maximumTypeItemsDrop = maxItems;
            lootbag.weight = weight;
            lootbag.items = Set.of(
                    createDefaultLootItem("minecraft:diamond", 1, 1),
                    createDefaultLootItem("minecraft:emerald", 1, 1),
                    createDefaultLootItem("minecraft:gold_ingot", 2, 4),
                    createDefaultLootItem("minecraft:iron_ingot", 3, 10)
            );
            return lootbag;
        }

        private static LootbagDefinition createEpicLootbag(String name, int maxItems, int weight) {
            LootbagDefinition lootbag = new LootbagDefinition();
            lootbag.name = name;
            lootbag.maximumTypeItemsDrop = maxItems;
            lootbag.weight = weight;
            lootbag.items = Set.of(
                    createDefaultLootItem("minecraft:diamond", 2, 3),
                    createDefaultLootItem("minecraft:netherite_scrap", 1, 2),
                    createDefaultLootItem("minecraft:emerald", 3, 5),
                    createDefaultLootItem("minecraft:golden_apple", 1, 2),
                    createDefaultLootItem("minecraft:gold_ingot", 5, 10),
                    createDefaultLootItem("minecraft:iron_ingot", 7, 15)
            );
            return lootbag;
        }

        private static LootbagDefinition createLegendaryLootbag(String name, int maxItems, int weight) {
            LootbagDefinition lootbag = new LootbagDefinition();
            lootbag.name = name;
            lootbag.maximumTypeItemsDrop = maxItems;
            lootbag.weight = weight;
            lootbag.items = Set.of(
                    createDefaultLootItem("minecraft:netherite_scrap", 2, 3),
                    createDefaultLootItem("minecraft:elytra", 1, 1),
                    createDefaultLootItem("minecraft:diamond_block", 1, 1),
                    createDefaultLootItem("minecraft:emerald_block", 1, 2),
                    createDefaultLootItem("minecraft:gold_block", 2, 3),
                    createDefaultLootItem("minecraft:enchanted_golden_apple", 1, 3),
                    createDefaultLootItem("minecraft:golden_apple", 3, 4),
                    createDefaultLootItem("minecraft:totem_of_undying", 1, 1),
                    createDefaultLootItem("minecraft:emerald", 3, 10)
            );
            return lootbag;
        }


    }


}