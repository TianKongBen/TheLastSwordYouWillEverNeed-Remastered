package net.tianben.tlsywen.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterial implements ToolMaterial {

    THELASTSWORDYOUWILLEVERNEEDLV1(1600f),
    THELASTSWORDYOUWILLEVERNEEDLV2(2400f),
    THELASTSWORDYOUWILLEVERNEEDLV3(3200f),
    THELASTSWORDYOUWILLEVERNEEDLV4(4000f),
    THELASTSWORDYOUWILLEVERNEEDLV5(4800f),
    THELASTSWORDYOUWILLEVERNEEDLV6(5600f),
    THELASTSWORDYOUWILLEVERNEEDLV7(6400f),
    THELASTSWORDYOUWILLEVERNEEDLV8(7200f),
    THELASTSWORDYOUWILLEVERNEEDLV9(8000f),
    THELASTSWORDYOUWILLEVERNEEDLV10(8800f),
    REALLYTHELASTSWORDYOUWILLEVERNEED(7999999874453995500f);

    private static final float MINING_SPEED = 10.0f;
    private static final int ENCHANTABILITY = 20;
    private static final int MINING_LEVEL = 5;

    private final float attackDamage;

    ModToolMaterial(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public int getDurability() {
        return -1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return MINING_SPEED;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return MINING_LEVEL;
    }

    @Override
    public int getEnchantability() {
        return ENCHANTABILITY;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
