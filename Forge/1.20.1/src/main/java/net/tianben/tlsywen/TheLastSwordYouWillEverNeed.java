package net.tianben.tlsywen;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.config.ModConfig;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.render.RenderLD;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public final class TheLastSwordYouWillEverNeed {
    public static final String MOD_ID = "tlsywen";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheLastSwordYouWillEverNeed(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ConfigManager.init();

        if (ConfigManager.isClothConfigLoaded()) {
            context.registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(
                            (mc, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get()
                    )
            );
        }

        ModItemGroups.registerItemGroups(modEventBus);
        ModBlocks.registerModBlocks(modEventBus);
        ModItems.registerModItems(modEventBus);
        ModEntities.registerModEntities(modEventBus);
        modEventBus.addListener(this::addCreativeTabItems);
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

    // 将压缩星和龙晶块添加到建筑方块
    private void addBuildingBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_BLOCK),
                new ItemStack(ModItems.COMPRESSED_STAR.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.COMPRESSED_STAR.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_BLOCK.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    // 将龙晶矿添加到自然方块
    private void addNaturalBlocks(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.ANCIENT_DEBRIS),
                new ItemStack(ModItems.DRAGON_CRYSTAL_ORE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    // 将物品添加到战斗用品
    private void addCombatItems(@NotNull BuildCreativeModeTabContentsEvent event) {
        addWeapons(event);
        addArmor(event);
    }

    // 将最终之剑添加到下界合金剑后面
    private void addWeapons(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_SWORD),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get()),
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get()),
                new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    // 将龙晶套添加到下界合金靴子后面
    private void addArmor(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_BOOTS),
                new ItemStack(ModItems.DRAGON_CRYSTAL_HELMET.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_HELMET.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_CHESTPLATE.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
        event.getEntries().putAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_CHESTPLATE.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_LEGGINGS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );event.getEntries().putAfter(
                new ItemStack(ModItems.DRAGON_CRYSTAL_LEGGINGS.get()),
                new ItemStack(ModItems.DRAGON_CRYSTAL_BOOTS.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    // 将龙晶添加到原材料
    private void addIngredients(@NotNull BuildCreativeModeTabContentsEvent event) {
        event.getEntries().putAfter(
                new ItemStack(Items.NETHERITE_INGOT),
                new ItemStack(ModItems.DRAGON_CRYSTAL.get()),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerRenderers(@NotNull EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(
                    ModEntities.LD.get(),
                    RenderLD::new
            );
        }
    }
}