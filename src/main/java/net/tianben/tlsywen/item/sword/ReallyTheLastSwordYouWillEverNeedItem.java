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
import net.tianben.tlsywen.entity.ReallyLDEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReallyTheLastSwordYouWillEverNeedItem extends PickaxeItem {

    private static final float ATTACK_DAMAGE = 7999999874453995500f;

    private static final float ATTACK_SPEED = -2.4f;

    public ReallyTheLastSwordYouWillEverNeedItem(ToolMaterial toolMaterial) {
        super(toolMaterial, (int) ATTACK_DAMAGE, ATTACK_SPEED, new FabricItemSettings());
    }

    public float getAttackDamage() {
        return ATTACK_DAMAGE;
    }

    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 60.0f;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("tooltip.tlsywen." + Registries.ITEM.getId(this).getPath()).fillStyle(Style.EMPTY.withColor(Formatting.GRAY).withItalic(false)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            ReallyLDEntity lightningProjectileEntity = new ReallyLDEntity(world, user);
            lightningProjectileEntity.setItem(itemStack);
            lightningProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 0.0f);
            world.spawnEntity(lightningProjectileEntity);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return TypedActionResult.pass(itemStack);
    }
}
