package net.tianben.tlsywen.item.group;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItemsNeoForge;

public final class ModItemGroupsNeoForge {
    private static final String GROUP_ID = "the_last_sword_you_will_ever_need";
    private static final String BACKGROUND = "tlsywen_items.png";

    public static final ResourceKey<CreativeModeTab> THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, GROUP_ID)
    );

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> the_last_sword_you_will_ever_need = TABS.register(GROUP_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup." + GROUP_ID).withStyle(ChatFormatting.WHITE))
                    .backgroundSuffix(BACKGROUND)
                    .icon(() -> new ItemStack((ItemLike) ModItemsNeoForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get()))
                    .displayItems((params, output) -> {
                        // 添加方块
                        output.accept(ModItemsNeoForge.COMPRESSED_STAR.get());
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_BLOCK.get());
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_ORE.get());
                        // 添加最终之剑
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV1.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV2.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV3.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV4.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV5.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV6.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV7.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV8.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV9.get());
                        output.accept((ItemLike) ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV10.get());
                        output.accept((ItemLike) ModItemsNeoForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get());
                        // 添加龙晶套
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_HELMET.get());
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_CHESTPLATE.get());
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_LEGGINGS.get());
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL_BOOTS.get());
                        // 添加材料
                        output.accept(ModItemsNeoForge.DRAGON_CRYSTAL.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}