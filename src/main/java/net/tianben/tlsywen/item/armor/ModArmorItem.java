package net.tianben.tlsywen.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tianben.tlsywen.item.ModItems;

import java.util.Objects;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            if(hasFullSuitOfArmorOn(player)) {
                player.getAbilities().allowFlying = true;
            } else {
                if(player.getAbilities().allowFlying && !player.isCreative() && !player.isSpectator()) {
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                    player.sendAbilitiesUpdate();
                }
            }
        }

        if(!world.isClient()) {
            if(entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entity;
                if(hasFullSuitOfArmorOn(player)) {
                    if(player.getStatusEffect(StatusEffects.NIGHT_VISION) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.NIGHT_VISION)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.NIGHT_VISION, 0, 127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.HASTE) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.HASTE)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.HASTE, 0,127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.STRENGTH) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.STRENGTH)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.STRENGTH, 0, 127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.REGENERATION) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.RESISTANCE)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.RESISTANCE, 0, 127, false, true, false));
                    }
                }

            }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return (helmet.getItem().equals(ModItems.DRAGON_CRYSTAL_HELMET) &&
                chestplate.getItem().equals(ModItems.DRAGON_CRYSTAL_CHESTPLATE) &&
                leggings.getItem().equals(ModItems.DRAGON_CRYSTAL_LEGGINGS) &&
                boots.getItem().equals(ModItems.DRAGON_CRYSTAL_BOOTS));
    }
}