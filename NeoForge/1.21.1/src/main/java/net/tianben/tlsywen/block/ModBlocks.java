package net.tianben.tlsywen.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.sound.ModSounds;

import java.util.function.Supplier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModBlocks {

    private ModBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);

    public static final DeferredBlock<Block> COMPRESSED_STAR = registerBlock("compressed_star",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0f, 6.0f)
                    .lightLevel(state -> 14)));

    public static final DeferredBlock<DragonCrystalBlock> DRAGON_CRYSTAL_BLOCK =
            registerBlock("dragon_crystal_block", () -> new DragonCrystalBlock(
                    BlockBehaviour.Properties.of()
                            .strength(5.5f, 6.0f)
                            .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)));

    public static final DeferredBlock<DragonCrystalBlock> DRAGON_CRYSTAL_ORE =
            registerBlock("dragon_crystal_ore", () -> new DragonCrystalBlock(
                    BlockBehaviour.Properties.of()
                            .strength(5.5f, 6.0f)
                            .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DragonCrystalBlockEntity>>
            DRAGON_CRYSTAL_BE = BLOCK_ENTITIES.register("dragon_crystal_block_entity",
            () -> BlockEntityType.Builder.of(
                    DragonCrystalBlockEntity::new,
                    DRAGON_CRYSTAL_BLOCK.get(),
                    DRAGON_CRYSTAL_ORE.get()
            ).build(null));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
}