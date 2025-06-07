package net.tianben.tlsywen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.tianben.tlsywen.attachment.PlayerFlightAttachmentFabric;
import net.tianben.tlsywen.block.ModBlocksFabric;
import net.tianben.tlsywen.detailab.helper.ConfigHelperFabric;
import net.tianben.tlsywen.detailab.helper.PlatformHelperFabric;
import net.tianben.tlsywen.entity.ModEntitiesFabric;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.ModItemsFabric;
import net.tianben.tlsywen.item.group.ModItemGroupsFabric;
import net.tianben.tlsywen.platform.FabricPlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import net.tianben.tlsywen.util.FabricResourceManager;

public class TheLastSwordYouWillEverNeedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigHelperFabric.registerClientConfig(PlatformHelperFabric::getInstance);

        PlatformBridgeHolder.BRIDGE = new FabricPlatformBridge();

        TheLastSwordYouWillEverNeed.init();

        PlayerFlightAttachmentFabric.FLIGHT_ATTACHMENT.toString();

        ModItemGroupsFabric.register();

        ModItemsFabric.registerItems();
        ModBlocksFabric.register();

        FabricResourceManager.init();

        ModEntitiesFabric.register();

        //将压缩星和龙晶块添加到建筑方块
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.NETHERITE_BLOCK, ModItems.COMPRESSED_STAR);
            entries.addAfter(ModItems.COMPRESSED_STAR, ModItems.DRAGON_CRYSTAL_BLOCK);
        });

        //将龙晶矿添加到自然方块
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.ANCIENT_DEBRIS, ModItems.DRAGON_CRYSTAL_ORE);
        });

        //将最终之剑和龙晶套添加到战斗用品
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addAfter(Items.NETHERITE_SWORD, ModItems.THELASTSWORDYOUWILLEVERNEEDLV1);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1, ModItems.THELASTSWORDYOUWILLEVERNEEDLV2);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2, ModItems.THELASTSWORDYOUWILLEVERNEEDLV3);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3, ModItems.THELASTSWORDYOUWILLEVERNEEDLV4);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4, ModItems.THELASTSWORDYOUWILLEVERNEEDLV5);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5, ModItems.THELASTSWORDYOUWILLEVERNEEDLV6);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6, ModItems.THELASTSWORDYOUWILLEVERNEEDLV7);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7, ModItems.THELASTSWORDYOUWILLEVERNEEDLV8);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8, ModItems.THELASTSWORDYOUWILLEVERNEEDLV9);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9, ModItems.THELASTSWORDYOUWILLEVERNEEDLV10);
            entries.addAfter(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10, ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);
            entries.addAfter(Items.NETHERITE_BOOTS, ModItems.DRAGON_CRYSTAL_HELMET);
            entries.addAfter(ModItems.DRAGON_CRYSTAL_HELMET, ModItems.DRAGON_CRYSTAL_CHESTPLATE);
            entries.addAfter(ModItems.DRAGON_CRYSTAL_CHESTPLATE, ModItems.DRAGON_CRYSTAL_LEGGINGS);
            entries.addAfter(ModItems.DRAGON_CRYSTAL_LEGGINGS, ModItems.DRAGON_CRYSTAL_BOOTS);
        });

        //将龙晶添加到原材料
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_INGOT, ModItems.DRAGON_CRYSTAL);
        });
    }
}
