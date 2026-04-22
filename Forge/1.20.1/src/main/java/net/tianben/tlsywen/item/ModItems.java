package net.tianben.tlsywen.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.armor.ModArmorItem;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolMaterial;
import net.tianben.tlsywen.item.sword.TheLastSwordYouWillEverNeedItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheLastSwordYouWillEverNeed.MOD_ID);

    // 注册方块物品
    public static final RegistryObject<Item> COMPRESSED_STAR = ITEMS.register("compressed_star",
            () -> new BlockItem(ModBlocks.COMPRESSED_STAR.get(), new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_BLOCK = ITEMS.register("dragon_crystal_block",
            () -> new BlockItem(ModBlocks.DRAGON_CRYSTAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_ORE = ITEMS.register("dragon_crystal_ore",
            () -> new BlockItem(ModBlocks.DRAGON_CRYSTAL_ORE.get(), new Item.Properties()));

    // 注册最终之剑
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV1 = registerItem("the_last_sword_you_will_ever_need_lv1",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV1));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV2 = registerItem("the_last_sword_you_will_ever_need_lv2",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV2));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV3 = registerItem("the_last_sword_you_will_ever_need_lv3",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV3));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV4 = registerItem("the_last_sword_you_will_ever_need_lv4",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV4));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV5 = registerItem("the_last_sword_you_will_ever_need_lv5",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV5));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV6 = registerItem("the_last_sword_you_will_ever_need_lv6",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV6));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV7 = registerItem("the_last_sword_you_will_ever_need_lv7",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV7));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV8 = registerItem("the_last_sword_you_will_ever_need_lv8",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV8));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV9 = registerItem("the_last_sword_you_will_ever_need_lv9",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV9));
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV10 = registerItem("the_last_sword_you_will_ever_need_lv10",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV10));
    public static final RegistryObject<Item> REALLYTHELASTSWORDYOUWILLEVERNEED = registerItem("really_the_last_sword_you_will_ever_need",
            () -> new TheLastSwordYouWillEverNeedItem(ModToolMaterial.REALLYTHELASTSWORDYOUWILLEVERNEED));

    // 注册装备
    public static final RegistryObject<Item> DRAGON_CRYSTAL_HELMET = registerItem("dragon_crystal_helmet",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_CHESTPLATE = registerItem("dragon_crystal_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_LEGGINGS = registerItem("dragon_crystal_leggings",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_BOOTS = registerItem("dragon_crystal_boots",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_CRYSTAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // 注册材料
    public static final RegistryObject<Item> DRAGON_CRYSTAL = registerItem("dragon_crystal",
            () -> new Item(new Item.Properties()));

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    public static void registerModItems(@NotNull IEventBus eventBus) {
        TheLastSwordYouWillEverNeed.LOGGER.info("注册模组物品" + MOD_ID);
        ITEMS.register(eventBus);
    }
}