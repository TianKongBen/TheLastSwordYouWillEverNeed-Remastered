package net.tianben.tlsywen.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class ModEntities {

    public static final EntityType<LDEntity> LD = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "lightning_diamond"),
            FabricEntityTypeBuilder.<LDEntity>create(SpawnGroup.MISC, LDEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(10)
                    .build());

    private ModEntities() {}
}
