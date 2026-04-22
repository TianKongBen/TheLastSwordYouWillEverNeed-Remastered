package net.tianben.tlsywen.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class DragonCrystalBlock extends BaseEntityBlock {
    public static final BooleanProperty ACTIVATED = POWERED;

    public static final MapCodec<DragonCrystalBlock> CODEC = simpleCodec(DragonCrystalBlock::new);

    public DragonCrystalBlock(Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        world.setBlock(pos, state.setValue(ACTIVATED, false), 2);
    }

    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (world.isClientSide) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
                crystal.playSound(world, pos);
            }
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonCrystalBlockEntity crystal) {
                crystal.playSound(world, pos);
            }
            if (stack.getItem() instanceof BlockItem) {
                return stack.useOn(new UseOnContext(player, player.getUsedItemHand(), hit));
            } else {
                return InteractionResult.CONSUME;
            }
        }
    }

    @Override
    public void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (world.getBlockEntity(pos) instanceof DragonCrystalBlockEntity crystal) {
            crystal.playSound(world, pos);
        }
        super.attack(state, world, pos, player);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DragonCrystalBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return null;
    }
}