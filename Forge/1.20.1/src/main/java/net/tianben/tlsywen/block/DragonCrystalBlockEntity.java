package net.tianben.tlsywen.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DragonCrystalBlockEntity extends BlockEntity {
    private static final SoundEvent sound = SoundEvents.ENDER_DRAGON_GROWL;

    public DragonCrystalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.DRAGON_CRYSTAL_BLOCK_ENTITY.get(), pos, state);
    }

    public void playSound(Level level, BlockPos pos) {
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}