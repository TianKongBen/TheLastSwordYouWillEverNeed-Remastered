package net.tianben.tlsywen.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.DRAGON_CRYSTAL, RecipeCategory.DECORATIONS,
                ModBlocks.DRAGON_CRYSTAL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COMPRESSED_STAR)
                .pattern("NN")
                .pattern("NN")
                .input('N', Items.NETHER_STAR)
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COMPRESSED_STAR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DRAGON_EGG)
                .pattern("ODO")
                .pattern("OCO")
                .pattern("OOO")
                .input('O', Items.OBSIDIAN)
                .input('D', Items.DIAMOND_BLOCK)
                .input('C', ModBlocks.COMPRESSED_STAR)
                .criterion(hasItem(ModBlocks.COMPRESSED_STAR), conditionsFromItem(ModBlocks.COMPRESSED_STAR))
                .offerTo(exporter, new Identifier(getRecipeName(Items.DRAGON_EGG)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV1)
                .pattern(" D ")
                .pattern("DED")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('E', Items.DRAGON_EGG)
                .criterion(hasItem(Items.DRAGON_EGG), conditionsFromItem(Items.DRAGON_EGG))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV2)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV1)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV3)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV2)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV4)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV3)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV5)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV4)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV6)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV5)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV7)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV6)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV8)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV7)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV9)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV8)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THELASTSWORDYOUWILLEVERNEEDLV10)
                .pattern(" D ")
                .pattern("DTD")
                .pattern(" D ")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV9)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED)
                .pattern("DDD")
                .pattern("DTD")
                .pattern("DDD")
                .input('D', Items.DIAMOND_BLOCK)
                .input('T', ModItems.THELASTSWORDYOUWILLEVERNEEDLV10)
                .criterion(hasItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10), conditionsFromItem(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_CRYSTAL_HELMET)
                .pattern("DDD")
                .pattern("D D")
                .input('D', ModItems.DRAGON_CRYSTAL)
                .criterion(hasItem(ModItems.DRAGON_CRYSTAL), conditionsFromItem(ModItems.DRAGON_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DRAGON_CRYSTAL_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_CRYSTAL_CHESTPLATE)
                .pattern("D D")
                .pattern("DDD")
                .pattern("DDD")
                .input('D', ModItems.DRAGON_CRYSTAL)
                .criterion(hasItem(ModItems.DRAGON_CRYSTAL), conditionsFromItem(ModItems.DRAGON_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DRAGON_CRYSTAL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_CRYSTAL_LEGGINGS)
                .pattern("DDD")
                .pattern("D D")
                .pattern("D D")
                .input('D', ModItems.DRAGON_CRYSTAL)
                .criterion(hasItem(ModItems.DRAGON_CRYSTAL), conditionsFromItem(ModItems.DRAGON_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DRAGON_CRYSTAL_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_CRYSTAL_BOOTS)
                .pattern("D D")
                .pattern("D D")
                .input('D', ModItems.DRAGON_CRYSTAL)
                .criterion(hasItem(ModItems.DRAGON_CRYSTAL), conditionsFromItem(ModItems.DRAGON_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DRAGON_CRYSTAL_BOOTS)));
    }
}
