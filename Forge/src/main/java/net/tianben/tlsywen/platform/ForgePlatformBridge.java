package net.tianben.tlsywen.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tianben.tlsywen.block.DragonCrystalBlockEntity;
import net.tianben.tlsywen.entity.LDEntity;
import net.tianben.tlsywen.block.ModBlocksForge;
import net.tianben.tlsywen.entity.ModEntitiesForge;

public class ForgePlatformBridge implements PlatformBridge {
    @Override
    public EntityType<? extends LDEntity> getLDEntityType() {
        return ModEntitiesForge.LD.get();
    }

    @Override
    public BlockEntity createDragonCrystalBlockEntity(BlockPos pos, BlockState state) {
        return new DragonCrystalBlockEntity(ModBlocksForge.DRAGON_CRYSTAL_BE.get(), pos, state);
    }
}