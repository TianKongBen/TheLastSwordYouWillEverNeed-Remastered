package net.tianben.tlsywen.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterial implements ToolMaterial {
    THELASTSWORDYOUWILLEVERNEEDLV1(10.0f, 1600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV2(10.0f, 2400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV3(10.0f, 3200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV4(10.0f, 4000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV5(10.0f, 4800f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV6(10.0f, 5600f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV7(10.0f, 6400f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV8(10.0f, 7200f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV9(10.0f, 8000f, 20),
    THELASTSWORDYOUWILLEVERNEEDLV10(10.0f, 8800f, 20),
    REALLYTHELASTSWORDYOUWILLEVERNEED(10.0f, 7999999874453995500f, 20);

    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;

    ModToolMaterial(float miningSpeed, float attackDamage, int enchantability) {
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
    }

    @Override
    public int getDurability() {
        return -1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return 5;
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
