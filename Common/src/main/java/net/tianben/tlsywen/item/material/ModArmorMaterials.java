package net.tianben.tlsywen.item.material;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

public enum ModArmorMaterials implements ArmorMaterial {
    DRAGON_CRYSTAL("dragon_crystal", new int[] { 1, 2, 1, 1}, 20,
            SoundEvents.ENDER_DRAGON_GROWL, 0F, 0F);

    private final String name;
    private final int enchantability;
    private final int[] slotProtections;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;

    ModArmorMaterials(String name, int[] slotProtections, int enchantability, SoundEvent sound, float toughness, float knockbackResistance) {
        this.name = name;
        this.slotProtections = slotProtections;
        this.enchantability = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return -1;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.slotProtections[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }

    @Override
    public String getName() {
        return TheLastSwordYouWillEverNeed.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
