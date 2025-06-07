package net.tianben.tlsywen.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tianben.tlsywen.sound.ModSounds;

public class ModBlocks {
    public static final Block COMPRESSED_STAR = new Block(Block.Properties.of()
                    .strength(0f, 6.0f)
                    .lightLevel(state -> 14)
    );
    public static final Block DRAGON_CRYSTAL_BLOCK = new DragonCrystalBlock(
            BlockBehaviour.Properties.of()
                    .strength(5.5f, 6.0f)
                    .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS.get())
    );
    public static final Block DRAGON_CRYSTAL_ORE = new DragonCrystalBlock(
            BlockBehaviour.Properties.of()
                    .strength(5.5f, 6.0f)
                    .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS.get())
    );
}