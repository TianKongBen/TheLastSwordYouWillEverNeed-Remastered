package net.tianben.tlsywen.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.tianben.tlsywen.compat.MaidEffectHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    private static final int TICK_INTERVAL = 10;

    @Inject(method = "tick", at = @At("TAIL"))
    private void tlsywen$onTick(CallbackInfo ci) {
        var entity = (LivingEntity) (Object) this;
        var level = entity.level();

        if (level.isClientSide() || level.getGameTime() % TICK_INTERVAL != 0) {
            return;
        }

        if (MaidEffectHandler.isMaidLoaded()) {
            MaidEffectHandler.updateMaidEffects(entity);
        }
    }
}