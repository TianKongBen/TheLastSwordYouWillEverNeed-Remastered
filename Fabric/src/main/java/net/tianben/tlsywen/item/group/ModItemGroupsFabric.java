package net.tianben.tlsywen.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItems;

public class ModItemGroupsFabric {
    public static final ResourceKey<CreativeModeTab> THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(),
        new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, "the_last_sword_you_will_ever_need")
    );

    public static final CreativeModeTab the_last_sword_you_will_ever_need = FabricItemGroup.builder()
        .title(Component.translatable("itemgroup.the_last_sword_you_will_ever_need").withStyle(ChatFormatting.WHITE))
        .backgroundSuffix("tlsywen_items.png")
        .icon(() -> new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED))
        .displayItems((context, entries) -> {
            //添加方块
            entries.accept(ModItems.COMPRESSED_STAR);
            entries.accept(ModItems.DRAGON_CRYSTAL_BLOCK);
            entries.accept(ModItems.DRAGON_CRYSTAL_ORE);
            //添加最终之剑
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9);
            entries.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10);
            entries.accept(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);
            //添加龙晶套
            entries.accept(ModItems.DRAGON_CRYSTAL_HELMET);
            entries.accept(ModItems.DRAGON_CRYSTAL_CHESTPLATE);
            entries.accept(ModItems.DRAGON_CRYSTAL_LEGGINGS);
            entries.accept(ModItems.DRAGON_CRYSTAL_BOOTS);
            //添加材料
            entries.accept(ModItems.DRAGON_CRYSTAL);
        })
        .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP, the_last_sword_you_will_ever_need);
    }
}