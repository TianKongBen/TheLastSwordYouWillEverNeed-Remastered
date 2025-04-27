package net.tianben.tlsywen.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DragonCrystalBlockEntity extends BlockEntity {
    public DragonCrystalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.DRAGON_CRYSTAL_BLOCK_ENTITY, pos, state);
    }

    public void playSound(World world, BlockPos pos) {
        SoundEvent sound = SoundEvents.ENTITY_ENDER_DRAGON_GROWL;
        world.playSound((PlayerEntity)null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
