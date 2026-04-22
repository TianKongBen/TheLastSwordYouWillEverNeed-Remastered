package net.tianben.tlsywen.item.sword;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("deprecation")
public class TheLastSwordYouWillEverNeedItem extends PickaxeItem {

    private static final float ATTACK_SPEED = -2.4f;
    private static final float MINING_SPEED = 2400.0f;
    private static final float PROJECTILE_SPEED = 1.5f;
    private static final float PROJECTILE_INACCURACY = 1.0f;

    private static final int TOOLTIP_COLOR = 0xAAAAAA;

    public TheLastSwordYouWillEverNeedItem(Tier tier) {
        super(tier, -1, ATTACK_SPEED, new Properties());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return MINING_SPEED;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        super.appendHoverText(stack, level, tooltip, context);

        var itemKey = BuiltInRegistries.ITEM.getKey(this).getPath();
        tooltip.add(Component.translatable("tooltip.tlsywen." + itemKey)
                .setStyle(Style.EMPTY
                        .withColor(TOOLTIP_COLOR)
                        .withItalic(false)));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            LDEntity projectile = new LDEntity(level, player);
            projectile.setItem(itemStack);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(),
                    0.0f, PROJECTILE_SPEED, PROJECTILE_INACCURACY);
            level.addFreshEntity(projectile);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResultHolder.pass(itemStack);
    }
}