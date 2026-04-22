package net.tianben.tlsywen.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class LDEntity extends ThrowableItemProjectile {
    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0f;

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

    protected boolean isBurning() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == PARTICLE_EVENT) {
            Vec3 vec3 = getDeltaMovement();
            Vec3 pos = new Vec3(
                    getX() + vec3.x,
                    getY() + vec3.y,
                    getZ() + vec3.z
            );
            for (int i = 0; i < 4; ++i) {
                level().addParticle(
                        ParticleTypes.POOF,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z
                );
            }
        }
    }

    private void lightning(BlockPos blockPos) {
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level());
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf(blockPos));
            level().addFreshEntity(lightning);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        if(!level().isClientSide()){
            lightning(result.getEntity().blockPosition());
            Entity entity = result.getEntity();
            Entity owner = getOwner();

            float damage = BASE_DAMAGE;
            if (owner instanceof Player player) {
                var heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof TieredItem tieredItem) {
                    damage = tieredItem.getTier().getAttackDamageBonus();
                }

                entity.hurt(damageSources().thrown(this, player), damage);
            } else if (owner != null) {
                entity.hurt(damageSources().thrown(this, owner), damage);
            }
        }

        discard();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        if(!level().isClientSide()) {
            level().broadcastEntityEvent(this, PARTICLE_EVENT);
            lightning(result.getBlockPos());
        }

        discard();
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean bl = false;
        if (isBurning()) {
            setSecondsOnFire(1);
        }
        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            onHit(hitResult);
        }

        checkInsideBlocks();
        Vec3 vec3 = getDeltaMovement();
        setPos(
                getX() + vec3.x,
                getY() + vec3.y,
                getZ() + vec3.z
        );

        updateRotation();

        if (isInWater()) {
            Vec3 pos = new Vec3(getX(), getY(), getZ());
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.BUBBLE,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }

        if (!isNoGravity()) {
            Vec3 vec32 = getDeltaMovement();
            setDeltaMovement(vec32.x, vec32.y - getGravity(), vec32.z);
        }
    }

    protected float getGravity() {
        return 0f;
    }
}