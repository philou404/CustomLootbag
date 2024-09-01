package net.supraerror.customlootbag.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.supraerror.customlootbag.CustomLootbag;
import net.supraerror.customlootbag.data.lang.ModEnLangProvider;
import net.supraerror.customlootbag.data.texture.ModItemStateProvider;


/**
 * The type Mod data generators.
 */
public final class ModDataGenerators {


    /**
     * Gather data.
     *
     * @param event the event
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(true, new ModEnLangProvider(output));
            generator.addProvider(true, new ModItemStateProvider(output, existingFileHelper));
        } catch (RuntimeException e) {
            CustomLootbag.LOGGER.error("Failed to generate custom lootbag data", e);
        }

    }
}
