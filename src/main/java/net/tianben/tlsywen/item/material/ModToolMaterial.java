package net.tianben.tlsywen.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.tianben.tlsywen.item.ModItems;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    THELASTSWORDYOUWILLEVERNEEDLV1(-1, 10.0f, 1600f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV2(-1, 10.0f, 2400f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV3(-1, 10.0f, 3200f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV4(-1, 10.0f, 4000f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV5(-1, 10.0f, 4800f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV6(-1, 10.0f, 5600f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV7(-1, 10.0f, 6400f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV8(-1, 10.0f, 7200f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV9(-1, 10.0f, 8000f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    THELASTSWORDYOUWILLEVERNEEDLV10(-1, 10.0f, 8800f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL)),
    REALLYTHELASTSWORDYOUWILLEVERNEED(-1, 10.0f, 7999999874453995500f, 20, () -> Ingredient.ofItems(ModItems.DRAGON_CRYSTAL))
    ;

    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int itemDurability, float miningSpeed, float attckDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attckDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
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
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
