package net.tianben.tlsywen.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.armor.ModArmorItem;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolTiers;
import net.tianben.tlsywen.item.sword.TheLastSwordYouWillEverNeedItem;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModItems {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    // 注册方块
    public static final DeferredHolder<Item, BlockItem> COMPRESSED_STAR = ITEMS.register(
            "compressed_star", () -> new BlockItem(ModBlocks.COMPRESSED_STAR.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> DRAGON_CRYSTAL_BLOCK = ITEMS.register(
            "dragon_crystal_block", () -> new BlockItem(ModBlocks.DRAGON_CRYSTAL_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> DRAGON_CRYSTAL_ORE = ITEMS.register(
            "dragon_crystal_ore", () -> new BlockItem(ModBlocks.DRAGON_CRYSTAL_ORE.get(),
                    new Item.Properties()));

    // 注册最终之剑
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV1 = ITEMS.register("the_last_sword_you_will_ever_need_lv1",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV1, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV1, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV2 = ITEMS.register("the_last_sword_you_will_ever_need_lv2",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV2, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV2, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV3 = ITEMS.register("the_last_sword_you_will_ever_need_lv3",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV3, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV3, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV4 = ITEMS.register("the_last_sword_you_will_ever_need_lv4",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV4, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV4, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV5 = ITEMS.register("the_last_sword_you_will_ever_need_lv5",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV5, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV5, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV6 = ITEMS.register("the_last_sword_you_will_ever_need_lv6",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV6, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV6, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV7 = ITEMS.register("the_last_sword_you_will_ever_need_lv7",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV7, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV7, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV8 = ITEMS.register("the_last_sword_you_will_ever_need_lv8",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV8, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV8, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV9 = ITEMS.register("the_last_sword_you_will_ever_need_lv9",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV9, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV9, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV10 = ITEMS.register("the_last_sword_you_will_ever_need_lv10",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV10, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.THELASTSWORDYOUWILLEVERNEEDLV10, -1, -2.4f))));
    public static final DeferredItem<TheLastSwordYouWillEverNeedItem> REALLYTHELASTSWORDYOUWILLEVERNEED = ITEMS.register("really_the_last_sword_you_will_ever_need",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolTiers.REALLYTHELASTSWORDYOUWILLEVERNEED, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.REALLYTHELASTSWORDYOUWILLEVERNEED, -1, -2.4f))));

    // 注册装备
    public static final DeferredItem<ArmorItem> DRAGON_CRYSTAL_HELMET = ITEMS.register("dragon_crystal_helmet",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<ArmorItem> DRAGON_CRYSTAL_CHESTPLATE = ITEMS.register("dragon_crystal_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<ArmorItem> DRAGON_CRYSTAL_LEGGINGS = ITEMS.register("dragon_crystal_leggings",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<ArmorItem> DRAGON_CRYSTAL_BOOTS = ITEMS.register("dragon_crystal_boots",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // 注册材料
    public static final DeferredItem<Item> DRAGON_CRYSTAL = ITEMS.register("dragon_crystal",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}