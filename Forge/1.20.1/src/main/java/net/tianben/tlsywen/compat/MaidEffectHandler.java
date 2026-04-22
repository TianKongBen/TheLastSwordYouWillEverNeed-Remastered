package net.tianben.tlsywen.compat;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

public final class MaidEffectHandler {

    private static final String MAID_CLASS_NAME = "com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid";
    private static final boolean TOUHOU_LITTLE_MAID_LOADED = ModList.get().isLoaded("touhou_little_maid");

    private MaidEffectHandler() {}

    public static void updateMaidEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED || !isMaid(entity)) return;

        try {
            boolean isFullSet = (boolean) MaidEffectHelper.class
                    .getMethod("isMaidWearingFullSet", LivingEntity.class)
                    .invoke(null, entity);

            String methodName = isFullSet ? "applyInfiniteEffects" : "removeArmorEffects";
            MaidEffectHelper.class
                    .getMethod(methodName, LivingEntity.class)
                    .invoke(null, entity);
        } catch (Exception ignored) {
        }
    }

    public static boolean isMaidLoaded() {
        return TOUHOU_LITTLE_MAID_LOADED;
    }

    private static boolean isMaid(LivingEntity entity) {
        return entity.getClass().getName().equals(MAID_CLASS_NAME);
    }
}