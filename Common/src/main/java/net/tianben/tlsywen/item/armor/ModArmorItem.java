package net.tianben.tlsywen.item.armor;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide() && entity instanceof Player player && isWearingFullSet(player)) {
            applyArmorEffects(player);
        }
    }

    protected void applyArmorEffects(Player player) {
        applyEffect(player, MobEffects.NIGHT_VISION);
        applyEffect(player, MobEffects.DIG_SPEED);
        applyEffect(player, MobEffects.DAMAGE_BOOST);
        applyEffect(player, MobEffects.DAMAGE_RESISTANCE);
    }

    private void applyEffect(Player player, MobEffect effect) {
        player.addEffect(new MobEffectInstance(effect, 0, 127, false, true, false));
    }

    protected static boolean isWearingFullSet(Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ModArmorItem)) {
                return false;
            }
        }
        return true;
    }
}