package net.tianben.tlsywen.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tianben.tlsywen.item.ModItems;

public class LDEntity extends ThrownItemEntity {

    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0f;
    private static final double SPEED_THRESHOLD = 0.001;
    private static final int MAX_STATIONARY_TICKS = 5;

    private int stationaryTicks;

    public LDEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LDEntity(World world, LivingEntity owner) {
        super(ModEntities.LD, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THELASTSWORDYOUWILLEVERNEEDLV1;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void handleStatus(byte status) {
        if (status != PARTICLE_EVENT) return;

        Vec3d velocity = getVelocity();
        Vec3d pos = getPos().add(velocity);

        for (int i = 0; i < 4; i++) {
            getWorld().addParticle(
                    ParticleTypes.POOF,
                    pos.x - velocity.x * 0.25,
                    pos.y - velocity.y * 0.25,
                    pos.z - velocity.z * 0.25,
                    velocity.x, velocity.y, velocity.z
            );
        }
    }

    private void spawnLightning(BlockPos pos) {
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(getWorld());
        if (lightning != null) {
            lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            getWorld().spawnEntity(lightning);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!getWorld().isClient()) {
            Entity target = entityHitResult.getEntity();
            Entity owner = getOwner();

            spawnLightning(target.getBlockPos());

            float damage = getDamage(owner);
            if (owner instanceof PlayerEntity player) {
                target.damage(getDamageSources().thrown(this, player), damage);
            } else if (owner != null) {
                target.damage(getDamageSources().thrown(this, owner), damage);
            }
        }
        discard();
    }

    private float getDamage(Entity owner) {
        if (owner instanceof PlayerEntity player) {
            var heldItem = player.getMainHandStack();
            if (heldItem.getItem() instanceof ToolItem toolItem) {
                return toolItem.getMaterial().getAttackDamage();
            }
        }
        return BASE_DAMAGE;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!getWorld().isClient()) {
            getWorld().sendEntityStatus(this, PARTICLE_EVENT);
            spawnLightning(blockHitResult.getBlockPos());
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

        if (isTouchingWater()) {
            Vec3d velocity = getVelocity();
            Vec3d pos = getPos();
            for (int i = 0; i < 4; i++) {
                getWorld().addParticle(
                        ParticleTypes.BUBBLE,
                        pos.x - velocity.x * 0.25,
                        pos.y - velocity.y * 0.25,
                        pos.z - velocity.z * 0.25,
                        velocity.x, velocity.y, velocity.z
                );
            }
        }

        if (!hasNoGravity()) {
            Vec3d velocity = getVelocity();
            setVelocity(velocity.x, velocity.y - getGravity(), velocity.z);
        }
    }

    private boolean isSpeedZero() {
        Vec3d movement = getVelocity();
        return Math.abs(movement.x) <= SPEED_THRESHOLD &&
                Math.abs(movement.y) <= SPEED_THRESHOLD &&
                Math.abs(movement.z) <= SPEED_THRESHOLD;
    }

    @Override
    protected float getGravity() {
        return 0f;
    }
}
