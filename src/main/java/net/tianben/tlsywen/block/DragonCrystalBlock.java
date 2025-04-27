package net.tianben.tlsywen.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DragonCrystalBlock extends BlockWithEntity {
    public static final BooleanProperty ACTIVATED;

    public DragonCrystalBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.getStateManager().getDefaultState()).with(ACTIVATED, false));
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        world.setBlockState(pos, (BlockState)state.with(ACTIVATED, false), 2);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        ActionResult useResult = stack.useOnBlock(new ItemUsageContext(player, hand, hit));
        BlockEntity blockEntity;
        DragonCrystalBlockEntity crystal;
        if (useResult.isAccepted()) {
            blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonCrystalBlockEntity) {
                crystal = (DragonCrystalBlockEntity)blockEntity;
                crystal.playSound(world, pos);
            }

            return ActionResult.success(world.isClient);
        } else {
            blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonCrystalBlockEntity) {
                crystal = (DragonCrystalBlockEntity)blockEntity;
                crystal.playSound(world, pos);
                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        }
    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
            crystal.playSound(world, pos);
        }

        super.onBlockBreakStart(state, world, pos, player);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonCrystalBlockEntity(pos, state);
    }

    public boolean emitsRedstonePower(BlockState state) {
        return false;
    }

    public boolean hasComparatorOutput(BlockState state) {
        return false;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{ACTIVATED});
    }

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    static {
        ACTIVATED = Properties.POWERED;
    }
}
