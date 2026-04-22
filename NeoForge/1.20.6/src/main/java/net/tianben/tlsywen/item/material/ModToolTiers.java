package net.tianben.tlsywen.item.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.tianben.tlsywen.util.ModTags;

public enum ModToolTiers implements Tier {
    THELASTSWORDYOUWILLEVERNEEDLV1(2400f, 1600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV2(2400f, 2400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV3(2400f, 3200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV4(2400f, 4000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV5(2400f, 4800f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV6(2400f, 5600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV7(2400f, 6400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV8(2400f, 7200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV9(2400f, 8000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV10(2400f, 8800f, 20),
    REALLYTHELASTSWORDYOUWILLEVERNEED(2400f, 7999999874453995500f, 20);

    private final float speed;
    private final float damage;
    private final int enchantmentValue;

    ModToolTiers(float speed, float damage, int enchantmentValue) {
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
    }

    @Override
    public int getUses() {
        return 0;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return ModTags.Blocks.INCORRECT_FOR_DRAGON_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }
}