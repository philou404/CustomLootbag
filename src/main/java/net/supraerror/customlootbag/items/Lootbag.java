package net.supraerror.customlootbag.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.supraerror.customlootbag.random.RandomSelector;
import net.supraerror.customlootbag.random.WeightedObject;
import net.supraerror.customlootbag.utils.InputUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;


/**
 * The type Lootbag.
 */
public class Lootbag extends Item implements IItemExtension, WeightedObject {
    private final Collection<LootObject> lootObjects;
    private final int maximumTypeItemsDrop;
    private final String name;
    private final int weight;

    /**
     * Instantiates a new Lootbag.
     *
     * @param properties  the properties
     * @param lootObjects the loot objects
     * @param name        the name
     * @param weight      the weight
     */
    public Lootbag(Item.Properties properties, Collection<LootObject> lootObjects, String name, int weight) {
        this(properties, lootObjects, 1, name, weight);

    }

    /**
     * Instantiates a new Lootbag.
     *
     * @param properties           the properties
     * @param lootObjects          the loot objects
     * @param maximumTypeItemsDrop the maximum type items drop
     * @param name                 the name
     * @param weight               the weight
     */
    public Lootbag(Item.Properties properties, Collection<LootObject> lootObjects, int maximumTypeItemsDrop, String name, int weight) {
        super(properties);
        this.lootObjects = InputUtils.nonNullCollection(Objects.requireNonNull(lootObjects, "Collection of loot objects is null"));
        this.maximumTypeItemsDrop = InputUtils.inRange(1, Integer.MAX_VALUE, maximumTypeItemsDrop);
        this.name = name;
        this.weight = weight;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide) {
            Player player = context.getPlayer();
            if (player != null) {
                openLootbag((ServerLevel) level, player);
                context.getItemInHand().shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    private void openLootbag(ServerLevel level, Player player) {

        int nbItemDrop = 0;
        Collection<LootObject> lootObjects = new HashSet<>(this.lootObjects);
        while (!lootObjects.isEmpty() && nbItemDrop < maximumTypeItemsDrop) {
            LootObject loot = RandomSelector.drawItem(lootObjects);
            ItemStack itemStack = new ItemStack(loot.getItem(), loot.getQuantity());
            player.drop(itemStack, false);
            ++nbItemDrop;
        }
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
}
