package net.tianben.tlsywen.item.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> DRAGON_CRYSTAL = registerArmorMaterial("dragon_crystal",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 1);
                map.put(ArmorItem.Type.CHESTPLATE, 2);
                map.put(ArmorItem.Type.HELMET, 1);
    }), 20, Registries.SOUND_EVENT.getEntry(SoundEvents.ENTITY_ENDER_DRAGON_GROWL), Ingredient::ofItems,
                    List.of(new ArmorMaterial.Layer(Identifier.of(MOD_ID, "dragon_crystal"))), 0, 0));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(MOD_ID, name), material.get());
    }
}