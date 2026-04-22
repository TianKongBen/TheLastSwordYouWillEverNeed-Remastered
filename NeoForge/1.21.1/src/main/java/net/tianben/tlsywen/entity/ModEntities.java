package net.tianben.tlsywen.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModEntities {
    private static final String ENTITY_ID = "lightning_diamond";
    private static final float ENTITY_SIZE = 0.25f;
    private static final int TRACK_RANGE = 4;
    private static final int UPDATE_INTERVAL = 10;

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<LDEntity>> LD = ENTITY_TYPES.register(
            ENTITY_ID, () -> EntityType.Builder.<LDEntity>of(LDEntity::new, MobCategory.MISC)
                    .sized(ENTITY_SIZE, ENTITY_SIZE)
                    .clientTrackingRange(TRACK_RANGE)
                    .updateInterval(UPDATE_INTERVAL)
                    .build(ENTITY_ID));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}