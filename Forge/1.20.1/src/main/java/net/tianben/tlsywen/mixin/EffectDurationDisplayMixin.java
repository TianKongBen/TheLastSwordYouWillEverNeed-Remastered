package net.tianben.tlsywen.mixin;

import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashSet;
import java.util.Set;

@Mixin(EffectRenderingInventoryScreen.class)
public abstract class EffectDurationDisplayMixin {

    @Unique
    private static final Set<MobEffect> ARMOR_EFFECTS = new HashSet<>();

    static {
        ARMOR_EFFECTS.add(MobEffects.NIGHT_VISION);
        ARMOR_EFFECTS.add(MobEffects.DIG_SPEED);
        ARMOR_EFFECTS.add(MobEffects.DAMAGE_BOOST);
        ARMOR_EFFECTS.add(MobEffects.DAMAGE_RESISTANCE);
    }

    @ModifyVariable(
            method = "renderLabels",
            at = @At("STORE"),
            ordinal = 0
    )
    private MobEffectInstance modifyEffectForRendering(MobEffectInstance effect) {
        if (ARMOR_EFFECTS.contains(effect.getEffect()) &&
                effect.getDuration() == -1 &&
                effect.getAmplifier() == 127) {
            return new MobEffectInstance(effect.getEffect(), 0, effect.getAmplifier(),
                    effect.isAmbient(), effect.isVisible(), effect.showIcon());
        }
        return effect;
    }
}