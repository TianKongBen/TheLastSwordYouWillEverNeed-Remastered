package net.tianben.tlsywen.item.material;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.tianben.tlsywen.util.ModTags;

public enum ModToolMaterials implements ToolMaterial {
    THELASTSWORDYOUWILLEVERNEEDLV1(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 1600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV2(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 2400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV3(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 3200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV4(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 4000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV5(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 4800f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV6(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 5600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV7(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 6400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV8(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 7200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV9(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 8000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV10(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 8800f, 20),
    REALLYTHELASTSWORDYOUWILLEVERNEED(ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL, 2400f, 7999999874453995500f, 20);

    private final TagKey<Block> inverseTag;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;

    ModToolMaterials(final TagKey<Block> inverseTag, final float miningSpeed, final float attackDamage, final int enchantability) {
        this.inverseTag = inverseTag;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
    }

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return inverseTag;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems();
    }
}
