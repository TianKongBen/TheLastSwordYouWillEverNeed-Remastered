package net.tianben.tlsywen.item.material;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> DRAGON_CRYSTAL = register("dragon_crystal",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 1);
                attribute.put(ArmorItem.Type.LEGGINGS, 1);
                attribute.put(ArmorItem.Type.CHESTPLATE, 2);
                attribute.put(ArmorItem.Type.HELMET, 1);
            }), 20, BuiltInRegistries.SOUND_EVENT
                    .getHolderOrThrow(BuiltInRegistries.SOUND_EVENT.getResourceKey(SoundEvents.ENDER_DRAGON_GROWL).get()),
            0f, 0f);

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, Holder<SoundEvent> equipSound,
                                                  float toughness, float knockbackResistance) {
        ResourceLocation location = ResourceLocation.tryBuild(MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of();
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}