package net.tianben.tlsywen.entity;

import net.minecraft.core.BlockPos;
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
    private static final float BASE_DAMAGE = 1600.0f;
    private static final double SPEED_THRESHOLD = 0.001;
    private static final int MAX_STATIONARY_TICKS = 5;

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
        if (id != PARTICLE_EVENT) return;

        Vec3 velocity = getDeltaMovement();
        Vec3 pos = position().add(velocity);

        for (int i = 0; i < 4; i++) {
            level().addParticle(
                    ParticleTypes.POOF,
                    pos.x - velocity.x * 0.25,
                    pos.y - velocity.y * 0.25,
                    pos.z - velocity.z * 0.25,
                    velocity.x, velocity.y, velocity.z
            );
        }
    }

    private void spawnLightning(BlockPos pos) {
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level());
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf(pos));
            level().addFreshEntity(lightning);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        if (!level().isClientSide()) {
            Entity target = result.getEntity();
            Entity owner = getOwner();

            spawnLightning(target.blockPosition());

            float damage = getDamage(owner);
            if (owner instanceof Player player) {
                target.hurt(damageSources().thrown(this, player), damage);
            } else if (owner != null) {
                target.hurt(damageSources().thrown(this, owner), damage);
            }
        }
        discard();
    }

    private float getDamage(Entity owner) {
        if (owner instanceof Player player) {
            var heldItem = player.getMainHandItem();
            if (heldItem.getItem() instanceof TieredItem tieredItem) {
                return tieredItem.getTier().getAttackDamageBonus();
            }
        }
        return BASE_DAMAGE;
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
            Vec3 velocity = getDeltaMovement();
            Vec3 pos = position();
            for (int i = 0; i < 4; i++) {
                level().addParticle(
                        ParticleTypes.BUBBLE,
                        pos.x - velocity.x * 0.25,
                        pos.y - velocity.y * 0.25,
                        pos.z - velocity.z * 0.25,
                        velocity.x, velocity.y, velocity.z
                );
            }
        }

        if (!isNoGravity()) {
            Vec3 velocity = getDeltaMovement();
            setDeltaMovement(velocity.x, velocity.y - getGravity(), velocity.z);
        }
    }

    private boolean isSpeedZero() {
        Vec3 movement = getDeltaMovement();
        return Math.abs(movement.x) <= SPEED_THRESHOLD &&
                Math.abs(movement.y) <= SPEED_THRESHOLD &&
                Math.abs(movement.z) <= SPEED_THRESHOLD;
    }

    @Override
    protected float getGravity() {
        return 0f;
    }
}