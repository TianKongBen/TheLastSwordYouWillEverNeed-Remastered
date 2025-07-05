package net.tianben.tlsywen.item.material;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModToolMaterial implements Tier {
    THELASTSWORDYOUWILLEVERNEEDLV1(-1, 10.0f, 1600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV2(-1, 10.0f, 2400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV3(-1, 10.0f, 3200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV4(-1, 10.0f, 4000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV5(-1, 10.0f, 4800f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV6(-1, 10.0f, 5600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV7(-1, 10.0f, 6400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV8(-1, 10.0f, 7200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV9(-1, 10.0f, 8000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV10(-1, 10.0f, 8800f, 20),
    REALLYTHELASTSWORDYOUWILLEVERNEED(-1, 10.0f, 7999999874453995500f, 20);

    private static final int TOOL_LEVEL = 5;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;

    ModToolMaterial(int durability, float miningSpeed, float attackDamage, int enchantability) {
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
    }

    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return TOOL_LEVEL;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }
}