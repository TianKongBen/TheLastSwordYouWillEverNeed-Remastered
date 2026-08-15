package net.tianben.tlsywen.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class LDEntity extends ThrowableItemProjectile {

    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0F;
    private static final double SPEED_THRESHOLD = 0.001;
    private static final int MAX_STATIONARY_TICKS = 5;
    private static final int PARTICLE_COUNT = 4;

    private int stationaryTicks;

    public LDEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public LDEntity(Level level, LivingEntity owner) {
        super(ModEntities.LD.get(), owner, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == PARTICLE_EVENT) {
            spawnTrailParticles(ParticleTypes.POOF);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        if (!level().isClientSide()) {
            var target = result.getEntity();
            var owner = getOwner();

            spawnLightning(target.blockPosition());

            float damage = getDamage(owner);
            if (owner != null) {
                target.hurt(damageSources().thrown(this, owner), damage);
            }
        }
        discard();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, PARTICLE_EVENT);
            spawnLightning(result.getBlockPos());
        }
        discard();
    }

    @Override
    public void tick() {
        super.tick();

        if (isSpeedZero()) {
            if (++stationaryTicks >= MAX_STATIONARY_TICKS) {
                discard();
                return;
            }
        } else {
            stationaryTicks = 0;
        }

        if (isInWater()) {
            spawnTrailParticles(ParticleTypes.BUBBLE);
        }

        if (!isNoGravity()) {
            var v = getDeltaMovement();
            setDeltaMovement(v.x, v.y - getDefaultGravity(), v.z);
        }
    }

    private void spawnLightning(BlockPos pos) {
        if (EntityType.LIGHTNING_BOLT.create(level()) instanceof LightningBolt lightning) {
            lightning.moveTo(Vec3.atBottomCenterOf(pos));
            level().addFreshEntity(lightning);
        }
    }

    private void spawnTrailParticles(ParticleOptions particle) {
        var v = getDeltaMovement();
        var p = position().add(v);
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            level().addParticle(
                    particle,
                    p.x - v.x * 0.25,
                    p.y - v.y * 0.25,
                    p.z - v.z * 0.25,
                    v.x, v.y, v.z
            );
        }
    }

    private float getDamage(Entity owner) {
        if (owner instanceof Player player
                && player.getMainHandItem().getItem() instanceof TieredItem tieredItem) {
            return tieredItem.getTier().getAttackDamageBonus();
        }
        return BASE_DAMAGE;
    }

    private boolean isSpeedZero() {
        var v = getDeltaMovement();
        return Math.abs(v.x) <= SPEED_THRESHOLD
                && Math.abs(v.y) <= SPEED_THRESHOLD
                && Math.abs(v.z) <= SPEED_THRESHOLD;
    }

    protected double getDefaultGravity() {
        return 0.0;
    }
}