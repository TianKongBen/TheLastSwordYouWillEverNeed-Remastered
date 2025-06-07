package net.tianben.tlsywen.sound;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

public class ModSounds {
    public static final Supplier<SoundType> DRAGON_CRYSTAL_BLOCK_SOUNDS = () -> new SoundType(
            1.0F, 1.0F,
            SoundEvents.STONE_BREAK,
            SoundEvents.ENDER_DRAGON_GROWL,
            SoundEvents.STONE_PLACE,
            SoundEvents.STONE_HIT,
            SoundEvents.STONE_FALL
    );
}