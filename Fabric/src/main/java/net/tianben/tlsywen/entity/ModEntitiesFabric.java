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

public class ModEntitiesFabric {
    public static final EntityType<LDEntity> LD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, "lightning_diamond"),
            FabricEntityTypeBuilder.create(MobCategory.MISC, LDEntity::createForRegistration)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(10)
                    .build()
    );

    public static void register() {
        PlatformBridgeHolder.BRIDGE = new FabricPlatformBridge();
    }
}