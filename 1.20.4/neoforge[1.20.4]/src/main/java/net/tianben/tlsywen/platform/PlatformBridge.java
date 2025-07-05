package net.tianben.tlsywen.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface PlatformBridge {
    EntityType<? extends ThrowableItemProjectile> getLDEntityType();
    BlockEntity createDragonCrystalBlockEntity(BlockPos pos, BlockState state);
}