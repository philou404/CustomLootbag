package net.supraerror.customlootbag.data.texture;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.supraerror.customlootbag.items.ModItems;
import net.supraerror.customlootbag.utils.NameUtility;

import static net.supraerror.customlootbag.CustomLootbag.MODID;

/**
 * The type Mod item state provider.
 */
public final class ModItemStateProvider extends ItemModelProvider {

    /**
     * Instantiates a new Mod item state provider.
     *
     * @param output             the output
     * @param existingFileHelper the existing file helper
     */
    public ModItemStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    public void registerModels() {

        for (var lootbagEntry : ModItems.LOOTBAG_ITEMS.entrySet()) {
            try {
                item(lootbagEntry.getValue().asItem());

            } catch (Exception e) {
                LOGGER.error("Failed to register lootbag item {} models", lootbagEntry.getKey(), e);
            }

        }

    }

    private void item(Item item) {
        String name = NameUtility.getItemName(item);
        getBuilder(name).parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", modLoc("item/" + name));
    }
}
