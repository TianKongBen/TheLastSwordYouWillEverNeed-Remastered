package net.tianben.tlsywen.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class NoNightVisionFlashingMixin {
	@Inject(
			method = "getNightVisionStrength",
			at = @At("HEAD"),
			cancellable = true
	)
	private static void onGetNightVisionStrength(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir) {
		if (entity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
			cir.setReturnValue(1.0f);
			cir.cancel();
		}
	}
}