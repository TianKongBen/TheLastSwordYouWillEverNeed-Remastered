package net.tianben.tlsywen.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;

public class ModEntities {
    public static final EntityType<LDlv1Entity> LDlv1 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv1"),
            FabricEntityTypeBuilder.<LDlv1Entity>create(SpawnGroup.MISC, LDlv1Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv2Entity> LDlv2 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv2"),
            FabricEntityTypeBuilder.<LDlv2Entity>create(SpawnGroup.MISC, LDlv2Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv3Entity> LDlv3 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv3"),
            FabricEntityTypeBuilder.<LDlv3Entity>create(SpawnGroup.MISC, LDlv3Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv4Entity> LDlv4 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv4"),
            FabricEntityTypeBuilder.<LDlv4Entity>create(SpawnGroup.MISC, LDlv4Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv5Entity> LDlv5 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv5"),
            FabricEntityTypeBuilder.<LDlv5Entity>create(SpawnGroup.MISC, LDlv5Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv6Entity> LDlv6 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv6"),
            FabricEntityTypeBuilder.<LDlv6Entity>create(SpawnGroup.MISC, LDlv6Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv7Entity> LDlv7 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv7"),
            FabricEntityTypeBuilder.<LDlv7Entity>create(SpawnGroup.MISC, LDlv7Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv8Entity> LDlv8 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv8"),
            FabricEntityTypeBuilder.<LDlv8Entity>create(SpawnGroup.MISC, LDlv8Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv9Entity> LDlv9 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv9"),
            FabricEntityTypeBuilder.<LDlv9Entity>create(SpawnGroup.MISC, LDlv9Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<LDlv10Entity> LDlv10 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "ld_lv10"),
            FabricEntityTypeBuilder.<LDlv10Entity>create(SpawnGroup.MISC, LDlv10Entity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static final EntityType<ReallyLDEntity> ReallyLD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "really_ld"),
            FabricEntityTypeBuilder.<ReallyLDEntity>create(SpawnGroup.MISC, ReallyLDEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());
}