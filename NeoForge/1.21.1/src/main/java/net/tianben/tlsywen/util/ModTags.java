package net.tianben.tlsywen.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModTags {

    private ModTags() {}

    public static final class Blocks {

        private Blocks() {}

        public static final TagKey<Block> INCORRECT_FOR_DRAGON_TOOL =
                create("incorrect_for_dragon_tool");

        private static TagKey<Block> create(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MOD_ID, name));
        }
    }
}