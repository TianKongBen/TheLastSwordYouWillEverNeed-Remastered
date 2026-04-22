package net.tianben.tlsywen.util;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_DRAGON_TOOL = createTag("incorrect_for_dragon_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
        }
    }
}