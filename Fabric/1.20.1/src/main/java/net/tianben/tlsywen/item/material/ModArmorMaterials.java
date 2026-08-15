package net.tianben.tlsywen.item.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public enum ModArmorMaterials implements ArmorMaterial {

    DRAGON_CRYSTAL(
            "dragon_crystal",
            new int[]{1, 2, 1, 1},
            20,
            SoundEvents.ENTITY_ENDER_DRAGON_GROWL,
            0f,
            0f
    );

    private final String name;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;

    ModArmorMaterials(String name, int[] protectionAmounts, int enchantability,
                      SoundEvent equipSound, float toughness, float knockbackResistance) {
        this.name = name;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return -1;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
