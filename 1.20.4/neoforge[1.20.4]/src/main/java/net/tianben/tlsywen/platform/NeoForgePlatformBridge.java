package net.tianben.tlsywen.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.block.DragonCrystalBlockEntity;
import net.tianben.tlsywen.block.ModBlocksNeoForge;
import net.tianben.tlsywen.entity.ModEntitiesNeoForge;
import org.jetbrains.annotations.NotNull;

public final class NeoForgePlatformBridge implements PlatformBridge {

    @Override
    public EntityType<? extends ThrowableItemProjectile> getLDEntityType() {
        return ModEntitiesNeoForge.LD.get();
    }

    @Override
    public @NotNull BlockEntity createDragonCrystalBlockEntity(
            @NotNull BlockPos pos,
            @NotNull BlockState state) {
        return new DragonCrystalBlockEntity(
                ModBlocksNeoForge.DRAGON_CRYSTAL_BE.get(),
                pos,
                state
        );
    }
}