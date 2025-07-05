package net.tianben.tlsywen.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModBlocksNeoForge {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID);

    // 方块注册
    public static final DeferredBlock<Block> COMPRESSED_STAR = (DeferredBlock<Block>) BLOCKS.register(
            "compressed_star",
            () -> ModBlocks.COMPRESSED_STAR
    );
    public static final DeferredBlock<Block> DRAGON_CRYSTAL_BLOCK = (DeferredBlock<Block>) BLOCKS.register(
            "dragon_crystal_block",
            () -> ModBlocks.DRAGON_CRYSTAL_BLOCK
    );
    public static final DeferredBlock<Block> DRAGON_CRYSTAL_ORE = (DeferredBlock<Block>) BLOCKS.register(
            "dragon_crystal_ore",
            () -> ModBlocks.DRAGON_CRYSTAL_ORE
    );

    // 方块实体注册
    private static final BlockEntityTypeHolder DRAGON_CRYSTAL_BE_HOLDER = new BlockEntityTypeHolder();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DragonCrystalBlockEntity>> DRAGON_CRYSTAL_BE =
            BLOCK_ENTITIES.register(
                    "dragon_crystal_block_entity",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new DragonCrystalBlockEntity(DRAGON_CRYSTAL_BE_HOLDER.get(), pos, state),
                            DRAGON_CRYSTAL_BLOCK.get()
                    ).build(null)
            );

    static {
        DRAGON_CRYSTAL_BE_HOLDER.setSupplier(DRAGON_CRYSTAL_BE::get);
    }

    private static final class BlockEntityTypeHolder {
        private BlockEntityType<DragonCrystalBlockEntity> type;
        private Supplier<BlockEntityType<DragonCrystalBlockEntity>> supplier;

        public void setSupplier(Supplier<BlockEntityType<DragonCrystalBlockEntity>> supplier) {
            this.supplier = supplier;
        }

        public BlockEntityType<DragonCrystalBlockEntity> get() {
            if (type == null) {
                type = supplier.get();
            }
            return type;
        }
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }
}