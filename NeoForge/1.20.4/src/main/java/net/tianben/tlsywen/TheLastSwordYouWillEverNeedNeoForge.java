package net.tianben.tlsywen;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.tianben.tlsywen.attachment.PlayerFlightAttachmentNeoForge;
import net.tianben.tlsywen.block.ModBlocksNeoForge;
import net.tianben.tlsywen.client.render.NeoForgeRenderLD;
import net.tianben.tlsywen.entity.ModEntitiesNeoForge;
import net.tianben.tlsywen.item.ModItemsNeoForge;
import net.tianben.tlsywen.item.group.ModItemGroupsNeoForge;
import net.tianben.tlsywen.platform.NeoForgePlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import org.jetbrains.annotations.NotNull;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mod(MOD_ID)
public final class TheLastSwordYouWillEverNeedNeoForge {
    public TheLastSwordYouWillEverNeedNeoForge() {
        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

        initializePlatform();
        initializeCore();
        registerContent(modEventBus);

        modEventBus.addListener(this::addCreativeTabItems);
    }

    private void initializePlatform() {
        PlatformBridgeHolder.setBridge(new NeoForgePlatformBridge());
    }

    private void initializeCore() {
        TheLastSwordYouWillEverNeed.init();
    }

    private void registerContent(IEventBus bus) {
        ModItemGroupsNeoForge.register(bus);
        ModBlocksNeoForge.register(bus);
        ModItemsNeoForge.register(bus);
        ModEntitiesNeoForge.register(bus);
        PlayerFlightAttachmentNeoForge.register(bus);
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
                new ItemStack(ModItemsNeoForge.COMPRESSED_STAR.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.COMPRESSED_STAR.get()),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_BLOCK.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addNaturalBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.ANCIENT_DEBRIS),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_ORE.get()),
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
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                new ItemStack(ModItemsNeoForge.REALLYTHELASTSWORDYOUWILLEVERNEED.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addArmor(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_BOOTS),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_HELMET.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_HELMET.get()),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_CHESTPLATE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_CHESTPLATE.get()),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_LEGGINGS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_LEGGINGS.get()),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL_BOOTS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addIngredients(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(ModItemsNeoForge.DRAGON_CRYSTAL.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(
                    ModEntitiesNeoForge.LD.get(),
                    NeoForgeRenderLD::new
            );
        }
    }
}