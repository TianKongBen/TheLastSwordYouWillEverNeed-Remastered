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
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.entity.LDEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends DiggerItem {

    private static final float MINING_SPEED = 2_400.0F;
    private static final float PROJECTILE_SPEED = 1.5F;
    private static final float PROJECTILE_INACCURACY = 1.0F;

    /** 所有等级的翻译键前缀。 */
    private static final String TOOLTIP_PREFIX = "tooltip.tlsywen.";

    /** tooltip 统一用的灰色、非斜体样式。 */
    private static final Style TOOLTIP_STYLE =
            Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(false);

    public TheLastSwordYouWillEverNeedItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties
                .component(DataComponents.UNBREAKABLE, new Unbreakable(false)));
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return MINING_SPEED;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, TooltipContext context,
                                @NotNull List<Component> tooltipComponents,
                                @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        String path = BuiltInRegistries.ITEM.getKey(this).getPath();
        tooltipComponents.add(
                Component.translatable(TOOLTIP_PREFIX + path).setStyle(TOOLTIP_STYLE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level,
                                                           @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        var stack = player.getItemInHand(hand);

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