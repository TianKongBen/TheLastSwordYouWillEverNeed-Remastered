package net.tianben.tlsywen;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tianben.tlsywen.block.ModBlocksForge;
import net.tianben.tlsywen.entity.ModEntitiesForge;
import net.tianben.tlsywen.item.group.ModItemGroupsForge;
import net.tianben.tlsywen.platform.ForgePlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import net.tianben.tlsywen.item.ModItemsForge;
import net.tianben.tlsywen.network.NetworkHandlerForge;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public class TheLastSwordYouWillEverNeedForge {

    public TheLastSwordYouWillEverNeedForge() {
        PlatformBridgeHolder.BRIDGE = new ForgePlatformBridge();

        TheLastSwordYouWillEverNeed.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItemGroupsForge.register(bus);

        ModBlocksForge.register(bus);

        ModItemsForge.register(bus);

        ModEntitiesForge.register(bus);

        NetworkHandlerForge.register();

        bus.addListener(this::addCreative);
    }

    @Mod.EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModSetup {
        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        //将压缩星和龙晶块添加到建筑方块
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.getEntries().putAfter(
                    new ItemStack(Items.NETHERITE_BLOCK),
                    new ItemStack(ModItemsForge.COMPRESSED_STAR.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.COMPRESSED_STAR.get()),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_BLOCK.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }

        //将龙晶矿添加到自然方块
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.getEntries().putAfter(
                    new ItemStack(Items.ANCIENT_DEBRIS),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_ORE.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }

        //将最终之剑和龙晶套添加到战斗用品
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(
                    new ItemStack(Items.NETHERITE_SWORD),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                    new ItemStack(ModItemsForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(Items.NETHERITE_BOOTS),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_HELMET.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_HELMET.get()),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_CHESTPLATE.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
            event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_CHESTPLATE.get()),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_LEGGINGS.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );event.getEntries().putAfter(
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_LEGGINGS.get()),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL_BOOTS.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }

        //将龙晶添加到原材料
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(
                    new ItemStack(Items.NETHERITE_INGOT),
                    new ItemStack(ModItemsForge.DRAGON_CRYSTAL.get()),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }

    @Mod.EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class TheLastSwordYouWillEverNeedForgeClient {
        public TheLastSwordYouWillEverNeedForgeClient() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        }

        private void clientSetup(final FMLClientSetupEvent event) {
        }
    }
}