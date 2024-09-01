package net.supraerror.customlootbag.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.supraerror.customlootbag.CustomLootbag;
import net.supraerror.customlootbag.config.LootbagContentAndTypeConfig;
import net.supraerror.customlootbag.config.LootbagDropConfig;
import net.supraerror.customlootbag.items.ModItems;
import net.supraerror.customlootbag.random.RandomGenerator;
import net.supraerror.customlootbag.random.RandomSelector;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Lootbag drop handler.
 */
@EventBusSubscriber(modid = CustomLootbag.MODID)
public class LootbagDropHandler {


    private static final LootbagContentAndTypeConfig config = LootbagContentAndTypeConfig.loadConfig();
    private static final Set<String> MOBS_WHO_DROPS = new HashSet<>(LootbagDropConfig.getStringSet("drop.mob_who_drop_lootbag"));
    private static int DEAD = 0;

    /**
     * On entity drop.
     *
     * @param event the event
     */
    @SubscribeEvent
    public static void onEntityDrop(@NotNull LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.level().isClientSide()) {
            return;
        }

        ResourceLocation entityId = EntityType.getKey(entity.getType());
        if (MOBS_WHO_DROPS.contains(entityId.toString())) {
            if (doubleDeathVerificator()) return;

            if (LootbagDropConfig.getDouble("drop.chance_to_drop") < RandomGenerator.getRandomDouble()) return;

            if (config != null) {
                dropLootbagInTheWorld(event, entity);

            }
        }
    }

    private static void dropLootbagInTheWorld(@NotNull LivingDropsEvent event, LivingEntity entity) {
        ++DEAD;
        ItemStack lootbagStack = new ItemStack(Objects.requireNonNull(RandomSelector.drawItem(ModItems.getLootBagItems())));
        event.getDrops().add(new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), lootbagStack));
    }

    private static boolean doubleDeathVerificator() {
        if (DEAD % 2 == 0) {
            ++DEAD;
            if (DEAD >= 1_000_000) {
                DEAD = 0;
            }
            return true;
        }
        return false;
    }
}
