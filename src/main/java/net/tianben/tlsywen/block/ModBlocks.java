package net.tianben.tlsywen.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.sound.ModSounds;

public class ModBlocks {
    //注册方块
    public static final Block COMPRESSED_STAR = registerBlock("compressed_star",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).strength(0f, 6.0f)
                    .luminance(state -> 14).solidBlock(Blocks::never)));
    public static final Block DRAGON_CRYSTAL_BLOCK = registerBlock("dragon_crystal_block",
            new SoundBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM).requiresTool().strength(5.5f, 6.0f)
                    .sounds(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)));
    public static final Block DRAGON_CRYSTAL_ORE = registerBlock("dragon_crystal_ore",
            new SoundBlock(FabricBlockSettings.create().mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM).requiresTool().strength(5.5f, 6.0f)
                    .sounds(ModSounds.DRAGON_CRYSTAL_BLOCK_SOUNDS)));

    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        TheLastSwordYouWillEverNeed.LOGGER.info("Registering ModBlocks for " + TheLastSwordYouWillEverNeed.MOD_ID);
    }
}
