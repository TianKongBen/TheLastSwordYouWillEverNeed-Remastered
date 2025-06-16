package net.tianben.tlsywen.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModBlocksFabric {
    public static BlockEntityType<DragonCrystalBlockEntity> DRAGON_CRYSTAL_BE;

    public static void register() {
        // 注册方块
        Registry.register(
                BuiltInRegistries.BLOCK,
                new ResourceLocation(MOD_ID, "compressed_star"),
                ModBlocks.COMPRESSED_STAR
        );
        Registry.register(
                BuiltInRegistries.BLOCK,
                new ResourceLocation(MOD_ID, "dragon_crystal_block"),
                ModBlocks.DRAGON_CRYSTAL_BLOCK
        );
        Registry.register(
                BuiltInRegistries.BLOCK,
                new ResourceLocation(MOD_ID, "dragon_crystal_ore"),
                ModBlocks.DRAGON_CRYSTAL_ORE
        );

        // 注册方块实体
        DRAGON_CRYSTAL_BE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                new ResourceLocation(MOD_ID, "dragon_crystal_block_entity"),
                FabricBlockEntityTypeBuilder.create(
                        (pos, state) -> new DragonCrystalBlockEntity(DRAGON_CRYSTAL_BE, pos, state),
                        ModBlocks.DRAGON_CRYSTAL_BLOCK
                ).build()
        );
    }
}