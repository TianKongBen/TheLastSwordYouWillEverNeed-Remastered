package net.tianben.tlsywen.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tianben.tlsywen.block.ModBlocksForge;
import net.tianben.tlsywen.item.armor.ModArmorItemForge;
import net.tianben.tlsywen.item.material.ModArmorMaterials;
import net.tianben.tlsywen.item.material.ModToolMaterial;
import net.tianben.tlsywen.item.sword.TheLastSwordYouWillEverNeedItem;
import org.jetbrains.annotations.NotNull;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModItemsForge {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // 注册方块
    public static final RegistryObject<Item> COMPRESSED_STAR = ITEMS.register(
            "compressed_star", () -> new BlockItem(ModBlocksForge.COMPRESSED_STAR.get(),
                    new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_BLOCK = ITEMS.register(
            "dragon_crystal_block", () -> new BlockItem(ModBlocksForge.DRAGON_CRYSTAL_BLOCK.get(),
                    new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CRYSTAL_ORE = ITEMS.register(
            "dragon_crystal_ore", () -> new BlockItem(ModBlocksForge.DRAGON_CRYSTAL_ORE.get(),
                    new Item.Properties()));

    // 注册最终之剑
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV1 = registerSword(
            "the_last_sword_you_will_ever_need_lv1", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV1);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV2 = registerSword(
            "the_last_sword_you_will_ever_need_lv2", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV2);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV3 = registerSword(
            "the_last_sword_you_will_ever_need_lv3", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV3);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV4 = registerSword(
            "the_last_sword_you_will_ever_need_lv4", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV4);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV5 = registerSword(
            "the_last_sword_you_will_ever_need_lv5", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV5);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV6 = registerSword(
            "the_last_sword_you_will_ever_need_lv6", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV6);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV7 = registerSword(
            "the_last_sword_you_will_ever_need_lv7", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV7);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV8 = registerSword(
            "the_last_sword_you_will_ever_need_lv8", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV8);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV9 = registerSword(
            "the_last_sword_you_will_ever_need_lv9", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV9);
    public static final RegistryObject<Item> THELASTSWORDYOUWILLEVERNEEDLV10 = registerSword(
            "the_last_sword_you_will_ever_need_lv10", ModToolMaterial.THELASTSWORDYOUWILLEVERNEEDLV10);
    public static final RegistryObject<Item> REALLYTHELASTSWORDYOUWILLEVERNEED = registerSword(
            "really_the_last_sword_you_will_ever_need", ModToolMaterial.REALLYTHELASTSWORDYOUWILLEVERNEED);

    // 注册装备
    static ArmorMaterial material = ModArmorMaterials.DRAGON_CRYSTAL;
    public static final RegistryObject<Item> DRAGON_CRYSTAL_HELMET = registerArmor(
            "dragon_crystal_helmet", material, EquipmentSlot.HEAD);
    public static final RegistryObject<Item> DRAGON_CRYSTAL_CHESTPLATE = registerArmor(
            "dragon_crystal_chestplate", material, EquipmentSlot.CHEST);
    public static final RegistryObject<Item> DRAGON_CRYSTAL_LEGGINGS = registerArmor(
            "dragon_crystal_leggings", material, EquipmentSlot.LEGS);
    public static final RegistryObject<Item> DRAGON_CRYSTAL_BOOTS = registerArmor(
            "dragon_crystal_boots", material, EquipmentSlot.FEET);

    // 注册材料
    public static final RegistryObject<Item> DRAGON_CRYSTAL = ITEMS.register(
            "dragon_crystal", () -> new Item(new Item.Properties()));

    private ModItemsForge() {}

    public static void register(@NotNull IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> registerSword(String name, ModToolMaterial material) {
        return ITEMS.register(name,
                () -> new TheLastSwordYouWillEverNeedItem(material, new Item.Properties()));
    }

    private static RegistryObject<Item> registerArmor(String name, ArmorMaterial material, EquipmentSlot slot) {
        return ITEMS.register(name, () -> new ModArmorItemForge(material, slotToType(slot), new Item.Properties()));
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