package net.tianben.tlsywen.item.material;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public enum ModToolMaterial implements Tier {
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

    private static final float SPEED = 10.0f;
    private static final int ENCHANTMENT_VALUE = 20;
    private static final int LEVEL = 5;

    private final float attackDamageBonus;

    ModToolMaterial(float attackDamageBonus) {
        this.attackDamageBonus = attackDamageBonus;
    }

    @Override
    public int getUses() {
        return -1;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return LEVEL;
    }

    @Override
    public int getEnchantmentValue() {
        return ENCHANTMENT_VALUE;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}