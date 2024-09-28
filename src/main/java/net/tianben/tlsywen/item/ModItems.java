package net.tianben.tlsywen.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolMaterial;
import net.tianben.tlsywen.item.armor.ModArmorItem;
import net.tianben.tlsywen.item.sword.*;

public class ModItems{
    //注册方块物品
    public static final Item COMPRESSED_STAR = Items.register(ModBlocks.COMPRESSED_STAR);
    public static final Item DRAGON_CRYSTAL_BLOCK = Items.register(ModBlocks.DRAGON_CRYSTAL_BLOCK);
    public static final Item DRAGON_CRYSTAL_ORE = Items.register(ModBlocks.DRAGON_CRYSTAL_ORE);

    //注册最终之剑
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV1 = registerItem("the_last_sword_you_will_ever_need_lv1",
            new TheLastSwordYouWillEverNeedlv1Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV1));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV2 = registerItem("the_last_sword_you_will_ever_need_lv2",
            new TheLastSwordYouWillEverNeedlv2Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV2));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV3 = registerItem("the_last_sword_you_will_ever_need_lv3",
            new TheLastSwordYouWillEverNeedlv3Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV3));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV4 = registerItem("the_last_sword_you_will_ever_need_lv4",
            new TheLastSwordYouWillEverNeedlv4Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV4));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV5 = registerItem("the_last_sword_you_will_ever_need_lv5",
            new TheLastSwordYouWillEverNeedlv5Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV5));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV6 = registerItem("the_last_sword_you_will_ever_need_lv6",
            new TheLastSwordYouWillEverNeedlv6Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV6));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV7 = registerItem("the_last_sword_you_will_ever_need_lv7",
            new TheLastSwordYouWillEverNeedlv7Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV7));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV8 = registerItem("the_last_sword_you_will_ever_need_lv8",
            new TheLastSwordYouWillEverNeedlv8Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV8));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV9 = registerItem("the_last_sword_you_will_ever_need_lv9",
            new TheLastSwordYouWillEverNeedlv9Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV9));
    public static final Item THELASTSWORDYOUWILLEVERNEEDLV10 = registerItem("the_last_sword_you_will_ever_need_lv10",
            new TheLastSwordYouWillEverNeedlv10Item(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV10));
    public static final Item REALLYTHELASTSWORDYOUWILLEVERNEED = registerItem("really_the_last_sword_you_will_ever_need",
            new ReallyTheLastSwordYouWillEverNeedItem(ModToolMaterial.REALLYTHELASTSWORDYOUWILLEVERNEED));

    //注册装备
    public static final Item DRAGON_CRYSTAL_HELMET = registerItem("dragon_crystal_helmet",
            new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item DRAGON_CRYSTAL_CHESTPLATE = registerItem("dragon_crystal_chestplate",
            new ArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item DRAGON_CRYSTAL_LEGGINGS = registerItem("dragon_crystal_leggings",
            new ArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item DRAGON_CRYSTAL_BOOTS = registerItem("dragon_crystal_boots",
            new ArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.BOOTS,
                    new FabricItemSettings()));

    //注册材料
    public static final Item DRAGON_CRYSTAL = registerItem("dragon_crystal",
            new Item(new FabricItemSettings()));

    //将压缩星和龙晶块添加到建筑方块
    private static void addItemToBuilding_blocksItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.NETHERITE_BLOCK, COMPRESSED_STAR);
        entries.addAfter(COMPRESSED_STAR, DRAGON_CRYSTAL_BLOCK);
    }

    //将龙晶矿添加到自然方块
    private static void addItemToNaturalItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.ANCIENT_DEBRIS, DRAGON_CRYSTAL_ORE);
    }

    //将最终之剑和龙晶套添加到战斗用品
    private static void addItemToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.NETHERITE_SWORD, THELASTSWORDYOUWILLEVERNEEDLV1);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV1, THELASTSWORDYOUWILLEVERNEEDLV2);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV2, THELASTSWORDYOUWILLEVERNEEDLV3);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV3, THELASTSWORDYOUWILLEVERNEEDLV4);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV4, THELASTSWORDYOUWILLEVERNEEDLV5);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV5, THELASTSWORDYOUWILLEVERNEEDLV6);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV6, THELASTSWORDYOUWILLEVERNEEDLV7);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV7, THELASTSWORDYOUWILLEVERNEEDLV8);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV8, THELASTSWORDYOUWILLEVERNEEDLV9);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV9, THELASTSWORDYOUWILLEVERNEEDLV10);
        entries.addAfter(THELASTSWORDYOUWILLEVERNEEDLV10, REALLYTHELASTSWORDYOUWILLEVERNEED);
        entries.addAfter(Items.NETHERITE_BOOTS, DRAGON_CRYSTAL_HELMET);
        entries.addAfter(DRAGON_CRYSTAL_HELMET, DRAGON_CRYSTAL_CHESTPLATE);
        entries.addAfter(DRAGON_CRYSTAL_CHESTPLATE, DRAGON_CRYSTAL_LEGGINGS);
        entries.addAfter(DRAGON_CRYSTAL_LEGGINGS, DRAGON_CRYSTAL_BOOTS);
    }

    //将龙晶添加到原材料
    private static void addItemToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.NETHERITE_INGOT, DRAGON_CRYSTAL);
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, name), item);
    }
    public static void registerModItems() {
        TheLastSwordYouWillEverNeed.LOGGER.info("Registering Mod Items for " + TheLastSwordYouWillEverNeed.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemToBuilding_blocksItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemToNaturalItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemToCombatItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIngredientItemGroup);
    }
}
