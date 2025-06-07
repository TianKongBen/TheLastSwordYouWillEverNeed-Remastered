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

    private final float attackDamage;
    private final int durability;
    private final int speed;
    private final int enchantability;

    ModToolMaterial(int durability, float speed, float attackDamage, int enchantability) {
        this.durability = durability;
        this.speed = (int) speed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }
}
