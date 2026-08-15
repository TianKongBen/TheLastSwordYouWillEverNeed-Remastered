package net.tianben.tlsywen;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.config.ModConfig;
import net.tianben.tlsywen.entity.ModEntities;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.render.RenderLD;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Supplier;

@Mod(TheLastSwordYouWillEverNeed.MOD_ID)
public class TheLastSwordYouWillEverNeed {

    public static final String MOD_ID = "tlsywen";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final CreativeModeTab.TabVisibility VISIBILITY =
            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    private static final List<Supplier<? extends Item>> SWORD_TIERS = List.of(
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV1,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV2,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV3,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV4,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV5,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV6,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV7,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV8,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV9,
            ModItems.THELASTSWORDYOUWILLEVERNEEDLV10,
            ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED
    );

    private static final List<Supplier<? extends Item>> DRAGON_CRYSTAL_ARMOR = List.of(
            ModItems.DRAGON_CRYSTAL_HELMET,
            ModItems.DRAGON_CRYSTAL_CHESTPLATE,
            ModItems.DRAGON_CRYSTAL_LEGGINGS,
            ModItems.DRAGON_CRYSTAL_BOOTS
    );

    public TheLastSwordYouWillEverNeed(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ConfigManager.init();

        if (ConfigManager.isClothConfigLoaded()) {
            modContainer.registerExtensionPoint(
                    IConfigScreenFactory.class,
                    (container, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get()
            );
        }

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
        switch (event.getTabKey().location().getPath()) {
            case "building_blocks" -> addBuildingBlocks(event);
            case "natural_blocks" -> addNaturalBlocks(event);
            case "combat" -> addCombatItems(event);
            case "ingredients" -> addIngredients(event);
        }
    }

    private void addBuildingBlocks(BuildCreativeModeTabContentsEvent event) {
        insertChain(event, Items.NETHERITE_BLOCK, List.of(
                ModItems.COMPRESSED_STAR,
                ModItems.DRAGON_CRYSTAL_BLOCK
        ));
    }

    private void addNaturalBlocks(BuildCreativeModeTabContentsEvent event) {
        insertChain(event, Items.ANCIENT_DEBRIS, List.of(ModItems.DRAGON_CRYSTAL_ORE));
    }

    private void addCombatItems(BuildCreativeModeTabContentsEvent event) {
        insertChain(event, Items.NETHERITE_SWORD, SWORD_TIERS);
        insertChain(event, Items.NETHERITE_BOOTS, DRAGON_CRYSTAL_ARMOR);
    }

    private void addIngredients(BuildCreativeModeTabContentsEvent event) {
        insertChain(event, Items.NETHERITE_INGOT, List.of(ModItems.DRAGON_CRYSTAL));
    }

    private static void insertChain(BuildCreativeModeTabContentsEvent event,
                                    Item anchor,
                                    List<Supplier<? extends Item>> items) {
        Item previous = anchor;
        for (var supplier : items) {
            Item current = supplier.get();
            event.insertAfter(
                    new ItemStack(previous),
                    new ItemStack(current),
                    VISIBILITY
            );
            previous = current;
        }
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    static class ClientModEvents {

        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.LD.get(), RenderLD::new);
        }
    }
}