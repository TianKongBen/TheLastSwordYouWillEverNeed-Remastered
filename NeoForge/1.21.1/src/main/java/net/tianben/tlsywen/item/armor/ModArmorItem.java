package net.tianben.tlsywen.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.compat.MaidEffectHandler;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModArmorItem extends ArmorItem {

    private static final Set<Holder<MobEffect>> ARMOR_EFFECTS = Set.of(
            MobEffects.NIGHT_VISION,
            MobEffects.DIG_SPEED,
            MobEffects.DAMAGE_BOOST,
            MobEffects.DAMAGE_RESISTANCE
    );

    private static final List<EquipmentSlot> ARMOR_SLOTS = List.of(
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
    );

    private static final int EFFECT_AMPLIFIER = 127;
    private static final int EFFECT_DURATION = -1;
    private static final int TICK_INTERVAL = 10;

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties.stacksTo(1));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
                              @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (level.isClientSide() || level.getGameTime() % TICK_INTERVAL != 0) {
            return;
        }

        if (entity instanceof Player player) {
            updatePlayerState(player);
        }

        if (entity instanceof LivingEntity living && MaidEffectHandler.isMaidLoaded()) {
            MaidEffectHandler.updateMaidEffects(living);
        }
    }

    private static void updatePlayerState(Player player) {
        boolean fullSet = isWearingDragonCrystalArmor(player);
        updateEffects(player, fullSet);
        updateFlightAbility(player, fullSet);
    }

    public static void updateEffects(LivingEntity entity, boolean fullSet) {
        for (var effect : ARMOR_EFFECTS) {
            var current = entity.getEffect(effect);
            boolean hasInfinite = current != null
                    && current.getAmplifier() == EFFECT_AMPLIFIER
                    && current.getDuration() == EFFECT_DURATION;

            if (fullSet && !hasInfinite) {
                entity.addEffect(new MobEffectInstance(
                        effect, EFFECT_DURATION, EFFECT_AMPLIFIER, false, true, false));
            } else if (!fullSet && hasInfinite) {
                entity.removeEffect(effect);
            }
        }
    }

    private static void updateFlightAbility(Player player, boolean fullSet) {
        if (player.isCreative() || player.isSpectator()) {
            if (PlayerFlightAttachment.hasFlight(player)) {
                PlayerFlightAttachment.setFlight(player, false);
            }
            return;
        }

        if (fullSet != PlayerFlightAttachment.hasFlight(player)) {
            setFlightAbility(player, fullSet);
            PlayerFlightAttachment.setFlight(player, fullSet);
        }
    }

    private static void setFlightAbility(Player player, boolean enabled) {
        var abilities = player.getAbilities();
        abilities.mayfly = enabled;
        if (!enabled) {
            abilities.flying = false;
        }
        player.onUpdateAbilities();
    }

    private static final List<Supplier<? extends Item>> DRAGON_CRYSTAL_ARMOR = List.of(
            ModItems.DRAGON_CRYSTAL_HELMET,
            ModItems.DRAGON_CRYSTAL_CHESTPLATE,
            ModItems.DRAGON_CRYSTAL_LEGGINGS,
            ModItems.DRAGON_CRYSTAL_BOOTS
    );

    public static boolean isWearingDragonCrystalArmor(Player player) {
        var slots = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
        for (int i = 0; i < slots.size(); i++) {
            if (player.getItemBySlot(slots.get(i)).getItem() != DRAGON_CRYSTAL_ARMOR.get(i).get()) {
                return false;
            }
        }
        return true;
    }
}