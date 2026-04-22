package net.tianben.tlsywen.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModEntities {
    private static final String ENTITY_ID = "lightning_diamond";
    private static final float ENTITY_SIZE = 0.25f;
    private static final int TRACK_RANGE = 4;
    private static final int UPDATE_INTERVAL = 10;

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<EntityType<LDEntity>> LD = ENTITY_TYPES.register(ENTITY_ID,
            () -> EntityType.Builder.<LDEntity>of(LDEntity::new, MobCategory.MISC)
                    .sized(ENTITY_SIZE, ENTITY_SIZE)
                    .clientTrackingRange(TRACK_RANGE)
                    .updateInterval(UPDATE_INTERVAL)
                    .build(MOD_ID + ":" + ENTITY_ID));

    public static void registerModEntities(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}