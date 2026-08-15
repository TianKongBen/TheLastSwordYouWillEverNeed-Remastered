package net.tianben.tlsywen.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.item.ModItems;

import java.util.Set;

public class ModArmorItem extends ArmorItem {

    private static final Set<StatusEffect> ARMOR_EFFECTS = Set.of(
            StatusEffects.NIGHT_VISION,
            StatusEffects.HASTE,
            StatusEffects.STRENGTH,
            StatusEffects.RESISTANCE
    );

    private static final int EFFECT_AMPLIFIER = 127;
    private static final int EFFECT_DURATION = -1;

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (world.isClient() || world.getTime() % 10 != 0) return;
        if (entity instanceof PlayerEntity player) {
            updatePlayerState(player);
        }
    }

    private static void updatePlayerState(PlayerEntity player) {
        boolean isFullSet = isWearingDragonCrystalArmor(player);
        updateEffects(player, isFullSet);
        updateFlightAbility(player);
    }

    public static void updateEffects(LivingEntity entity, boolean isFullSet) {
        for (StatusEffect effect : ARMOR_EFFECTS) {
            var current = entity.getStatusEffect(effect);
            boolean hasInfiniteEffect = current != null &&
                    current.getAmplifier() == EFFECT_AMPLIFIER &&
                    current.getDuration() == EFFECT_DURATION;

            if (isFullSet && !hasInfiniteEffect) {
                entity.addStatusEffect(new StatusEffectInstance(effect, EFFECT_DURATION, EFFECT_AMPLIFIER, false, true, false));
            } else if (!isFullSet && hasInfiniteEffect) {
                entity.removeStatusEffect(effect);
            }
        }
    }

    private static void updateFlightAbility(PlayerEntity player) {
        boolean shouldHaveFlight = isWearingDragonCrystalArmor(player);
        boolean hasFlight = PlayerFlightAttachment.hasFlight(player);

        if (player.isCreative() || player.isSpectator()) {
            if (hasFlight) {
                PlayerFlightAttachment.setFlight(player, false);
            }
            return;
        }

        if (shouldHaveFlight != hasFlight) {
            setFlightAbility(player, shouldHaveFlight);
            PlayerFlightAttachment.setFlight(player, shouldHaveFlight);
        }
    }

    private static void setFlightAbility(PlayerEntity player, boolean enabled) {
        player.getAbilities().allowFlying = enabled;
        if (!enabled) {
            player.getAbilities().flying = false;
        }
        player.sendAbilitiesUpdate();
    }

    public static boolean isWearingDragonCrystalArmor(PlayerEntity player) {
        var armor = player.getInventory().armor;
        return armor.get(0).getItem() == ModItems.DRAGON_CRYSTAL_BOOTS &&
                armor.get(1).getItem() == ModItems.DRAGON_CRYSTAL_LEGGINGS &&
                armor.get(2).getItem() == ModItems.DRAGON_CRYSTAL_CHESTPLATE &&
                armor.get(3).getItem() == ModItems.DRAGON_CRYSTAL_HELMET;
    }
}
