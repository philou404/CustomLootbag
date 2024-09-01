package net.supraerror.customlootbag.data.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.supraerror.customlootbag.CustomLootbag;
import net.supraerror.customlootbag.items.ModItems;
import net.supraerror.customlootbag.utils.NameUtility;


/**
 * The type Mod en lang provider.
 */
public final class ModEnLangProvider extends LanguageProvider {
    /**
     * Instantiates a new Mod en lang provider.
     *
     * @param output the output
     */
    public ModEnLangProvider(PackOutput output) {
        super(output, CustomLootbag.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        for (var lootbagEntry : ModItems.LOOTBAG_ITEMS.entrySet()) {
            addItem(lootbagEntry.getValue(), NameUtility.getReadableName(lootbagEntry.getKey()));

        }

    }

}
