package net.tianben.tlsywen.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DragonCrystalBlockEntity extends JukeboxBlockEntity {
    public DragonCrystalBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void playSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.BLOCKS, 1f, 1f);
    }
}
