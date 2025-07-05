package net.tianben.tlsywen;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tianben.tlsywen.block.ModBlocksForge;
import net.tianben.tlsywen.client.render.ForgeRenderLD;
import net.tianben.tlsywen.entity.ModEntitiesForge;
import net.tianben.tlsywen.item.ModItemsForge;
import net.tianben.tlsywen.item.group.ModItemGroupsForge;
import net.tianben.tlsywen.platform.ForgePlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import org.jetbrains.annotations.NotNull;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public final class TheLastSwordYouWillEverNeedForge {

    public TheLastSwordYouWillEverNeedForge() {
        initializePlatform();
        initializeCore();
        registerContent();
    }

    private void initializePlatform() {
        PlatformBridgeHolder.setBridge(new ForgePlatformBridge());
    }

    private void initializeCore() {
        TheLastSwordYouWillEverNeed.init();
    }

    private void registerContent() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItemGroupsForge.register(bus);
        ModBlocksForge.register(bus);
        ModItemsForge.register(bus);
        ModEntitiesForge.register(bus);
        bus.addListener(this::addCreativeTabItems);
    }

    private void addCreativeTabItems(@NotNull BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            addBuildingBlocks(event);
        } else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            addNaturalBlocks(event);
        } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            addCombatItems(event);
        } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            addIngredients(event);
        }
    }

    private void addBuildingBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
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

    private void addNaturalBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.ANCIENT_DEBRIS),
                new ItemStack(ModItemsForge.DRAGON_CRYSTAL_ORE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addCombatItems(@NotNull BuildCreativeModeTabContentsEvent event) {
        addWeapons(event);
        addArmor(event);
    }

    private void addWeapons(@NotNull BuildCreativeModeTabContentsEvent event) {
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
    }

    private void addArmor(@NotNull BuildCreativeModeTabContentsEvent event) {
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

    private void addIngredients(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(ModItemsForge.DRAGON_CRYSTAL.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    @Mod.EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerRenderers(@NotNull EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(
                    ModEntitiesForge.LD.get(),
                    ForgeRenderLD::new
            );
        }
    }
}