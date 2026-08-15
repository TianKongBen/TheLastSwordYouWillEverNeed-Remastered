package net.tianben.tlsywen.item.material;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModArmorTiers {

    private ModArmorTiers() {}

    public static final Holder<ArmorMaterial> DRAGON_CRYSTAL = register(
            "dragon_crystal",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 1);
                attribute.put(ArmorItem.Type.CHESTPLATE, 2);
                attribute.put(ArmorItem.Type.HELMET, 1);
            }),
            20,
            SoundEvents.ENDER_DRAGON_GROWL,
            0.0F,
            0.0F);

    private static Holder<ArmorMaterial> register(String name,
                                                  EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability,
                                                  SoundEvent equipSound,
                                                  float toughness,
                                                  float knockbackResistance) {
        var location = ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
        Supplier<Ingredient> repairIngredient = () -> Ingredient.of();
        var layers = List.of(new ArmorMaterial.Layer(location));

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                location,
                new ArmorMaterial(
                        typeProtection,
                        enchantability,
                        BuiltInRegistries.SOUND_EVENT.wrapAsHolder(equipSound),
                        repairIngredient,
                        layers,
                        toughness,
                        knockbackResistance));
    }
}