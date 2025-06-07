package net.tianben.tlsywen.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.armor.ModArmorItemFabric;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolMaterial;
import net.tianben.tlsywen.item.sword.TheLastSwordYouWillEverNeedItem;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModItemsFabric {
    public static void registerItems() {
        //注册方块
        ModItems.COMPRESSED_STAR = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(MOD_ID, "compressed_star"),
                new BlockItem(ModBlocks.COMPRESSED_STAR, new Item.Properties()));
        ModItems.DRAGON_CRYSTAL_BLOCK = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(MOD_ID, "dragon_crystal_block"),
                new BlockItem(ModBlocks.DRAGON_CRYSTAL_BLOCK, new Item.Properties()));
        ModItems.DRAGON_CRYSTAL_ORE = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(MOD_ID, "dragon_crystal_ore"),
                new BlockItem(ModBlocks.DRAGON_CRYSTAL_ORE, new Item.Properties()));

        //注册最终之剑
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV1 = registerSword("the_last_sword_you_will_ever_need_lv1",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV1);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV2 = registerSword("the_last_sword_you_will_ever_need_lv2",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV2);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV3 = registerSword("the_last_sword_you_will_ever_need_lv3",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV3);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV4 = registerSword("the_last_sword_you_will_ever_need_lv4",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV4);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV5 = registerSword("the_last_sword_you_will_ever_need_lv5",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV5);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV6 = registerSword("the_last_sword_you_will_ever_need_lv6",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV6);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV7 = registerSword("the_last_sword_you_will_ever_need_lv7",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV7);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV8 = registerSword("the_last_sword_you_will_ever_need_lv8",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV8);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV9 = registerSword("the_last_sword_you_will_ever_need_lv9",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV9);
        ModItems.THELASTSWORDYOUWILLEVERNEEDLV10 = registerSword("the_last_sword_you_will_ever_need_lv10",
                ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV10);
        ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED = registerSword("really_the_last_sword_you_will_ever_need",
                ModToolMaterial.REALLYTHELASTSWORDYOUWILLEVERNEED);

        //注册装备
        ModItems.DRAGON_CRYSTAL_HELMET = registerArmor("dragon_crystal_helmet", ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.HELMET);
        ModItems.DRAGON_CRYSTAL_CHESTPLATE = registerArmor("dragon_crystal_chestplate", ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.CHESTPLATE);
        ModItems.DRAGON_CRYSTAL_LEGGINGS = registerArmor("dragon_crystal_leggings", ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.LEGGINGS);
        ModItems.DRAGON_CRYSTAL_BOOTS = registerArmor("dragon_crystal_boots", ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.BOOTS);

        //注册材料
        ModItems.DRAGON_CRYSTAL = registerItem("dragon_crystal", new Item(new FabricItemSettings()));
    }

    private static Item registerSword(String name, ModToolMaterial material) {
        return registerItem(name, new TheLastSwordYouWillEverNeedItem(material, new FabricItemSettings()));
    }

    private static Item registerArmor(String name, ArmorMaterial material, ArmorItem.Type type) {
        return registerItem(name, new ModArmorItemFabric(material, type, new FabricItemSettings()));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(MOD_ID, name), item);
    }
}