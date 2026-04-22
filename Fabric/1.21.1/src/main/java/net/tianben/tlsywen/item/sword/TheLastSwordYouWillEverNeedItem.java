package net.tianben.tlsywen.item.sword;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tianben.tlsywen.entity.LDEntity;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends MiningToolItem {
    private static final float PROJECTILE_SPEED = 1.5f;
    private static final float PROJECTILE_INACCURACY = 1.0f;

    public TheLastSwordYouWillEverNeedItem(ToolMaterial material, Item.Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings
                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(false)));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("tooltip.tlsywen." + Registries.ITEM
                .getId(this).getPath()).fillStyle(Style.EMPTY.withColor(Formatting.GRAY)
                .withItalic(false)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            LDEntity lightningProjectileEntity = new LDEntity(world, user);
            lightningProjectileEntity.setItem(itemStack);
            lightningProjectileEntity.setVelocity(
                    user,
                    user.getPitch(),
                    user.getYaw(),
                    0.0f,
                    PROJECTILE_SPEED,
                    PROJECTILE_INACCURACY
            );
            world.spawnEntity(lightningProjectileEntity);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return TypedActionResult.pass(itemStack);
    }
}
