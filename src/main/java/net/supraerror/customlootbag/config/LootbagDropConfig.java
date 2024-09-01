package net.supraerror.customlootbag.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.ParsingException;
import net.supraerror.customlootbag.CustomLootbag;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LootbagDropConfig {
    private static final String CONFIG_PATH = "config/custom_lootbag_mob_drop.toml";
    private static final List<String> ALL_HOSTILE_MOBS = Collections.unmodifiableList(List.of("minecraft:blaze",
            "minecraft:cave_spider",
            "minecraft:creeper",
            "minecraft:drowned",
            "minecraft:elder_guardian",
            "minecraft:ender_dragon",
            "minecraft:enderman",
            "minecraft:endermite",
            "minecraft:evoker",
            "minecraft:ghast",
            "minecraft:guardian",
            "minecraft:hoglin",
            "minecraft:husk",
            "minecraft:magma_cube",
            "minecraft:phantom",
            "minecraft:piglin",
            "minecraft:piglin_brute",
            "minecraft:pillager",
            "minecraft:ravager",
            "minecraft:shulker",
            "minecraft:silverfish",
            "minecraft:skeleton",
            "minecraft:slime",
            "minecraft:spider",
            "minecraft:stray",
            "minecraft:vex",
            "minecraft:vindicator",
            "minecraft:warden",
            "minecraft:witch",
            "minecraft:wither",
            "minecraft:wither_skeleton",
            "minecraft:zoglin",
            "minecraft:zombie",
            "minecraft:zombie_villager",
            "minecraft:zombified_piglin"));
    private static CommentedConfig config;

    public static void loadConfig() {
        Path configFilePath = Paths.get(CONFIG_PATH);
        createDefaultConfig(configFilePath);
        try {
            CommentedFileConfig fileConfig = CommentedFileConfig.builder(configFilePath).autoreload().autosave().build();
            fileConfig.load();
            config = fileConfig;

        } catch (ParsingException e) {
            System.err.println("Erreur de parsing du fichier de configuration : " + e.getMessage());
        }
    }

    private static void createDefaultConfig(Path configFilePath) {

        File configFile = new File(CONFIG_PATH);

        if (!configFile.exists()) {
            // Crée le fichier de configuration avec des valeurs par défaut
            CommentedConfig defaultConfig = CommentedConfig.inMemory();
            defaultConfig.setComment("drop", "Configuration");
            defaultConfig.setComment("drop.list_mob_who_drop_lootbag", "list of every mob who can drop lootbag");
            defaultConfig.set("drop.mob_who_drop_lootbag", ALL_HOSTILE_MOBS);
            defaultConfig.set("drop.chance_to_drop", 0.2);

            configFile.getParentFile().mkdirs();
            CommentedFileConfig commentedFileConfig = CommentedFileConfig.builder(configFile).sync().autosave().build();
            commentedFileConfig.load();
            commentedFileConfig.putAll(defaultConfig);
            commentedFileConfig.save();

            CustomLootbag.LOGGER.info("Le fichier de configuration par défaut a été créé : " + CONFIG_PATH);
        }
    }

    public static Set<String> getStringSet(String key) {
        return new HashSet<>(config.getOrElse(key, List.of()));
    }

    public static double getDouble(String key) {
        return config.getOrElse(key, 0d);
    }

}
