package net.tianben.tlsywen.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings.maxCount(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(!world.isClient() && world.getTime() % 10 == 0) {
            if(entity instanceof PlayerEntity player) {
                updateFlightAbility(player);
                if(isWearingDragonCrystalArmor(player)) {
                    if(player.getStatusEffect(StatusEffects.NIGHT_VISION) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.NIGHT_VISION)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.NIGHT_VISION, 10, 127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.HASTE) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.HASTE)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.HASTE, 10,127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.STRENGTH) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.STRENGTH)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.STRENGTH, 10, 127, false, true, false));
                    }

                    if(player.getStatusEffect(StatusEffects.REGENERATION) == null || Objects.requireNonNull(player.getStatusEffect(StatusEffects.RESISTANCE)).getDuration() < 250){
                        player.addStatusEffect(new  StatusEffectInstance(StatusEffects.RESISTANCE, 10, 127, false, true, false));
                    }
                }
            }
        }
    }

    private void updateFlightAbility(@NotNull PlayerEntity player) {
        boolean shouldHaveFlight = isWearingDragonCrystalArmor(player);
        boolean hasFlight = PlayerFlightAttachment.hasFlight(player);

        if(player.isCreative() || player.isSpectator()) {
            if(hasFlight) {
                PlayerFlightAttachment.setFlight(player, false);
            }
            return;
        }

        if(!hasFlight && shouldHaveFlight) {
            player.getAbilities().flying = false;
            setFlightAbility(player, true);
            PlayerFlightAttachment.setFlight(player, true);
        }
        else if(hasFlight && !shouldHaveFlight) {
            setFlightAbility(player, false);
            PlayerFlightAttachment.setFlight(player, false);
        }
    }

    private void setFlightAbility(@NotNull PlayerEntity playerEntity, boolean enabled) {
        playerEntity.getAbilities().allowFlying = enabled;
        if (!enabled) {
            playerEntity.getAbilities().flying = false;
        }
        playerEntity.sendAbilitiesUpdate();
    }

    private boolean isWearingDragonCrystalArmor(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);
        return helmet.getItem() == ModItems.DRAGON_CRYSTAL_HELMET &&
                chestplate.getItem() == ModItems.DRAGON_CRYSTAL_CHESTPLATE &&
                leggings.getItem() == ModItems.DRAGON_CRYSTAL_LEGGINGS &&
                boots.getItem() == ModItems.DRAGON_CRYSTAL_BOOTS;
    }
}