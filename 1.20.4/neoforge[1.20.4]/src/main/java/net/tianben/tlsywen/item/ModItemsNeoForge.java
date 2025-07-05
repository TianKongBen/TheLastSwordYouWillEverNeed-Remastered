package net.tianben.tlsywen.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.block.ModBlocksNeoForge;
import net.tianben.tlsywen.item.armor.ModArmorItemNeoForge;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolMaterial;
import net.tianben.tlsywen.item.sword.TheLastSwordYouWillEverNeedItem;
import org.jetbrains.annotations.NotNull;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModItemsNeoForge {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.createItems(MOD_ID);

    // 注册方块
    public static final DeferredHolder<Item, BlockItem> COMPRESSED_STAR = ITEMS.register(
            "compressed_star", () -> new BlockItem(ModBlocksNeoForge.COMPRESSED_STAR.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> DRAGON_CRYSTAL_BLOCK = ITEMS.register(
            "dragon_crystal_block", () -> new BlockItem(ModBlocksNeoForge.DRAGON_CRYSTAL_BLOCK.get(),
                    new Item.Properties()));
    public static final DeferredHolder<Item, BlockItem> DRAGON_CRYSTAL_ORE = ITEMS.register(
            "dragon_crystal_ore", () -> new BlockItem(ModBlocksNeoForge.DRAGON_CRYSTAL_ORE.get(),
                    new Item.Properties()));

    // 注册最终之剑
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV1 = registerSword(
            "the_last_sword_you_will_ever_need_lv1", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV1);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV2 = registerSword(
            "the_last_sword_you_will_ever_need_lv2", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV2);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV3 = registerSword(
            "the_last_sword_you_will_ever_need_lv3", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV3);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV4 = registerSword(
            "the_last_sword_you_will_ever_need_lv4", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV4);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV5 = registerSword(
            "the_last_sword_you_will_ever_need_lv5", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV5);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV6 = registerSword(
            "the_last_sword_you_will_ever_need_lv6", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV6);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV7 = registerSword(
            "the_last_sword_you_will_ever_need_lv7", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV7);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV8 = registerSword(
            "the_last_sword_you_will_ever_need_lv8", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV8);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV9 = registerSword(
            "the_last_sword_you_will_ever_need_lv9", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV9);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> THELASTSWORDYOUWILLEVERNEEDLV10 = registerSword(
            "the_last_sword_you_will_ever_need_lv10", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV10);
    public static final DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> REALLYTHELASTSWORDYOUWILLEVERNEED = registerSword(
            "really_the_last_sword_you_will_ever_need", ModToolMaterial.REALLYTHELASTSWORDYOUWILLEVERNEED);

    // 注册装备
    static ArmorMaterial material = ModArmorMaterials.DRAGON_CRYSTAL;
    public static final DeferredHolder<Item, ModArmorItemNeoForge> DRAGON_CRYSTAL_HELMET = registerArmor(
            "dragon_crystal_helmet", material, EquipmentSlot.HEAD);
    public static final DeferredHolder<Item, ModArmorItemNeoForge> DRAGON_CRYSTAL_CHESTPLATE = registerArmor(
            "dragon_crystal_chestplate", material, EquipmentSlot.CHEST);
    public static final DeferredHolder<Item, ModArmorItemNeoForge> DRAGON_CRYSTAL_LEGGINGS = registerArmor(
            "dragon_crystal_leggings", material, EquipmentSlot.LEGS);
    public static final DeferredHolder<Item, ModArmorItemNeoForge> DRAGON_CRYSTAL_BOOTS = registerArmor(
            "dragon_crystal_boots", material, EquipmentSlot.FEET);

    // 注册材料
    public static final DeferredItem<Item> DRAGON_CRYSTAL = (DeferredItem<Item>) ITEMS.register(
            "dragon_crystal", () -> new Item(new Item.Properties()));

    private ModItemsNeoForge() {}

    public static void register(@NotNull IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static DeferredHolder<Item, TheLastSwordYouWillEverNeedItem> registerSword(String name, ModToolMaterial material) {
        return ITEMS.register(name,
                () -> new TheLastSwordYouWillEverNeedItem(material, new Item.Properties()));
    }

    private static DeferredHolder<Item, ModArmorItemNeoForge> registerArmor(String name, ArmorMaterial material, EquipmentSlot slot) {
        return ITEMS.register(name, () -> new ModArmorItemNeoForge(material, slotToType(slot), new Item.Properties()));
    }

    private static @NotNull ArmorItem.Type slotToType(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> ArmorItem.Type.HELMET;
            case CHEST -> ArmorItem.Type.CHESTPLATE;
            case LEGS -> ArmorItem.Type.LEGGINGS;
            case FEET -> ArmorItem.Type.BOOTS;
            default -> throw new IllegalArgumentException("Invalid armor slot: " + slot);
        };
    }
}