package net.supraerror.customlootbag;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.supraerror.customlootbag.config.LootbagDropConfig;
import net.supraerror.customlootbag.data.ModDataGenerators;
import net.supraerror.customlootbag.events.LootbagDropHandler;
import net.supraerror.customlootbag.items.ModItems;
import net.supraerror.customlootbag.ui.ModTabs;
import org.slf4j.Logger;


@Mod(CustomLootbag.MODID)
public class CustomLootbag {
    /**
     * The constant MODID.
     */
    public static final String MODID = "custom_lootbag";
    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LogUtils.getLogger();


    public CustomLootbag(IEventBus modEventBus, ModContainer modContainer) {
        LootbagDropConfig.loadConfig();
        ModItems.init();
        ModItems.ITEMS.register(modEventBus);
        ModTabs.CREATIVE_TABS.register(modEventBus);
        modEventBus.addListener(ModDataGenerators::gatherData);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.addListener(LootbagDropHandler::onEntityDrop);

//         Register our mod's ModConfigSpec so that FML can create and load the config file for us
//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ModTabs.LOOTBAG_TAB.getKey()) {
            ModTabs.init(event);
        }

    }


}
