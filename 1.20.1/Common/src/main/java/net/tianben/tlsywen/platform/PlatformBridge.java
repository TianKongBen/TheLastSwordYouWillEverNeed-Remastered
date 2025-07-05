package net.tianben.tlsywen.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.entity.LDEntity;

public interface PlatformBridge {
    EntityType<? extends LDEntity> getLDEntityType();
    BlockEntity createDragonCrystalBlockEntity(BlockPos pos, BlockState state);
}