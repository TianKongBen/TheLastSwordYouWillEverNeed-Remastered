package net.tianben.tlsywen.item.sword;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tianben.tlsywen.entity.LDEntity;

import java.util.List;

public class TheLastSwordYouWillEverNeedItem extends PickaxeItem {

    private static final float ATTACK_SPEED = -2.4f;
    private static final float MINING_SPEED = 2400.0f;
    private static final float PROJECTILE_SPEED = 1.5f;
    private static final float PROJECTILE_INACCURACY = 1.0f;
    private static final Formatting TOOLTIP_COLOR = Formatting.GRAY;

    public TheLastSwordYouWillEverNeedItem(ToolMaterial toolMaterial) {
        super(toolMaterial, -1, ATTACK_SPEED, new FabricItemSettings());
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return MINING_SPEED;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        var itemKey = Registries.ITEM.getId(this).getPath();
        tooltip.add(Text.translatable("tooltip.tlsywen." + itemKey)
                .fillStyle(Style.EMPTY.withColor(TOOLTIP_COLOR).withItalic(false)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            LDEntity projectile = new LDEntity(world, user);
            projectile.setItem(itemStack);
            projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, PROJECTILE_SPEED, PROJECTILE_INACCURACY);
            world.spawnEntity(projectile);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return TypedActionResult.pass(itemStack);
    }
}
