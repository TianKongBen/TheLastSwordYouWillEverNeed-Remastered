package net.tianben.tlsywen.item.material;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

public enum ModArmorMaterials implements ArmorMaterial {
    DRAGON_CRYSTAL(
            "dragon_crystal",
            new int[]{1, 2, 1, 1},
            20,
            SoundEvents.ENDER_DRAGON_GROWL,
            0F,
            0F
    );

    private final String name;
    private final int[] protectionForSlot;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;

    ModArmorMaterials(String name, int[] protectionForSlot, int enchantability,
                      SoundEvent equipSound, float toughness, float knockbackResistance) {
        this.name = name;
        this.protectionForSlot = protectionForSlot;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return -1;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protectionForSlot[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }

    @Override
    public String getName() {
        return TheLastSwordYouWillEverNeed.MOD_ID + ":" + name;
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