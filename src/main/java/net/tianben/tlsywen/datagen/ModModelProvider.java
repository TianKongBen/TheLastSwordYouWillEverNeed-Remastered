package net.tianben.tlsywen.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COMPRESSED_STAR);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DRAGON_CRYSTAL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DRAGON_CRYSTAL, Models.GENERATED);

        itemModelGenerator.register(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED, Models.HANDHELD);
    }
}
