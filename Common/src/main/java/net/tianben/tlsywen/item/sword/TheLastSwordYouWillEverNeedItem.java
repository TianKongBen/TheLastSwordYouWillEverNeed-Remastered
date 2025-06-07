package net.tianben.tlsywen.item.sword;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends PickaxeItem {
    private final Tier tier;
    private static final float ATTACK_SPEED = -2.4f;

    public TheLastSwordYouWillEverNeedItem(Tier tier, Properties properties) {
        super(tier, -1, ATTACK_SPEED, properties);
        this.tier = tier;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 2400.0f;
    }

    public void addTooltips(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag flag) {
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltips, flag);
        String path = BuiltInRegistries.ITEM.getKey(this).getPath();
        tooltips.add(Component.translatable("tooltip.tlsywen." + path)
                .withStyle(ChatFormatting.GRAY));
        addTooltips(stack, level, tooltips, flag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            LDEntity projectile = new LDEntity(level, player);
            projectile.setItem(stack);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public Tier getTier() {
        return tier;
    }
}