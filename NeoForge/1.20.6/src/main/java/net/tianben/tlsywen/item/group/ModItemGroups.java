package net.tianben.tlsywen.item.group;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItems;

import java.util.function.Supplier;

public class ModItemGroups {
    private static final String GROUP_ID = "the_last_sword_you_will_ever_need";
    private static final String BACKGROUND = "tlsywen_items.png";

    public static final ResourceKey<CreativeModeTab> THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, GROUP_ID)
    );

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final Supplier<CreativeModeTab> the_last_sword_you_will_ever_need = TABS.register(GROUP_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup." + GROUP_ID).withStyle(ChatFormatting.WHITE))
                    .backgroundSuffix(BACKGROUND)
                    .icon(() -> new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get()))
                    .displayItems((params, output) -> {
                        // 添加方块
                        output.accept(ModItems.COMPRESSED_STAR.get());
                        output.accept(ModItems.DRAGON_CRYSTAL_BLOCK.get());
                        output.accept(ModItems.DRAGON_CRYSTAL_ORE.get());
                        // 添加最终之剑
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9);
                        output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10);
                        output.accept(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);
                        // 添加龙晶套
                        output.accept(ModItems.DRAGON_CRYSTAL_HELMET);
                        output.accept(ModItems.DRAGON_CRYSTAL_CHESTPLATE);
                        output.accept(ModItems.DRAGON_CRYSTAL_LEGGINGS);
                        output.accept(ModItems.DRAGON_CRYSTAL_BOOTS);
                        // 添加材料
                        output.accept(ModItems.DRAGON_CRYSTAL);
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}