package net.tianben.tlsywen.mixin;

import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Set;

@Mixin(AbstractInventoryScreen.class)
public abstract class EffectDurationDisplayMixin {

    @Unique
    private static final Set<StatusEffect> ARMOR_EFFECTS = Set.of(
            StatusEffects.NIGHT_VISION,
            StatusEffects.HASTE,
            StatusEffects.STRENGTH,
            StatusEffects.RESISTANCE
    );

    @ModifyVariable(
            method = "drawStatusEffectDescriptions",
            at = @At("STORE"),
            ordinal = 0
    )
    private StatusEffectInstance modifyEffectForRendering(StatusEffectInstance effect) {
        if (ARMOR_EFFECTS.contains(effect.getEffectType()) &&
                effect.getDuration() == -1 &&
                effect.getAmplifier() == 127) {
            return new StatusEffectInstance(effect.getEffectType(), 0, effect.getAmplifier(),
                    effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon());
        }
        return effect;
    }
}
