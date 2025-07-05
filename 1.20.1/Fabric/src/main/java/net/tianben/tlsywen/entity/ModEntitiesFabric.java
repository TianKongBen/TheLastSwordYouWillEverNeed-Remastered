package net.tianben.tlsywen.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.platform.FabricPlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;

public final class ModEntitiesFabric {
    private static final String ENTITY_ID = "lightning_diamond";
    private static final float ENTITY_SIZE = 0.25f;
    private static final int TRACK_RANGE = 4;
    private static final int UPDATE_INTERVAL = 10;

    public static final EntityType<LDEntity> LD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, ENTITY_ID),
            FabricEntityTypeBuilder.create(MobCategory.MISC, LDEntity::createForRegistration)
                    .dimensions(EntityDimensions.fixed(ENTITY_SIZE, ENTITY_SIZE))
                    .trackRangeBlocks(TRACK_RANGE)
                    .trackedUpdateRate(UPDATE_INTERVAL)
                    .build()
    );

    private ModEntitiesFabric() {}

    public static void register() {
        PlatformBridgeHolder.setBridge(new FabricPlatformBridge());
    }
}