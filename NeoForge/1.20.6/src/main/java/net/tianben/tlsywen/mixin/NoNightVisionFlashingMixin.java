package net.tianben.tlsywen.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class NoNightVisionFlashingMixin {

    @Inject(
            method = "getNightVisionScale",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetNightVisionStrength(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
        if (entity.hasEffect(MobEffects.NIGHT_VISION)) {
            cir.setReturnValue(1.0f);
            cir.cancel();
        }
    }
}