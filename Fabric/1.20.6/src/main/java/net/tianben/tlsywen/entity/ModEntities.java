package net.tianben.tlsywen.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class ModEntities {
    private static final String ENTITY_ID = "lightning_diamond";
    private static final float ENTITY_SIZE = 0.25f;
    private static final int TRACK_RANGE = 4;
    private static final int UPDATE_INTERVAL = 10;

    public static final EntityType<LDEntity> LD = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, ENTITY_ID),
            EntityType.Builder.<LDEntity>create(LDEntity::new, SpawnGroup.MISC)
                    .dimensions(ENTITY_SIZE, ENTITY_SIZE)
                    .maxTrackingRange(TRACK_RANGE)
                    .trackingTickInterval(UPDATE_INTERVAL)
                    .build());
}