package net.tianben.tlsywen.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.platform.NeoForgePlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import org.jetbrains.annotations.NotNull;

public final class ModEntitiesNeoForge {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<LDEntity>> LD = ENTITIES.register(
            "lightning_diamond",
            () -> EntityType.Builder.of(LDEntity::createForRegistration, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("lightning_diamond")
    );

    private ModEntitiesNeoForge() {}

    public static void register(@NotNull IEventBus bus) {
        ENTITIES.register(bus);
        PlatformBridgeHolder.setBridge(new NeoForgePlatformBridge());
    }
}