package net.tianben.tlsywen.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModEntities {
    private static final String ENTITY_ID = "lightning_diamond";

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<EntityType<LDEntity>> LD = ENTITY_TYPES.register(ENTITY_ID,
            () -> EntityType.Builder.<LDEntity>of(LDEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(MOD_ID + ":" + ENTITY_ID));

    private ModEntities() {}

    public static void registerModEntities(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}