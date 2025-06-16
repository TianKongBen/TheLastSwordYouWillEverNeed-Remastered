package net.tianben.tlsywen.item.group;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItemsForge;

public final class ModItemGroupsForge {
    private static final String GROUP_ID = "the_last_sword_you_will_ever_need";
    private static final String BACKGROUND = "tlsywen_items.png";

    public static final ResourceKey<CreativeModeTab> THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, GROUP_ID)
    );

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final RegistryObject<CreativeModeTab> the_last_sword_you_will_ever_need = TABS.register(GROUP_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup." + GROUP_ID).withStyle(ChatFormatting.WHITE))
                    .backgroundSuffix(BACKGROUND)
                    .icon(() -> new ItemStack(ModItemsForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get()))
                    .displayItems((params, output) -> {
                        //添加方块
                        output.accept(ModItemsForge.COMPRESSED_STAR.get());
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_BLOCK.get());
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_ORE.get());
                        //添加最终之剑
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV1.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV2.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV3.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV4.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV5.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV6.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV7.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV8.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV9.get());
                        output.accept(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV10.get());
                        output.accept(ModItemsForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get());
                        //添加龙晶套
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_HELMET.get());
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_CHESTPLATE.get());
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_LEGGINGS.get());
                        output.accept(ModItemsForge.DRAGON_CRYSTAL_BOOTS.get());
                        //添加材料
                        output.accept(ModItemsForge.DRAGON_CRYSTAL.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}