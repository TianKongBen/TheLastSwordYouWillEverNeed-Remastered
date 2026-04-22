package net.tianben.tlsywen;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.render.RenderLD;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public class TheLastSwordYouWillEverNeed {
    public static final String MOD_ID = "tlsywen";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheLastSwordYouWillEverNeed(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModItemGroups.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        PlayerFlightAttachment.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
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

    private void addBuildingBlocks(BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(
                new ItemStack(Items.NETHERITE_BLOCK),
                new ItemStack(ModItems.COMPRESSED_STAR.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.COMPRESSED_STAR.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_BLOCK.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addNaturalBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(
                new ItemStack(Items.ANCIENT_DEBRIS),
                new ItemStack(ModItems.DRAGON_CRYSTAL_ORE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addCombatItems(@NotNull BuildCreativeModeTabContentsEvent event) {
        addWeapons(event);
        addArmor(event);
    }

    private void addWeapons(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(
                new ItemStack(Items.NETHERITE_SWORD),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addArmor(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(
                new ItemStack(Items.NETHERITE_BOOTS),
                new ItemStack(ModItems.DRAGON_CRYSTAL_HELMET.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_HELMET.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_CHESTPLATE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_CHESTPLATE.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_LEGGINGS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.insertAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_LEGGINGS.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_BOOTS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private void addIngredients(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(ModItems.DRAGON_CRYSTAL.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = TheLastSwordYouWillEverNeed.MOD_ID, value = Dist.CLIENT)
    static class ClientModEvents {
        private static boolean hasRegistered = false;

        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            if (!hasRegistered && ModEntities.LD.isBound()) {
                event.registerEntityRenderer(
                        ModEntities.LD.get(),
                        RenderLD::new
                );
                hasRegistered = true;
            }
        }
    }
}