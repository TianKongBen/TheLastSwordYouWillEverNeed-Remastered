package net.tianben.tlsywen.item.material;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum ModToolMaterial implements Tier {
    THELASTSWORDYOUWILLEVERNEEDLV1(10.0f, 1600f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV2(10.0f, 2400f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV3(10.0f, 3200f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV4(10.0f, 4000f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV5(10.0f, 4800f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV6(10.0f, 5600f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV7(10.0f, 6400f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV8(10.0f, 7200f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV9(10.0f, 8000f, 20, 5),
    THELASTSWORDYOUWILLEVERNEEDLV10(10.0f, 8800f, 20, 5),
    REALLYTHELASTSWORDYOUWILLEVERNEED(10.0f, 7999999874453995500f, 20, 5);

    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final int level;

    ModToolMaterial(float speed, float attackDamageBonus, int enchantmentValue, int level) {
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.level = level;
    }

    @Override
    public int getUses() {
        return -1;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}