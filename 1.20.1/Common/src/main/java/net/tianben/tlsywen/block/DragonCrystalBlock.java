package net.tianben.tlsywen.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED;

public class DragonCrystalBlock extends BaseEntityBlock {
    public static final BooleanProperty ACTIVATED = POWERED;

    public DragonCrystalBlock(Properties settings) {
        super(settings);
        registerDefaultState(stateDefinition.any().setValue(ACTIVATED, false));
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        world.setBlock(pos, state.setValue(ACTIVATED, false), 2);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var useResult = player.getItemInHand(hand).useOn(new UseOnContext(player, hand, hit));
        if (world.getBlockEntity(pos) instanceof DragonCrystalBlockEntity crystal) {
            crystal.playSound(world, pos);
            return InteractionResult.sidedSuccess(world.isClientSide());
        }
        return useResult;
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
        return PlatformBridgeHolder.getBridge().createDragonCrystalBlockEntity(pos, state);
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