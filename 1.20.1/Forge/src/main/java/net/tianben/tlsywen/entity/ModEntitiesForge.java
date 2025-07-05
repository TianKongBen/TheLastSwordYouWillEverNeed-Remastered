package net.tianben.tlsywen.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.platform.ForgePlatformBridge;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;
import org.jetbrains.annotations.NotNull;

public final class ModEntitiesForge {
    private static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheLastSwordYouWillEverNeed.MOD_ID);

    public static final RegistryObject<EntityType<LDEntity>> LD = ENTITIES.register(
            "lightning_diamond",
            () -> EntityType.Builder.of(LDEntity::createForRegistration, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("lightning_diamond")
    );

    private ModEntitiesForge() {}

    public static void register(@NotNull IEventBus bus) {
        ENTITIES.register(bus);
        PlatformBridgeHolder.setBridge(new ForgePlatformBridge());
    }
}