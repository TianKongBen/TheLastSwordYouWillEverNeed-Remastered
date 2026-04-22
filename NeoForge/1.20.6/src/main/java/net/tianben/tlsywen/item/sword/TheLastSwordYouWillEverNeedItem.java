package net.tianben.tlsywen.item.sword;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.Level;
import net.tianben.tlsywen.entity.LDEntity;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends DiggerItem {
    private static final float PROJECTILE_SPEED = 1.5f;
    private static final float PROJECTILE_INACCURACY = 1.0f;

    public TheLastSwordYouWillEverNeedItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties
                .component(DataComponents.UNBREAKABLE, new Unbreakable(false)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.tlsywen." + BuiltInRegistries.ITEM
                .getKey(this).getPath()).setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)
                .withItalic(false)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            var projectile = new LDEntity(level, player);
            projectile.setItem(stack);
            projectile.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,
                    PROJECTILE_SPEED,
                    PROJECTILE_INACCURACY
            );
            level.addFreshEntity(projectile);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.pass(stack);
    }
}