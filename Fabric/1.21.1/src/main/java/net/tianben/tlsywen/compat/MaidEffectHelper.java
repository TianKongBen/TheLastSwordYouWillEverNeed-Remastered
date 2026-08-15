package net.tianben.tlsywen.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.armor.ModArmorItem;

public final class MaidEffectHelper {

    private static final String MAID_CLASS_NAME = "com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid";
    private static final boolean TOUHOU_LITTLE_MAID_LOADED = FabricLoader.getInstance().isModLoaded("touhou_little_maid");

    private MaidEffectHelper() {}

    public static boolean isMaidWearingFullSet(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED || !isMaid(entity)) return false;

        return isDragonCrystalArmor(entity.getEquippedStack(EquipmentSlot.HEAD)) &&
                isDragonCrystalArmor(entity.getEquippedStack(EquipmentSlot.CHEST)) &&
                isDragonCrystalArmor(entity.getEquippedStack(EquipmentSlot.LEGS)) &&
                isDragonCrystalArmor(entity.getEquippedStack(EquipmentSlot.FEET));
    }

    public static void applyInfiniteEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED) return;
        ModArmorItem.updateEffects(entity, true);
    }

    public static void removeArmorEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED) return;
        ModArmorItem.updateEffects(entity, false);
    }

    private static boolean isMaid(LivingEntity entity) {
        return entity.getClass().getName().equals(MAID_CLASS_NAME);
    }

    private static boolean isDragonCrystalArmor(ItemStack stack) {
        var item = stack.getItem();
        return item == ModItems.DRAGON_CRYSTAL_HELMET ||
                item == ModItems.DRAGON_CRYSTAL_CHESTPLATE ||
                item == ModItems.DRAGON_CRYSTAL_LEGGINGS ||
                item == ModItems.DRAGON_CRYSTAL_BOOTS;
    }
}
