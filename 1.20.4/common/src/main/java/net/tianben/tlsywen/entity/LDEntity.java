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
import net.tianben.tlsywen.platform.PlatformBridgeHolder;

public class LDEntity extends ThrowableItemProjectile {
    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0f;

    public LDEntity(EntityType<? extends LDEntity> type, Level level) {
        super(type, level);
    }

    public static LDEntity createForRegistration(EntityType<?> type, Level level) {
        return new LDEntity((EntityType<? extends LDEntity>) type, level);
    }

    public LDEntity(Level level, LivingEntity owner) {
        super(PlatformBridgeHolder.getBridge().getLDEntityType(), level);
        setOwner(owner);
        setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THELASTSWORDYOUWILLEVERNEEDLV1;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    protected boolean isBurning() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == PARTICLE_EVENT) {
            var vec3 = this.getDeltaMovement();
            var pos = new Vec3(
                    this.getX() + vec3.x,
                    this.getY() + vec3.y,
                    this.getZ() + vec3.z
            );
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.POOF,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }
    }

    private void lightning(BlockPos blockPos) {
        var lightning = EntityType.LIGHTNING_BOLT.create(level());
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf(blockPos));
            level().addFreshEntity(lightning);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!level().isClientSide()) {
            lightning(entityHitResult.getEntity().blockPosition());
            var entity = entityHitResult.getEntity();
            var owner = getOwner();

            float damage = BASE_DAMAGE;
            if (owner instanceof Player player) {
                var heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof TieredItem tieredItem) {
                    damage = tieredItem.getTier().getAttackDamageBonus();
                }
                entity.hurt(damageSources().thrown(this, player), damage);
                doEnchantDamageEffects(player, entity);
            } else if (owner != null) {
                entity.hurt(damageSources().thrown(this, owner), damage);
            }
        }
        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, PARTICLE_EVENT);
            lightning(blockHitResult.getBlockPos());
        }
        discard();
    }

    @Override
    public void tick() {
        super.tick();
        var hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (isBurning()) {
            setSecondsOnFire(1);
        }
        if (hitResult.getType() != HitResult.Type.MISS) {
            onHit(hitResult);
        }

        checkInsideBlocks();
        var vec3 = getDeltaMovement();
        setPos(
                getX() + vec3.x,
                getY() + vec3.y,
                getZ() + vec3.z
        );

        updateRotation();

        if (isInWater()) {
            var pos = new Vec3(getX(), getY(), getZ());
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.BUBBLE,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }

        if (!isNoGravity()) {
            var movement = getDeltaMovement();
            setDeltaMovement(movement.x, movement.y - getGravity(), movement.z);
        }
    }

    @Override
    protected float getGravity() {
        return 0f;
    }
}