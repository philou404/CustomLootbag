package net.supraerror.customlootbag.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Objects;

import static net.supraerror.customlootbag.CustomLootbag.MODID;


/**
 * The type Name utility.
 */
public final class NameUtility {


    /**
     * Gets item name.
     *
     * @param item the item
     * @return the item name
     */
    public static String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");

    }

    /**
     * Gets item by name.
     *
     * @param modid the modid
     * @param name  the name
     * @return the item by name
     */
    public static Item getItemByName(String modid, String name) {

        ResourceLocation location = ResourceLocation.parse(modid + ":" + name);
        return Objects.requireNonNull(BuiltInRegistries.ITEM.get(location), "ERROR : " + modid + ":" + name + "don't found");

    }

    /**
     * Gets item by id.
     *
     * @param id the id
     * @return the item by id
     */
    public static Item getItemByID(String id) {

        ResourceLocation location = ResourceLocation.parse(id);
        return Objects.requireNonNull(BuiltInRegistries.ITEM.get(location), "ERROR : " + id + "don't found");

    }


    /**
     * Gets item by name.
     *
     * @param name the name
     * @return the item by name
     */
    public static Item getItemByName(String name) {

        return getItemByName(MODID, name);

    }

    /**
     * Convertit un nom lisible en un nom de registre valide pour Minecraft.
     *
     * @param readableName Le nom lisible à convertir
     * @return Un nom de registre valide
     */
    public static String getRegistryName(String readableName) {
        // Convertir en minuscules
        String lowercaseName = readableName.toLowerCase();

        // Remplacer les espaces par des underscores
        String underscoreName = lowercaseName.replace(' ', '_');

        // Supprimer tous les caractères non alphanumériques (sauf underscore)
        return underscoreName.replaceAll("[^a-z0-9_]", "");
    }

    /**
     * Convertit un nom de registre en un nom lisible.
     *
     * @param registryName Le nom de registre à convertir
     * @return Un nom lisible
     */
    public static String getReadableName(String registryName) {
        // Remplacer les underscores par des espaces
        String spacedName = registryName.replace('_', ' ');

        // Mettre en majuscule la première lettre de chaque mot
        String[] words = spacedName.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }
}
