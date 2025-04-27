package net.tianben.tlsywen.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


    public DragonCrystalBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {


            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    @Override
        BlockEntity blockEntity = world.getBlockEntity(pos);
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonCrystalBlockEntity(pos, state);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
    }
}