package net.tianben.tlsywen.block;

import net.minecraft.block.*;
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
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DragonCrystalBlock extends BlockWithEntity {
    public static final BooleanProperty ACTIVATED = Properties.POWERED; // 使用 POWERED 属性表示是否激活

    public DragonCrystalBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(ACTIVATED, false));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(world, pos, state, placer, stack);
        // 初始化时设置为未激活状态
        world.setBlockState(pos, state.with(ACTIVATED, false), Block.NOTIFY_LISTENERS);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        // 尝试使用物品（包括方块、实体物品等）
        ActionResult useResult = stack.useOnBlock(new ItemUsageContext(player, hand, hit));
        if (useResult.isAccepted()) {
            // 如果物品放置成功，播放龙鸣声
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
                crystal.playSound(world, pos);
            }
            return ActionResult.success(world.isClient);
        }

        // 如果物品没有放置成功，则只播放龙鸣声
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
            crystal.playSound(world, pos);
            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        // 在玩家开始破坏方块时播放龙鸣声
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
            crystal.playSound(world, pos);
        }

        // 调用父类方法（如果有默认逻辑）
        super.onBlockBreakStart(state, world, pos, player);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonCrystalBlockEntity(pos, state);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return false; // 不再发出红石信号
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return false; // 不再支持比较器输出
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL; // 保持模型渲染
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED); // 添加激活状态属性
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return null; // 不需要 ticker
    }
}