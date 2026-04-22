package net.tianben.tlsywen.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> INCORRECT_FOR_DRAGON_TOOL = createTag("incorrect_for_dragon_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.tryBuild(MOD_ID, name));
        }
    }
}
