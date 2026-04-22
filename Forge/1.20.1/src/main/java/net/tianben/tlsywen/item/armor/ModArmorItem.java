package net.tianben.tlsywen.item.armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity,
                              int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if(!level.isClientSide() && level.getGameTime() % 10 == 0) {
            if(entity instanceof Player player) {
                updateFlightAbility(player);
                if(isWearingDragonCrystalArmor(player)) {
                    if(player.getEffect(MobEffects.NIGHT_VISION) == null || 
                       Objects.requireNonNull(player.getEffect(MobEffects.NIGHT_VISION)).getDuration() < 250){
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,
                                220, 127, false, true, false));
                    }

                    if(player.getEffect(MobEffects.DIG_SPEED) == null || 
                       Objects.requireNonNull(player.getEffect(MobEffects.DIG_SPEED)).getDuration() < 250){
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
                                220, 127, false, true, false));
                    }

                    if(player.getEffect(MobEffects.DAMAGE_BOOST) == null || 
                       Objects.requireNonNull(player.getEffect(MobEffects.DAMAGE_BOOST)).getDuration() < 250){
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST
                                , 220, 127, false, true, false));
                    }

                    if(player.getEffect(MobEffects.DAMAGE_RESISTANCE) == null || 
                       Objects.requireNonNull(player.getEffect(MobEffects.DAMAGE_RESISTANCE)).getDuration() < 250){
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                                220, 127, false, true, false));
                    }
                }
            }
        }
    }

    private void updateFlightAbility(@NotNull Player player) {
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

    private void setFlightAbility(@NotNull Player player, boolean enabled) {
        player.getAbilities().mayfly = enabled;
        if (!enabled) {
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }

    private boolean isWearingDragonCrystalArmor(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);
        return helmet.getItem() == ModItems.DRAGON_CRYSTAL_HELMET.get() &&
                chestplate.getItem() == ModItems.DRAGON_CRYSTAL_CHESTPLATE.get() &&
                leggings.getItem() == ModItems.DRAGON_CRYSTAL_LEGGINGS.get() &&
                boots.getItem() == ModItems.DRAGON_CRYSTAL_BOOTS.get();
    }
}