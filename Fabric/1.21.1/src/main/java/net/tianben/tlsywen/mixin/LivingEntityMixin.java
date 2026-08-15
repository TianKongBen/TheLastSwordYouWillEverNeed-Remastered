package net.tianben.tlsywen.mixin;

import net.minecraft.entity.LivingEntity;
import net.tianben.tlsywen.compat.MaidEffectHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void onTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.getWorld().isClient()) return;
        if (entity.getWorld().getTime() % 10 != 0) return;

        if (MaidEffectHandler.isMaidLoaded()) {
            MaidEffectHandler.updateMaidEffects(entity);
        }
    }
}
