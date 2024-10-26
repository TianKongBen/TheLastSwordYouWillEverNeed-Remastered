package net.tianben.tlsywen.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItems;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> THE_LAST_SWORD_YOU_WILL_EVER_NEED = ModItemGroups.register("the_last_sword_you_will_ever_need");

    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(id));
    }

    public static final ItemGroup the_last_sword_you_will_ever_need = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "the_last_sword_you_will_ever_need"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.the_last_sword_you_will_ever_need"))
                    .texture("tlsywen_items.png")
                    .icon(() -> new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED)).entries((displayContext, entries) -> {
                        //添加方块
                        entries.add(ModItems.COMPRESSED_STAR);
                        entries.add(ModItems.DRAGON_CRYSTAL_BLOCK);
                        entries.add(ModItems.DRAGON_CRYSTAL_ORE);

                        //添加最终之剑
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10);
                        entries.add(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);

                        //添加龙晶套
                        entries.add(ModItems.DRAGON_CRYSTAL_HELMET);
                        entries.add(ModItems.DRAGON_CRYSTAL_CHESTPLATE);
                        entries.add(ModItems.DRAGON_CRYSTAL_LEGGINGS);
                        entries.add(ModItems.DRAGON_CRYSTAL_BOOTS);

                        //添加材料
                        entries.add(ModItems.DRAGON_CRYSTAL);
                    }).build());

    public static void registerItemGroups() {
        TheLastSwordYouWillEverNeed.LOGGER .info("Registering Item Groups for " +TheLastSwordYouWillEverNeed.MOD_ID);
    }
}
