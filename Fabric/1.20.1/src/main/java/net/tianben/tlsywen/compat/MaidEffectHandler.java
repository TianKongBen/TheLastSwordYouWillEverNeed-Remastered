package net.tianben.tlsywen.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;

public final class MaidEffectHandler {

    private static final String MAID_CLASS_NAME = "com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid";
    private static final boolean TOUHOU_LITTLE_MAID_LOADED = FabricLoader.getInstance().isModLoaded("touhou_little_maid");

    private MaidEffectHandler() {}

    public static void updateMaidEffects(LivingEntity entity) {
        if (!TOUHOU_LITTLE_MAID_LOADED || !isMaid(entity)) return;

        boolean isFullSet = MaidEffectHelper.isMaidWearingFullSet(entity);
        if (isFullSet) {
            MaidEffectHelper.applyInfiniteEffects(entity);
        } else {
            MaidEffectHelper.removeArmorEffects(entity);
        }
    }

    public static boolean isMaidLoaded() {
        return TOUHOU_LITTLE_MAID_LOADED;
    }

    private static boolean isMaid(LivingEntity entity) {
        return entity.getClass().getName().equals(MAID_CLASS_NAME);
    }
}
