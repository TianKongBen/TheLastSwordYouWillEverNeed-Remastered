package net.tianben.tlsywen.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.sound.ModSounds;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<Block> COMPRESSED_STAR = BLOCKS.register("compressed_star",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(0f, 6.0f)
                    .lightLevel(state -> 14)
                    .noOcclusion()));
    public static final RegistryObject<Block> DRAGON_CRYSTAL_BLOCK = BLOCKS.register("dragon_crystal_block",
            () -> new DragonCrystalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)
                    .requiresCorrectToolForDrops()
                    .strength(5.5f, 6.0f)));
    public static final RegistryObject<Block> DRAGON_CRYSTAL_ORE = BLOCKS.register("dragon_crystal_ore",
            () -> new DragonCrystalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)
                    .requiresCorrectToolForDrops()
                    .strength(5.5f, 6.0f)));

    @SuppressWarnings("DataFlowIssue")
    public static final RegistryObject<BlockEntityType<DragonCrystalBlockEntity>> DRAGON_CRYSTAL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dragon_crystal_block_entity",
                    () -> BlockEntityType.Builder.of(
                            DragonCrystalBlockEntity::new,
                            DRAGON_CRYSTAL_BLOCK.get(),
                            DRAGON_CRYSTAL_ORE.get()
                    ).build(null));

    private ModBlocks() {}

    public static void registerModBlocks(IEventBus bus) {
        TheLastSwordYouWillEverNeed.LOGGER.info("注册模组方块" + MOD_ID);
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }
}