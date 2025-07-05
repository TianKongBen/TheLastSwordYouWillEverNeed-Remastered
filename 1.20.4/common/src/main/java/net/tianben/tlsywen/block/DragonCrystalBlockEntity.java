package net.tianben.tlsywen.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DragonCrystalBlockEntity extends BlockEntity {
    public DragonCrystalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void playSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.ENDER_DRAGON_GROWL, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}