package net.tianben.tlsywen.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class ForgeNoNightVisionFlashingMixin {
    @Redirect(
        method = "getNightVisionScale(Lnet/minecraft/world/entity/LivingEntity;F)F", 
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/effect/MobEffectInstance;getDuration()I"
        )
    )
    private static int redirectDuration(MobEffectInstance instance) {
        if (instance.getEffect() == MobEffects.NIGHT_VISION) {
            return Integer.MAX_VALUE;
        }
        return instance.getDuration();
    }
}