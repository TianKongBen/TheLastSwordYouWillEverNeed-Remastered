package net.tianben.tlsywen.item.sword;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends PickaxeItem {
    private static final float ATTACK_SPEED = -2.4f;
    private static final float MINING_SPEED = 2400.0f;
    private static final float PROJECTILE_SPEED = 1.5f;
    private static final float PROJECTILE_INACCURACY = 1.0f;

    public TheLastSwordYouWillEverNeedItem(Tier tier) {
        super(tier, 0, ATTACK_SPEED, new Item.Properties());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return MINING_SPEED;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag context) {
        super.appendHoverText(stack, level, tooltip, context);
        tooltip.add(Component.translatable("tooltip.tlsywen." + BuiltInRegistries.ITEM
                .getKey(this).getPath()).setStyle(Style.EMPTY.withColor(0xAAAAAA)
                .withItalic(false)));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            LDEntity lightningProjectileEntity = new LDEntity(level, player);
            lightningProjectileEntity.setItem(itemStack);
            lightningProjectileEntity.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0f,
                    PROJECTILE_SPEED,
                    PROJECTILE_INACCURACY
            );
            level.addFreshEntity(lightningProjectileEntity);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.pass(itemStack);
    }
}