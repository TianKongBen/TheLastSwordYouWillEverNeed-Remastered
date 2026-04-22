package net.tianben.tlsywen.item.group;

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
import net.tianben.tlsywen.item.ModItems;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModItemGroups {
    private static final String GROUP_ID = "the_last_sword_you_will_ever_need";
    private static final String BACKGROUND = "tlsywen_items.png";

    public static final ResourceKey<CreativeModeTab> THE_LAST_SWORD_YOU_WILL_EVER_NEED_GROUP = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, GROUP_ID)
    );

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> the_last_sword_you_will_ever_need = TABS.register(GROUP_ID,
            () -> {
                CreativeModeTab.Builder builder = CreativeModeTab.builder()
                        .title(Component.translatable("itemgroup." + GROUP_ID))
                        .icon(() -> new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get()))
                        .displayItems((params, output) -> {
                            //添加方块
                            output.accept(ModItems.COMPRESSED_STAR.get());
                            output.accept(ModItems.DRAGON_CRYSTAL_BLOCK.get());
                            output.accept(ModItems.DRAGON_CRYSTAL_ORE.get());
                            //添加最终之剑
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get());
                            output.accept(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get());
                            output.accept(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get());
                            //添加龙晶套
                            output.accept(ModItems.DRAGON_CRYSTAL_HELMET.get());
                            output.accept(ModItems.DRAGON_CRYSTAL_CHESTPLATE.get());
                            output.accept(ModItems.DRAGON_CRYSTAL_LEGGINGS.get());
                            output.accept(ModItems.DRAGON_CRYSTAL_BOOTS.get());
                            //添加材料
                            output.accept(ModItems.DRAGON_CRYSTAL.get());
                        });
                builder.backgroundSuffix(BACKGROUND);

                return builder.build();
            }
    );

    public static void registerItemGroups(IEventBus modEventBus) {
        TheLastSwordYouWillEverNeed.LOGGER.info("注册模组物品组" + MOD_ID);
        TABS.register(modEventBus);
    }
}