package net.tianben.tlsywen.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class ModSounds {
    private static final float VOLUME = 1f;
    private static final float PITCH = 1f;

    public static final BlockSoundGroup DRAGON_CRYSTAL_BLOCK_SOUNDS = new BlockSoundGroup(
            VOLUME, PITCH,
            SoundEvents.BLOCK_STONE_BREAK,
            SoundEvents.ENTITY_ENDER_DRAGON_GROWL,
            SoundEvents.BLOCK_STONE_PLACE,
            SoundEvents.BLOCK_STONE_HIT,
            SoundEvents.BLOCK_STONE_FALL
    );
}
