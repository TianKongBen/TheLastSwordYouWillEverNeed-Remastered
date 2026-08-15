package net.tianben.tlsywen.compat;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.tianben.tlsywen.item.ModItems;

import java.util.Set;

public final class MaidEffectHelper {

    private static final String MAID_CLASS_NAME = "com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid";
    private static final boolean TOUHOU_LITTLE_MAID_LOADED = ModList.get().isLoaded("touhou_little_maid");

    private static Class<?> maidClass;

    static {
        if (TOUHOU_LITTLE_MAID_LOADED) {
            try {
                maidClass = Class.forName(MAID_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                maidClass = null;
            }
        }
    }

    private static final Set<Holder<MobEffect>> ARMOR_EFFECTS = Set.of(
            MobEffects.NIGHT_VISION,
            MobEffects.DIG_SPEED,
            MobEffects.DAMAGE_BOOST,
            MobEffects.DAMAGE_RESISTANCE
    );

    private static final int EFFECT_AMPLIFIER = 127;
    private static final int EFFECT_DURATION = -1;

    private MaidEffectHelper() {}

    public static boolean isMaid(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED || entity == null) return false;
        if (maidClass != null) {
            return maidClass.isInstance(entity);
        }
        return entity.getClass().getName().equals(MAID_CLASS_NAME);
    }

    public static boolean isMaidWearingFullSet(LivingEntity entity) {
        if (!isMaid(entity)) return false;

        return isDragonCrystalArmor(entity.getItemBySlot(EquipmentSlot.HEAD)) &&
                isDragonCrystalArmor(entity.getItemBySlot(EquipmentSlot.CHEST)) &&
                isDragonCrystalArmor(entity.getItemBySlot(EquipmentSlot.LEGS)) &&
                isDragonCrystalArmor(entity.getItemBySlot(EquipmentSlot.FEET));
    }

    public static void applyInfiniteEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED) return;
        updateEffects(entity, true);
    }

    public static void removeArmorEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED) return;
        updateEffects(entity, false);
    }

    private static boolean isDragonCrystalArmor(ItemStack stack) {
        var item = stack.getItem();
        return item == ModItems.DRAGON_CRYSTAL_HELMET.get() ||
                item == ModItems.DRAGON_CRYSTAL_CHESTPLATE.get() ||
                item == ModItems.DRAGON_CRYSTAL_LEGGINGS.get() ||
                item == ModItems.DRAGON_CRYSTAL_BOOTS.get();
    }

    private static void updateEffects(LivingEntity entity, boolean apply) {
        for (Holder<MobEffect> effect : ARMOR_EFFECTS) {
            MobEffectInstance current = entity.getEffect(effect);
            boolean hasInfiniteEffect = current != null &&
                    current.getAmplifier() == EFFECT_AMPLIFIER &&
                    current.getDuration() == EFFECT_DURATION;

            if (apply && !hasInfiniteEffect) {
                entity.addEffect(new MobEffectInstance(
                        effect,
                        EFFECT_DURATION,
                        EFFECT_AMPLIFIER,
                        false,
                        true,
                        true
                ));
            } else if (!apply && hasInfiniteEffect) {
                entity.removeEffect(effect);
            }
        }
    }
}