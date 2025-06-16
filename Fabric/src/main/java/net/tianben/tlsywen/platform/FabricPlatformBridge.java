package net.tianben.tlsywen.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.block.DragonCrystalBlockEntity;
import net.tianben.tlsywen.entity.LDEntity;
import net.tianben.tlsywen.block.ModBlocksFabric;
import net.tianben.tlsywen.entity.ModEntitiesFabric;
import org.jetbrains.annotations.NotNull;

public final class FabricPlatformBridge implements PlatformBridge {

    @Override
    public @NotNull EntityType<? extends LDEntity> getLDEntityType() {
        return ModEntitiesFabric.LD;
    }

    @Override
    public @NotNull BlockEntity createDragonCrystalBlockEntity(
            @NotNull BlockPos pos,
            @NotNull BlockState state) {
        return new DragonCrystalBlockEntity(
                ModBlocksFabric.DRAGON_CRYSTAL_BE,
                pos,
                state
        );
    }
}