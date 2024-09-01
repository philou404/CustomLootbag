package net.supraerror.customlootbag.ui;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.supraerror.customlootbag.items.ModItems;

import static net.supraerror.customlootbag.CustomLootbag.MODID;


/**
 * The type Mod tabs.
 */
public class ModTabs {

    /**
     * The constant CREATIVE_TABS.
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    /**
     * The constant LOOTBAG_TAB.
     */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LOOTBAG_TAB = CREATIVE_TABS.register("custom_lootbag_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("Custom Lootbag"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> Items.APPLE.getDefaultInstance())
            .displayItems((parameters, output) -> {

            }).build());

    /**
     * Init.
     *
     * @param event the event
     */
    public static void init(BuildCreativeModeTabContentsEvent event) {

        for (String lootBagName : ModItems.lootbagsNameOrder) {
            event.accept(ModItems.LOOTBAG_ITEMS.get(lootBagName));
        }

    }


}
