package net.tianben.tlsywen.sound;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public final class ModSounds {
    private static final float VOLUME = 1.0f;
    private static final float PITCH = 1.0f;

    public static final SoundType DRAGON_CRYSTAL_BLOCK_SOUNDS = new SoundType(
            VOLUME, PITCH,
            SoundEvents.STONE_BREAK,
            SoundEvents.ENDER_DRAGON_GROWL,
            SoundEvents.STONE_PLACE,
            SoundEvents.STONE_HIT,
            SoundEvents.STONE_FALL
    );

    private ModSounds() {}
}