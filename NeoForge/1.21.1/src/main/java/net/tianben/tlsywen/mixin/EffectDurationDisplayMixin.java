package net.tianben.tlsywen.mixin;

import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Set;

@Mixin(EffectRenderingInventoryScreen.class)
public abstract class EffectDurationDisplayMixin {

    @Unique
    private static final Set<Holder<MobEffect>> TLSYWEN_ARMOR_EFFECTS = Set.of(
            MobEffects.NIGHT_VISION,
            MobEffects.DIG_SPEED,
            MobEffects.DAMAGE_BOOST,
            MobEffects.DAMAGE_RESISTANCE
    );

    @Unique
    private static final int EFFECT_AMPLIFIER = 127;

    @Unique
    private static final int EFFECT_DURATION = -1;

    @ModifyVariable(
            method = "renderLabels",
            at = @At("STORE"),
            ordinal = 0
    )
    private MobEffectInstance tlsywen$modifyEffectForRendering(MobEffectInstance effect) {
        boolean isOurEffect = TLSYWEN_ARMOR_EFFECTS.contains(effect.getEffect())
                && effect.getAmplifier() == EFFECT_AMPLIFIER
                && effect.getDuration() == EFFECT_DURATION;

        if (!isOurEffect) {
            return effect;
        }

        return new MobEffectInstance(
                effect.getEffect(),
                0,
                effect.getAmplifier(),
                effect.isAmbient(),
                effect.isVisible(),
                effect.showIcon()
        );
    }
}