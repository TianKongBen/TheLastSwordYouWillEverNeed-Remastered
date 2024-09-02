package net.tianben.tlsywen.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

public class ModSounds {
    public static final BlockSoundGroup DRAGON_CRYSTAL_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f,
            SoundEvents.BLOCK_STONE_BREAK, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundEvents.BLOCK_STONE_PLACE,
            SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundEvents.BLOCK_STONE_FALL);

    public static void registerSounds() {
        TheLastSwordYouWillEverNeed.LOGGER.info("Registering Sounds for " + TheLastSwordYouWillEverNeed.MOD_ID);
    }
}
